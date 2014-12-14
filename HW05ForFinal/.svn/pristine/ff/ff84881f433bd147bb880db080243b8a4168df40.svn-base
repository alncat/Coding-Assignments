package model.ball;

import util.Dispatcher;

/**
 * Composite class to combine IInteractStrategies.
 * @author jonathanerickson
 *
 */
public class MultiInteractStrategy implements IInteractStrategy {

	/*
	 * First strategy to combine.
	 */
	private IInteractStrategy _s1;
	/*
	 * Second strategy to combine.
	 */
	private IInteractStrategy _s2;
	
	/**
	 * Make a new combined strategy with input strategies.
	 * @param s1, first strategy to combine
	 * @param s2, second strategy to combine
	 */
	public MultiInteractStrategy(IInteractStrategy s1, IInteractStrategy s2){
		_s1 = s1;
		_s2 = s2;
	}

	@Override
	/**
	 * Update the ball with both strategies.
	 */
	public void interact(Ball context, Ball target, Dispatcher disp) {
		_s1.interact(context, target, disp);
		_s2.interact(context, target, disp);
	}


}
