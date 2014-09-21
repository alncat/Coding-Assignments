package shape;

import java.awt.Graphics;

public class Circle extends AShape {
	/**
	 * Do I need a getter for r?
	 * How to auto-generate Java doc?
	 * Is that let paint has all the params best way?
	 */
	int r;
	/**
	 * 
	 * @param radius
	 */
	public Circle(int radius) {
		super();
		r = radius;
	}
	/* (non-Javadoc)
	 * @see shape.AShape#paint(int, int, java.awt.Graphics)
	 * 
	 * Plots the circle object on the XY coordinates and paints
	 * it the input color.
	 */
	@Override
	public void paint(int x, int y, Graphics g) {
		this.x = x;
		this.y = y;
		
		g.drawOval(x, y, r, r);
	}
	
}
