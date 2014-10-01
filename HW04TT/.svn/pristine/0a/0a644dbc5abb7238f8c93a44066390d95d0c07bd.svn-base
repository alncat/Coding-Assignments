package factories;

import java.awt.Shape;
import java.awt.geom.Rectangle2D;

public class RectangleShapeFactory implements IShapeFactory {

	//Singleton for the rectangle shape
	public static RectangleShapeFactory Singleton = new RectangleShapeFactory();

	/**
	 * Empty constructor.
	 */
	private RectangleShapeFactory() {}

	/* (non-Javadoc)
	 * @see factories.IShapeFactory#makeShape(double, double, double, double)
	 */
	@Override
	public Shape makeShape(double x, double y, double xScale, double yScale) {
		return new Rectangle2D.Double(x, y, xScale, yScale);
	}

}
