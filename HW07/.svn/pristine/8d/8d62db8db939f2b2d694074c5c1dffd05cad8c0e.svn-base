package client.model.task;

import java.rmi.RemoteException;

import provided.compute.ILocalTaskViewAdapter;
import provided.compute.ITask;

public class isPerfectSquare implements ITask<Boolean>{
	
    private transient ILocalTaskViewAdapter taskView = ILocalTaskViewAdapter.DEFAULT_ADAPTER;
    
    private int num;
    
    public isPerfectSquare(int num){
    	this.num = num;
    	taskView.append("Checking if the input number is a perfect square root...");
    }
    
	@Override
	public Boolean execute() throws RemoteException {
	    System.out.println("Executing client's isPerfectSquare task.");
	    taskView.append("Client's isPerfectSquare executing...");
	    return checkEven(num);
	}
	
	
	/**
	 * A method to check if the input number is even
	 * @param num
	 * @return true if num is perfect square root, false otherwise
	 */
	public Boolean checkEven(int num){
		double roundHere;
		double checkSquare;
		checkSquare = Math.sqrt(num);
		
		roundHere = Math.round(Math.sqrt(num));
		
		
		if(roundHere == checkSquare) return true;
		else return false;
	}

	@Override
	public void setTaskViewAdapter(ILocalTaskViewAdapter viewAdapter) {
		taskView = viewAdapter;
	}

}
