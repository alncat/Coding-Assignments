package strategies.paint;

import java.awt.Graphics;
import java.awt.geom.AffineTransform;
import java.util.concurrent.atomic.AtomicBoolean;

import balls.Ball;

public abstract class APaintStrategy implements IPaintStrategy {
	private AffineTransform at;

	public void init(Ball context) {}

	public APaintStrategy(AffineTransform a) {
		at = a;
	}

	protected AffineTransform getAT() {
		return at;
	}

	public void paint(Graphics g, Ball host) {
		double scale = host.getRadius();
		at.setToTranslation(host.getXLoc(), host.getYLoc());
		at.scale(scale, scale);
		at.rotate(Math.atan2(host.getYVel()/8, host.getXVel()/8));
		//at.translate(host.getXLoc(), host.getYLoc());
		//at.rotate(Math.atan2(host.getYVel(),host.getXVel()));
		g.setColor(host.getColor());
		paintCfg(g, host);
		paintXfrm(g, host, at);
	}


	protected void paintCfg(Graphics g, Ball host) {}

	public abstract void paintXfrm(Graphics g, Ball host, AffineTransform af);

}