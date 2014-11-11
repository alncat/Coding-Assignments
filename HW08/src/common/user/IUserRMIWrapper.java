package common.user;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IUserRMIWrapper extends Remote {
	/** String used for bounding in the registry */
	public static final String BOUND_NAME = "IUser";

	public IUser getUser() throws RemoteException;
}
