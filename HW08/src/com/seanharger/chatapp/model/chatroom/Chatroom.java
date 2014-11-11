package com.seanharger.chatapp.model.chatroom;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import com.seanharger.chatapp.model.message.chat.JoinChatroomMessage;
import com.seanharger.chatapp.model.message.chat.LeaveMessage;
import com.seanharger.chatapp.model.message.chat.TextMessage;

import provided.datapacket.ADataPacketAlgoCmd;
import provided.datapacket.DataPacket;
import provided.datapacket.DataPacketAlgo;
import common.ICmd2ModelAdapter;
import common.chatroom.IChatroomID;
import common.chatroom.IChatroomToChatroomAdapter;
import common.message.NullMessage;
import common.message.chat.IChatMessage;
import common.message.chat.IJoinChatroomMessage;
import common.message.chat.ILeaveMessage;
import common.message.chat.ITextMessage;
import common.message.connect.IChatroomInviteMessage;
import common.message.connect.IConnectMessage;
import common.user.IUser;

public class Chatroom {
  private IChatroomID chatroomId;
  private IChatroomToViewAdapter view;
  private String name;
  private IUser thisUser;
  private List<IChatroomToChatroomAdapter> userChatroomAdapters;

  private DataPacketAlgo<DataPacket<? extends IChatMessage>, IChatroomToChatroomAdapter> chatHost =
      new DataPacketAlgo<DataPacket<? extends IChatMessage>, IChatroomToChatroomAdapter>(
          new ADataPacketAlgoCmd<DataPacket<? extends IChatMessage>, Object, IChatroomToChatroomAdapter>() {

            private static final long serialVersionUID = 7541508735919507513L;

            @Override
            public DataPacket<? extends IChatMessage> apply(Class<?> index,
                DataPacket<Object> host, IChatroomToChatroomAdapter... params) {
              return null;
            }

            @Override
            public void setCmd2ModelAdpt(ICmd2ModelAdapter cmd2ModelAdpt) {

            }
          }) {

        private static final long serialVersionUID = -7385090018966824899L;

        {
          setCmd(
              ITextMessage.class,
              new ADataPacketAlgoCmd<DataPacket<? extends IChatMessage>, Object, IChatroomToChatroomAdapter>() {

                private static final long serialVersionUID = 4783116422972675010L;

                @Override
                public DataPacket<? extends IChatMessage> apply(Class<?> index,
                    DataPacket<Object> host, IChatroomToChatroomAdapter... params) {
                  ITextMessage txtMessage = (ITextMessage) host.getData();
                  IChatroomToChatroomAdapter sendingRoom = (IChatroomToChatroomAdapter) params[0];
                  view.displayMessage(String.format("%s : %s", sendingRoom.getUser().toString(),
                      txtMessage.getText()));
                  return new DataPacket<NullMessage>(NullMessage.class, NullMessage.SINGLETON);
                }

                @Override
                public void setCmd2ModelAdpt(ICmd2ModelAdapter cmd2ModelAdpt) {

                }

              });

          setCmd(
              IJoinChatroomMessage.class,
              new ADataPacketAlgoCmd<DataPacket<? extends IChatMessage>, Object, IChatroomToChatroomAdapter>() {

                private static final long serialVersionUID = 3963012518156912018L;

                @Override
                public DataPacket<? extends IChatMessage> apply(Class<?> index,
                    DataPacket<Object> host, IChatroomToChatroomAdapter... params) {
                  IJoinChatroomMessage joinMessage = (IJoinChatroomMessage) host.getData();
                  IChatroomToChatroomAdapter sendingRoom = params[0];
                  userChatroomAdapters.add(joinMessage.getAdapter());
                  view.displayMessage(String.format("%s joined the room.", sendingRoom.getUser()
                      .toString()));
                  return new DataPacket<NullMessage>(NullMessage.class, NullMessage.SINGLETON);
                }

                @Override
                public void setCmd2ModelAdpt(ICmd2ModelAdapter cmd2ModelAdpt) {

                }

              });

          setCmd(
              ILeaveMessage.class,
              new ADataPacketAlgoCmd<DataPacket<? extends IChatMessage>, Object, IChatroomToChatroomAdapter>() {

                private static final long serialVersionUID = 3963012518156912018L;

                @Override
                public DataPacket<? extends IChatMessage> apply(Class<?> index,
                    DataPacket<Object> host, IChatroomToChatroomAdapter... params) {
                  ILeaveMessage leaveMessage = (ILeaveMessage) host.getData();
                  userChatroomAdapters.remove(leaveMessage.getAdapter());
                  view.displayMessage(String.format("%s left the room.", leaveMessage.getAdapter()
                      .getUser().toString()));
                  return new DataPacket<NullMessage>(NullMessage.class, NullMessage.SINGLETON);
                }

                @Override
                public void setCmd2ModelAdpt(ICmd2ModelAdapter cmd2ModelAdpt) {

                }

              });

        }
      };

  private IChatroomToChatroomAdapter thisAdapter;

  private IChatroomToChatroomRemote thisRemote = new IChatroomToChatroomRemote() {

    @Override
    public DataPacket<? extends IChatMessage> sendChatroomMessage(
        DataPacket<? extends IChatMessage> message, IChatroomToChatroomAdapter sendingAdapter)
        throws RemoteException {
      System.out.println("Processed a message");
      return message.execute(chatHost, sendingAdapter);
    }

  };

  public Chatroom(String name, IUser thisUser, IChatroomToViewAdapter view,
      List<IChatroomToChatroomAdapter> userStubs) {
    this.name = name;
    this.thisUser = thisUser;
    this.view = view;
    this.userChatroomAdapters = userStubs;
    this.chatroomId = new ChatroomID(null, name);

    try {
      IChatroomToChatroomRemote thisChatroomStub =
          (IChatroomToChatroomRemote) UnicastRemoteObject.exportObject(thisRemote, 2101);
      this.thisAdapter = new ChatroomToChatroomAdapter(thisUser, chatroomId, thisChatroomStub);
      this.userChatroomAdapters.add(this.thisAdapter);

      new Thread(() -> {
        IJoinChatroomMessage joinMessage = new JoinChatroomMessage(this.thisAdapter);

        for (IChatroomToChatroomAdapter existingUser : this.userChatroomAdapters) {
          if (existingUser.getChatroomID().equals(this.chatroomId)) {
            continue;
          }

          try {
            existingUser.sendChatroomMessage(new DataPacket<IJoinChatroomMessage>(
                IJoinChatroomMessage.class, joinMessage), this.thisAdapter);
          } catch (Exception e) {
            e.printStackTrace();
          }
        }
      }).start();;
    } catch (RemoteException e) {
      e.printStackTrace();
      System.exit(-1);
    }
  }

  public Chatroom(String name, IUser thisUser, IChatroomToViewAdapter view) {
    this(name, thisUser, view, new ArrayList<IChatroomToChatroomAdapter>());
  }

  public String getName() {
    return this.name;
  }

  public List<IChatroomToChatroomAdapter> getUserStubs() {
    return userChatroomAdapters;
  }

  public void installAdapter(IChatroomToViewAdapter view) {
    this.view = view;
  }

  public void sendMessage(String message) {
    new Thread(() -> {
      TextMessage txtMessage = new TextMessage(message);

      for (IChatroomToChatroomAdapter room : userChatroomAdapters) {
        try {
          room.sendChatroomMessage(new DataPacket<ITextMessage>(ITextMessage.class, txtMessage),
              thisAdapter);
        } catch (RemoteException e) {
          e.printStackTrace();
        }
      }
    }).start();
  }

  public void leaveRoom() {
    new Thread(() -> {
      LeaveMessage leaveMessage = new LeaveMessage(this.thisAdapter);

      for (IChatroomToChatroomAdapter room : userChatroomAdapters) {
        try {
          room.sendChatroomMessage(
              new DataPacket<ILeaveMessage>(ILeaveMessage.class, leaveMessage), this.thisAdapter);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }

      view.removeChatroom();
    }).start();
  }

  public IChatroomID getChatroomID() {
    return this.chatroomId;
  }
}