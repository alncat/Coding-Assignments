package controller;

import java.awt.Component;
import java.awt.Graphics;

/**
 * The interface of the adapter from the view to the model that enables the view
 * to talk to the model.
 */
public interface IView2ModelAdapter {

	/**
	 * Tells the model to repaint.
	 * 
	 * @param g
	 *            The graphics object to be repainted onto.
	 */
	public void paint(Graphics g);

	/**
	 * Adds a ball of the specified class.
	 * 
	 * @param className
	 *            the name of the ball, including the package name
	 * @param canvas
	 *            the Component that will contain the ball.
	 */
	public void addBall(String className, Component canvas);

	/**
	 * Removes all balls
	 */
	public void clearBalls();

	/** No-op implementation of IView2ModelAdapter */
	public static final IView2ModelAdapter NULL_OBJECT = new IView2ModelAdapter() {
		@Override
		public void paint(Graphics g) {
		}

		@Override
		public void addBall(String className, Component canvas) {

		}

		@Override
		public void clearBalls() {

		}
	};

}
