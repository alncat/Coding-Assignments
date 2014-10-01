package view;

import java.awt.Graphics;

public interface IModelUpdateAdapter<TDropListItem> {

	/**
	 * Tells the model to update
	 * 
	 * @param g - canvas to update on.
	 */
	public void update(Graphics g);
	
	/**
	 * No-op singleton implementation of IModelUpdateAdapter.
	 */
	@SuppressWarnings("rawtypes")
	public static final IModelUpdateAdapter NULL_OBJECT = new IModelUpdateAdapter() {
		public void update(Graphics g){}
	};
}
