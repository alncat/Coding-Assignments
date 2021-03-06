import java.util.ArrayList;

// This simple interface allows us to get the top K objects out of a large set
// (that is, the K objects that are inserted having the **lowest** score vales)
// The idea is that you would create an ITopKMachine using a specific K. Then,
// you insert the vlaues one-by-one into the machine using the "insert" method.
// Whenever you want to obtain the current top K, you call "getTopK", which puts
// the top K into the array list. In addition, the machine can be queried to
// see what is the current worst score that will still put a value into the top K.
interface ITopKMachine<T> {

	// insert a new value into the machine. If its score is greater than the current
	// cutoff, it is ignored. If its score is smaller than the current cutoff, the
	// insertion will evict the value with the worst score.
	void insert(double score, T value);

	// get the current top K in an array list
	ArrayList<T> getTopK();

	// let the caller know the current cutoff value that will get one into the top K list
	double getCurrentCutoff();

	boolean memebership(T value); // see if a node is in the tree

	int size();// computes the size of the tree

	int checkBalance();// checks to see if the tree is balanced
	
	boolean membership(T value);

}
