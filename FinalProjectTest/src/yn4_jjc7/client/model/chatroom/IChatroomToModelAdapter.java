package yn4_jjc7.client.model.chatroom;

/**
 * Adapter used to communicate from the chatroom model to the main model that manages chatrooms.
 */
public interface IChatroomToModelAdapter {

	/**
	 * Called when a chatroom dies and wishes to remove itself from the manager's list of chatrooms.
	 * 
	 * @param room the room to remove from the manager
	 */
	public void removeChatroom(Chatroom room);

}
