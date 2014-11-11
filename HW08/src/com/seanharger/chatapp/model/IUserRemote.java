package com.seanharger.chatapp.model;

import java.rmi.Remote;
import java.rmi.RemoteException;

import provided.datapacket.DataPacket;
import common.message.connect.IConnectMessage;

public interface IUserRemote extends Remote {
  
  public DataPacket<? extends IConnectMessage> sendMessage(
      DataPacket<? extends IConnectMessage> message) throws RemoteException;

}
