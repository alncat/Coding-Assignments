package model.ball;

import util.Dispatcher;

/**
 * Strategy that combines multiple strategies.
 */
public class MultiStrategy implements IUpdateStrategy {
	
	/**
	 * First strategy to combine.
	 */
	private IUpdateStrategy _s1;
	/**
	 * Second strategy to combine.
	 */
	private IUpdateStrategy _s2;
	
	/**
	 * Make a new combined strategy with input strategies.
	 * @param s1, first strategy to combine
	 * @param s2, second strategy to combine
	 */
	public MultiStrategy(IUpdateStrategy s1, IUpdateStrategy s2){
		_s1 = s1;
		_s2 = s2;
	}

	@Override
	/**
	 * Update the ball with both strategies.
	 */
	public void updateState(Ball ball, Dispatcher disp) {
		_s1.updateState(ball, disp);
		_s2.updateState(ball, disp);
	}

	

	@Override
	/**
	 * Initializes both update strategies on the context ball
	 */
	public void init(Ball ball) {
		_s1.init(ball);
		_s2.init(ball);
		
	}

}
