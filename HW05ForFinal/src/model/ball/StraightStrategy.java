package model.ball;

import util.Dispatcher;

/**
 * A strategy that makes a ball go in straight lines.
 */
public class StraightStrategy implements IUpdateStrategy {

	/**
	 * Makes a straight line strategy for movement
	 */
	public StraightStrategy(){	
	}
	
	/**
	 * Makes the ball go in straight lines.
	 */
	@Override
	public void updateState(Ball ball, Dispatcher disp) {
	}

	@Override
	/**
	 * No-op initialization
	 */
	public void init(Ball ball) {
		// no-op
		
	}

}
