package factories;

import java.awt.Point;
import java.awt.geom.AffineTransform;

public class Fish1PolygonFactory extends PolygonFactory {

	public static Fish1PolygonFactory Singleton = new Fish1PolygonFactory();

	private Fish1PolygonFactory() {
		super(new AffineTransform(), 0.5, new Point[] {new Point(1, 0), new Point(2, 1),
				new Point(5, -1), new Point(5, 1), new Point(2, -1), new Point(1, 0)});
	}

	public Fish1PolygonFactory(AffineTransform at, double scaleFactor, Point[] pts) {
		super(at, scaleFactor, pts);
	}

}
