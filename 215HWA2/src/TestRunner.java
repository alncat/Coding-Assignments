import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.junit.runner.Request;
import org.junit.runner.notification.RunListener;
import org.junit.runner.Description;

// this class is responsible for running all of the test cases
public class TestRunner {
   
   // the default is to run ALL of the test cases... if you want to run just
   // ONE of the test cases, set this to false, and then...
   private static boolean runAllTestCases = true;
   
   // put the name of the test in here, if you just want to run one specific test
   // (also, you need to set runAllTestCases to be false!)
   private static String specificTestCaseToRun = "testSingleDenseSetSize1";
   
   public static void main (String[] args) {
      
      // first we set up the test cases
      Request whatToRun = null;
      if (runAllTestCases)
         // we can choose to run ALL of them
         whatToRun = Request.aClass (DoubleVectorTester.class);
      else
         // or a specific one
         whatToRun = Request.method (DoubleVectorTester.class, specificTestCaseToRun);
         
      // now run them
      System.out.println ("RUNNING THE TEST CASES\n*************************\n");
      JUnitCore myJUnit = new JUnitCore ();
      myJUnit.addListener (new MyListener ());
      Result result = myJUnit.run (whatToRun);
      
      // print out each failure
      System.out.println ("\n\nTEST RESULTS\n*************************\n");
      int counter = 0;
      for (Failure failure : result.getFailures()) {
         System.out.println ("Failure number " + (counter++) + " on test " + failure.getTestHeader () + ":");
         
         // if there was an exception, print it
         if (failure.getMessage() == null)
           System.out.println (failure.getTrace() + "\n");
           
         // otherwise, print the error message
         else
           System.out.println (failure.getMessage() + "\n");
      }
      
      // report the final result
      if (counter == 0) {
         System.out.println ("PASSED EVERYTHING!");
         if (!runAllTestCases) {
           System.out.println ("\tBut remember, you just ran " + specificTestCaseToRun + ".");   
         }
      } else {
         System.out.println ("FAILED " + counter + " TEST CASES.");
      }
   }
}   

// this lis
class MyListener extends RunListener {
    
    public void testStarted (Description description) {
      System.out.print ("*****Starting " + description + "..."); 
    }
    
    public void testFinished (Description description) {
      System.out.println ("finished " + description + ".\n");
    } 
}
