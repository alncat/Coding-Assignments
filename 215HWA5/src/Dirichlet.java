class Dirichlet extends ARandomGenerationAlgorithm<IDoubleVector> {

	private IDoubleVector shapes;
	private Gamma[] dirichletVector;
	private double leftmostStep;
	private int numSteps;

	public Dirichlet(long mySeed, DirichletParam myParam) {
		super(mySeed);

		shapes = myParam.getShapes();
		leftmostStep = myParam.getLeftmostStep();
		numSteps = myParam.getNumSteps();
	}

	public Dirichlet(IPRNG useMe, DirichletParam myParam) {
		super(useMe);

		shapes = myParam.getShapes();
		leftmostStep = myParam.getLeftmostStep();
		numSteps = myParam.getNumSteps();
	}

	/**
	 * Generate another random object
	 */
	public IDoubleVector getNext() {
		IDoubleVector vector = new DenseDoubleVector(shapes.getLength(), 0.0);
		try {
			dirichletVector = new Gamma[shapes.getLength()];
			// places the shapes values into the// parameters
			for (int i = 0; i < shapes.getLength(); i++) {															
				SCGamma fakeGamma =
						new SCGamma(getPRNG(), new GammaParam(shapes.getItem(i), 1.0, leftmostStep,
								numSteps));
				try {
					vector.setItem(i, fakeGamma.getNext()); 
				} catch (OutOfBoundsException e) {
				}
			}
			// the sum of all the elements will be equal to one
			vector.normalize(); 
		} catch (Exception e) {
		}
		return vector;
	}
}
