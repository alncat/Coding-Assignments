package strategies.paint;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;

import balls.Ball;

public class ShapePaintStrategy extends APaintStrategy {

	public ShapePaintStrategy(AffineTransform a) {
		super(a);
	}

	private Shape shape;

	/**
	 * 
	 * Setter for shape and calls a super on
	 * the AffineTransform object
	 * 
	 * @param at
	 * @param makeShape
	 */
	public ShapePaintStrategy(AffineTransform at, Shape makeShape) {
		super(at);
		shape = makeShape;
	}

	//
	// /**
	// * Paints a square on the given graphics context using the color and radius
	// * provided by the host.
	// * @param g The Graphics context that will be paint on
	// * @param host The host Ball that the required information will be pulled from.
	// */
	// public void paint(Graphics g, Ball host) {
	// int halfSide = host.getRadius();
	// g.setColor(host.getColor());
	// g.fillRect(host.getXLoc()-halfSide, host.getYLoc()-halfSide, 2*halfSide, 2*halfSide);
	// }

	/* (non-Javadoc)
	 * @see strategies.paint.APaintStrategy#paintXfrm(java.awt.Graphics, balls.Ball, java.awt.geom.AffineTransform)
	 */
	public void paintXfrm(Graphics g, Ball host, AffineTransform at) {
		((Graphics2D) g).fill(at.createTransformedShape(shape));
	}



}
