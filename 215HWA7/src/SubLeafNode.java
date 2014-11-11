import java.util.*;

class SubLeafNode<Key extends IPointInMetricSpace<Key>, MyData> {
	private int nodeSize;
	private ArrayList<DataWrapper<Key, MyData>> mapTree;
	// key holders to comapre when finding the max radius of a sphere
	private Key compareOne, compareTwo;

	public SubLeafNode(int nodeLimit) {
		nodeSize = nodeLimit;
		mapTree = new ArrayList<DataWrapper<Key, MyData>>();
	}

	// inserts a key data pair into the leaf node
	public void insertLeaf(DataWrapper<Key, MyData> leaf) {// Inserts a leafnode into the arraylist
		mapTree.add(leaf);
	}

	// gets a key data pair out of the leaf
	public ArrayList<DataWrapper<Key, MyData>> getKeyPairs() {
		ArrayList<DataWrapper<Key, MyData>> holdKeyLeafs =
				new ArrayList<DataWrapper<Key, MyData>>();
		for (int i = 0; i < mapTree.size(); i++) {
			holdKeyLeafs.add(mapTree.get(i));
		}
		return holdKeyLeafs;
	}

	// prints the
	public void printKeys() {
		for (int i = 0; i < mapTree.size(); i++) {
			System.out.println("Key and Leaf Combination: " + mapTree.get(i).getKey());
		}
		System.out.println("_______________________Space it out_____________________");
	}

	// finds the radius for a sphere
	public double computeRadius() {
		// small radius that a sphere can have
		double radius = 0.0;
		// do a nested for loop to compare all the keys to one another to get the max radius
		for (int i = 0; i < mapTree.size(); i++) {
			compareOne = mapTree.get(i).getKey();
			// go to the other keys for comparison
			for (int j = 0; j < mapTree.size(); j++) {
				compareTwo = mapTree.get(j).getKey();
				if (compareTwo.getDistance(compareOne) > radius) {
					// update the radius
					radius = compareTwo.getDistance(compareOne);
				}
			}
		}
		return radius;
	}

	// tells how many leaf nodes are present
	public int leafSize() {
		return mapTree.size();
	}

	// gets a specific leaf node from the tree
	public Key getLeaf(int index) {
		return mapTree.get(index).getKey();
	}

	// removes a leaf from the leaf node
	public DataWrapper<Key, MyData> removeLeaf(int index) {
		return mapTree.remove(index);
	}
}
