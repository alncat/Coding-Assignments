import java.util.*;
// This simple interface allows us to get the top K objects out of a large set
// (that is, the K objects that are inserted having the **lowest** score vales)
// The idea is that you would create an ITopKMachine using a specific K. Then,
// you insert the vlaues one-by-one into the machine using the "insert" method.
// Whenever you want to obtain the current top K, you call "getTopK", which puts
// the top K into the array list. In addition, the machine can be queried to
// see what is the current worst score that will still put a value into the top K.

abstract class AbstractITopKMachine<T extends Comparable<T>> implements ITopKMachine<T> {

	// insert a new value into the machine. If its score is greater than the current
	// cutoff, it is ignored. If its score is smaller than the current cutoff, the
	// insertion will evict the value with the worst score.
	abstract public void insert(double score, T value);

	// get the current top K in an array list
	abstract public ArrayList<T> getTopK();

	// let the caller know the current cutoff value that will get one into the top K list
	abstract public double getCurrentCutoff();

	// produces a string representation of the AVL tree
	abstract public String toString();

	abstract public int checkBalance();// checks to see if the left and right heights are equal

	abstract public boolean memebership(T value);// check to see if there is node in the tree.

	abstract public int size();// finds the number of nodes in the tree

}
