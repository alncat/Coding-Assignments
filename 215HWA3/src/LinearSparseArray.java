import java.util.*;

class LinearSparseArray<T> extends ASparseArray {

  //Will hold the indices for the values
  private int[] indices;
  //class for Vector
  private Vector<T> data;
  //class variables for number of 
  //slots and number of puts called
  private int totalSlots, counter;



  /**
   * Constructor that takes in the number of initial slots for the
   * array.
   * @param initialSlots
   */
  public LinearSparseArray(int initialSlots) {
    //holds the indices for the sparse array
    indices = new int[2000000];
    //stores the objects of type T
    data = new Vector<T>();
    //takes in the number of slots
    totalSlots = initialSlots;
    //keeps track of of the number of put operations
    counter = 0;
  }

  /**
   * Add element to the array at position.
   * 
   * @param position position in the array
   * @param element data to place in the array
   */
  @SuppressWarnings("unchecked")
  @Override
  public void put(int position, Object element) {
    //sets the corresponding position and counter
    indices[counter] = position;
    //increment number of slots
    totalSlots++;
    //add element to data vector
    data.add(counter, (T) element);
    //increment number of puts
    counter++;
  };

  /**
   * Get element at the given position.
   *
   * @param position position in the array
   * @return element at that position or null if there is none
   */
  public T get(int position) {
    //search until matching index is found
    //if not found just return null
   for (int i = 0; i < counter; i++) {
      if (indices[i] == position) {
        return data.get(i);
      }
    }
    return null;
  }

  /**
   * Create an iterator over the array.
   *
   * @return an iterator over the sparse array
   */
  public Iterator<IIndexedData<T>> iterator() {
    Iterator<IIndexedData<T>> myIter = new LinearIndexedIterator<T>(data, indices);
    return myIter;
  }


  @SuppressWarnings("hiding")
  class LinearIndexedIterator<T> implements Iterator<IIndexedData<T>> {

    private int index;
    private IndexedData<T> myData;
    private Vector<IndexedData<T>> vecData;


    /**
     * Constructor to perform searches items in a vector.
     *  
     * @param inputData
     * @param posIndex
     */
    public LinearIndexedIterator(Vector<T> inputData, int[] posIndex) {
      index = 0;
      vecData = new Vector<IndexedData<T>>();
      for (int i = 0; i < inputData.size(); i++) {
        myData = new IndexedData<T>(i * 100, inputData.get(i));
        vecData.add(myData);
      }


    }

    /* (non-Javadoc)
     * @see java.util.Iterator#hasNext()
     */
    public boolean hasNext() {
      if (data.size() < index + 1) {
        return false;
      } else {
        return true;
      }
    }

    /* (non-Javadoc)
     * @see java.util.Iterator#next()
     */
    public IIndexedData<T> next() {
      IIndexedData<T> temp = vecData.get(index);
      index++;
      return temp;
    }

    /* (non-Javadoc)
     * @see java.util.Iterator#remove()
     */
    public void remove() {
      throw new RuntimeException("There not be any removes called.");
    }


  }

  public class LinearSearch {

    /**
     * Method that performs a linear search on an input array of
     * integers.
     * 
     * @param data
     * @param key
     * @return
     */
    public int find(final int[] data, final int key) {
      for (int i = 0; i < data.length; ++i) {
        if (data[i] > key) {
          return -1;
        } else if (data[i] == key) {
          return i;
        }
      }
      return -1;
    }
  }

}
