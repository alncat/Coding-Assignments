import java.util.*;
import java.io.*;

/**
 * This class is used to count occurences of words (strings) in a
 * corpus.
 */
 class WordMounter22 implements java.io.Serializable {
  
  private Map<String, Integer> mapP = new HashMap<String, Integer>();
  
  //private ArrayList<String> wordList = new ArrayList<String>();
  /**
   * Create a new, empty word counter
   */
  public WordMounter22 () {
   
  }
  
  public Iterator getTopKWordsThere(){
    Set<String> theWordsFound01 = returnKeys();
    System.out.println("The size of words found in my set: "+ theWordsFound01.size());
    Iterator<String> wordsLooping = theWordsFound01.iterator();
    SortedMap<Integer, String> freqSortWords = new TreeMap<Integer, String>();
    int indexForTopWord = 0;
    while(wordsLooping.hasNext()){
      String keyToGet = wordsLooping.next();
      freqSortWords.put(mapP.get(keyToGet), keyToGet);
    }
    System.out.println("the size of may sorted map tree: " +freqSortWords.size());
    Set s = freqSortWords.entrySet();
    Iterator iter = s.iterator();
    return iter;
  }
  
  public void addToQue(){
    PriorityQueue cough = new PriorityQueue();
    for(int i = 0; i < 10; i++){
    cough.add(i);
    }
    for(int i = 0; i < 10; i++){
    System.out.println(cough.remove());
    }
  }
  
  public int giveMeVal(String key){
  return mapP.get(key);
  }
  
  public Set<String> returnKeys(){
  return mapP.keySet();
  }
  
  /**
   * Accepts a word and increments the count of the number of times
   * this word has been seen.
   * 
   * @param addMe   The word to count   
   */    
  public void insert (String addMe) {
    
    if (mapP.containsKey(addMe)){//Adds the words to the hashmap and increments the words if there are already there
      
      mapP.put(addMe, mapP.get(addMe) + 1);
    }else{
      
      mapP.put(addMe, 1 );
    }
  }
  //counts the number of words in the map
  public int uniqueWords(){
    return mapP.size();
  }
  
  /**
   * Returns the kth most frequent word in the corpus so far.  
   * Returns null if there are not enough words.
   * 
   * @param k     Note that the most frequent word is at k = 0
   * @return      The kth most frequent word in the corpus to date
   */
  public ArrayList<String>  getKthMostFrequent () {//return type was a string
    
//    if (mapP.size() <= k){ // if the size of the hashmap is less than or equal to K than returns nothing 
//      return null;
//    }
    
    ArrayList<String> wordsList =  new ArrayList<String>(mapP.keySet());//places all the keys into the wordlist
    
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
    
    return wordsList;
  }
  
  
  
  public String bobobo(){
    return "BOBOBO";
  }
}