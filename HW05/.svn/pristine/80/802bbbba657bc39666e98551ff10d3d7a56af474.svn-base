package model.ball;

import util.Dispatcher;

/**
 * Update strategy that causes the ball to "kill" a target ball upon collision
 *
 */
public class KillStrategy implements  IUpdateStrategy {

	/**
	 * Initializes the context ball by setting its interact strategy
	 * @param context
	 * 		The context ball
	 */
	public void init(Ball context) {
		context.setInteractStrategy(new MultiInteractStrategy(context.getInteractStrategy(), new IInteractStrategy(){
			@Override
			public void interact(Ball context, Ball target, Dispatcher disp) {
				disp.deleteObserver(target);		
			}
			
		}));
	}
	@Override
	/**
	 * No-op updateState
	 */
	public void updateState(Ball context, Dispatcher disp){
		// No-op
	}

}




