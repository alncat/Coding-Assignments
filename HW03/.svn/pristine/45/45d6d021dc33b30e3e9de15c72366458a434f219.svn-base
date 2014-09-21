package balls;

import java.awt.Color;
import java.awt.Component;
import java.awt.Point;

/**
 * A Ball the moves within straight lines and bounces off walls. The size of the
 * ball varies between half of the radius parameter passed into the constructor
 * and twice the radius passed in to the constructor.
 * 
 * @author Jake Kornblau, Jayson Carter
 *
 */
public class SizeChangingBall extends ABall {
	/** The maximum radius that the ball can have. */
	private int maxRadius;

	/** The minimum radius that the ball can have. */
	private int minRadius;

	/** How much the radius should change by. */
	private int radiusChange;

	/**
	 * Constructor for the SizeChangingBall object
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
	public SizeChangingBall(Point loc, int radius, Point velocity, Color color,
			Component canvas) {
		super(radius, color, velocity, loc, canvas);
		maxRadius = 2 * radius;
		minRadius = radius / 2;
		radiusChange = 1;
	}

	/**
	 * Updates the state of the ball by changing the size of the radius.
	 */
	@Override
	public void updateState() {
		/*
		 * Gets the radius of the ball. Changes the radius as specified by
		 * radiusChange.
		 */
		int radius = super.getRadius();
		super.setRadius(radius + radiusChange);

		/* Updates radiusChange based on the current radius of the ball. */
		if (radius + radiusChange == maxRadius) {
			radiusChange = -1;
		} else if (radius + radiusChange == minRadius) {
			radiusChange = 1;
		}
	}
}
