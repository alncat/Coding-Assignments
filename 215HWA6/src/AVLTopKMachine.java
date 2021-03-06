import java.util.*;

class AVLTopKMachine<T extends Comparable<T>> implements ITopKMachine<T> {

	private double theCutoff;
	private ArrayList<T> topK, kIndex, sortedTopK;
	private ArrayList<Double> doubleTopK;
	private int kItems, heightleft, heightright;
	// create a BST
	private ChrisBST<T> myTree;// = new ChrisBST<T>();

	public AVLTopKMachine(int k) {
		kItems = k;// the value of k, the number of items with the lowest score
		myTree = new ChrisBST<T>();
		topK = new ArrayList<T>();// arrayList will hold the top K scores
		doubleTopK = new ArrayList<Double>();
		sortedTopK = new ArrayList<T>();
	}


	// insert a new value into the machine. If its score is greater than the current
	// cutoff, it is ignored. If its score is smaller than the current cutoff, the
	// insertion will evict the value with the worst score.
	public void insert(double score, T value) {
		//System.out.println(size());
		if (size() == kItems) {// when k limit is met, remove nodes if score less than highest

			if (score >= getCurrentCutoff()) {// score is greater than the current cutoff, ignore
												// it.
				// System.out.println("Score did not meet Cutoff: " + score);
				return;// ignore it, no changes should be made to the tree
			}

			if (size() == kItems && score < getCurrentCutoff()) {// only remove when items in the
																	// tree go over capacity
				//System.out.println("BUH" + size());
				double removeDouble = getCurrentCutoff();// find the higest value that needs to be
															// removed since there are more than k
															// items
				// remove the higest value from all topK's and the AVL tree

				int removeThisHere = doubleTopK.indexOf(removeDouble);
				topK.remove(removeThisHere);
				doubleTopK.remove(removeDouble);
				myTree.nodeRemove();
				//System.out.println(size() + "AGAIN");
				// add the new score/value to all topK's and the AVL tree
				topK.add(value);
				doubleTopK.add(score);
				myTree.insert(score, value);

				if (myTree.leftHeight() - myTree.rightHeight() <= -2) {// if right side longer than
																		// left rotate right
					if (myTree.checkRightLeft() == true) {
						//System.out.println("Did a right left rotation in loop.");
						myTree.rightLeft();
					} else {
						//System.out.println("Did a right left rotation in full tree in loop.");
						myTree.rotateRight();
					}
				}
				if (myTree.leftHeight() - myTree.rightHeight() >= 2) {// if left side longer than
																		// right rotate left
					if (myTree.checkLeftRight() == true) {
						//System.out.println("Did a left right rotation in full tree in loop.");
						myTree.leftRight();
					} else {
						//System.out.println("Did a left rotation only in full tree in loop.");
						myTree.rotateLeft();
					}
				}

				//System.out.println("Updated AVL tree: " + myTree.inOrderPrint());
				// System.out.println("The top K list: " + doubleTopK);
				return;
			}
		}
		// adds all the correspending values
		topK.add(value);
		doubleTopK.add(score);
		myTree.insert(score, value);

		if (myTree.leftHeight() - myTree.rightHeight() <= -2) {// if right side longer than left
																// rotate right
			if (myTree.checkRightLeft() == true) {

				myTree.rightLeft();// performs a right left rotation on tree
			} else {

				myTree.rotateRight();// performs a right rotation on tree
			}
		}

		if (myTree.leftHeight() - myTree.rightHeight() >= 2) {// if left side longer than right
																// rotate left
			if (myTree.checkLeftRight() == true) {

				myTree.leftRight();// performs a left right rotation on tree
			} else {
				// System.out.println("Did a left rotation only outside of full tree.4");
				myTree.rotateLeft();// performs a left rotation on tree
			}
		}

		//System.out.println("Filling AVL tree: " + myTree.inOrderPrint());
	}



	// get the current top K in an array list
	public ArrayList<T> getTopK() {
		ArrayList<T> temp = new ArrayList(topK);
		Collections.sort(temp);// sorts the contents in the correct order
		return temp;
	}

	// let the caller know the current cutoff value that will get one into the top K list
	public double getCurrentCutoff() {

		double highest = Double.NEGATIVE_INFINITY;// lowest possible value for comparison
		for (int i = 0; i < topK.size(); i++) {// go through all the elements and compare
			Double numComp = doubleTopK.get(i);// set the ith element here
			if (numComp > highest) {// if new higher element, replace older higest
				highest = numComp;
			}
		}

		return highest;
	}

	// produces a string representation of the AVL tree
	public String toString() {
		return myTree.inOrderPrint();
	}

	public int checkBalance() {// checks to see if the left and right are equal
		return myTree.height();
	}

	public boolean membership(T value) {// check to see if there is node in the tree.
		if (myTree.isThere(value) == true) {
			return true;
		} else {
			return false;
		}
	}

	public int size() {// finds the number of nodes in the tree
		int treeSize = topK.size();
		return treeSize;
	}


	@Override
	public boolean memebership(T value) {
		// TODO Auto-generated method stub
		return false;
	}

}
