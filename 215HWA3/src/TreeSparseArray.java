import java.util.*;

class TreeSparseArray<T> implements ISparseArray<T>{
  
  Map<Integer, T> treeMap = new TreeMap<Integer, T>(); //Map<Integer, double> treeMap = new TreeMap<Integer, double>();
  private int counter = 0;
  
  /**
   * Add element to the array at position.
   * 
   * @param position  position in the array
   * @param element   data to place in the array
   */
  public void put (int position, T element){     
    treeMap.put( position,  element);
  }
  
  /**
   * Get element at the given position.
   *
   * @param position  position in the array
   * @return          element at that position or null if there is none
   */
  public T get (int position){  
    return treeMap.get(position);
  }
  
  /**
   * Create an iterator over the array.
   *
   * @return  an iterator over the sparse array
   */
  public Iterator<IIndexedData<T>> iterator (){
    //TreeIndexedIterator myIter = new TreeIndexedIterator<IIndexedData<T>>();
    Iterator<IIndexedData<T>> myIter = new TreeIndexedIterator<T>(treeMap);
    //return myIter = new TreeIndexedIterator<T>(); 
    //TreeIndexedIterator <IIndexedData <T>> myIter = new TreeIndexedIterator<IIndexedData<T>>();
    return myIter;//myIter = myArray.iterator ();
    //Iterator <IIndexedData <T>> myIter = new Iterator<IIndexedData<T>>();
    //TreeIndexedIterator <IIndexedData <T>> myIter = new Iterator<IIndexedData<T>>();
  }
  
  public TreeSparseArray(){//Consturctor for the class that does not take any arguments and it is in default mode        
    //int counter = 0;      
  }  
  
  class TreeIndexedIterator<T> implements Iterator<IIndexedData<T>>{
    
    private Map <Integer, T> mapIn;
    private Iterator<Integer> keys;    
    private int index = 0;
//   private int arrayCounter;
//   private int numbers;   
//   private Set keys = treeMap.keySet();   
//   private Iterator []anArray = new Iterator[treeMap.size()]; 
    
    public TreeIndexedIterator(Map<Integer, T> treeMap){     
      mapIn = treeMap;
      keys = mapIn.keySet().iterator();
      //myData = new IIndexedData<T>(); CANNOT DO THIS, WILL NOT WORK/COMPILE
//     for (Iterator i = keys.iterator(); i.hasNext();){
//     
//       anArray[arrayCounter] = i;
//       arrayCounter++;
//     }     
    }
    
    public boolean hasNext(){
      //System.out.println("Tell me the size of my treeMap in line 74:" + treeMap.size());     
      return keys.hasNext(); //index++ < treeMap.size(); 
    }
    
    public IIndexedData<T> next(){
      int index = keys.next();
      T data = mapIn.get(index);
      IndexedData<T> retVal = new IndexedData <T> (index, data);
      return retVal;  //FIGURE OUT WHY THIS IS RETURNING NULL
      //return treeMap.get(anArray[numbers++]);
    }
    
    public void remove(){ 
      throw new RuntimeException("There should not be any removes called.");
    }
           
  }
}




