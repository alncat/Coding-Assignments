package model.shape;

import java.awt.Shape;

/**
 * Interface for a factory that makes shapes
 * 
 * @author lss4, jfe2, yh20
 *
 */
public interface IShapeFactory {
	/**
	 * Method makes a Shape instance with the input starting location
	 * 
	 * @param x
	 *            coordinate of x start point
	 * @param y
	 *            coordinate of y start point
	 * @param xScale
	 *            scale of the x (width in some cases)
	 * @param yScale
	 *            scale of the y (height in some cases)
	 * @return a shape object with the input locations and scale
	 */
	public Shape makeShape(double x, double y, double xScale, double yScale);
}
