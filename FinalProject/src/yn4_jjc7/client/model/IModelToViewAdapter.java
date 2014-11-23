package yn4_jjc7.client.model;

import java.util.List;

import yn4_jjc7.client.model.chatroom.Chatroom;
import yn4_jjc7.client.model.chatroom.IChatroomToViewAdapter;
import common.chatroom.IChatroomID;

/**
 * Adapter for the main ChatApp model to communicate with its view.
 * 
 * @author Jayson Carter, Sean Harger
 *
 */
public interface IModelToViewAdapter {

  /**
   * Creates a view adapter for the chatroom to communicate with its mini-view.
   * 
   * @param room the chatroom to make the adapter for
   * @return the adapter that was created
   */
  public IChatroomToViewAdapter makeChatroomToViewAdapter(Chatroom room);

  /**
   * Instructs the view to query the user which chatroom to join.
   * 
   * @param chatroomIds the list of chatroom id options to join
   * @return the id of the chatroom that was chosen
   */
  public IChatroomID chooseChatroomToJoin(List<IChatroomID> chatroomIds);

  /**
   * Instructs the view to query the user which chatroom to invite another user to.
   * 
   * @param chatrooms the list of chatrooms for the user to invite the other user to
   * @return the chatroom that was selected
   */
  public Chatroom chooseChatroomToInvite(List<Chatroom> chatrooms);

  /**
   * Instructs the view to display an information dialog with the specified content.
   * 
   * @param title the title of the message
   * @param message the content of the message
   */
  public void showInformationDialog(String title, String message);

  /**
   * Instructs the view to query the user a yes-no question with the specified content.
   * 
   * @param title the title of the question
   * @param question the body of the question
   * @return true if the user selected "yes", false if the user selected "no"
   */
  public boolean displayQuestionMessage(String title, String question);

  /**
   * Instructs the view to display an error dialog with the specified content.
   * 
   * @param title the title of the error
   * @param errorMessage the content of the error
   */
  public void showErrorDialog(String title, String errorMessage);

  /** The null adapter */
  public static final IModelToViewAdapter NULL_OBJECT = new IModelToViewAdapter() {

    @Override
    public IChatroomToViewAdapter makeChatroomToViewAdapter(Chatroom room) {
      return null;
    }

    @Override
    public void showInformationDialog(String title, String message) {

    }

    @Override
    public boolean displayQuestionMessage(String title, String question) {
      return false;
    }

    @Override
    public void showErrorDialog(String title, String errorMessage) {

    }

    @Override
    public IChatroomID chooseChatroomToJoin(List<IChatroomID> chatroomIds) {
      return null;
    }

    @Override
    public Chatroom chooseChatroomToInvite(List<Chatroom> chatrooms) {
      return null;
    }
  };
}
