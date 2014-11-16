package com.seanharger.chatapp.model.message.chat.unknown;

import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import provided.datapacket.ADataPacketAlgoCmd;
import provided.datapacket.DataPacket;
import common.ICmd2ModelAdapter;
import common.chatroom.IChatroomToChatroomAdapter;
import common.message.NullMessage;
import common.message.chat.IChatMessage;

/**
 * Our unknown message implementation. Displays a "Donald duck" message in the chatroom and adds
 * a picture of the famous Disney icon as well.
 * 
 * @author Jayson Carter, Sean Harger
 *
 */
public class DonaldAlgo extends
    ADataPacketAlgoCmd<DataPacket<? extends IChatMessage>, Object, IChatroomToChatroomAdapter> {

  /** Serial ID */
  private static final long serialVersionUID = 8229090220907590550L;

  /** The adapter that this unkown message uses to interact with the system it is installed on */
  private transient ICmd2ModelAdapter cmd2ModelAdpt;
  
  @Override
  public DataPacket<? extends IChatMessage> apply(Class<?> index, DataPacket<Object> host,
      IChatroomToChatroomAdapter... params) {
    cmd2ModelAdpt.append("Donald duck\n");
    String donaldUrl = "http://img1.wikia.nocookie.net/__cb20121120233839/scratchpad/images/1/1e/Donald_Duck.gif";
    JLabel photo;
    try {
      photo = new JLabel(new ImageIcon(new URL(donaldUrl)));
      cmd2ModelAdpt.addComponent(photo);
    } catch (MalformedURLException e) {
      System.err.println("Could not get Donald image.");
    }
    return new DataPacket<NullMessage>(NullMessage.class, NullMessage.SINGLETON);
  }

  @Override
  public void setCmd2ModelAdpt(ICmd2ModelAdapter cmd2ModelAdpt) {
    this.cmd2ModelAdpt = cmd2ModelAdpt;
  }

}
