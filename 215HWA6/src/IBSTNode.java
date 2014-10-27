// this is the basic node type that we are using to construct our tree
interface IBSTNode<T extends Comparable<T>> {
	public boolean isThere(T findMe);

	public IBSTNode<T> insert(double insertThis, T insertMe);

	public boolean isBalanced();

	public int height();

	public String inOrderPrint();

	public String postOrderPrint();

	public IBSTNode<T> nodeRemove();

	public IBSTNode<T> rotateRight();

	public IBSTNode<T> rotateLeft();

	public IBSTNode<T> getLeftChild();

	public IBSTNode<T> getRightChild();

	public void setRightChild(IBSTNode<T> node);

	public void setLeftChild(IBSTNode<T> node);

	public int leftHeight();

	public int rightHeight();

	public IBSTNode<T> leftRight();

	public IBSTNode<T> rightLeft();

	public boolean checkLeftRight();

	public boolean checkRightLeft();
}


class EmptyNode<T extends Comparable<T>> implements IBSTNode<T> {

	// nothing is in an empty node
	public boolean isThere(T findMe) {
		return false;
	}

	// just return a new occupied node with the guy insertMe inside of it
	public IBSTNode<T> insert(double insertThis, T insertMe) {// NEW ADDITION
		return new OccupiedNode<T>(insertThis, insertMe);// NEW ADDICTION
	}

	public IBSTNode<T> nodeRemove() {
		return new EmptyNode<T>();
	}

	public IBSTNode<T> rotateRight() {
		return new EmptyNode<T>();
	}

	public IBSTNode<T> rotateLeft() {
		return new EmptyNode<T>();
	}

	public IBSTNode<T> getLeftChild() {
		return new EmptyNode<T>();
	}

	public IBSTNode<T> getRightChild() {
		return new EmptyNode<T>();
	}

	public void setRightChild(IBSTNode<T> node) {
		return;
	}

	public void setLeftChild(IBSTNode<T> node) {
		return;
	}

	public int leftHeight() {
		return 0;
	}

	public int rightHeight() {
		return 0;
	}

	public IBSTNode<T> leftRight() {
		return new EmptyNode<T>();
	}

	public IBSTNode<T> rightLeft() {
		return new EmptyNode<T>();
	}

	public boolean checkLeftRight() {
		return false;
	}

	public boolean checkRightLeft() {
		return false;
	}

	// replace these with your own code!!!
	public boolean isBalanced() {
		return true;
	}

	public int height() {
		return 0;
	}

	public String inOrderPrint() {
		return "()";
	}

	public String postOrderPrint() {

		return "";
	}
}


class OccupiedNode<T extends Comparable<T>> implements IBSTNode<T> {

	// this is the data we store in this node
	private T myData;
	private double myScore;// NEW ADDITION

	// the left and right subtrees
	private IBSTNode<T> left = new EmptyNode<T>();
	private IBSTNode<T> right = new EmptyNode<T>();

	// constructor
	public OccupiedNode(double startWithMe, T initWithMe) {// NEW ADDITION
		myData = initWithMe;
		myScore = startWithMe;// NEW ADDITION
	}

	// recursively look for findMe
	public boolean isThere(T findMe) {
		if (myData.compareTo(findMe) > 0)
			return left.isThere(findMe);
		else
			return myData.compareTo(findMe) == 0 || right.isThere(findMe);
	}

	// recursively add addMe
	public IBSTNode<T> insert(double addThis, T addMe) {// NEW ADDITION
		if (myScore > addThis)
			left = left.insert(addThis, addMe);// //////////////NEW ADDITION
		else
			right = right.insert(addThis, addMe);// /////////////
		if (left.leftHeight() - left.rightHeight() <= -2) {// if right side longer than left rotate
															// right
			if (left.checkRightLeft()) {
				left = left.rightLeft();
			} else {
				left = left.rotateRight();
			}
		}
		if (left.leftHeight() - left.rightHeight() >= 2) {// if left side longer than right rotate
															// left
			if (left.checkLeftRight()) {
				left = left.leftRight();
			} else {
				left = left.rotateLeft();
			}
		}

		if (right.leftHeight() - right.rightHeight() <= -2) {// if right side longer than left
																// rotate right
			if (right.checkRightLeft() == true) {
				right = right.rightLeft();
			} else {
				right = right.rotateRight();
			}
		}
		if (right.leftHeight() - right.rightHeight() >= 2) {// if left side longer than right rotate
															// left
			if (right.checkLeftRight() == true) {
				right = right.leftRight();
			} else {
				right = right.rotateLeft();
			}
		}
		return this;// ///////////
	}



	public IBSTNode<T> nodeRemove() {
		if (right instanceof EmptyNode) {// if the node does not have a child, this is the rightmost
											// child
			return left;// there might be something in the left child, so return it
		} else {// if node has a child continue to next right tree
			// right = right.nodeRemove();//keep iterating up the tree//////////////////////////
			if (right.leftHeight() - right.rightHeight() <= -2) {// if right side longer than left
																	// rotate right
				if (right.checkRightLeft() == true) {
					System.out.println("remove type 1");
					right = right.rightLeft();
				} else {
					right = right.rotateRight();
				}
			}
			if (right.leftHeight() - right.rightHeight() >= 2) {// if left side longer than right
																// rotate left
				if (right.checkLeftRight() == true) {
					right = right.leftRight();
				} else {
					right = right.rotateLeft();
				}
			}
			right = right.nodeRemove();// keep iterating up the tree//////////////////////////

			return this;// parent of rightmost child does not change/////////////////////
		}
	}

	public IBSTNode<T> getLeftChild() {
		// return the left child of the node right of the root
		return left;
	}

	public IBSTNode<T> getRightChild() {
		// return the right child of the node left of the root
		return right;
	}

	public void setRightChild(IBSTNode<T> node) {
		// Set the right child of the root
		right = node;
		return;
	}

	public void setLeftChild(IBSTNode<T> node) {
		// Set root node as the left child of the right child
		left = node;
		return;
	}

	public IBSTNode<T> rotateRight() {
		IBSTNode<T> grabRight = right;// get the node on the right of the root
		IBSTNode<T> parent = this;// get the root node
		IBSTNode<T> leftChild = grabRight.getLeftChild();
		parent.setRightChild(leftChild);
		grabRight.setLeftChild(parent);
		// make the left child of the right node the new right child of the root node
		// make the root the new left child of the right node
		return grabRight;
	}

	public IBSTNode<T> rotateLeft() {
		IBSTNode<T> grabLeft = left;
		IBSTNode<T> parent = this;// get the root node
		IBSTNode<T> rightChild = grabLeft.getRightChild();
		parent.setLeftChild(rightChild);
		grabLeft.setRightChild(parent);
		// make the right child of the left node the new left child of the root node
		// make the root the new right child of the left node
		return grabLeft;
	}

	public IBSTNode<T> rightLeft() {// does a right left rotation
		IBSTNode<T> grabRight = right;
		IBSTNode<T> leftChild = grabRight.getLeftChild();
		IBSTNode<T> parent = this;
		IBSTNode<T> subRightChild = leftChild.getRightChild();
		parent.setRightChild(leftChild);
		leftChild.setRightChild(grabRight);
		grabRight.setLeftChild(subRightChild);

		IBSTNode<T> secondLeftChild = leftChild.getLeftChild();
		parent.setRightChild(secondLeftChild);
		leftChild.setLeftChild(parent);


		return leftChild;
	}

	public IBSTNode<T> leftRight() {// does a left right rotation
		IBSTNode<T> parent = this;
		IBSTNode<T> grabLeft = left;
		IBSTNode<T> rightChild = grabLeft.getRightChild();
		IBSTNode<T> subLeftChild = rightChild.getLeftChild();
		parent.setLeftChild(rightChild);
		rightChild.setLeftChild(grabLeft);
		grabLeft.setRightChild(subLeftChild);

		IBSTNode<T> secondRightChild = rightChild.getRightChild();
		parent.setLeftChild(secondRightChild);
		rightChild.setRightChild(parent);

		return rightChild;
	}

	public boolean checkRightLeft() {
		IBSTNode<T> grabRight = right;
		if (grabRight.leftHeight() - grabRight.rightHeight() < -1) {
			return true;
		} else {
			return false;
		}
	}

	public boolean checkLeftRight() {
		IBSTNode<T> grabLeft = left;
		if (grabLeft.leftHeight() - grabLeft.rightHeight() > 1) {
			return true;
		} else {
			return false;
		}
	}

	// replace these with your own code!!!
	public int leftHeight() {
		int leftSideHeight = 0;
		leftSideHeight += 1 + left.leftHeight();
		return leftSideHeight;
	}

	public int rightHeight() {
		int rightSideHeight = 0;
		rightSideHeight += 1 + right.rightHeight();
		return rightSideHeight;
	}

	public boolean isBalanced() {
		return true;
	}

	public int height() {
		int leftle = 0;
		int rightri = 0;
		leftle += 1 + left.height();
		rightri += 1 + right.height();
		if (rightri > leftle) {
			return rightri;
		}
		return leftle;
	}

	public String inOrderPrint() {
		return "(" + left.inOrderPrint() + myData + right.inOrderPrint() + ")";
	}

	public String postOrderPrint() {
		return right.postOrderPrint() + " " + myData + " " + left.postOrderPrint();
	}


}
