package strategies.paint;

import java.awt.Graphics;
import java.awt.geom.AffineTransform;

import balls.Ball;


public class HatFishPaintStrategy extends MultiPaintStrategy {
	
	private int flag;

	/**
	 * No parameter constructor that creates a prototype ellipse that has twice the width as height
	 * but an average radius of 1. An AffineTranform for internal use is instantiated.
	 */
	public HatFishPaintStrategy() {
		this(new AffineTransform(), 0, 0, 4.0 / 3.0, 2.0 / 3.0);
	}

	/**
	 * Constructor that allows the specification of the location, x-radius and y-radius of the
	 * prototype ellipse. The AffineTransform to use is given.
	 * 
	 * @param at The AffineTransform to use for internal calculations
	 * @param x floating point x-coordinate of center of circle
	 * @param y floating point y-coordinate of center of circle
	 * @param width floating point x-radius of the circle (ellipse)
	 * @param height floating point y-radius of the circle (ellipse)
	 */
	public HatFishPaintStrategy(AffineTransform at, double x, double y, double width, double height) {
		super(at, new APaintStrategy[] {new EllipsePaintStrategy(at, x+width*1.6, y-.5*height, width/2, height/3), 
				new RectanglePaintStrategy(at, x+width*1.2, y-2*height/3, 2*width/3, 2*height/3),
				new FishPaintStrategy(at, x, y, width, height)});
	}

	protected void paintCfg(Graphics g, Ball host) {
		super.paintCfg(g, host);
		System.out.println(flag);
		if (host.getXVel() < 0 && flag == 0)  {
			flag = 1;
			localAT.scale(1.0, -1.0);
		}
		if (host.getXVel() > 0 && flag == 1) {
			flag = 0;
			localAT.scale(1.0, -1.0);
		}
	}
	
}
