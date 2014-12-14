package yn4_jjc7.client.model.message.connect;

import common.chatroom.IChatroomID;
import common.message.connect.IRequestChatroomMessage;

/**
 * Concrete wrapper for a <code>IRequestChatroomMessage</code>.
 */
public class RequestChatroomMessage implements IRequestChatroomMessage {

	/** Serial ID */
	private static final long serialVersionUID = 3316805928956033335L;

	/** The chatroom id of the room being requested to join */
	private IChatroomID chatroomId;
	/** The name of the user requesting to join */
	private String name;

	/**
	 * Creates a new chatroom join request.
	 * 
	 * @param chatroomId the unique id of the chatroom wished to join
	 * @param name the name of the user who requested to join
	 */
	public RequestChatroomMessage(IChatroomID chatroomId, String name) {
		this.chatroomId = chatroomId;
		this.name = name;
	}

	@Override
	public IChatroomID getChatroomID() {
		return chatroomId;
	}

	@Override
	public String getName() {
		return name;
	}

}
