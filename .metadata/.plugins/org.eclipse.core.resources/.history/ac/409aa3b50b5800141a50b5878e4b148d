class Gamma extends ARandomGenerationAlgorithm <Double>{

  private double shape, scale, leftmostStep, r, pdf, randomV, leftArea, area, totalArea, step, nextArea;
  private int numSteps;
  private ARandomGenerationAlgorithm<Double> singlegamma;
  
 public Gamma(long mySeed, GammaParam myParam){
 super (mySeed);
 
 shape = myParam.getShape();
    scale = myParam.getScale();
    leftmostStep = myParam.getLeftmostStep();
    numSteps = myParam.getNumSteps();
 

 }
 
 public Gamma(IPRNG useMe, GammaParam myParam){
 super(useMe);
 
 shape = myParam.getShape();
    scale = myParam.getScale();
    leftmostStep = myParam.getLeftmostStep();
    numSteps = myParam.getNumSteps();
 

 }

 
 /**
   * Generate another random object
   */
 public Double getNext (){

       int increment = 0; //Tells how many gammas with shapes of one will be created
       while (shape > 1.0){//Find out how many Gammas need to be created
       
         increment++;
         shape = shape - 1.0;
         
       }
       
       double xTotal = 0.0; //Keeps track of the sum of the gammas
       
       SCUnitGamma fakeunit = new SCUnitGamma(getPRNG(), new GammaParam(1.0, 1.0, leftmostStep, numSteps));
       for (int i = 0; i < increment; i++){//Sum all the Gamma's with shape values of 1's
       
       double xVariable = fakeunit.getNext();
       xTotal = xTotal + xVariable;
       }
       SCUnitGamma fakelastunit = new SCUnitGamma(getPRNG(), new GammaParam(shape, 1.0, leftmostStep, numSteps));
       double xVariable = fakelastunit.getNext();
       xTotal = xTotal + xVariable;
       
       return scale*xTotal;

 }
  

 
}