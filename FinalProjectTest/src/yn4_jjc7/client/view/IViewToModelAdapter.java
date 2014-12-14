package yn4_jjc7.client.view;

/**
 * Adapter for the main view to communicate with the main model.
 */
public interface IViewToModelAdapter {

	/**
	 * Instructs the model to create a new chatroom with the specified name.
	 * 
	 * @param name the name of the new chatroom to create
	 */
	public void createChatroom(String name);

	/**
	 * Instructs the model to initiate a join request to the user at the specified IP address.
	 * 
	 * @param ipAddress the IP address of the user to send a join request
	 */
	public void requestToJoin(String ipAddress);

	/**
	 * Instructs the model to send an invitaiton to the user at the specified IP address.
	 * 
	 * @param ipAddress the IP address of the user to send the invitation
	 */
	public void inviteToChatroom(String ipAddress);

	/**
	 * Instructs the model that the user has initiated a quit and it needs to clean up.
	 */
	public void quit();

	/** Null adapter */
	public static final IViewToModelAdapter NULL_OBJECT = new IViewToModelAdapter() {

		@Override
		public void createChatroom(String name) {
		}

		@Override
		public void requestToJoin(String ipAddress) {
		}

		@Override
		public void inviteToChatroom(String ipAddress) {
		}

		@Override
		public void quit() {
		}

	};

}
