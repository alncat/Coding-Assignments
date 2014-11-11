package com.seanharger.chatapp.view;

import common.chatroom.IChatroomID;

public interface IViewToModelAdapter {

  public void createChatroom(String name);

  public void joinChatroom(String ipAddress);

  public void requestToJoin(IChatroomID chatroomId);

  public static final IViewToModelAdapter NULL_OBJECT = new IViewToModelAdapter() {

    @Override
    public void createChatroom(String name) {

    }

    @Override
    public void joinChatroom(String ipAddress) {

    }

    @Override
    public void requestToJoin(IChatroomID chatroomId) {

    }

  };

}
