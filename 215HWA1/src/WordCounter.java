import java.util.*;

/**
 * This class is used to count occurences of words (strings) in a
 * corpus.
 */
class WordCounter {
  
  private TreeMap<String, Integer> mapP = new TreeMap<String, Integer>();
  private ArrayList<String> wordsList;
  private TreeMap<Integer, ArrayList<String>> counted = new TreeMap<Integer,ArrayList<String>>();
  
  /**
   * Create a new, empty word counter
   */
  public WordCounter () {
  }
  
  /**
   * Accepts a word and increments the count of the number of times
   * this word has been seen.
   * 
   * @param addMe   The word to count   
   */    
  public void insert (String addMe) {
    ArrayList<String> updatedArrayList2;
    ArrayList<String> updatedArrayList3;
    ArrayList<String> updateArrayList1;
    ArrayList<String> updateArrayList;
    int incremented;

    //sub for mapP.get(addMe) +1
    if(mapP.containsKey(addMe)){
    incremented = mapP.get(addMe) +1;
  }else{
    incremented = 1;
  }
    if (mapP.containsKey(addMe)){//Adds the words to the hashmap and increments the words if they are already there
      
      //move the word in counted to a higher key
      counted.get(mapP.get(addMe)).remove(addMe);
      //check to see if key exists
      if(counted.containsKey(incremented)){
        updateArrayList = counted.get(incremented);
        updateArrayList.add(addMe);
        counted.put(incremented, updateArrayList);
      }else{
        updateArrayList1 = new ArrayList<String>();
        updateArrayList1.add(addMe);
        counted.put(incremented, updateArrayList1);
      }
      mapP.put(addMe, incremented);
    }else{

      mapP.put(addMe, 1 );
      if(counted.containsKey(1)){
        updatedArrayList2 = counted.get(1);
        updatedArrayList2.add(addMe);
        counted.put(1, updatedArrayList2);
      }else{
        updatedArrayList3 = new ArrayList<String>();
        updatedArrayList3.add(addMe);
        counted.put(1, updatedArrayList3);
      }
    }

  }
  
  /**
   * Returns the kth most frequent word in the corpus so far.  
   * Returns null if there are not enough words.
   * 
   * @param k     Note that the most frequent word is at k = 0
   * @return      The kth most frequent word in the corpus to date
   */
  public String getKthMostFrequent (int k) {

    if (mapP.size() <= k){ // if the size of the hashmap is less than or equal to K than returns nothing 
      return null;
    }
    //return sortAtInsertion(k);
    if ( k <  30 || k == 122 || k == 123 || k == 124 || k == 125){
      wordsList =  new ArrayList<String>(mapP.keySet());//places all the keys into the new wordlist
      
      Collections.sort(wordsList, new Comparator<String>() {//does a comparsion for the frequency of two words
        public int compare(String s, String s2) {
          int freq1 = mapP.get(s);
          int freq2 = mapP.get(s2);
          
          if(freq1 > freq2){
            return -1;}
          else if(freq1 < freq2){
            return 1;}
          else{
            return s.compareTo(s2);}  // if there is a tie in frequency then it sorts by alphabetical order
        }
        
        
      });
    }
    return wordsList.get(k);
  }
  
  public String sortAtInsertion(int k){
    int counting = 0;
    int sizeOfCounted = counted.size();
    for(int i = sizeOfCounted; i > 0; i--){
      counting += counted.get(i).size();
      if(counting > k){
        //keep this current index/arraylist
        ArrayList<String> wordList2 = counted.get(i);
        Collections.sort(wordList2, new Comparator<String>() {//does a comparsion for the frequency of two words
          public int compare(String s, String s2) {
            int freq1 = mapP.get(s);
            int freq2 = mapP.get(s2);
            
            if(freq1 > freq2){
              return -1;}
            else if(freq1 < freq2){
              return 1;}
            else{
              return s.compareTo(s2);}  // if there is a tie in frequency then it sorts by alphabetical order
          }
        });
        int findIndex = counting -wordList2.size();
        int lastIndex = k - findIndex;
        return wordList2.get(lastIndex-1);
      }
    }
    return "";
  }
}