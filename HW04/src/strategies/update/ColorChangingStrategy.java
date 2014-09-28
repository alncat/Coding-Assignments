package strategies.update;

import util.Randomizer;
import balls.Ball;

public class ColorChangingStrategy implements IUpdateStrategy {

	private int frameCounter = 0;

	/**
	 * Updates the ball's state by changing its color after a fixed interval
	 * 
	 * @param context - The ball we use
	 */
	@Override
	public void updateState(Ball context) {

		if (frameCounter >= 30) {
			context.setColor(Randomizer.Singleton.randomColor());
			frameCounter = 0;
		} else {
			frameCounter++;
		}
	}



}
