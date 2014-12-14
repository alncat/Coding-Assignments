package yn4_jjc7.client.model.chatroom;

import java.rmi.Remote;
import java.rmi.RemoteException;

import provided.datapacket.DataPacket;

import common.chatroom.IChatroomAdapter;
import common.message.chat.IChatMessage;

/**
 * Interface for a remote stub used to send chatroom messages from one remote host to another.
 */
public interface IChatroomToChatroomRemote extends Remote {

	/**
	 * Sends a message to the remote user.
	 * 
	 * @param message the message to send
	 * @param sendingAdapter the adapter of the user who sent the message
	 * @return the return data packet
	 * @throws RemoteException if there was an error remotely invoking the method over the network
	 */
	public DataPacket<? extends IChatMessage> sendChatroomMessage(
			DataPacket<? extends IChatMessage> message, IChatroomAdapter sendingAdapter)
			throws RemoteException;

}
