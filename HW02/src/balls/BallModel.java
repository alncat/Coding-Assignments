package balls;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Observer;

import javax.swing.Timer;

import util.Randomizer;
import controller.IModel2ViewAdapter;

/**
 * BallModel class containing business logic for adding balls, clearing balls,
 * and telling the view when to repaint.
 * 
 * @author Jake Kornblau, Jayson Carter
 */
public class BallModel {

	/**
	 * View Adapter. Initialized to a no-op to ensure well-defined behavior by
	 * the system.
	 */
	private IModel2ViewAdapter _model2ViewAdpt = IModel2ViewAdapter.NULL_OBJECT;

	/** Dispatcher to dispatch notifications to the ABall objects */
	private Dispatcher myDispatcher;

	/** Timer object used to tell the view when to repaint. */
	private Timer _timer;

	/**
	 * Constructor for the BallModel class
	 * 
	 * @param model2ViewAdpt
	 *            the view adapter for the model.
	 */
	public BallModel(IModel2ViewAdapter model2ViewAdpt) {
		_model2ViewAdpt = model2ViewAdpt;
		myDispatcher = new Dispatcher();
		int _timeSlice = 50;
		_timer = new Timer(_timeSlice, (e) -> _model2ViewAdpt.update());
	}

	/** Starts the Model */
	public void start() {
		_timer.start();
	}

	/**
	 * Adds a Sprite (Ball) to the Dispatcher
	 * 
	 * @param aSprite
	 *            An observer to be added to myDispatchers
	 */
	public void addSprite(Observer aSprite) {
		if (aSprite != null) {
			myDispatcher.addObserver(aSprite);
		}
	}

	/**
	 * This is the method that is called by the view's adapter to the model,
	 * i.e. is called by IView2ModelAdapter.paint(). This method will update the
	 * sprites's painted locations by painting all the sprites onto the given
	 * Graphics object.
	 * 
	 * @param g
	 *            The Graphics object from the view's paintComponent() call.
	 */
	public void update(Graphics g) {
		/*
		 * The Graphics object is being given to all the sprites (Observers)
		 */
		myDispatcher.notifyAll(g);
	}

	/**
	 * The following method returns an instance of an ABall, given a fully
	 * qualified class name (package.classname) of a subclass of ABall. The
	 * method assumes that there is only one constructor for the supplied class
	 * that has the same *number* of input parameters as specified in the args
	 * array and that it conforms to a specific signature, i.e. specific order
	 * and types of input parameters in the args array. Adds the instance of the
	 * supplied class to the balls list.
	 * 
	 * @param className
	 *            A string that is the fully qualified class name of the desired
	 *            object
	 * @param canvas
	 *            The Container that the ABall should be contained within.
	 * 
	 * @return An concrete implementation of the ABall abstract class as
	 *         specified by the classname. Null if none exists.
	 */
	public ABall loadBall(String className, Component canvas) {
		try {
			int startRadius = Randomizer.Singleton.randomInt(5, 25);
			Rectangle bounds = canvas.getBounds();
			Rectangle startArea = new Rectangle(bounds.width - 2 * startRadius,
					bounds.height - 2 * startRadius);
			Point startLoc = Randomizer.Singleton.randomLoc(startArea);
			startLoc.x += startRadius;
			startLoc.y += startRadius;
			Point startVel = Randomizer.Singleton.randomVel(new Rectangle(
					(int) Math.ceil(bounds.width / 20), (int) Math
							.ceil(bounds.height / 20)));
			Object[] args = new Object[] { startLoc, startRadius, startVel,
					Randomizer.Singleton.randomColor(), canvas };
			java.lang.reflect.Constructor<?> cs[] = Class.forName(className)
					.getConstructors(); // get all the constructors
			java.lang.reflect.Constructor<?> c = null;
			/*
			 * Finds the first constructor with the right number of input
			 * parameters
			 */
			for (int i = 0; i < cs.length; i++) {
				if (args.length == (cs[i]).getParameterTypes().length) {
					c = cs[i];
					break;
				}
			}
			return (ABall) c.newInstance(args);
		} catch (Exception ex) {
			System.err.println("Class " + className
					+ " failed to load. \nException = \n" + ex);
			return null;
		}
	}

	/**
	 * Clears all balls from the dispatcher
	 */
	public void clearBalls() {
		myDispatcher.deleteObservers();
	}

}
