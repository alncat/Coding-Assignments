package view;

import java.awt.Graphics;

/**
 * The interface of the adapter from the view to the model that enables the view
 * to talk to the model.
 */
public interface IView2ModelAdapter<TStrategyDropListItem, TPaintDropListItem> {

	/**
	 * Instructs the model to paint each of its component observers.
	 * 
	 * @param g
	 *            the graphics object used to help paint the models
	 */
	public void paint(Graphics g);

	/**
	 * Take the given short strategy name and return a corresponding something
	 * to put onto both drop lists.
	 * 
	 * @param classname
	 *            The shortened class name of the desired strategy
	 * @return Something to put onto both the drop lists.
	 */
	public TStrategyDropListItem addStrategy(String classname);

	public TPaintDropListItem addPaintStrategy(String classname);

	/**
	 * Make a ball with the selected short strategy name.
	 * 
	 * @param selectedItem
	 *            A shorten class name for the desired strategy
	 */
	public void makeBall(TStrategyDropListItem selectedItem,
			TPaintDropListItem selectedItem2);

	/**
	 * Return a new object to put on both lists, given two items from the lists.
	 * 
	 * @param selectedItem1
	 *            An object from one drop list
	 * @param selectedItem2
	 *            An object from the other drop list
	 * @return An object to put back on both lists.
	 */
	public TStrategyDropListItem combineStrategies(
			TStrategyDropListItem selectedItem1,
			TStrategyDropListItem selectedItem2);

	/**
	 * Switch all the switcher balls to the given strategy.
	 * 
	 * @param selectedItem
	 *            A shorten class name for the desired strategy
	 */
	public void switchStrategy(TStrategyDropListItem selectedItem);

	/**
	 * Make a ball whose strategy could be switched.
	 */
	public void makeSwitcherBall(TPaintDropListItem selectedItem);

	/**
	 * Instructs the model to delete all of its component objects.
	 */
	public void reset();

	/**
	 * No-op singleton implementation of IView2ModelAdapter
	 */
	public static final IView2ModelAdapter<Object, Object> NULL_OBJECT = new IView2ModelAdapter<Object, Object>() {
		@Override
		public void paint(Graphics g) {
		}

		@Override
		public void reset() {
		}

		@Override
		public Object addStrategy(String classname) {
			return null;
		}

		@Override
		public void makeBall(Object selectedItem, Object selectedItem2) {
		}

		@Override
		public Object combineStrategies(Object selectedItem1,
				Object selectedItem2) {
			return null;
		}

		@Override
		public void makeSwitcherBall(Object selectedItem) {
		}

		@Override
		public void switchStrategy(Object selectedItem) {
		}

		@Override
		public Object addPaintStrategy(String classname) {
			return null;
		}

	};

}