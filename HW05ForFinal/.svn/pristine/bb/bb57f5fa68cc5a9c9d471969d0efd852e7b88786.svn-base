package model.paint;

import java.awt.*;

import model.ball.Ball;

/**
 * Paint strategy that paints a filled square with the Ball's radius. This
 * functionality is duplicated by the RectanglePaintStrategy. The class
 * demonstrates a direct implementation of IPaintStrategy.
 * 
 * @author lss4, yh20, jfe2
 */
public class SquarePaintStrategy implements IPaintStrategy {

	/**
	 * No parameter constructor for the class
	 */
	public SquarePaintStrategy() {
	}

	@Override
	/**
	 * Paints a square on the given graphics context using the color and radius 
	 * provided by the host. 
	 * @param g The Graphics context that will be paint on
	 * @param host The host Ball that the required information will be pulled from.
	 */
	public void paint(Graphics g, Ball host) {
		int halfSide = host.getRadius();
		g.setColor(host.getColor());
		g.fillRect(host.getLocation().x - halfSide, host.getLocation().y
				- halfSide, 2 * halfSide, 2 * halfSide);
	}

	@Override
	/**
	 * By default, do nothing for initialization.
	 */
	public void init(Ball context) {
	}

}
