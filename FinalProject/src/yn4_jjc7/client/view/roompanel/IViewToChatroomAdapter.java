package yn4_jjc7.client.view.roompanel;

/**
 * Adapter for the chatroom view to communicate with its model.
 * 
 * @author Jayson Carter, Sean Harger
 *
 */
public interface IViewToChatroomAdapter {

  /**
   * Instructs the chatroom model to send the specified message to the chatroom.
   * @param message the message to send
   */
  public void send(String message);

  /**
   * Instructs the chatroom model to leave the room.
   */
  public void leave();
  
  /**
   * Instructs the chatroom to send the unknown message type.
   */
  public void sendDonald();
  
  public void sendMap();

  /** Null adapter */
  public static final IViewToChatroomAdapter NULL_OBJECT = new IViewToChatroomAdapter() {

    @Override
    public void send(String message) {

    }

    @Override
    public void leave() {

    }

    @Override
    public void sendDonald() {
      
    }

	@Override
	public void sendMap() {
	}
    
  };
}
