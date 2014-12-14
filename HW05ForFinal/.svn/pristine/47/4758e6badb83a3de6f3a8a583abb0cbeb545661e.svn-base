package model.paint;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Shape;
import java.awt.geom.AffineTransform;

import model.ball.Ball;
import model.shape.EllipseShapeFactory;

/**
 * Creates a composite fish with an eye that stays upright.
 * @author jfe2, lss4, yh20
 *
 */
public class EyedFish0PaintStrategy extends MultiPaintStrategy {
	
	/**
	 * Constructor that calls the MultiPaintStrategy constructor to make a 
	 * composite of a fish strategy and an ellipse strategy
	 */
	public EyedFish0PaintStrategy(){
		// Make eye
		super(makeFish(), makeEye());
	}
	
	/**
	 * Method to make the fish strategy.
	 * @return A Fish0PaintStrategy
	 */
	private static APaintStrategy makeFish(){
		return new Fish0PaintStrategy();
	}
	
	/**
	 * Creates the eye for the composite fish. The strategy for the eye overrides
	 * the paint method for APaintStrategies so that the eye is always black.
	 * @return An UprightPaintStrategy for the eye
	 */
	private static APaintStrategy makeEye(){
		AffineTransform eyeAt = new AffineTransform();
		Shape eyeCircle = EllipseShapeFactory.Singleton.makeShape(0, -.5, 0.3, 0.3);
		UprightShapePaintStrategy eyeShape = new UprightShapePaintStrategy(eyeAt, eyeCircle){
			@Override
			public void paint(Graphics g, Ball host) {
				double scale = host.getRadius();
				super.getAT().setToTranslation(host.getLocation().x, host.getLocation().y);
				super.getAT().scale(scale, scale);
				super.getAT().rotate(Math.atan2(host.getVelocity().y, host.getVelocity().x));
				g.setColor(Color.BLACK);
				paintCfg(g, host);
				paintXfrm(g, host, super.getAT());
				if (Math.abs(Math.atan2(host.getVelocity().y, host.getVelocity().x)) > Math.PI / 2.0) {
					super.getAT().scale(1.0, -1.0);
				}
			}
		
		};
		return eyeShape;
	}
}
