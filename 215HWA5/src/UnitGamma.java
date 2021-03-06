

class UnitGamma extends ARandomGenerationAlgorithm<Double> {



	private double shape, scale, leftmostStep, r, pdf, area, nextArea, leftArea, 
			randomV, maxpdfValue, xValue, yValue;
	private int numSteps, exitloop, exit;
	private IPRNG z;

	public UnitGamma(long mySeed, GammaParam myParam) {
		super(mySeed);

		shape = myParam.getShape();
		leftmostStep = myParam.getLeftmostStep();
		numSteps = myParam.getNumSteps();
	}

	public UnitGamma(IPRNG useMe, GammaParam myParam) {
		super(useMe);

		shape = myParam.getShape();
		leftmostStep = myParam.getLeftmostStep();
		numSteps = myParam.getNumSteps();
	}

	/**
	 * Generate another random object
	 */
	public Double getNext() {// pdf = step^(k-1) * e^(-step/shape)
		//TODO arraylist of sums and iterate backwards.
		double step = leftmostStep;
		double totalArea = 0.0;
		while (step < leftmostStep * Math.pow(2.0, numSteps)) {// COMPUTES THE TOTAL AREA

			pdf = gamma(step);// height of the box
			area = pdf * step; // base times height for the area of the box
			totalArea += area;
			step *= 2.0;

		}// COMPUTES THE TOTAL AREA
		exit = 0;
		exitloop = 0;
		xValue = genUniform(0.0, totalArea); // GENERATE NUMBER FROM A TO B
		double xValue2 = 0.0;
		step = leftmostStep;
		while (exitloop != 1) {
			while (exit != 1) {// FIND THE STEP THAT I FITS THE LOW AND HIGH
				if (step > leftmostStep * Math.pow(2.0, numSteps)) {
					step = leftmostStep;
				}

				if (step < xValue && xValue < 2.0 * step) {
					exit = 1;
				} else {
					step = 2.0 * step;
				}
			}
			xValue2 = genUniform(step, 2.0 * step);
			maxpdfValue = gamma(step);// FIND THE PDF(X)
			yValue = genUniform(0.0, maxpdfValue);// Step four generate a number from 0 to high pdf

			pdf = gamma(xValue2);
			if (yValue < pdf) {
				exitloop = 1;
			}
		}


		return xValue2;
	}
	
	private double gamma(double x) {
		return Math.pow(x, (shape - 1.0)) * Math.exp(-x);
	}



}
