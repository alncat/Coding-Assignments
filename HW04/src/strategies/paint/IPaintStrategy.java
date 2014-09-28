package strategies.paint;

import java.awt.Graphics;

import strategies.IStrategy;
import balls.Ball;

public interface IPaintStrategy extends IStrategy {
	public void init(Ball context);

	public void paint(Graphics g, Ball host);
}
