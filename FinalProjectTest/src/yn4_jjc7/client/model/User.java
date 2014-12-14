package yn4_jjc7.client.model;

import java.net.Inet4Address;
import java.rmi.RemoteException;

import provided.datapacket.DataPacket;
import common.message.connect.IConnectMessage;
import common.user.IUser;

/**
 * Concrete implmentation of the <code>IUser</code> adapter interface.
 */
public class User implements IUser {

	/** Serial ID */
	private static final long serialVersionUID = 3663268407108655497L;

	/** The name of the user */
	private String name;
	/** The IP address of the user */
	private Inet4Address ipAddress;
	/** The stub used to invoke methods on this user remotely. */
	private IUserRemote remoteUserStub;

	/**
	 * Creates a new user adapter.
	 * 
	 * @param name the name of the user
	 * @param ipAddress the IP address of the user
	 * @param remoteUserStub the stub used to communicate with this user remotely
	 */
	public User(String name, Inet4Address ipAddress, IUserRemote remoteUserStub) {
		this.name = name;
		this.ipAddress = ipAddress;
		this.remoteUserStub = remoteUserStub;
	}

	@Override
	public String toString() {
		return this.name;
	}

	@Override
	public Inet4Address getIPAddress() {
		return this.ipAddress;
	}

	@Override
	public DataPacket<? extends IConnectMessage> sendMessage(
			DataPacket<? extends IConnectMessage> message, IUser sender) throws RemoteException {
		return remoteUserStub.sendMessage(message, sender);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ipAddress == null) ? 0 : ipAddress.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (ipAddress == null) {
			if (other.ipAddress != null)
				return false;
		} else if (!ipAddress.equals(other.ipAddress))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}
