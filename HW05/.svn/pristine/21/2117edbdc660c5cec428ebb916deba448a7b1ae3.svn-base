package model.paint;

import java.awt.Graphics;
import java.awt.geom.AffineTransform;

import model.ball.Ball;

/**
 * Abstract class that provides default behavior for subclasses that will 
 * decorate another IPaintStrategy to add functionality to that strategy.
 * All this class's methods do is to simply delegate to the decoree. A subclass 
 * should override one or more methods, adding additional processing either 
 * before or after delegating to the decoree. Note that this class cannot be 
 * used by the BallWord system directly as it lacks a no-parameter constructor.
 * @author Yi Hua
 *
 */
public class ADecoratorPaintStrategy extends APaintStrategy {
	
	private APaintStrategy _decoree;

	/**
	 * Constructor for the ADecoratorPaintStrategy. Stores the decoree and 
	 * @param decoree
	 */
	public ADecoratorPaintStrategy(APaintStrategy decoree) {
		super(new AffineTransform());
		_decoree = decoree;
	}
	
	/**
	 * Inits the decoree.
	 */
	@Override
	public void init(Ball host){
		_decoree.init(host);
	}
	
	/**
	 * Paints the decoree.
	 */
	@Override
	public void paint(Graphics g, Ball host){
		_decoree.paint(g, host);
	}

	/**
	 * Paints the decoree with a transform.
	 */
	@Override
	public void paintXfrm(Graphics g, Ball host, AffineTransform at2) {
		_decoree.paint(g, host);

	}

}
