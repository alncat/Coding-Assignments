import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.TreeMap;

/**
 * This class is used to count occurences of words (strings) in a corpus.
 */
class WordCounter {
  private ArrayList<String> wordsList;// = new ArrayList<String>();
  private TreeMap<String, Integer> mapP = new TreeMap<String, Integer>();

  // key is the Kth word and value is the word itself
  private TreeMap<Integer, BubbleWord> FreqMap = new TreeMap<Integer, BubbleWord>();

  // container class where the key is the word and the value is the frequency
  private BubbleWord wordHolder;

  // number of words in the FreqMap
  private int wordsCounted = 0;

  /**
   * Create a new, empty word counter
   */
  public WordCounter() {}

  /**
   * Accepts a word and increments the count of the number of times this word has been seen.
   * 
   * @param addMe The word to count
   */
  public void insert(String addMe) {

    int incremented, adjustIndex;
    int freqCount, compareCount;
    BubbleWord keepContainer, newIndexContainer;
    String compareWord;

    // sub for mapP.get(addMe) +1
    if (mapP.containsKey(addMe)) {
      incremented = mapP.get(addMe) + 1;
      mapP.put(addMe, incremented);
      //System.out.println("Bing");

      //System.out.println("Bong");
      // Update the frequency count for the word
      for (int i = 0; i < FreqMap.size(); i++) {
        if (addMe.contains(FreqMap.get(i).getWord())) {
          // Hold the word container
          keepContainer = FreqMap.get(i);

          // update the count in container
          keepContainer.updateFreq(addMe);

          // hold the new freq
          freqCount = keepContainer.getFreq(addMe);

          // sort the word by going up, minus one so it will not compare itself
          for (int j = i - 1; j > 0; j--) {

            compareWord = FreqMap.get(j).getWord();
            compareCount = FreqMap.get(j).getFreq(compareWord);

            // first case, find all the words that have the same frequency
            if (freqCount == compareCount) {// Find the word in the map
              // compare the word, zero is the same, positive is less than, negative is greater than
              if (compareWord.compareToIgnoreCase(addMe) >= 0) {
                continue;
              }

              if (compareWord.compareToIgnoreCase(addMe) < 0) {
                // loop to push the words downward
                adjustIndex = FreqMap.size();
                while (j > adjustIndex) {
                  FreqMap.put(adjustIndex + 1, FreqMap.get(adjustIndex));
                  adjustIndex--;
                }
                FreqMap.put(j + 1, keepContainer);
              }

              // second case
              if (freqCount > compareCount) {
                continue;
              }

              // third case
              if (freqCount < compareCount) {
                adjustIndex = FreqMap.size();
                while (j > adjustIndex) {
                  FreqMap.put(adjustIndex + 1, FreqMap.get(adjustIndex));
                  adjustIndex--;
                }
                FreqMap.put(j + 1, keepContainer);
              }

            }

          }// end of for loop
        }

      }
      // check and see if the word needs to move up or down in the map
    } else {
      //incremented = 1;
      mapP.put(addMe, 1);

      System.out.println("Bing");
      //just add it if the map is empty
      if(FreqMap.isEmpty()){
      FreqMap.put(FreqMap.size(), wordHolder = new BubbleWord(addMe, 1));

      return;
      }
      
      //place it at the bottom if not empty
      if(!FreqMap.isEmpty()){
       FreqMap.put(FreqMap.size() +1, wordHolder = new BubbleWord(addMe, 1)); 

       System.out.println("Bong");
      }
      
      // Update the frequency count for the word
      for (int i = 0; i < FreqMap.size(); i++) {
        if (addMe.compareToIgnoreCase((FreqMap.get(i).getWord())) == 0) {
          // Hold the word container
          keepContainer = FreqMap.get(i);

          // update the count in container
          keepContainer.updateFreq(addMe);

          // hold the new freq
          freqCount = keepContainer.getFreq(addMe);

          // sort the word by going up, minus one so it will not compare itself
          for (int j = i - 1; j > 0; j--) {

            compareWord = FreqMap.get(j).getWord();
            compareCount = FreqMap.get(j).getFreq(compareWord);

            // first case, find all the words that have the same frequency
            if (freqCount == compareCount) {// Find the word in the map
              // compare the word, zero is the same, positive is less than, negative is greater than
              if (compareWord.compareToIgnoreCase(addMe) >= 0) {
                continue;
              }

              if (compareWord.compareToIgnoreCase(addMe) < 0) {
                // loop to push the words downward
                adjustIndex = FreqMap.size();
                while (j > adjustIndex) {
                  FreqMap.put(adjustIndex + 1, FreqMap.get(adjustIndex));
                  adjustIndex--;
                }
                FreqMap.put(j + 1, keepContainer);
              }

              // second case
              if (freqCount > compareCount) {
                continue;
              }

              // third case
              if (freqCount < compareCount) {
                adjustIndex = FreqMap.size();
                while (j > adjustIndex) {
                  FreqMap.put(adjustIndex + 1, FreqMap.get(adjustIndex));
                  adjustIndex--;
                }
                FreqMap.put(j + 1, keepContainer);
              }

            }

          }// end of for loop
        }

      }
      
    }


  }

  /**
   * Returns the kth most frequent word in the corpus so far.
   * 
   * @param k Note that the most frequent word is at k = 0
   * @return The kth most frequent word in the corpus to date
   */
  public String getKthMostFrequent(int k) {
    return FreqMap.get(k).getWord();
  }

}
