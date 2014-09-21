import javax.naming.spi.DirStateFactory.Result;

import org.junit.runner.JUnitCore;
import org.junit.runner.notification.Failure;

public class TestRunner {
   public static void main(String[] args) {
      
      // run the test cases
      System.out.println ("Program output:\n*************************");
      //Result result = JUnitCore.runClasses(FactorizationTester.class);
      
      // print out each failure
      System.out.println ("\n\nTest results:\n*************************");
      int counter = 0;
      /*for (Failure failure : result.getFailures()) {
         System.out.print ("Failure number " + (counter++) + ": ");
         System.out.println (failure.toString());
      }*/
      
      if (counter == 0)
         System.out.println ("PASSED EVERYTHING!");
      else 
         System.out.println ("FAILED " + counter + " TEST CASES.");
   }
}  	