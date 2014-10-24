import java.util.*;

class Lab8 {
  
  // this simple method randomly shuffles the first 100 items in an array
  static Random shuffler = new Random (324234);  
  static void shuffle (Integer [] list) {
    for (Integer i = 0; i < 100; i++) {
      Integer pos = i + shuffler.nextInt (100 - i);
      Integer temp = list[i];
      list[i] = list[pos];
      list[pos] = temp;
    }
  }
  
  public static void main (String [] args) {
    
    // create a BST
    ChrisBST <Integer> myTree = new ChrisBST <Integer> ();
    
    // create a list haveing 100 ints, and randomly shuffle it
    Integer [] list = new Integer[100];
    for (Integer i = 0; i < 100; i++) {
      list[i] = i;   
    }
    shuffle (list);

    // add all 100 ints into the BST
    for (Integer i : list) {
      myTree.insert (i); 
    }

    // shuffle the list again, and remove the first 50 items from the BST
    shuffle (list);
    for (int i = 0; i < 50; i++) {
      
      // note that we do a double remove here... the second remove should not 
      // cause any problems!
      myTree.remove (list[i]); 
      myTree.remove (list[i]); 
    }
    
    // now, go through the list one more time and make sure the first 50 ints
    // are gone from the BST, and make sure that the last 50 ints are still there
    boolean bad = false;
    for (int i = 0; i < 100; i++) {
      if (myTree.isThere (list[i]) && i < 50) {
        System.out.println ("Bad! I found " + list[i] + " after removal.");
        bad = true;
      } else if (!myTree.isThere (list[i]) && i >= 50) {
        System.out.println ("Bad! I did not find " + list[i] + ".");
        bad = true;
      }
    }
    
    // if we made it through, then print a nice message
    if (!bad)
      System.out.println ("Congrats!  Everything appears to be in order.");
    
  }
}