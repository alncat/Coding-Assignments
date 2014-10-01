package model.paint;

import java.awt.Graphics;
import java.awt.geom.AffineTransform;

import model.ball.Ball;

/**
 * A composite design pattern extension of APaintStrategy that paints a set of
 * paint strategies.
 * Note: this paint strategy cannot be used directly by the BallWorld system 
 * because it lacks a no-parameter constructor. 
 * @author Yi Hua
 *
 */
public class MultiPaintStrategy extends APaintStrategy {

	/**
	 * Array of all paint strategies
	 */
	private APaintStrategy[] _pStrgy;
	
	/**
	 * Number of paint strategies in the MultiPaintStrategy
	 */
	private int _nStrgy;
	
	/**
	 * Create a multi paint strategy
	 * @param pStrgys Array of all paint strategies to use
	 */
	public MultiPaintStrategy(APaintStrategy... pStrgys){
		this(new AffineTransform(), pStrgys);
	}
	
	/**
	 * Helper constructor that allows the MultiPaint strategy to init an AffineTransform
	 * @param at the Affine trasform
	 * @param pStrgys Array of all paint strategies
	 */
	private MultiPaintStrategy(AffineTransform at, APaintStrategy... pStrgys){
		super(at);
		_nStrgy = pStrgys.length;
		_pStrgy = pStrgys;
	}
	
	/**
	 * Init all paint strategies
	 */
	@Override
	public void init(Ball host){
		for (int i = 0; i < _nStrgy; i++){
			_pStrgy[i].init(host);
		}
	}
	
	/**
	 * Paint all paint strategies
	 */
	@Override
	public void paint(Graphics g, Ball host) {
		for (int i = 0; i < _nStrgy; i++){
			_pStrgy[i].paint(g, host);
		}

	}

	@Override
	/**
	 * Does not do anything, as no additional transforms are needed.
	 */
	public void paintXfrm(Graphics g, Ball host, AffineTransform at2) {
		/* No additional transforms needed */
		
	}

}
