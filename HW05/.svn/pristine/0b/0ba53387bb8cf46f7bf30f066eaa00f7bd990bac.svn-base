package model.shape;

import java.awt.Shape;
import java.awt.geom.Ellipse2D;

/**
 * Factory for making ellipses
 * 
 * @author lss4, yh20, jfe2
 *
 */
public class EllipseShapeFactory implements IShapeFactory {
	/**
	 * A singleton for making ellipse shapes
	 */
	public static EllipseShapeFactory Singleton = new EllipseShapeFactory();

	/**
	 * Constructs an EllipseShapeFactory to create ellipses.
	 */
	public EllipseShapeFactory() {

	}

	@Override
	/**
	 * Returns an ellipse shape with given position and scale
	 */
	public Shape makeShape(double x, double y, double xScale, double yScale) {
		return new Ellipse2D.Double(x, y, xScale, yScale);
	}

}
