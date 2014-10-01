package model.paint;

import java.awt.Graphics;
import java.awt.Shape;
import java.awt.geom.AffineTransform;

import model.ball.Ball;

/**
 * Paint strategy that keeps a shape upright no matter what direction it is
 * going, by overwriting the paintCfg method.
 * 
 * @author lss4, yh20, jfe2
 *
 */
public class UprightShapePaintStrategy extends ShapePaintStrategy {

	/**
	 * Creates a strategy for a shape to remain upright as it moves
	 * 
	 * @param at
	 *            AffineTransform the Shape is using
	 * @param shape
	 *            the shape to be drawn
	 */
	public UprightShapePaintStrategy(AffineTransform at, Shape shape) {
		super(at, shape);
	}

	/**
	 * Orients the shape so that it is upright
	 */
	@Override
	protected void paintCfg(Graphics g, Ball host) {
		super.paintCfg(g, host);
		if (Math.abs(Math.atan2(host.getVelocity().y, host.getVelocity().x)) > Math.PI / 2.0) {
			super.getAT().scale(1.0, -1.0);
		}
	}

}
