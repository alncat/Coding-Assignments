package com.seanharger.chatapp.model.chatroom;

import java.net.Inet4Address;

import common.chatroom.IChatroomID;

public class ChatroomID implements IChatroomID {

  private static final long serialVersionUID = 2631838645808442211L;

  private Inet4Address address;
  private String name;
  private long time;

  public ChatroomID(Inet4Address address, String name) {
    this.address = address;
    this.name = name;
    this.time = System.currentTimeMillis();
  }

  public Inet4Address getAddress() {
    return address;
  }

  @Override
  public int hashCode() {
    return (int) (name.hashCode() + address.hashCode() + time);
  }

  @Override
  public String toString() {
    return name;
  }

  @Override
  public boolean equals(Object other) {
    if (other instanceof ChatroomID) {
      return equals((ChatroomID) other);
    }
    return false;
  }

  public boolean equals(ChatroomID other) {
    return name.equals(other.name) && time == other.time;
  }
}