package strategies.paint;

import java.awt.geom.AffineTransform;


public class PikachuImagePaintStrategy extends ImagePaintStrategy {

	/**
	 * Constructor that random chooses between the lamb and humming bird images.
	 */
	public PikachuImagePaintStrategy() {
		this(new AffineTransform(), "images/pikachu.gif",
				1.0);
	}

	/**
	 * Constructor modifies the size of the image chosen.
	 * 
	 * @param at: The AffineTransform object
	 * @param fileName: image file name
	 * @param fillFactor: Determines the size of the image produced
	 */
	public PikachuImagePaintStrategy(AffineTransform at, String fileName, double fillFactor) {
		super(at, fileName, fillFactor);
	}

}
