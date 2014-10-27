package client.model.task;

import java.rmi.RemoteException;

import provided.compute.ILocalTaskViewAdapter;
import provided.compute.ITask;

public class isEven implements ITask<Boolean>{
	
    private transient ILocalTaskViewAdapter taskView = ILocalTaskViewAdapter.DEFAULT_ADAPTER;
    
    private int num;
    
    public isEven(int num){
    	this.num = num;
    	taskView.append("Checking if the input number is even...");
    }
    
	@Override
	public Boolean execute() throws RemoteException {
	    System.out.println("Executing client's isEven task.");
	    taskView.append("Client's isEven executing...");
	    return checkEven(num);
	}
	
	
	/**
	 * A method to check if the input number is even
	 * @param num
	 * @return true if num is even, false otherwise
	 */
	public Boolean checkEven(int num){
		if(num%2 == 0) return true;
		else return false;
	}

	@Override
	public void setTaskViewAdapter(ILocalTaskViewAdapter viewAdapter) {
		taskView = viewAdapter;
	}

}
