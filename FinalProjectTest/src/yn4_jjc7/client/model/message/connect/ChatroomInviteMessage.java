package yn4_jjc7.client.model.message.connect;

import java.util.List;

import common.chatroom.IChatroomAdapter;
import common.chatroom.IChatroomID;
import common.message.connect.IChatroomInviteMessage;

/**
 * Concrete wrapper for an <code>IChatroomInviteMessage</code>.
 */
public class ChatroomInviteMessage implements IChatroomInviteMessage {

	/** Serial ID */
	private static final long serialVersionUID = 7984242124785610055L;

	/** The unique identifier of the chatroom that the invitation is for */
	private IChatroomID chatroomId;

	/** The list of adapters to current members of the chatroom */
	private List<IChatroomAdapter> memberAdapters;

	/** The name of the person who sent the invitation */
	private String inviter;

	/**
	 * Creates a chatroom invite message.
	 * 
	 * @param chatroomId the unique id of the chatroom for the invitation
	 * @param memberAdapters the list of members currently in the chatroom
	 * @param inviter the name of the user sending the invitation
	 */
	public ChatroomInviteMessage(IChatroomID chatroomId,
			List<IChatroomAdapter> memberAdapters, String inviter) {
		this.chatroomId = chatroomId;
		this.memberAdapters = memberAdapters;
		this.inviter = inviter;
	}

	@Override
	public IChatroomID getChatroomID() {
		return chatroomId;
	}

	@Override
	public List<IChatroomAdapter> getMemberAdapters() {
		return memberAdapters;
	}

	@Override
	public String getName() {
		return this.inviter;
	}

}
