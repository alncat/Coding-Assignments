package yn4_jjc7.client.model.chatroom;

import java.awt.Component;

/**
 * Adapter used by the chatroom to communicate with its personal chatroom view.
 */
public interface IChatroomToViewAdapter {

	/**
	 * Displays a message on the chatroom's view.
	 * 
	 * @param message the message to display
	 */
	void displayMessage(String message);

	/**
	 * Adds a view component to the chatroom's view.
	 * 
	 * @param comp the view to add
	 */
	void addComponent(Component comp);

	/**
	 * Instructs the view to remove this chatroom's view from the main view.
	 */
	void removeChatroom();

	/** The null adapter */
	static final IChatroomToViewAdapter NULL_OBJECT = new IChatroomToViewAdapter() {

		@Override
		public void displayMessage(String message) {
		}

		@Override
		public void removeChatroom() {
		}

		@Override
		public void addComponent(Component comp) {
		}

	};

}
