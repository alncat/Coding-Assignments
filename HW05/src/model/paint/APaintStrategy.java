package model.paint;

import java.awt.Graphics;
import java.awt.geom.AffineTransform;

import model.ball.Ball;

/**
 * Abstract class for the various ways to paint shapes/graphics.
 * 
 * @author lss4, yh20, jef2
 *
 */
public abstract class APaintStrategy implements IPaintStrategy {

	/**
	 * The AffineTransform used for the painting strategy.
	 */
	protected AffineTransform at;

	/**
	 * Constructs APaintStrategy with the input AffineTransform
	 * 
	 * @param init_at
	 *            AffineTransform for this APaintStrategy
	 */
	public APaintStrategy(AffineTransform init_at) {
		at = init_at;
	}

	/**
	 * Rotates, scales, then translates the item to be painted. Then, the item
	 * is colored, configured, and transformed to complete the painting.
	 */
	//@Override
	public void paint(Ball host, Graphics g) {
		double scale = host.getRadius();
		at.setToTranslation(host.getLocation().x, host.getLocation().y);
		at.scale(scale, scale);
		at.rotate(Math.atan2(host.getVelocity().y, host.getVelocity().x));
		g.setColor(host.getColor());
		paintCfg(g, host);
		paintXfrm(g, host, at);

	}

	/**
	 * Paints the graphics
	 * 
	 * @param g
	 *            graphics object to paint in
	 * @param host
	 *            the ball that is being transformed
	 * @param at2
	 *            secondary affine transformation to use
	 */
	public abstract void paintXfrm(Graphics g, Ball host, AffineTransform at2);

	/**
	 * Additional processing for the paint method process before the final
	 * transformations are performed.
	 * 
	 * @param g
	 *            the graphics object
	 * @param host
	 *            the Ball that is being transformed
	 */
	protected void paintCfg(Graphics g, Ball host) {
		/* Defaults to do nothing */

	}

	/**
	 * Abstract init does nothing.
	 */
	public void init(Ball host) {
		/* Default does not need initialization */

	}

	/**
	 * Get the AffineTransform
	 * 
	 * @return the transform for this paint strategy
	 */
	protected AffineTransform getAT() {
		return at;
	}

}
