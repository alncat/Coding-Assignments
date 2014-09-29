package factories;

import java.awt.Point;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.geom.AffineTransform;

public class PolygonFactory implements IShapeFactory {

	// public static PolygonFactory Poly = new PolygonFactory();

	private Polygon poly = new Polygon();
	private AffineTransform at;
	private double scaleFactor = 1.0;

	/**
	 * Constructor that takes in the scale factor and scales
	 * all the points for the polygon generated.
	 * 
	 * @param at
	 * @param scaleFactor
	 * @param pts
	 */
	public PolygonFactory(AffineTransform at, double scaleFactor, Point... pts) {
		this.at = at;
		this.scaleFactor = scaleFactor;
		for (int i = 0; i < pts.length; i++) {
			poly.addPoint((int) pts[i].getX(), (int) pts[i].getY());
		}
	}

	/* (non-Javadoc)
	 * @see factories.IShapeFactory#makeShape(double, double, double, double)
	 */
	public Shape makeShape(double x, double y, double xScale, double yScale) {
		at.setToTranslation(x, y);
		at.scale(xScale * scaleFactor, yScale * scaleFactor); // optional rotation can be added as
																// well
		return at.createTransformedShape(poly);
	}
}
