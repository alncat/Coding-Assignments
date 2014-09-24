import java.util.*;

class LinearSparseArray<T> extends ASparseArray {//ISparseArray<T>
  
  private int [] indices;
  private Vector<T> data;
  private int totalSlots, counter;
  private int [] nothing = new int [1];
  
  
  public LinearSparseArray(int initialSlots){   
    indices = new int [2000000];
    data = new Vector <T>(); 
    totalSlots = initialSlots;
    counter = 0;
  }
  
  /**
   * Add element to the array at position.
   * 
   * @param position  position in the array
   * @param element   data to place in the array
   */
  @Override
  public void put (int position, T element){
    
    indices[counter] = position;
    totalSlots++;
    data.add(counter, element);
    counter++;  
  };
  
  /**
   * Get element at the given position.
   *
   * @param position  position in the array
   * @return          element at that position or null if there is none
   */
  public T get (int position){
    /*LinearSearch search = new LinearSearch();
    if ((position >= 0) && (position < data.size())){      
      return data.get(position);// found at index
    }else {     
      return null; //not found
    }*/
    
    for(int i = 0; i < counter; i++){//indices.length
      if (indices[i] == position){
      return data.get(i);
      } 
    }
    return null;
  }
  
  /**
   * Create an iterator over the array.
   *
   * @return  an iterator over the sparse array
   */
  public Iterator<IIndexedData<T>> iterator (){
    Iterator<IIndexedData<T>> myIter = new LinearIndexedIterator<T>(data, indices);
    return myIter;
  }
  
  
  class LinearIndexedIterator<T> implements Iterator<IIndexedData<T>>{
    
    private int index;
    private IndexedData<T> [] arrayValues;
    private IndexedData <T> myData; //= new IndexedData <T> (key, value);
    private Vector<IndexedData<T>> vecData;
    //trial run
    private int [] actualPos, empty = new int [1];
    private int counted = 0, counting = 0;
    //trial run
    
    public LinearIndexedIterator(Vector<T> inputData, int [] posIndex){
      index = 0;
      //arrayValues = new IndexedData<T> [inputData.size()];//NOT
      vecData = new Vector<IndexedData<T>>();
      for(int i = 0; i < inputData.size(); i++){
        //arrayValues[i] = myData = new IndexedData<T>(i,inputData.get(i));
        myData = new IndexedData<T>(i*100, inputData.get(i));
        vecData.add(myData);
      }
      //trial run***************
      //count the number of non-null indices
//      for(int i = 0; i < posIndex.length; i++){
//        if(posIndex[i] != empty[0]){
//        counted++;
//        }
//      }
//      actualPos = new int[counted];
//      //fill in the array with the non-null indices
//      for(int i = 0; i < posIndex.length; i++){
//        if(posIndex[i] != empty[0]){
//        actualPos[counting] = posIndex[i];
//        counting++;
//        }
//      }
//      for(int i = 0; i < actualPos.length; i++){
//      myData = new IndexedData<T>(actualPos[i], inputData.get(actualPos[i]));
//      vecData.add(myData);
//      }
      //trial run**************
      
    }
    
    public boolean hasNext(){
      if (data.size() < index + 1){
        return false; 
      }else{
        //index++;
        return true;
      }
    }
    
    public IIndexedData<T> next(){
      IIndexedData<T> temp = vecData.get(index);
      index++;
      return temp;
    }
    
    public void remove(){     
      throw new RuntimeException("There not be any removes called.");
    }
    
    
  }
  
  public class LinearSearch{
    
    public int find (final int[] data, final int key){     
      for ( int i = 0; i < data.length; ++i){       
        if (data[i] > key){         
          return -1;
        }else if( data[i]== key) {          
          return i;
        }               
      }
      return -1;
    }
  }
  
}
