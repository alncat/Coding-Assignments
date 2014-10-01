package model.command;

import model.ball.Ball;
import util.Dispatcher;

/**
 * Interface for the ball to receive commands from the dispatcher
 * @author lss4, jfe2, yh20
 *
 */
@FunctionalInterface
public abstract interface IBallCmd {
    /**
     * The method run by the ball's update method which is called when the ball is updated by the dispatcher.
     * @param context The ball that is calling this method.   The context under which the command is to be run.
     * @param disp The Dispatcher that sent the command out.
     */	
    public abstract void apply(Ball context, Dispatcher disp);
}