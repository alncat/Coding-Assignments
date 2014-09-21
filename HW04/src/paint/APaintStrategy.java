package paint;

import java.awt.Graphics;
import java.awt.geom.AffineTransform;

public abstract class APaintStrategy implements IPaintStrategy {

	public void init() {
		// TODO Auto-generated method stub

	}

	public void paint(Graphics g) {
		// TODO Auto-generated method stub

	}
	
	public void paintCfg() {}
	
	public abstract void paintXfrm(AffineTransform af);

}
