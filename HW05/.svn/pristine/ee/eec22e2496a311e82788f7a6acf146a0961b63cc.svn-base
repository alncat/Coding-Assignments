package model.paint;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import model.ball.Ball;

/**
 * Strategy for painting a ball.
 * 
 * @author lss4, yh20, jfe2
 *
 */
public class BallPaintStrategy implements IPaintStrategy {

	/**
	 * Paints a ball with the same radius and location as the input host ball.
	 */
	@Override
	public void paint(Graphics g, Ball host) {
		Point location = host.getLocation();
		Color color = host.getColor();
		int radius = host.getRadius();
		g.setColor(color);
		g.fillOval(location.x - radius, location.y - radius, 2 * radius,
				2 * radius);

	}

	/**
	 * No extra initialization.
	 */
	@Override
	public void init(Ball host) {
		/* BallPaintStrategy needs no extra intialization */

	}

}
