package com.seanharger.chatapp.model.message.connect;

import java.util.List;

import common.chatroom.IChatroomID;
import common.chatroom.IChatroomToChatroomAdapter;
import common.message.connect.IChatroomInviteMessage;

public class ChatroomInviteMessage implements IChatroomInviteMessage {

  private static final long serialVersionUID = 7984242124785610055L;

  private IChatroomID chatroomId;
  private List<IChatroomToChatroomAdapter> memberAdapters;

  public ChatroomInviteMessage(IChatroomID chatroomId,
      List<IChatroomToChatroomAdapter> memberAdapters) {
    this.chatroomId = chatroomId;
    this.memberAdapters = memberAdapters;
  }

  @Override
  public IChatroomID getChatroomID() {
    return chatroomId;
  }

  @Override
  public List<IChatroomToChatroomAdapter> getMemberAdapters() {
    return memberAdapters;
  }

}
