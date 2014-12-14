package yn4_jjc7.client.model.message.chat;

import common.chatroom.IChatroomAdapter;
import common.message.chat.IJoinChatroomMessage;

/**
 * Concrete wrapper for the <code>IJoinChatroomMessage</code>.
 * 
 * @author Jayson Carter, Sean Harger
 *
 */
public class JoinChatroomMessage implements IJoinChatroomMessage {

	/** Serial ID */
	private static final long serialVersionUID = -6196612404147031393L;

	/** The adapter to access the chatroom of the newly joined user */
	private IChatroomAdapter chatroomAdapter;

	/**
	 * Creates a new join chatroom message containing the adapter to call back to the joined user.
	 * 
	 * @param chatroomAdapter the adapter to the joining user
	 */
	public JoinChatroomMessage(IChatroomAdapter chatroomAdapter) {
		this.chatroomAdapter = chatroomAdapter;
	}

	@Override
	public IChatroomAdapter getAdapter() {
		return this.chatroomAdapter;
	}

}
