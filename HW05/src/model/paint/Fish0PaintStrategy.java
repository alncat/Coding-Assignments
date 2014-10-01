package model.paint;

import model.shape.Fish0ShapeFactory;

/**
 * Makes a fish with an open mouth.
 * 
 * @author lss4, yh20, jfe2
 *
 */
public class Fish0PaintStrategy extends ShapePaintStrategy {
	/**
	 * The shape factory for this paint strategy
	 */
	private static Fish0ShapeFactory f = new Fish0ShapeFactory();

	/**
	 * Constructs a fish paint strategy.
	 */
	public Fish0PaintStrategy() {
		super(f.makeShape(0, 0, 1.0, 1.0));
	}

}
