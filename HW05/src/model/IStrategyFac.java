package model;

import model.ball.IUpdateStrategy;

/**
 * Interface for factor that makes update strategies.
 */
public interface IStrategyFac {

	/**
	 * Returns a new IUpdateStrategy.
	 * @return an instance of IUpdateStrategy.
	 */
	IUpdateStrategy make();
}
