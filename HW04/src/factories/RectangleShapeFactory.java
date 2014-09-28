package factories;

import java.awt.Shape;
import java.awt.geom.Rectangle2D;

public class RectangleShapeFactory implements IShapeFactory {

	public static RectangleShapeFactory Singleton = new RectangleShapeFactory();

	private RectangleShapeFactory() {}

	@Override
	public Shape makeShape(double x, double y, double xScale, double yScale) {
		// TODO Auto-generated method stub
		return new Rectangle2D.Double(x, y, xScale, yScale);
	}

}
