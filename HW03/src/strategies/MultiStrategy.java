package strategies;

import balls.Ball;

public class MultiStrategy implements IUpdateStrategy{
	
	/**
	 * Holders for two strategies that we are combining
	 */
	private IUpdateStrategy s1;
	private IUpdateStrategy s2;
	
	/**
	 * Constructor for MulitStrategy.
	 */
	public MultiStrategy(IUpdateStrategy ins1, IUpdateStrategy ins2){
		s1 = ins1;
		s2 = ins2;
	}
	
	/**
	 * Update the state recursively on both strategies that made up the multiStrategy.
	 * @param context - The ball we are implementing our strategy on
	 */
	public void updateState(Ball context) {
		s1.updateState(context);
		s2.updateState(context);
	}

}
