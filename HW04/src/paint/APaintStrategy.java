package paint;

import java.awt.Graphics;
import java.awt.geom.AffineTransform;

import balls.Ball;
public abstract class APaintStrategy implements IPaintStrategy {
  private AffineTransform at;
  
	public void init(Ball context) {}
	
	public APaintStrategy(AffineTransform a){
	  at = a;
	}

	public void paint(Graphics g, Ball host) {
	  double scale = host.getRadius();
	  at.setToTranslation(host.getXLoc(), host.getYLoc());
	  at.scale(scale, scale);
	  at.rotate(Math.atan2(host.getYVel(), host.getXVel()));
	  g.setColor(host.getColor());    
	  paintCfg(g, host);
	  paintXfrm(g, host, at);
	}

	
	protected void  paintCfg(Graphics g, Ball host) {}
	
	public abstract void paintXfrm(Graphics g, Ball host, AffineTransform af);

}
