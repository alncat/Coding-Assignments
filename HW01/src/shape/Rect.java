package shape;

import java.awt.Graphics;

public class Rect extends AShape{
	int w;
	int h;
	/**
	 * Gives the width and height of the rectangle
	 * @param width 
	 * @param height
	 */
	public Rect(int width, int height) {
		w = width;
		h = height;
	}
	/* (non-Javadoc)
	 * @see shape.AShape#paint(int, int, java.awt.Graphics)
	 * 
	 * Method paints the rectangle to the corresponding color
	 */
	@Override
	public void paint(int x, int y, Graphics g) {
		// TODO Auto-generated method stub
		g.drawRect(x, y, w, h);
	}

}
