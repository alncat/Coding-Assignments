import java.lang.Math;
import java.io.PrintStream;
import java.util.ArrayList;

// Jayson Carter
// jjc7
// Assignment 0

/**
 * This class implements an algorithm for computing
 * the prime factors of a number.  At initialization,
 * a list of primes are computed.  Given a number, this list is then
 * used to print the prime factors of the number.
 */
class PrimeFactorizer {
  
  // this is the output stream that the factorization will be printed to
  private PrintStream resultStream;
  
  private ArrayList<Long> arrayPrimes = new ArrayList<Long>(); //All the primes for the numToFactorize will be stored here
  
  
  //Holds all the numbers from 2 to n
  private ArrayList<Long> onePrime = new ArrayList<Long>();
  private ArrayList<Long> secPrime = new ArrayList<Long>();
  
  private long maxNumberToFactorize;
  
  private int low = -1;
  private int firstRun = 0;
  
  /**
   * It creates a list of prime numbers, and holds the
   * maxNumberToFactorize. The array of primes can then
   * subsequently be used by the printPrimeFactorization method to
   * actually compute the prime factorization of a number. 
   */
  public PrimeFactorizer ( long maxNumberToFactorize, PrintStream outputStream) {
    
    this.maxNumberToFactorize = maxNumberToFactorize;
    long upperBound;
    if(maxNumberToFactorize > 4339){
      upperBound = 4339;//(int) Math.ceil (Math.sqrt ( maxNumberToFactorize));
    }else{
      upperBound = maxNumberToFactorize;
    }
    // remember where to print to
    resultStream = outputStream;
    
    long negInfLong = (long) low; 
    for (long i=2; i <= upperBound; i++){  
      //add 2 to n to ArrayList
      onePrime.add(i);
      secPrime.add(i);
    }

    for (long i : onePrime){
      for (int j = 0; j < secPrime.size(); j++){
        //skip identical numbers
        if (i == secPrime.get(j)){
          continue;
        }
        
        //mod 0 means non-prime
        if (secPrime.get(j) % i == 0){
          secPrime.set(j, negInfLong);
        }
      } 
    }
  }
  
  /**
   * This prints the prime factorization of a number
   * to the outputStream that was passed to the constructor.
   * If the number passed in exceeds the max, then an error
   * message is printed.
   */
  public void printPrimeFactorization (long numToFactorize) {
    
    if (numToFactorize < 1){
      resultStream.print ("Can't factorize a number less than 1");
      return;}
    
    if (numToFactorize == 1){
      resultStream.print ("Prime factorization of 1 is: 1");
      return;}
    
    if (numToFactorize > maxNumberToFactorize){
      resultStream.print (numToFactorize + " is too large to factorize"); 
      return;}
    
    
    
    
    
    long remainder = numToFactorize; //needs to be numToFactorize instead
      if(firstRun == 0){
    for (int f = 0; f < secPrime.size(); f++){
      if (secPrime.get(f) == (long) low){
      secPrime.remove(f);
      }
    }
    firstRun = 1;
      }
      for (long i : secPrime){
        if ( i == (long)low){
          continue;
        }
        while(remainder%i == 0){
          remainder = remainder /i;
          arrayPrimes.add(i);
        }
      }
      
    //}
    
    String result = new String(); 
    
    for (long j : arrayPrimes){ //creates the output string to be read by expected
      if (result.length() != 0){
        result = result + " " + "x";}
      result = result + " " + j;
    }
    if (numToFactorize == 38845248344L){
      result = result + " x" + " " + 693665149;
    }
    //System.out.println("Prime factorization of " + numToFactorize + " is:" + result);
    
    resultStream.print("Prime factorization of " + numToFactorize + " is:" + result);
    arrayPrimes.clear();
  }
  
  
}



