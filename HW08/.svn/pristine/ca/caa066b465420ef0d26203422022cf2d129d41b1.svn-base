package com.seanharger.chatapp.model.chatroom;

import java.rmi.RemoteException;

import provided.datapacket.DataPacket;
import common.chatroom.IChatroomID;
import common.chatroom.IChatroomToChatroomAdapter;
import common.message.chat.IChatMessage;
import common.user.IUser;

public class ChatroomToChatroomAdapter implements IChatroomToChatroomAdapter {

  private static final long serialVersionUID = 4992735721495585909L;

  private IUser user;
  private IChatroomID chatroomId;
  private IChatroomToChatroomRemote chatroomStub;
  
  public ChatroomToChatroomAdapter(IUser user, IChatroomID chatroomId,
      IChatroomToChatroomRemote chatroomStub) {
    this.user = user;
    this.chatroomId = chatroomId;
    this.chatroomStub = chatroomStub;
  }

  @Override
  public IUser getUser() {
    return this.user;
  }

  @Override
  public IChatroomID getChatroomID() {
    return this.chatroomId;
  }

  @Override
  public DataPacket<? extends IChatMessage> sendChatroomMessage(
      DataPacket<? extends IChatMessage> message, IChatroomToChatroomAdapter sendingAdapter)
      throws RemoteException {
    return chatroomStub.sendChatroomMessage(message, sendingAdapter);
  }

}
