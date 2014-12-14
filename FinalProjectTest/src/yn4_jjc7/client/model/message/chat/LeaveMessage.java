package yn4_jjc7.client.model.message.chat;

import common.chatroom.IChatroomAdapter;
import common.message.chat.ILeaveMessage;

/**
 * Concrete wrapper for the <code>ILeaveMessage</code>.
 *
 * @author Jayson Carter, Sean Harger
 *
 */
public class LeaveMessage implements ILeaveMessage {

	/** Serial ID */
	private static final long serialVersionUID = -4363550554897869055L;

	/** The adapter of the leaving user */
	private IChatroomAdapter chatroomAdapter;

	/**
	 * Creates a new leave message with the adapter of the user that is leaving.
	 * 
	 * @param chatroomAdapter the adapter to the leaving user
	 */
	public LeaveMessage(IChatroomAdapter chatroomAdapter) {
		this.chatroomAdapter = chatroomAdapter;
	}

	@Override
	public IChatroomAdapter getAdapter() {
		return this.chatroomAdapter;
	}

}
