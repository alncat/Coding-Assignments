package strategies.paint;

import java.awt.Graphics;
import java.awt.geom.AffineTransform;

import balls.Ball;

public class AnimatePaintStrategy extends APaintStrategy {

	public AnimatePaintStrategy(AffineTransform a) {
		super(a);
	}

	@Override
	public void paintXfrm(Graphics g, Ball host, AffineTransform af) {

	}

}