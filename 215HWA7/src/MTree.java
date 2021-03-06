import java.util.*;

class MTree<Key extends IPointInMetricSpace<Key>, MyData> implements IMTree<Key, MyData> {

	private int maxInterNode, maxLeafNode;
	private ArrayList<DataWrapper<Key, MyData>> temp, temp2;// helps the find nodes in distance
	private ArrayList<DataWrapper<Key, MyData>> temp1;// helps the find closet k



	private ArrayList<SubInternalNode> realMTree, searchNodesHere;
	private SubInternalNode subInternalNode, testSubInternalNode, keepSubInternalNode;
	private SubLeafNode subLeafNode, placeHolder;
	private ArrayList<SubLeafNode> insertSubLeafNode;
	private DataWrapper<Key, MyData> pair, holdPair;
	private Key testKey;
	private ArrayList<DataWrapper<Key, MyData>> movePairs;
	private AVLTopKMachine cut;

	public MTree(int entriesInterNode, int entriesLeafNode) {

		maxInterNode = entriesInterNode;// max size of the internal nodes
		maxLeafNode = entriesLeafNode;// max size of the leaf nodes

		// Create a new M Tree
		realMTree = new ArrayList<SubInternalNode>();
	}

	// insert a new key/data pair into the map
	public void insert(Key keyToAdd, MyData dataToAdd) {

		// Step 1: Take key-data pair
		pair = new DataWrapper<Key, MyData>(keyToAdd, dataToAdd);// everytime an insertion is
																	// called, this should create a
																	// new key data pair to insert
																	// into the tree

		// Step 1.5 If mTree is empty create new internal node with Key and pointer

		if (realMTree.size() <= 0) {// takes care of the base case where there are no nodes

			// create a new leaf node
			subLeafNode = new SubLeafNode(maxLeafNode);
			// insert the key data pair into the leaf node
			subLeafNode.insertLeaf(pair);
			// create a new internal node and add the leaf node plus key for indicating distance
			subInternalNode = new SubInternalNode(keyToAdd, dataToAdd, subLeafNode);
			// add the internal node to the M Tree
			realMTree.add(subInternalNode);
			return;
		} else {// Step 2: For all the keys greater than the insert key, find key with smallest
				// distance

			// create a radius distance that is far away
			double lowDistance = Double.POSITIVE_INFINITY;
			// find the sphere with the lowest distance
			for (int i = 0; i < realMTree.size(); i++) {
				// loop through all the spheres available
				testSubInternalNode = realMTree.get(i);
				// find an internal node that has the lowest distance
				if (testSubInternalNode.getKey().getDistance(keyToAdd) < lowDistance) {
					// update the sphere distance
					lowDistance = testSubInternalNode.getKey().getDistance(keyToAdd);
					// keep up with this internal node
					keepSubInternalNode = realMTree.get(i);
				}// end of if statement
			}// end of for loop
				// internal node with the lowest distance
			insertSubLeafNode = keepSubInternalNode.getSubLeafNode();
			// place the key data pair into the node
			insertSubLeafNode.get(0).insertLeaf(pair);

			// if the leaf node is full, split it
			if (insertSubLeafNode.get(0).leafSize() > maxLeafNode) {
				// create a new data pair holder for the node
				movePairs = new ArrayList<DataWrapper<Key, MyData>>();
				// remove half of the nodes from the leaf node that is full and place them into the
				// holder
				for (int i = insertSubLeafNode.get(0).leafSize() - 1; i > (int) Math
						.floor(maxLeafNode / 2); i--) {
					movePairs.add(insertSubLeafNode.get(0).removeLeaf(i));
				}
				// create a new leaf node
				subLeafNode = new SubLeafNode(maxLeafNode);
				// move all the nodes in the holder to the new leaf node
				for (int i = 0; i < movePairs.size(); i++) {
					subLeafNode.insertLeaf(movePairs.get(i));
				}
				// add the leaf node to the internal node
				subInternalNode = new SubInternalNode(keyToAdd, dataToAdd, subLeafNode);
				// add the updated internal node to the tree
				realMTree.add(subInternalNode);
			}
		}// end for loop
		return;
	}

	// find all of the key/data pairs in the map that fall within a
	// particular distance of query point if no results are found, then
	// an empty array is returned (NOT a null!)
	public ArrayList<DataWrapper<Key, MyData>> find(Key query, double distance) {
		temp = new ArrayList<DataWrapper<Key, MyData>>();


		searchNodesHere = new ArrayList<SubInternalNode>();

		// find the spheres within the specificed distance
		for (int i = 0; i < realMTree.size(); i++) {

			// loop through all the spheres available
			testSubInternalNode = realMTree.get(i);

			// keep up with this internal node
			searchNodesHere.add(realMTree.get(i));

		}// end of for loop

		for (int i = 0; i < searchNodesHere.size(); i++) {

			// internal node with the lowest distance
			keepSubInternalNode = searchNodesHere.get(i);

			// getSubLeafNode is returning as an arrayList of SubLeafNode, SubLeafNode is an
			// arrayList of pairs
			insertSubLeafNode = keepSubInternalNode.getSubLeafNode();//

			// if input key is in range, search for neighboring nodes there
			if (insertSubLeafNode.get(0).computeRadius()
					+ searchNodesHere.get(i).getKey().getDistance(query) >= distance) {
				// place the key data pair into the node
				placeHolder = insertSubLeafNode.get(0);
				// look through all the pairs
				for (int j = 0; j < placeHolder.getKeyPairs().size(); j++) {
					holdPair = (DataWrapper<Key, MyData>) placeHolder.getKeyPairs().get(j);
					// if key falls within distance, add it to the return list
					if (holdPair.getKey().getDistance(query) <= distance) {
						temp.add(holdPair);
					}
				}
			}


		}
		return temp;
	}


	// find the k closest key/data pairs in the map to a particular
	// query point
	//
	// if the number of points in the map is less than k, then the
	// returned list will have less than k pairs in it
	//
	// if the number of points is zero, then an empty array is returned
	// (NOT a null!)
	public ArrayList<DataWrapper<Key, MyData>> findKClosest(Key query, int k) {
		temp1 = new ArrayList<DataWrapper<Key, MyData>>();
		temp2 = new ArrayList<DataWrapper<Key, MyData>>();

		int fill = 0;
		// initial starting distance to find nodes
		double dist = 5.0;
		// loop until the k value is met
		while (fill <= k) {
			temp2 = find(query, dist);
			fill = temp2.size();
			dist += dist;
		}
		// create an AVL tree of size k
		cut = new AVLTopKMachine(k);
		// all the keys found in the loop, add them to the AVL tree
		for (int i = 0; i < temp2.size(); i++) {
			cut.insert(temp2.get(i).getKey().getDistance(query), temp2.get(i));
		}

		// tree return the top K
		return cut.getTopK();
	}

	// returns the number of nodes that exist on a path from root to leaf in the tree...
	// whtever the details of the implementation are, a tree with a single leaf node should return
	// 1, and a tree
	// with more than one lead node should return at least two (since it must have at least one
	// internal node).
	public int depth() {

		return realMTree.size();
	}


}
