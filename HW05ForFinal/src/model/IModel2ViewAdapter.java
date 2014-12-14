package model;

import java.awt.Component;

/**
 * Interface that goes from the model to the view that enables the model to talk
 * to the view
 */
public interface IModel2ViewAdapter {
	
	/**
	 * The method that tells the view to update.
	 */
	public void update();
	

	/**
	 * Returns the Component in view where the balls are painted.
	 * @return panel, Component
	 */
	public Component getCanvas();
	
	/**
	 * No-op "null" adapter
	 */
	public static final IModel2ViewAdapter NULL_OBJECT = 
			new IModel2ViewAdapter() {
		@Override
		public void update() {}

		@Override
		public Component getCanvas() {return null;}
	};
	
}
