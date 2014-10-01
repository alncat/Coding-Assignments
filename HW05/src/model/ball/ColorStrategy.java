package model.ball;

import util.Dispatcher;
import util.Randomizer;

/**
 * Update the color of the ball to a random color.
 */
public class ColorStrategy implements IUpdateStrategy {

	@Override
	/**
	 * Update the color of the ball to a random color.
	 * @param		ball, the ball whose color changes
	 */
	public void updateState(Ball ball, Dispatcher disp) {
		ball.setColor(Randomizer.Singleton.randomColor());
	}

	@Override
	public void init(Ball ball) {
		// TODO Auto-generated method stub
		
	}

}
