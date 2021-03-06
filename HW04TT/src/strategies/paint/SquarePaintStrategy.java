package strategies.paint;

/**
 * package paint;
 * 
 * public class SquarePaintStrategy {
 * 
 * public SquarePaintStrategy() {  }
 * 
 * }
 */

import java.awt.*;

import balls.Ball;

/**
 * Paint strategy that paints a filled square with the Ball's radius. This functionality is
 * duplicated by the RectanglePaintStrategy. The class demonstrates a direct implementation of
 * IPaintStrategy.
 */
public class SquarePaintStrategy implements IPaintStrategy {

	/**
	 * No parameter constructor for the class
	 */
	public SquarePaintStrategy() {}

	/**
	 * Paints a square on the given graphics context using the color and radius provided by the
	 * host.
	 * 
	 * @param g The Graphics context that will be paint on
	 * @param host The host Ball that the required information will be pulled from.
	 */
	public void paint(Graphics g, Ball host) {
		int halfSide = host.getRadius();
		g.setColor(host.getColor());
		g.fillRect(host.getXLoc() - halfSide, host.getYLoc() - halfSide, 2 * halfSide, 2 * halfSide);
	}

	/**
	 * By default, do nothing for initialization.
	 */
	public void init(Ball context) {}
}
