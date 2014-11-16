package com.seanharger.chatapp.model;

import java.rmi.Remote;
import java.rmi.RemoteException;

import provided.datapacket.DataPacket;
import common.message.connect.IConnectMessage;
import common.user.IUser;

/**
 * Remote interface for calling methods on a remote user.
 * 
 * @author Jayson Carter, Sean Harger
 *
 */
public interface IUserRemote extends Remote {
  
  /**
   * Sends a message to the remote user.
   * @param message the message to send
   * @param sender the adapter to the sender
   * @return a data packet
   * @throws RemoteException if there was an error transmitting the message remotely
   */
  public DataPacket<? extends IConnectMessage> sendMessage(
      DataPacket<? extends IConnectMessage> message, IUser sender) throws RemoteException;

}
