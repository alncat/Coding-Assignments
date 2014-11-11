package com.seanharger.chatapp.model;

import java.util.List;

import com.seanharger.chatapp.model.chatroom.Chatroom;
import com.seanharger.chatapp.model.chatroom.IChatroomToViewAdapter;

import common.chatroom.IChatroomID;

public interface IModelToViewAdapter {

  public IChatroomToViewAdapter makeChatroomToViewAdapter(Chatroom room);

  public void setChatroomOptions(List<IChatroomID> chatroomIds);

  public void showInformationDialog(String title, String message);

  public boolean displayQuestionMessage(String title, String question);
  
  public void showErrorDialog(String title, String errorMessage);

  public static final IModelToViewAdapter NULL_OBJECT = new IModelToViewAdapter() {

    @Override
    public IChatroomToViewAdapter makeChatroomToViewAdapter(Chatroom room) {
      return null;
    }

    @Override
    public void setChatroomOptions(List<IChatroomID> chatroomIds) {

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

  };
}