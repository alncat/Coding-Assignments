package yn4_jjc7.client.model.message.connect;

import java.util.ArrayList;
import java.util.List;

import common.chatroom.IChatroomID;
import common.message.connect.IChatroomsListMessage;

/**
 * Concrete wrapper for the <code>IChatroomsListMessage</code>.
 */
public class ChatroomsListMessage implements IChatroomsListMessage {

	/** Serial ID */
	private static final long serialVersionUID = 2609425865999238541L;

	/** The list of chatroom identifiers */
	public ArrayList<IChatroomID> chatroomIds;

	/**
	 * Creates a new chatrooms list message with the specified list of chatroom ids.
	 * 
	 * @param chatroomIds the list of chatrooms
	 */
	public ChatroomsListMessage(ArrayList<IChatroomID> chatroomIds) {
		this.chatroomIds = chatroomIds;
	}

	@Override
	public List<IChatroomID> getChatroomIDs() {
		return this.chatroomIds;
	}

}
