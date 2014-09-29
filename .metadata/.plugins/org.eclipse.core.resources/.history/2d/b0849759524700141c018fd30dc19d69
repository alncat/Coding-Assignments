package strategies.paint;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.awt.image.ImageObserver;

import balls.Ball;

public class ImagePaintStrategy extends APaintStrategy {

	private ImageObserver imageObs;
	private Image image;
	private double scaleFactor = 1.0;
	private double fillFactor = 1.0;
	protected AffineTransform localAT = new AffineTransform();

	public ImagePaintStrategy(AffineTransform at, String fileName, double fillFactor) {
		super(at);
		this.fillFactor = fillFactor;
		try {
			image = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource(fileName));
		} catch (Exception e) {
			System.err.println("ImagePaintStrategy: Error reading file: " + fileName + "\n" + e);
		}
	}

	public void init(Ball host) {
		imageObs = host.getCanvas();
		MediaTracker mt = new MediaTracker(host.getCanvas());
		mt.addImage(image, 1);
		try {
			mt.waitForAll();
		} catch (Exception e) {
			System.err.println("ImagePaintStrategy.init(): Error waiting for image.  Exception = "
					+ e);
		}

		scaleFactor =
				2.0 / (fillFactor * (image.getWidth(imageObs) + image.getHeight(imageObs)) / 2.0);
	}

	public void paintXfrm(Graphics g, Ball host, AffineTransform at) {
		localAT.setToScale(scaleFactor, scaleFactor);
		localAT.translate(-image.getWidth(imageObs) / 2.0, -image.getHeight(imageObs) / 2.0);
		localAT.preConcatenate(at);
		((Graphics2D) g).drawImage(image, localAT, imageObs);
	}

	protected void paintCfg(Graphics g, Ball host) {
		super.paintCfg(g, host);
		if (Math.abs(Math.atan2(host.getYVel(), host.getXVel())) > Math.PI / 2.0) {
			localAT.scale(1.0, -1.0);
		}
	}

}
