package model.shape;

import java.awt.Point;
import java.awt.geom.AffineTransform;

/**
 * Shape factory for making fish with open mouths.
 * 
 * @author yh20, lss4, jfe2
 *
 */
public class Fish0ShapeFactory extends PolygonShapeFactory {

	/**
	 * Creates a fish shape with mouth open
	 * 
	 * @param at Affine transform for the fish
	 * @param scaleFactor scaling for the fish
	 * @param pts points to generate the fish
	 */
	public Fish0ShapeFactory() {
		super(new AffineTransform(), 0.33, new Point(3, -1), new Point(2, 0),
				new Point(3, 1), new Point(1, 3), new Point(-2, 3), new Point(
						-3, 1), new Point(-4, 2), new Point(-4, -2), new Point(
						-3, -1), new Point(-2, -3), new Point(1, -3));
	}

}
