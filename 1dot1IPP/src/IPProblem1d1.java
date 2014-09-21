import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;


public class IPProblem1d1 {

  public static void main(String[] args) {
    // TODO Auto-generated method stub
    
    System.out.println(isUnique("abcc"));
    System.out.println(isUnique2("abcc"));    
  }

  public static boolean isUnique(String sentence){
    int strLen = sentence.length();
    //String temp = "";
    Set<Character> letters = new TreeSet<Character>();
    for(int i = 0; i < strLen; i++){
      if(letters.contains(sentence.charAt(i))){
        return false;
      }else{
        letters.add(sentence.charAt(i));
      }
    }
  return true;                                         
  }
  
  
  public static boolean isUnique2(String sentence){
    int strLen = sentence.length();
    for(int i = 0; i < strLen; i++){
      for(int j = 0; j < strLen; j++){
        if(i == j){
          continue;
        }
        if(sentence.charAt(i) == sentence.charAt(j)){
          return false;
        }
      }
    }
    return true;
  }
}
