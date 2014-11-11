package com.seanharger.chatapp.model.message.chat;

import common.chatroom.IChatroomToChatroomAdapter;
import common.message.chat.ILeaveMessage;

public class LeaveMessage implements ILeaveMessage {

  private static final long serialVersionUID = -4363550554897869055L;

  private IChatroomToChatroomAdapter chatroomAdapter;
  
  public LeaveMessage(IChatroomToChatroomAdapter chatroomAdapter) {
    this.chatroomAdapter = chatroomAdapter;
  }
  
  @Override
  public IChatroomToChatroomAdapter getAdapter() {
    return this.chatroomAdapter;
  }

}