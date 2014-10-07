package model.ball;

import util.Dispatcher;

/**
 *	This strategy lets a ball change its strategy from one kind to another.
 */
public class SwitcherStrategy implements IUpdateStrategy {
	/**
	 * The actual strategy that a ball with be updated with.
	 * Initially set to the StraightStrategy.
	 */
	IUpdateStrategy _strategy = new StraightStrategy();
	int init_flag = 0;

	@Override
	/**
	 * Update the ball's state with set strategy.
	 */
	public void updateState(Ball ball, Dispatcher disp) {
		if(init_flag == 0){
			_strategy.init(ball);
			init_flag = 1;
		}
		_strategy.updateState(ball, disp);
	}
	
	/**
	 * Switch the update strategy to a new strategy.
	 * @param newStgy, new strategy with which the ball with be updated.
	 */
	public void setStrategy(IUpdateStrategy newStgy){
		_strategy = newStgy;
		init_flag = 0;
		
	}

	@Override
	/**
	 * Initializes the strategy on the ball, and sets a flag to say that
	 * it has been initialized
	 */
	public void init(Ball ball) {
		_strategy.init(ball);
		init_flag = 1;
		
	}

	

}
