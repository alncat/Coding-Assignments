import java.util.*;
import java.io.*;
public class SaveComponents implements java.io.Serializable{



   public ArrayList<String> theWordDicArrayList;
   public Map<String, Integer> theOtherWordDic;
   public int thenumberOfWords;
   public int thenumberOfTopics;
   public IDoubleVector theAlpha;
   public IDoubleVector theBeta;
   public String nameXMLFile;
   public double thesummation;
   public WordMounter thewordCount;
   

}