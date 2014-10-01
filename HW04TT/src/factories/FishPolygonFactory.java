package factories;

import java.awt.Point;
import java.awt.geom.AffineTransform;

public class FishPolygonFactory extends PolygonFactory {

	//generates a singleton for the fish polygon factor
	public static FishPolygonFactory Singleton = new FishPolygonFactory();

	/**
	 * Private construct that sets up the dimensions of the
	 * of the fish to be generated
	 */
	private FishPolygonFactory() {
		super(new AffineTransform(), 0.5, new Point[] {new Point(5, 0), new Point(3, -1), 
			new Point(0, 1), new Point(0, -1), new Point(3, 1), new Point(4, 0)});
	}

	/**
	 * Constructor produces a fish from the given points and scales
	 * each point to change the sizes of fish generated.
	 * 
	 * @param at
	 * @param scaleFactor
	 * @param pts
	 */
	public FishPolygonFactory(AffineTransform at, double scaleFactor, Point[] pts) {
		super(at, scaleFactor, pts);
	}

}
