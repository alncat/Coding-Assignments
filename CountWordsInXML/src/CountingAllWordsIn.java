import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class CountingAllWordsIn {

	public static void main(String[] args) {
		WordMounter22 wordCount = new WordMounter22();
		BufferedReader inputStream = null;
        String input = "/Users/Ace/Downloads/output0.xml";
        try {
          inputStream =
            new BufferedReader(new FileReader(input));
          
          String l;
          while ((l = inputStream.readLine()) != null){//(l = inputStream.readLine()) != null
        	  l = inputStream.readLine();
        	  if(l.contains("<doc")){ //||l.contains("</doc>")
        		  //System.out.println(l);
        		  //System.out.println(l.lastIndexOf("="));
        		  //System.out.println(l.lastIndexOf(">"));
        		  String title = "";
        		  for(int i = l.lastIndexOf("=")+2; i < l.lastIndexOf(">")-1; i++){
        			  title += l.charAt(i);
        		  }
        		  System.out.println(title);
        	  }
            countChris(l, wordCount);
          }
        }catch(IOException E){} finally {
          if (inputStream != null) {
            try{
              inputStream.close();
            }catch(IOException E){}
          }
          
        }
        
      //!!!!!!}
      int totalUniqueWords = wordCount.uniqueWords();
      System.out.println("Found " + totalUniqueWords + " unique words in the corpus.");

	}
	 public static int countChris (String inMe, WordMounter22 counter) {
		    Pattern pattern = Pattern.compile ("[a-zA-Z_0-9]*");//\\S^[\\s]
		    Matcher matcher = pattern.matcher (inMe);//
		    int returnVal = 0;
		    for (; matcher.find (); returnVal++) {
		      counter.insert(matcher.group().toLowerCase());
		      
		    }
		    return returnVal;
		  }
}