package model.ball;

import model.command.IBallCmd;
import util.Dispatcher;
import util.Randomizer;

public class ColorChangerStrategy implements IUpdateStrategy {

	int count = 0;
	@Override
	public void init(Ball context) {
			context.setInteractStrategy(new MultiInteractStrategy(context.getInteractStrategy(), new IInteractStrategy(){
				@Override
				public void interact(Ball ball, Ball target, Dispatcher disp) {
					target.setColor(context.getColor());
				}
				
			}));

		}

		

	@Override
	public void updateState(Ball context, Dispatcher dispatcher) {
		if (count == 100){
			context.setColor(Randomizer.Singleton.randomColor());
			dispatcher.notifyAll(new IBallCmd(){
				
				public void apply(Ball other, Dispatcher disp){
					if (context!=other){
						context.interactWith(other, disp);
					}
				}
			});
			count = 0;
		}
		else{
			count += 1;
		}

	}

}
