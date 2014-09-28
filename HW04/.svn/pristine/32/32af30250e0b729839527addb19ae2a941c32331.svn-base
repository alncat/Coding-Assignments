package strategies.paint;

import java.awt.Graphics;
import java.awt.geom.AffineTransform;

import balls.Ball;

public class MultiPaintStrategy extends APaintStrategy {

	/**
	 * Constructor for MulitStrategy.
	 */
	private APaintStrategy[] strats;

	// private AffineTransform at;

	public MultiPaintStrategy(APaintStrategy... pstrats) {
		this(new AffineTransform(), pstrats);
	}


	public MultiPaintStrategy(AffineTransform at, APaintStrategy... pstrats) {
		super(at);
		// this.at = at;
		strats = pstrats;
	}


	public void init(Ball host) {
		for (int i = 0; i < strats.length; i++) {
			strats[i].init(host);
		}
	}


	@Override
	public void paintXfrm(Graphics g, Ball host, AffineTransform at) {
		for (int i = 0; i < strats.length; i++) {
			strats[i].paintXfrm(g, host, at);
		}

	}

}
