package com.seanharger.chatapp.model.message.connect;

import common.chatroom.IChatroomID;
import common.message.connect.IRequestChatroomMessage;

public class RequestChatroomMessage implements IRequestChatroomMessage {

  private static final long serialVersionUID = 3316805928956033335L;

  private IChatroomID chatroomId;
  private String name;

  public RequestChatroomMessage(IChatroomID chatroomId, String name) {
    this.chatroomId = chatroomId;
    this.name = name;
  }

  @Override
  public IChatroomID getChatroomID() {
    return chatroomId;
  }
  
  @Override
  public String getName() {
    return name;
  }

}
