import junit.framework.TestCase;
import java.util.Random;
import java.util.Collections;
import java.util.ArrayList;

/**
 * A JUnit test case class.
 * Every method starting with the word "test" will be called when running
 * the test with JUnit.
 */
public class AVLTopKMachineTester extends TestCase {
  
  // this simple method randomly shuffles the items in an array
  Random shuffler = new Random (324234);
  void shuffle (Integer [] list) {
    for (Integer i = 0; i < list.length; i++) {
      Integer pos = i + shuffler.nextInt (list.length - i);
      Integer temp = list[i];
      list[i] = list[pos];
      list[pos] = temp;
    }
  }
  
  // the first param is the number of inserts to try.  The second is k. If the third param is true, 
  // then we do a random order of inserts.  If the third param is false, then we do inserts in order,
  // and the method expects a fourth boolean param that tells us whether we do reverse or forward 
  // inserts.  So testInserts (100, 5, false, true) will do 100 reverse order inserts, with a k of 5.
  // testInserts (100, 5, false, false) will do 100 in order inserts.  testInserts (100, 5, true) will
  // do 100 random inserts.
  private void testInserts (int numInserts, int k, boolean... controlTest) {
   
    // see what kind of test to do
    boolean reverseOrNot = false;
    boolean randomOrNot = controlTest[0];
    if (!randomOrNot) 
      reverseOrNot = controlTest[1];
    
    // create a list of random ints
    ITopKMachine <Integer> testMe = new AVLTopKMachine <Integer> (k);
    Integer [] list = new Integer [numInserts];
    for (int i = 0; i < numInserts; i++) {
      if (reverseOrNot)
        list[i] = numInserts - 1 - i;
      else
        list[i] = i; 
    }
    
    // if we are looking for randomness, shuffle the list
    if (randomOrNot)
      shuffle (list);
    
    // now add the ints
    for (int j = 0; j < list.length; j++) {
      
      Integer i = list[j];
      testMe.insert (i * 1.343432, i);
      
      // if we are not random, check to see that the cutoff is correct
      if (!randomOrNot) {
        
        double score = testMe.getCurrentCutoff ();
        
        // if we don't have k inserts, then we should have an extreme cutoff
        if (j + 1 < k) {
          if (score != Double.POSITIVE_INFINITY && score != Double.NEGATIVE_INFINITY)
            fail ("at insert number " + j + 1 + " expected positive or negative infinity; found " + score);
          
        // otherwise, check the cutoff
        } else {
          
          // for reverse order inserts, we have one cutoff...
          if (reverseOrNot) {
            
            assertEquals ("when checking cutoff during reverse inserts", (i + k - 1) * 1.343432, score);
            
          // and for in-order, we have another
          } else {
            assertEquals ("when checking cutoff during in order inserts", (k - 1) * 1.343432, score);
          }
        }
      }
    }
    
    // and make sure top k are correct
    ArrayList <Integer> retVal = testMe.getTopK ();
    Collections.sort (retVal);
    
    // don'e go past the size of the list
    if (k > numInserts)
      k = numInserts;
    
    // make sure the list is the right size
    assertEquals (retVal.size (), k);
    
    // and check its contents
    for (int i = 0; i < k; i++) {
      assertEquals ("when checking values returned getting top k", i, (int) retVal.get (i));
    }
  }
  
  // this checks for balance.. it does NOT check for the right answer... params ae same as above
  private void testBalance (int numInserts, int k, boolean... controlTest) {
       
    // see what kind of test to do
    boolean reverseOrNot = false;
    boolean randomOrNot = controlTest[0];
    if (!randomOrNot) 
      reverseOrNot = controlTest[1];
    
    // create a list of random ints
    ITopKMachine <Integer> testMe = new AVLTopKMachine <Integer> (k);
    Integer [] list = new Integer [numInserts];
    for (int i = 0; i < numInserts; i++) {
      if (reverseOrNot)
        list[i] = numInserts - 1 - i;
      else
        list[i] = i; 
    }
    
    // if we are looking for randomness, shuffle the list
    if (randomOrNot)
      shuffle (list);
    
    // now add the ints
    for (int j = 0; j < list.length; j++) { 
      Integer i = list[j];
      testMe.insert (i * 1.343432, i);
      
      // and check for balance
      if (j % 10 == 0) {
        IsBalanced temp = new IsBalanced ();
        try {
          temp.checkHeight (testMe.toString ());
        } catch (Exception e) {
          fail ("the tree was found to not be balanced"); 
        }
      }
      
      // check for balance one last time
      IsBalanced temp = new IsBalanced ();
      try {
        temp.checkHeight (testMe.toString ());
      } catch (Exception e) {
        fail ("the tree was found to not be balanced"); 
      }
    }      

  }
  
  
  /**
   * A test method.
   * (Replace "X" with a name describing the test.  You may write as
   * many "testSomething" methods in this class as you wish, and each
   * one will be called when running JUnit over this class.)
   */
  
  /******************************
    * These do RANDOM inserts *
    ******************************/
  
  public void testAFewRandomInsertsSmallK () {
    testInserts (10, 5, true);
  }
  
  public void testALotOfRandomInsertsSmallK () {
    testInserts (100, 5, true);
  }
  
  public void testAHugeNumberOfRandomInsertsSmallK () {
    testInserts (100000, 5, true);
  }
  
  public void testAFewRandomInsertsBigK () {
    testInserts (10, 100, true);
  }
  
  public void testALotOfRandomInsertsBigK () {
    testInserts (100, 100, true);
  }
  
  public void testAHugeNumberOfRandomInsertsBigK () {
    testInserts (100000, 100, true);
  }
 
    /******************************
    * These do ORDERED inserts *
    ******************************/
  
  public void testAFewOrderedInsertsSmallK () {
    testInserts (10, 5, false, false);
  }
  
  public void testALotOfOrderedInsertsSmallK () {
    testInserts (100, 5, false, false);
  }
  
  public void testAHugeNumberOfOrderedInsertsSmallK () {
    testInserts (100000, 5, false, false);
  }
  
  public void testAFewOrderedInsertsBigK () {
    testInserts (10, 100, false, false);
  }
  
  public void testALotOfOrderedInsertsBigK () {
    testInserts (100, 100, false, false);
  }
  
  public void testAHugeNumberOfOrderedInsertsBigK () {
    testInserts (100000, 100, false, false);
  }
  
  /******************************
    * These do REVERSE inserts *
    ******************************/
  
  public void testAFewReverseInsertsSmallK () {
    testInserts (10, 5, false, true);
  }
  
  public void testALotOfReverseInsertsSmallK () {
    testInserts (100, 5, false, true);
  }
  
  public void testAHugeNumberOfReverseInsertsSmallK () {
    testInserts (100000, 5, false, true);
  }
  
  public void testAFewReverseInsertsBigK () {
    testInserts (10, 100, false, true);
  }
  
  public void testALotOfReverseInsertsBigK () {
    testInserts (100, 100, false, true);
  }
  
  public void testAHugeNumberOfReverseInsertsBigK () {
    testInserts (100000, 100, false, true);
  }
  
    /***************************
    * Now check for balance!!! *
    **************************/
  
    /******************************
    * These do RANDOM inserts *
    ******************************/
  
  public void testAFewRandomInsertsSmallK_CheckBalance () {
    testBalance (10, 5, true);
  }
  
  public void testALotOfRandomInsertsSmallK_CheckBalance () {
    testBalance (100, 5, true);
  }
  
  public void testAHugeNumberOfRandomInsertsSmallK_CheckBalance () {
    testBalance (100000, 5, true);
  }
  
  public void testAFewRandomInsertsBigK_CheckBalance_CheckBalance () {
    testBalance (10, 100, true);
  }
  
  public void testALotOfRandomInsertsBigK_CheckBalance () {
    testBalance (100, 100, true);
  }
  
  public void testAHugeNumberOfRandomInsertsBigK_CheckBalance () {
    testBalance (100000, 100, true);
  }
 
    /******************************
    * These do ORDERED inserts *
    ******************************/
  
  public void testAFewOrderedInsertsSmallK_CheckBalance () {
    testBalance (10, 5, false, false);
  }
  
  public void testALotOfOrderedInsertsSmallK_CheckBalance () {
    testBalance (100, 5, false, false);
  }
  
  public void testAHugeNumberOfOrderedInsertsSmallK_CheckBalance () {
    testBalance (100000, 5, false, false);
  }
  
  public void testAFewOrderedInsertsBigK_CheckBalance () {
    testBalance (10, 100, false, false);
  }
  
  public void testALotOfOrderedInsertsBigK_CheckBalance () {
    testBalance (100, 100, false, false);
  }
  
  public void testAHugeNumberOfOrderedInsertsBigK_CheckBalance () {
    testBalance (100000, 100, false, false);
  }
  
  /******************************
    * These do REVERSE inserts *
    ******************************/
  
  public void testAFewReverseInsertsSmallK_CheckBalance () {
    testBalance (10, 5, false, true);
  }
  
  public void testALotOfReverseInsertsSmallK_CheckBalance () {
    testBalance (100, 5, false, true);
  }
  
  public void testAHugeNumberOfReverseInsertsSmallK_CheckBalance () {
    testBalance (100000, 5, false, true);
  }
  
  public void testAFewReverseInsertsBigK_CheckBalance () {
    testBalance (10, 100, false, true);
  }
  
  public void testALotOfReverseInsertsBigK_CheckBalance () {
    testBalance (100, 100, false, true);
  }
  
  public void testAHugeNumberOfReverseInsertsBigK_CheckBalance () {
    testBalance (100000, 100, false, true);
  }
  
}
