package balls;

import java.awt.Color;
import java.awt.Component;
import java.awt.Point;

import util.Randomizer;

/**
 * A Ball the moves within straight lines and bounces off walls. The color of
 * the ball changes every time the ball is repainted.
 * 
 * @author jkornblau
 *
 */
public class ColorChangingBall extends ABall {

	/**
	 * Constructor for the ColorChangingBall object
	 * 
	 * @param radius
	 *            The radius of the ball
	 * @param color
	 *            The color of the ball
	 * @param velocity
	 *            The velocity of the ball where velocity.x is the velocity in
	 *            the x direction and velocity.y is the velocity in the y
	 *            direction.
	 * @param loc
	 *            The starting location for the Ball
	 * @param canvas
	 *            The Component that the Ball will exist within.
	 */
	public ColorChangingBall(Point loc, int radius, Point velocity,
			Color color, Component canvas) {
		super(radius, color, velocity, loc, canvas);
	}

	/**
	 * Updates the state of the ball by randomly changing the color of the ball.
	 */
	@Override
	public void updateState() {
		super.setColor(Randomizer.Singleton.randomColor());
	}
}
