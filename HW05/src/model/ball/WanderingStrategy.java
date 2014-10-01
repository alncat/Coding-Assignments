package model.ball;

import java.awt.Point;

import util.Dispatcher;
import util.Randomizer;

/**
 * A strategy that has the ball wander around the screen in a random fashion.
 */
public class WanderingStrategy implements IUpdateStrategy {

	/**
	 * A strategy that changes a ball's velocity by a small random amount.
	 */
	public void updateState(Ball ball, Dispatcher disp) {
		int deltax = Randomizer.Singleton.randomInt(-5,5);
		int deltay = Randomizer.Singleton.randomInt(-5,5);
		ball.setVelocity(new Point(ball.getVelocity().x+deltax, 
				ball.getVelocity().y+deltay));
	}

	@Override
	public void init(Ball ball) {
		// TODO Auto-generated method stub
		
	}

	


}
