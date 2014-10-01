package model.ball;

import java.awt.Point;

import util.Dispatcher;
import util.Randomizer;

/**
 * Updates the velocity direction of the ball so that it runs in a curved path.
 */
public class CurveStrategy implements IUpdateStrategy {
	
	/**
	 * Angle the ball curves with.
	 */
	private double angle = Randomizer.Singleton.randomDouble(0.001, 1.5); 

	@Override
	/**
	 * Update the velocity of ball by changing the angle of velocity.
	 * @param 		ball whose velocity is updated.
	 */
	public void updateState(Ball ball, Dispatcher disp) {
		// Turn velocity by angle clockwise
		int old_veloX = ball.getVelocity().x;
		int old_veloY = ball.getVelocity().y;
		int new_veloX = (int) Math.round(old_veloX * Math.cos(angle) - 
							old_veloY * Math.sin(angle)); 
		int new_veloY = (int) Math.round(old_veloX * Math.sin(angle) + 
							old_veloY * Math.cos(angle));
		ball.setVelocity(new Point(new_veloX, new_veloY));
	}

	@Override
	public void init(Ball ball) {
		// TODO Auto-generated method stub
		
	}

}
