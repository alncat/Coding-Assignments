
class ChrisBST<T extends Comparable<T>> {
	IBSTNode<T> root = new EmptyNode<T>();

	// insert a new data item into the tree
	public void insert(double insertThis, T insertMe) {
		root = root.insert(insertThis, insertMe);
	}

	// see if a data item is there
	public boolean isThere(T findMe) {
		return root.isThere(findMe);
	}

	// check if the tree is balanced
	public boolean isBalanced() {
		return root.isBalanced();
	}

	public void nodeRemove() {// remove nodes from tree
		root = root.nodeRemove();
		return;
	}

	public void rotateRight() {
		root = root.rotateRight();
		return;
	}

	public void rotateLeft() {
		root = root.rotateLeft();
		return;
	}

	public int leftHeight() {
		return root.leftHeight();
	}

	public int rightHeight() {
		return root.rightHeight();
	}

	public void leftRight() {
		root = root.leftRight();
		return;
	}

	public void rightLeft() {
		root = root.rightLeft();
		return;
	}

	public boolean checkLeftRight() {
		return root.checkLeftRight();
	}

	public boolean checkRightLeft() {
		return root.checkRightLeft();
	}

	// compute the height of the tree
	public int height() {
		return root.height();
	}

	// print the tree's contents in order
	public String inOrderPrint() {
		return root.inOrderPrint();
	}

	// print the tree's contents in reverse order
	public String postOrderPrint() {
		return root.postOrderPrint();
	}

}
