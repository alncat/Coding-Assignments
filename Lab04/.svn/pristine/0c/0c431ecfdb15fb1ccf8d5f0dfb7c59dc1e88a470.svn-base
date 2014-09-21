package controller;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Graphics;

import view.BallView;
import balls.ABall;
import balls.BallModel;

/**
 * MVC Controller for the system
 */
public class Controller {

	/** The model object containing all business logic for BallWorld */
	private BallModel model;

	/** The view (GUI) for BallWorld */
	private BallView view;

	/**
	 * Controller constructor builds the system
	 */
	public Controller() {

		/* Instantiates a new instance of BallModel as model */
		model = new BallModel(new IModel2ViewAdapter() {

			@Override
			public void update() {
				view.update();
			}

		});

		/* Instantiates a new instance of BallView as view */
		view = new BallView(new IView2ModelAdapter() {

			@Override
			public void paint(Graphics g) {
				model.update(g);
			}

			@Override
			public void clearBalls() {
				model.clearBalls();
			}

			@Override
			public void addBall(String className, Component canvas) {
				ABall ball = model.loadBall(className, canvas);
				if (ball != null) {
					model.addSprite(ball);
				}
			}

		});
	}

	/**
	 * Starts the system
	 */
	public void start() {
		model.start();
		view.start();
	}

	/**
	 * Launches the application.
	 * 
	 * @param args
	 *            Arguments given by the system or command line.
	 */
	public static void main(String[] args) {
		/*
		 * Java specs say that the system must be constructed on the GUI event
		 * thread.
		 */
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					/* Instantiates the system */
					Controller controller = new Controller();

					/* Starts the system */
					controller.start();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
