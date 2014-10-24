
// a simple binary search tree implementation
public class ChrisBST <T extends Comparable <T>> {
  
  // the root is null at initialization, indicating an empty tree
  private Node root = null;
    
  // this is the node type that the tree is constructed from
  private class Node {
    
    // myData is the actual data contained in the tree
    private T myData = null;
    
    // and here are the two subtrees
    private Node leftSubtree = null;
    private Node rightSubtree = null;
    
    // create a node with no children, holding only the indicated data
    public Node (T initWithMe) {
      myData = initWithMe;  
    }
    
    // insert the object into the node (or the left or right subtree)
    public void insert (T insertMe) {
      
      // if the guy to insert is less than the current value, 
      // then the insert needs to go to the left
      if (myData.compareTo (insertMe) >= 0) {
        
        // if the left subtree is null, then create a new node
        // that simply holds the data to insert
        if (leftSubtree == null)
          leftSubtree = new Node (insertMe);
        
        // if the left is not null, then recursively insert
        else
          leftSubtree.insert (insertMe);
        
        // the guy to insert meets or exceeds the current value,
        // so do the same thing, but with the right subtree
      } else {
        if (rightSubtree == null)
          rightSubtree = new Node (insertMe);
        else
          rightSubtree.insert (insertMe);
      }
    } 
    
    // check if the particular object is in the node, or one of its subtrees
    public boolean isThere (T findMe) {
      
      // if it is in this node, we are done!
      if (myData.compareTo (findMe) == 0) {
        return true;
        
      // if myData exceeds findMe, then go to the left
      } else if (myData.compareTo (findMe) > 0) {
        return (leftSubtree != null) &&
          leftSubtree.isThere (findMe);
        
      // otherwise, go to the right
      } else {
        return (rightSubtree != null) &&
          rightSubtree.isThere (findMe);
      }
    }
    
    // your code here!
    
  }
  
  public void remove (T removeMe) {
    // your code here!
    if(isThere(removeMe)){
        
          if (removeMe.compareTo (removeMe) == 0) {
              removeMe = null;
              insert((T) removeMe.rightSubtree);
              insert((T) root.leftSubtree);
              //removeMe.
        return;
        
      // if myData exceeds findMe, then go to the left
      } else if (removeMe.compareTo (removeMe) > 0 && myData.leftSubtree != null) {
        
          leftSubtree.remove (removeMe);
         
        
      // otherwise, go to the right
      } else {
          rightSubtree.remove (removeMe);
      }
        
    }
  }
  
  // returns true if the object findMe is in the BST
  public boolean isThere (T findMe) {
    
    // only look for it if we have a non-empty tree
    if (root != null) {
      return root.isThere (findMe);
      
    // if the tree is empty, it can't be there
    } else {
      return false;
    }
  }
  
  // insert the object insertMe into the BST
  public void insert (T insertMe) {
    
    // only insert into a non-null tree
    if (root != null) {
      root.insert (insertMe);
      
    // if the tree is null, then create a new root holding insertMe
    } else {
      root = new Node (insertMe);
    }
  }
}