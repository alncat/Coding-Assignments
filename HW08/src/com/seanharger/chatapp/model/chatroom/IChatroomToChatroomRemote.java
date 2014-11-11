package com.seanharger.chatapp.model.chatroom;

import java.rmi.Remote;
import java.rmi.RemoteException;

import provided.datapacket.DataPacket;

import common.chatroom.IChatroomToChatroomAdapter;
import common.message.chat.IChatMessage;

public interface IChatroomToChatroomRemote extends Remote {

  public DataPacket<? extends IChatMessage> sendChatroomMessage(
      DataPacket<? extends IChatMessage> message, IChatroomToChatroomAdapter sendingAdapter)
      throws RemoteException;
}