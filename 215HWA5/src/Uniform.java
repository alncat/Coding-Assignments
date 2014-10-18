import java.util.*;

class Uniform extends ARandomGenerationAlgorithm <Double>{

  private double low, high, scale, bz;
  
  private IPRNG z;
  
  
  
  
  public Uniform (long mySeed, UniformParam myParam){
  super (mySeed);
  low = myParam.getLow();
  high = myParam.getHigh();
  z = new PRNG (mySeed);
  
  }
  
  
  public Uniform (IPRNG useMe, UniformParam myParam){
  super(useMe);
  low = myParam.getLow();
  high = myParam.getHigh();
  z = useMe;
  }
  
  
  /**
   * Generate another random object
   */
  public Double getNext (){
    bz = z.next();
   scale = (high - low)* bz + low;
    
    return scale;
  }
  
  /**
   * Resets the sequence of random objects that are created.  The net effect
   * is that if we create the IRandomGenerationAlgorithm object, 
   * then call getNext a bunch of times, and then call startOver (), then 
   * call getNext a bunch of times, we will get exactly the same sequence 
   * of random values the second time around.
   */
  public void startOver (){
    
   z.startOver();
  return;
  }
  
  /**
   * Return the next double value between 0.0 and 1.0
   */
  public double next(){
    bz = z.next();
  return bz;
  }
  

}