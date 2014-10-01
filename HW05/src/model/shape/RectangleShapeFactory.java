package model.shape;

import java.awt.Shape;
import java.awt.geom.Rectangle2D;

/**
 * Creates rectangle shapes
 * 
 * @author lss4, yh20, jfe2
 *
 */
public class RectangleShapeFactory implements IShapeFactory {
	/**
	 * Singleton for creating Rectangle objects
	 */
	public static RectangleShapeFactory Singleton = new RectangleShapeFactory();

	/**
	 * Creates a rectangle shape factory
	 */
	public RectangleShapeFactory() {
	};

	/**
	 * Returns an Rectangle shape of given size and location.
	 */
	@Override
	public Shape makeShape(double x, double y, double xScale, double yScale) {
		return new Rectangle2D.Double(x, y, xScale, yScale);
	}

}
