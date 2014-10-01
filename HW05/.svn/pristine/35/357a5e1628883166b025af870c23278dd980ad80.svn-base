package model.ball;

import util.Dispatcher;

/**
 * Updates a ball's radius as if it is breathing.
 */
public class BreathingStrategy implements IUpdateStrategy {
	
	/**
	 * How fast the radius changes.
	 * Default set to 3 pixels per update.
	 */
	private int changeSpeed = 3;
	/**
	 * Boolean defining if the radius is increasing.
	 * Default set to increasing.
	 */
	private boolean increasing = true;
		
	/**
	 * Update the radius of the ball - increasing until it exceeds max radius 
	 * limit, then decreasing until it exceeds the min radius limit.
	 * @param		ball - the ball on which the update happens.
	 */
	public void updateState(Ball ball, Dispatcher disp) {
		// Update a ball's radius
		int radius = ball.getRadius();
		if (increasing){
			if (radius <= Ball.getMaxRadius()){
				ball.setRadius(radius+changeSpeed);
			} else {
				// Too big, start decreasing
				increasing = false;
				ball.setRadius(radius-changeSpeed);
			}
		} else {
			if (radius >= Ball.getMinRadius()){
				ball.setRadius(radius-changeSpeed);
			} else {
				// Too small, start increasing
				increasing = true;
				ball.setRadius(radius+changeSpeed);
			}
		}
	}

	@Override
	public void init(Ball ball) {
		// TODO Auto-generated method stub
		
	}

}
