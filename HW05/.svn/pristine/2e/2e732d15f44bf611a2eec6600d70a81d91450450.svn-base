package model.shape;

import java.awt.Point;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.geom.AffineTransform;

/**
 * A shape factory for creating polygon shapes
 * 
 * @author lss4, yh20, jfe2
 *
 */
public class PolygonShapeFactory implements IShapeFactory {
	/**
	 * Polygon object that this shape factory makes
	 */
	private Polygon _polygon = new Polygon();
	/**
	 * Affine transform for this shape
	 */
	private AffineTransform _at;
	/**
	 * Scale factor for the polygon
	 */
	double _scaleFactor = 1.0;

	/**
	 * Makes a polygon shape factory for creating a polygon shape.
	 * @param at Affine transform for the polygon
	 * @param scaleFactor scaling for the polygon
	 * @param pts points to generate the polygon
	 */
	public PolygonShapeFactory(AffineTransform at, double scaleFactor,
			Point... pts) {
		// Set Affine transform and scale factor
		_at = at;
		_scaleFactor = scaleFactor;
		// Add points to the polygon
		for (int i = 0; i < pts.length; i++) {
			_polygon.addPoint(pts[i].x, pts[i].y);
		}
	}
	

	@Override
	/**
	 * Makes a shape with the desired x location, y location, xscale, and yscale.
	 */
	public Shape makeShape(double x, double y, double xScale, double yScale) {
		_at.setToTranslation(x, y);
		_at.scale(xScale * _scaleFactor, yScale * _scaleFactor); 
		return _at.createTransformedShape(_polygon);
	}
}
