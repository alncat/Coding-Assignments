package com.seanharger.chatapp.model.chatroom;

import java.awt.Component;


/**
 * Adapter used by the chatroom to communicate with its personal chatroom view.
 * 
 * @author Jayson Carter, Sean Harger
 *
 */

public interface IChatroomToViewAdapter {

  /**
   * Displays a message on the chatroom's view.
   * @param message the message to display
   */
  public void displayMessage(String message);
  
  /**
   * Adds a view component to the chatroom's view.
   * @param comp the view to add
   */
  public void addComponent(Component comp);

  /**
   * Instructs the view to remove this chatroom's view from the main view.
   */
  public void removeChatroom();
  
  public void showErrorDialog(String title, String errorMessage);

  /** The null adapter */
  public static final IChatroomToViewAdapter NULL_OBJECT = new IChatroomToViewAdapter() {

    @Override
    public void displayMessage(String message) {

    }

    @Override
    public void removeChatroom() {

    }

    @Override
    public void addComponent(Component comp) {
      
    }

    @Override
    public void showErrorDialog(String title, String errorMessage) {
      // TODO Auto-generated method stub
      
    }

   

  };

  
}
