package com.seanharger.chatapp.model;

import java.net.Inet4Address;
import java.rmi.RemoteException;

import provided.datapacket.DataPacket;
import common.message.connect.IConnectMessage;
import common.user.IUser;

public class User implements IUser {

  private static final long serialVersionUID = 3663268407108655497L;

  private String name;
  private Inet4Address ipAddress;
  private IUserRemote remoteUserStub;
  
  public User(String name, Inet4Address ipAddress, IUserRemote remoteUserStub) {
    this.name = name;
    this.ipAddress = ipAddress;
    this.remoteUserStub = remoteUserStub;
  }
  
  @Override
  public String toString() {
    return this.name;
  }
  
  @Override
  public Inet4Address getIPAddress() {
    return this.ipAddress;
  }

  @Override
  public DataPacket<? extends IConnectMessage> sendMessage(
      DataPacket<? extends IConnectMessage> message) throws RemoteException {
    return remoteUserStub.sendMessage(message);
  }
  
  @Override
  public boolean equals(Object other) {
    if (other instanceof User) {
      return equals((User) other);
    }
    return false;
  }
  
  public boolean equals(User other) {
    return this.name.equals(other.name) && this.ipAddress.equals(other.ipAddress);
  }
  
  @Override
  public int hashCode() {
    return this.name.hashCode() + this.ipAddress.hashCode();
  }

}