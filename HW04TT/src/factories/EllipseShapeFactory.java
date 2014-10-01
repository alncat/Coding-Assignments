package factories;

import java.awt.Shape;
import java.awt.geom.Ellipse2D;

public class EllipseShapeFactory implements IShapeFactory {

	//generates a static singleton for the ellipse shape factory
	public static EllipseShapeFactory Singleton = new EllipseShapeFactory();

	/**
	 * Empty constructor.
	 */
	private EllipseShapeFactory() {}

	/* (non-Javadoc)
	 * @see factories.IShapeFactory#makeShape(double, double, double, double)
	 */
	@Override
	public Shape makeShape(double x, double y, double xScale, double yScale) {
		return new Ellipse2D.Double(x, y, xScale, yScale);
	}


}
