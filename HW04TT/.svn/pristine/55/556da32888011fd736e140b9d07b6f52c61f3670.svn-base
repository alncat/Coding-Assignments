package strategies.paint;

import java.awt.Graphics;
import java.awt.geom.AffineTransform;

import balls.Ball;

public class MultiPaintStrategy extends APaintStrategy {

	/**
	 * Constructor for MulitStrategy.
	 */
	private APaintStrategy[] strats;
	protected AffineTransform localAT = new AffineTransform();

	// private AffineTransform at;

	/**
	 * 
	 * Setter for array of ApaintStrategy and calls a super on
	 * the AffineTransform object
	 * 
	 * @param pstrats
	 */
	public MultiPaintStrategy(APaintStrategy... pstrats) {
		this(new AffineTransform(), pstrats);
	}


	/**
	 * 
	 * Setter for array of ApaintStrategy and calls a super on
	 * the AffineTransform object
	 * 
	 * @param at
	 * @param pstrats
	 */
	public MultiPaintStrategy(AffineTransform at, APaintStrategy... pstrats) {
		super(at);
		// this.at = at;
		strats = pstrats;
	}


	/* (non-Javadoc)
	 * @see strategies.paint.APaintStrategy#init(balls.Ball)
	 */
	public void init(Ball host) {
		for (int i = 0; i < strats.length; i++) {
			strats[i].init(host);
		}
	}

	/* (non-Javadoc)
	 * @see strategies.paint.APaintStrategy#paintXfrm(java.awt.Graphics, balls.Ball, java.awt.geom.AffineTransform)
	 */
	@Override
	public void paintXfrm(Graphics g, Ball host, AffineTransform at) {
		at.concatenate(localAT);
		for (int i = 0; i < strats.length; i++) {
			strats[i].paintXfrm(g, host, at);
		}

	}

}
