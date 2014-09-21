import java.util.HashMap;


public class BubbleWord {

  private HashMap<String, Integer> container = new HashMap<String,Integer>();
  private String theWord = "";
  
  public BubbleWord(String word, int count){
    container.put(word, count);
    theWord = word;
  }
  
  /**
   * Updates the word count in the container.
   * @param word
   */
  public void updateFreq(String word){
    container.put(word, container.get(word)+1);
  }
 
  public int getFreq(String word){
    return container.get(word);
  }
  
  /**
   * Returns the word for the container.
   * @return
   */
  public String getWord(){
    return theWord;
  }
}
