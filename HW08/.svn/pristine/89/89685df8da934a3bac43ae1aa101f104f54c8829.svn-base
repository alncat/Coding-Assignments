package com.seanharger.chatapp.model.message.connect;

import java.util.ArrayList;
import java.util.List;

import common.chatroom.IChatroomID;
import common.message.connect.IChatroomsListMessage;

public class ChatroomsListMessage implements IChatroomsListMessage {

  private static final long serialVersionUID = 2609425865999238541L;

  public ArrayList<IChatroomID> chatroomIds;

  public ChatroomsListMessage() {
    this.chatroomIds = new ArrayList<IChatroomID>();
  }

  public void addRoom(IChatroomID chatroomId) {
    chatroomIds.add(chatroomId);
  }

  @Override
  public List<IChatroomID> getChatroomIDs() {
    return this.chatroomIds;
  }

}
