package common.user;

import java.io.Serializable;
import java.net.Inet4Address;
import java.rmi.RemoteException;

import provided.datapacket.DataPacket;

import common.message.connect.IConnectMessage;

/**
 * Interface for Users
 *
 * @author Jake Kornblau, Derek Peirce
 *
 */
public interface IUser extends Serializable {


	/** Returns the user's username */
	@Override
	public String toString();

	/**
	 * Returns the IP Address of the user
	 *
	 * @return the IPAddress of the user
	 */
	public Inet4Address getIPAddress();

	/**
	 * Sends a message used for getting or requesting chatrooms.
	 *
	 * @param message
	 *            an IConnectMessage for requesting or inviting a chatroom.
	 * @return The results of the request or invite.
	 */
	public DataPacket<? extends IConnectMessage> sendMessage(
			DataPacket<? extends IConnectMessage> message)
			throws RemoteException;

	/**
	 * Determines if two IUser are equal. Instances must still
	 * retain their original equality even after multiple deserialzation.
	 *
	 * @param o the Object to determine equality to.
	 * @return a boolean true o
	 */
	@Override
	public abstract boolean equals(Object o);

	/**
	 * Two IUser must return the same hash code
	 * if they are equal according to the equals function.
	 *
	 * @return the integer hash-code for this IUser
	 */
	@Override
	public abstract int hashCode();
}
