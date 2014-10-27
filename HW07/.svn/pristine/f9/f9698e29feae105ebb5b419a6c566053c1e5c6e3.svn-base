package client.model.task;

import java.rmi.RemoteException;
import java.util.Random;

import provided.compute.ILocalTaskViewAdapter;
import provided.compute.ITask;

public class RandomNum implements ITask<Integer>{
	
    /**
     * Adapter to the local view.  Marked "transient" so that it is not serialized
     * and instead is reattached at the destination (the server).  
     */
    private transient ILocalTaskViewAdapter taskView = ILocalTaskViewAdapter.DEFAULT_ADAPTER;
    
    private int range;
    
    public RandomNum(int range){
    	this.range = range;
    	taskView.append("Random number is being generated...");
    }
    
	@Override
	public Integer execute() throws RemoteException {
	    System.out.println("Executing client's RandomNum task.");
	    taskView.append("Client's RandomNum executing...");
	    return generate(range);
	}
	
	/**
	 * generate a random number between [0,range]
	 * @param range
	 * @return random number
	 */
	public Integer generate(int range){
		Random rand = new Random();
		return rand.nextInt(range+1);
	}

	@Override
	public void setTaskViewAdapter(ILocalTaskViewAdapter viewAdapter) {
		taskView = viewAdapter;
	}

}
