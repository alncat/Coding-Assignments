package model.ball;

import model.command.IBallCmd;
import util.Dispatcher;
import util.Randomizer;

/**
 * ColorChangerStrategy creates a new ball whose InteractStrategy is to 
 * 
 * @author jfe2, jjc7
 *
 */
public class ColorChangerStrategy implements IUpdateStrategy {

	int count = 0;
	@Override
	/**
	 * Initializes the context ball to have an interact strategy that changes the color of any ball it interacts with.
	 * @param context
	 * 		The context ball
	 */
	public void init(Ball context) {
			context.setInteractStrategy(new MultiInteractStrategy(context.getInteractStrategy(), new IInteractStrategy(){
				@Override
				public void interact(Ball ball, Ball target, Dispatcher disp) {
					target.setColor(context.getColor());
				}
				
			}));

	}

		

	@Override
	/**
	 * Updates the state of the context ball
	 * @param context
	 * 		The context ball
	 * @param dispatcher
	 * 		The dispatcher
	 */
	public void updateState(Ball context, Dispatcher dispatcher) {
		if (count == 100){
			context.setColor(Randomizer.Singleton.randomColor());
			dispatcher.notifyAll(new IBallCmd(){
				
				public void apply(Ball other, Dispatcher disp){
					if (context!=other){
						context.interactWith(other, disp);
					}
				}
			});
			count = 0;
		}
		else{
			count += 1;
		}

	}

}
