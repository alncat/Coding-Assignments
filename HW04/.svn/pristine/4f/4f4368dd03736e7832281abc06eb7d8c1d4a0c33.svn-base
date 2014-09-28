package model;

import java.awt.Component;

public interface IViewControlAdapter {
	/**
	 * Get the canvas from the view
	 * 
	 * @return canvas from view
	 */
	public Component getCanvas();

	/**
	 * No-op singleton implementation of IViewControlAdapter.
	 */
	public static final IViewControlAdapter NULL_OBJECT = new IViewControlAdapter() {
		public Component getCanvas() {
			return null;
		}
	};

}
