package common.user;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Remote object that wraps a serializeable IUser. Placed in a registry under the BOUND_NAME for other users to access.
 *
 * @author Jake Kornblau
 *
 */
public interface IUserRMIWrapper extends Remote {
	/** String used for bounding in the registry */
	public static final String BOUND_NAME = "IUser";

	/**
	 * Gets the IUser from the remote user.
	 * @return the IUser of the remote user
	 * @throws RemoteException if there was an exception in remotely getting the IUser.
	 */
	public IUser getUser() throws RemoteException;
}
