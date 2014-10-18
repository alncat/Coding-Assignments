 class Dirichlet extends ARandomGenerationAlgorithm <IDoubleVector>{

  private IDoubleVector shapes;
  private Gamma[] dirichletVector;
  private double leftmostStep;
  private int  numSteps;
  
  

  
  //private ARandomGenerationAlgorithm<IDoubleVector> dirichletVector;
  
  public Dirichlet(long mySeed, DirichletParam myParam){
    super(mySeed);
    
    shapes = myParam.getShapes();
    leftmostStep = myParam.getLeftmostStep();
    numSteps = myParam.getNumSteps();
  
    
  }
  
 
  public Dirichlet(IPRNG useMe, DirichletParam myParam){
    super(useMe);
  
  
    shapes = myParam.getShapes();
    leftmostStep = myParam.getLeftmostStep();
    numSteps = myParam.getNumSteps();
  
    
  }
  

  
  /**
   * Generate another random object
   */
  public IDoubleVector getNext (){
    
    IDoubleVector vector = new DenseDoubleVector(shapes.getLength(), 0.0);
    
    
    
    try {

    
    
    
      
      dirichletVector = new Gamma[shapes.getLength()];
      
    for ( int i = 0; i < shapes.getLength(); i++ ){//places the shapes values into the parameters
      
      SCGamma fakeGamma = new SCGamma(getPRNG(), new GammaParam(shapes.getItem(i), 1.0, leftmostStep, numSteps));
      try {
      vector.setItem(i, fakeGamma.getNext()); //fakeGamma times shape does not work!!!!!!
      }
      catch (OutOfBoundsException e) {
        
      }
    
    }
    
    vector.normalize(); //the sum of all the elements will be equal to one
    

    } catch (Exception e) {
       
    }
  return vector;
 }
  
  
}