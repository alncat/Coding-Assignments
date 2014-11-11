package com.seanharger.chatapp.model.chatroom;

public interface IChatroomToViewAdapter {


  public void displayMessage(String message);

  public void removeChatroom();

  public static final IChatroomToViewAdapter NULL_OBJECT = new IChatroomToViewAdapter() {

    @Override
    public void displayMessage(String message) {

    }

    @Override
    public void removeChatroom() {

    }

  };
}
