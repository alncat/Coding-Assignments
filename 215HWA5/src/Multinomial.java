class Multinomial extends ARandomGenerationAlgorithm<IDoubleVector> {

	private int numTrials;
	private IDoubleVector probs;
	private IPRNG z;

	private ARandomGenerationAlgorithm<IDoubleVector> multinomialVector;

	public Multinomial(int mySeed, MultinomialParam myParam) {
		super(mySeed);

		numTrials = myParam.getNumTrials();
		probs = myParam.getProbs();
		z = new PRNG(mySeed);

		//multinomialVector = new Multinomial(mySeed,new MultinomialParam(numTrials, probs));
	}

	public Multinomial(IPRNG useMe, MultinomialParam myParam) {
		super(useMe);

		numTrials = myParam.getNumTrials();
		probs = myParam.getProbs();
		z = useMe;

		//multinomialVector = new Multinomial(useMe, new MultinomialParam(numTrials, probs));
	}

	/**
	 * Generate another random object
	 */
	public IDoubleVector getNext() {
		//return multinomialVector.getNext();
		 multinomialVector = new Multinomial(z, new MultinomialParam(numTrials, probs));
		return (IDoubleVector) multinomialVector;
	}



}
