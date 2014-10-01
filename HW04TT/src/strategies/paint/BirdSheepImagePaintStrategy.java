package strategies.paint;

import java.awt.geom.AffineTransform;


public class BirdSheepImagePaintStrategy extends ImagePaintStrategy {

	/**
	 * Constructor that random chooses between the lamb and humming bird images.
	 */
	public BirdSheepImagePaintStrategy() {
		this(new AffineTransform(), "images/" + (Math.random() < .5 ? "humbird.gif" : "lamb.gif"),
				1.0);
	}

	/**
	 * Constructor modifies the size of the image chosen.
	 * 
	 * @param at: The AffineTransform object
	 * @param fileName: image file name
	 * @param fillFactor: Determines the size of the image produced
	 */
	public BirdSheepImagePaintStrategy(AffineTransform at, String fileName, double fillFactor) {
		super(at, fileName, fillFactor);
	}

}
