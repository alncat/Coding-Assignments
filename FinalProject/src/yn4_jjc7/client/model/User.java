package yn4_jjc7.client.model;

import java.net.Inet4Address;
import java.rmi.RemoteException;

import provided.datapacket.DataPacket;
import common.message.connect.IConnectMessage;
import common.user.IUser;

/**
 * Concrete implmentation of the <code>IUser</code> adapter interface.
 * 
 * @author Jayson Carter, Sean Harger
 *
 */
public class User implements IUser {

  /** Serial ID */
  private static final long serialVersionUID = 3663268407108655497L;

  /** The name of the user */
  private String name;
  /** The IP address of the user */
  private Inet4Address ipAddress;
  /** The stub used to invoke methods on this user remotely. */
  private IUserRemote remoteUserStub;
  
  /**
   * Creates a new user adapter.
   * @param name the name of the user
   * @param ipAddress the IP address of the user
   * @param remoteUserStub the stub used to communicate with this user remotely
   */
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
      DataPacket<? extends IConnectMessage> message, IUser sender) throws RemoteException {
    return remoteUserStub.sendMessage(message, sender);
  }
  
  @Override
  public boolean equals(Object other) {
    if (other instanceof User) {
      return equals((User) other);
    }
    return false;
  }
  
  /**
   * Compares two Users for equality. Equality is defined as a user adapter with the same name
   * and same IP address.
   * @param other the other user to compare with
   * @return true if the other user is equal to this one, false otherwise
   */
  public boolean equals(User other) {
    return this.name.equals(other.name) && this.ipAddress.equals(other.ipAddress);
  }
  
  @Override
  public int hashCode() {
    return this.name.hashCode() + this.ipAddress.hashCode();
  }

}
