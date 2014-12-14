package yn4_jjc7.client.model.chatroom;

import java.rmi.RemoteException;

import provided.datapacket.DataPacket;

import common.chatroom.IChatroomAdapter;
import common.chatroom.IChatroomID;
import common.message.chat.IChatMessage;
import common.user.IUser;

/**
 * Concrete implementation of a chatroom to chatroom adapter. This is used to communicate from one chatroom to another
 * over a remote interface.
 */
public class ChatroomAdapter implements IChatroomAdapter {

	/** The serial ID */
	private static final long serialVersionUID = 4992735721495585909L;

	/** The adapter for the user that this adapter will facilitate communication with */
	private IUser user;

	/** The unique identifier for the chatroom that this adapter is communicating to */
	private IChatroomID chatroomId;

	/** The stub used to execute remote methods on the user that this adapter is facilitating communication with */
	private IChatroomToChatroomRemote chatroomStub;

	/**
	 * Creates a new chatroom to chatroom adapter.
	 * 
	 * @param user the user adapter of the user who owns the chatroom stub
	 * @param chatroomId the unique identifier for the chatroom
	 * @param chatroomStub the stub used to invoke methods on this chatroom remotely
	 */
	public ChatroomAdapter(IUser user, IChatroomID chatroomId, IChatroomToChatroomRemote chatroomStub) {
		this.user = user;
		this.chatroomId = chatroomId;
		this.chatroomStub = chatroomStub;
	}

	@Override
	public IUser getUser() {
		return this.user;
	}

	@Override
	public IChatroomID getChatroomID() {
		return this.chatroomId;
	}

	@Override
	public DataPacket<? extends IChatMessage> sendChatroomMessage(
			DataPacket<? extends IChatMessage> message, IChatroomAdapter sendingAdapter)
			throws RemoteException {
		return chatroomStub.sendChatroomMessage(message, sendingAdapter);
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
		result = prime * result + ((chatroomId == null) ? 0 : chatroomId.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		ChatroomAdapter other = (ChatroomAdapter) obj;
		if (chatroomId == null) {
			if (other.chatroomId != null)
				return false;
		} else if (!chatroomId.equals(other.chatroomId))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

}
