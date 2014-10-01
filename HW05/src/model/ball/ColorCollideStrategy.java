package model.ball;

import util.Dispatcher;
import util.Randomizer;

/**
 * Strategy that causes the ball to change its color whenever it enters a collision.
 * @author jonathanerickson
 *
 */
public class ColorCollideStrategy implements IUpdateStrategy {
	
	@Override
	/**
	 * Initializes the context ball by setting its interact strategy.
	 * @param context
	 * 		The ball which will have the ColorCollideStrategy
	 */
	public void init(Ball context) {
		context.setInteractStrategy(new MultiInteractStrategy(context.getInteractStrategy(), new IInteractStrategy(){
			@Override
			public void interact(Ball ball, Ball target, Dispatcher disp) {
				ball.setColor(Randomizer.Singleton.randomColor());	
			}
			
		}));

	}

	
	@Override
	/**
	 * Doesn't do anything special for updating the state.
	 */
	public void updateState(Ball ball, Dispatcher dispatcher) {
		// no-op

	}

}
