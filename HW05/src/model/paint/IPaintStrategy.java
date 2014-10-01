package model.paint;

import java.awt.Graphics;

import model.ball.Ball;

/**
 * Strategy for different ways to paint images.
 * 
 * @author lss4, yh20, jfe2
 *
 */
public interface IPaintStrategy {

	/**
	 * Paint the ball using the paint strategy.
	 * 
	 * @param g
	 *            Graphics object to paint on
	 * @param host
	 *            the object to paint
	 */
	public void paint(Graphics g, Ball host);

	/**
	 * Initialize the object to paint.
	 * 
	 * @param host
	 *            object to paint
	 */
	public void init(Ball host);
}
