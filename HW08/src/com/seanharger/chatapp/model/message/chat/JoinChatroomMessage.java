package com.seanharger.chatapp.model.message.chat;

import common.chatroom.IChatroomToChatroomAdapter;
import common.message.chat.IJoinChatroomMessage;

public class JoinChatroomMessage implements IJoinChatroomMessage {

  private static final long serialVersionUID = -6196612404147031393L;

  private IChatroomToChatroomAdapter chatroomAdapter;
  
  public JoinChatroomMessage(IChatroomToChatroomAdapter chatroomAdapter) {
    this.chatroomAdapter = chatroomAdapter;
  }
  
  @Override
  public IChatroomToChatroomAdapter getAdapter() {
    return this.chatroomAdapter;
  }

}