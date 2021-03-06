package com.seanharger.chatapp.model.chatroom;

import java.awt.Component;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import com.seanharger.chatapp.model.message.chat.JoinChatroomMessage;
import com.seanharger.chatapp.model.message.chat.LeaveMessage;
import com.seanharger.chatapp.model.message.chat.SendCmdMessage;
import com.seanharger.chatapp.model.message.chat.TextMessage;
import com.seanharger.chatapp.model.message.chat.RequestCmdMessage;
import com.seanharger.chatapp.model.message.chat.unknown.DonaldAlgo;
import com.seanharger.chatapp.model.message.chat.unknown.DonaldMessage;

import provided.datapacket.ADataPacketAlgoCmd;
import provided.datapacket.DataPacket;
import provided.datapacket.DataPacketAlgo;
import provided.mixedData.IMixedDataDictionary;
import provided.mixedData.MixedDataDictionary;
import common.ICmd2ModelAdapter;
import common.chatroom.IChatroomID;
import common.chatroom.IChatroomToChatroomAdapter;
import common.message.IErrorMessage;
import common.message.INullMessage;
import common.message.NullMessage;
import common.message.chat.IChatMessage;
import common.message.chat.IJoinChatroomMessage;
import common.message.chat.ILeaveMessage;
import common.message.chat.IPingMessage;
import common.message.chat.ITextMessage;
import common.user.IUser;
import common.message.chat.IRequestCmdMessage;
import common.message.chat.ISendCmdMessage;

/**
 * Model for a chatroom instance.
 * 
 * @author Jayson Carter, Sean Harger
 *
 */
public class Chatroom {
  /** The identifier for this chatroom */
  private IChatroomID chatroomId;
  /** The adapter to the view */
  private IChatroomToViewAdapter view;
  /** The adapter for the local user */
  private IUser thisUser;
  /** The list of adapters to all users in the chatroom (includes thisUser) */
  private List<IChatroomToChatroomAdapter> userChatroomAdapters;
  /** The mixed data dictionary for use by unknown messages sent to this chatrooom */
  private MixedDataDictionary data = new MixedDataDictionary();
  /** The adapter to this chatroom that will be sent to other users to communicate with us */
  private IChatroomToChatroomAdapter thisAdapter;
  /** The adapter to the main model that manages this chatroom */
  private IChatroomToModelAdapter model;
  /**
   * The remote that will be encapsulated in the thisUser adapter to help remote users communicate
   * with this chatroom instance
   */
  private IChatroomToChatroomRemote thisRemote = new IChatroomToChatroomRemote() {

    @Override
    public DataPacket<? extends IChatMessage> sendChatroomMessage(
        DataPacket<? extends IChatMessage> message, IChatroomToChatroomAdapter sendingAdapter)
        throws RemoteException {
      System.out.println("Processed a message");
      return message.execute(chatVisitor, sendingAdapter);
    }

  };

  /**
   * The visitor that will be used by this chatroom to process DataPacket objects that contain
   * IChatMessage objects
   */
  private DataPacketAlgo<DataPacket<? extends IChatMessage>, IChatroomToChatroomAdapter> chatVisitor =
      new DataPacketAlgo<DataPacket<? extends IChatMessage>, IChatroomToChatroomAdapter>(
          new ADataPacketAlgoCmd<DataPacket<? extends IChatMessage>, Object, IChatroomToChatroomAdapter>() {

            /* Unknown message case. */

            private static final long serialVersionUID = 7541508735919507513L;

            @Override
            public DataPacket<? extends IChatMessage> apply(Class<?> index,
                DataPacket<Object> host, IChatroomToChatroomAdapter... params) {
              IChatroomToChatroomAdapter sender = params[0];
              try {
                DataPacket<? extends IChatMessage> response =
                    sender.sendChatroomMessage(new DataPacket<IRequestCmdMessage>(
                        IRequestCmdMessage.class, new RequestCmdMessage(index)), thisAdapter);

                response.execute(chatVisitor, sender);
                return host.execute(chatVisitor, params);
              } catch (RemoteException e) {

              }
              return new DataPacket<INullMessage>(INullMessage.class, NullMessage.SINGLETON);
            }

            @Override
            public void setCmd2ModelAdpt(ICmd2ModelAdapter cmd2ModelAdpt) {

            }
          }) {

        private static final long serialVersionUID = -7462140994737597017L;

        {
          setCmd(
              INullMessage.class,
              new ADataPacketAlgoCmd<DataPacket<? extends IChatMessage>, Object, IChatroomToChatroomAdapter>() {

                /* Null message case */

                private static final long serialVersionUID = 5861230867628406578L;

                @Override
                public DataPacket<? extends IChatMessage> apply(Class<?> index,
                    DataPacket<Object> host, IChatroomToChatroomAdapter... params) {
                  return new DataPacket<INullMessage>(INullMessage.class, NullMessage.SINGLETON);
                }

                @Override
                public void setCmd2ModelAdpt(ICmd2ModelAdapter cmd2ModelAdpt) {

                }

              });

          setCmd(
              IErrorMessage.class,
              new ADataPacketAlgoCmd<DataPacket<? extends IChatMessage>, Object, IChatroomToChatroomAdapter>() {

                /* Error message case */

                private static final long serialVersionUID = 4826965457258701801L;

                @Override
                public DataPacket<? extends IChatMessage> apply(Class<?> index,
                    DataPacket<Object> host, IChatroomToChatroomAdapter... params) {
                  IErrorMessage error = (IErrorMessage) host;
                  error.getCause().printStackTrace();
                  return new DataPacket<INullMessage>(INullMessage.class, NullMessage.SINGLETON);
                }

                @Override
                public void setCmd2ModelAdpt(ICmd2ModelAdapter cmd2ModelAdpt) {

                }

              });

          setCmd(
              IPingMessage.class,
              new ADataPacketAlgoCmd<DataPacket<? extends IChatMessage>, Object, IChatroomToChatroomAdapter>() {

                /* Ping case */

                private static final long serialVersionUID = 914807586642021802L;

                @Override
                public DataPacket<? extends IChatMessage> apply(Class<?> index,
                    DataPacket<Object> host, IChatroomToChatroomAdapter... params) {
                  return new DataPacket<INullMessage>(INullMessage.class, NullMessage.SINGLETON);
                }

                @Override
                public void setCmd2ModelAdpt(ICmd2ModelAdapter cmd2ModelAdpt) {

                }

              });

          setCmd(
              IRequestCmdMessage.class,
              new ADataPacketAlgoCmd<DataPacket<? extends IChatMessage>, Object, IChatroomToChatroomAdapter>() {

                private static final long serialVersionUID = 3295103158269328527L;

                /*
                 * Request command case: called when a user needs a command for a message they did
                 * not know how to process
                 */


                @Override
                public DataPacket<? extends IChatMessage> apply(Class<?> index,
                    DataPacket<Object> host, IChatroomToChatroomAdapter... params) {
                  IRequestCmdMessage requestCmdMsg = (IRequestCmdMessage) host.getData();
                  return new DataPacket<ISendCmdMessage>(
                      ISendCmdMessage.class,
                      new SendCmdMessage(
                          requestCmdMsg.getMessageID(),
                          (ADataPacketAlgoCmd<DataPacket<? extends IChatMessage>, Class<?>, IChatroomToChatroomAdapter>) chatVisitor
                              .getCmd(requestCmdMsg.getMessageID())));
                }

                @Override
                public void setCmd2ModelAdpt(ICmd2ModelAdapter cmd2ModelAdpt) {

                }

              });

          setCmd(
              ISendCmdMessage.class,
              new ADataPacketAlgoCmd<DataPacket<? extends IChatMessage>, Object, IChatroomToChatroomAdapter>() {

                private static final long serialVersionUID = -1746480363664278026L;

                /*
                 * Send command case: called when we received the command to handle a message that
                 * we did not know how to process
                 */


                @Override
                public DataPacket<? extends IChatMessage> apply(Class<?> index,
                    DataPacket<Object> host, IChatroomToChatroomAdapter... params) {
                  ISendCmdMessage requestCmdMsg = (ISendCmdMessage) host.getData();
                  requestCmdMsg.getCmd().setCmd2ModelAdpt(new ICmd2ModelAdapter() {

                    @Override
                    public void addComponent(Component component) {
                      view.addComponent(component);
                    }

                    @Override
                    public void append(String text) {
                      view.displayMessage(text);
                    }

                    @Override
                    public IMixedDataDictionary getMixedDataDictionary() {
                      return data;
                    }

                  });
                  chatVisitor.setCmd(requestCmdMsg.getCmdID(), requestCmdMsg.getCmd());
                  return new DataPacket<INullMessage>(INullMessage.class, NullMessage.SINGLETON);
                }

                @Override
                public void setCmd2ModelAdpt(ICmd2ModelAdapter cmd2ModelAdpt) {

                }

              });

          setCmd(
              ITextMessage.class,
              new ADataPacketAlgoCmd<DataPacket<? extends IChatMessage>, Object, IChatroomToChatroomAdapter>() {

                private static final long serialVersionUID = 469976173113304452L;

                /* Text message case: called when we receive a chat message from another user */


                @Override
                public DataPacket<? extends IChatMessage> apply(Class<?> index,
                    DataPacket<Object> host, IChatroomToChatroomAdapter... params) {
                  ITextMessage txtMessage = (ITextMessage) host.getData();
                  IChatroomToChatroomAdapter sendingRoom = (IChatroomToChatroomAdapter) params[0];
                  view.displayMessage(String.format("%s : %s", sendingRoom.getUser().toString(),
                      txtMessage.getText()));
                  return new DataPacket<INullMessage>(INullMessage.class, NullMessage.SINGLETON);
                }

                @Override
                public void setCmd2ModelAdpt(ICmd2ModelAdapter cmd2ModelAdpt) {

                }

              });

          setCmd(
              IJoinChatroomMessage.class,
              new ADataPacketAlgoCmd<DataPacket<? extends IChatMessage>, Object, IChatroomToChatroomAdapter>() {

                /* Join chatroom case: called when a new user joined this chatroom */

                private static final long serialVersionUID = 4533714929612084218L;

                @Override
                public DataPacket<? extends IChatMessage> apply(Class<?> index,
                    DataPacket<Object> host, IChatroomToChatroomAdapter... params) {
                  IJoinChatroomMessage joinMessage = (IJoinChatroomMessage) host.getData();
                  IChatroomToChatroomAdapter sendingRoom = params[0];
                  userChatroomAdapters.add(joinMessage.getAdapter());
                  view.displayMessage(String.format("%s joined the room.", sendingRoom.getUser()
                      .toString()));
                  return new DataPacket<INullMessage>(INullMessage.class, NullMessage.SINGLETON);
                }

                @Override
                public void setCmd2ModelAdpt(ICmd2ModelAdapter cmd2ModelAdpt) {

                }

              });

          setCmd(
              ILeaveMessage.class,
              new ADataPacketAlgoCmd<DataPacket<? extends IChatMessage>, Object, IChatroomToChatroomAdapter>() {

                private static final long serialVersionUID = 3390382639006865241L;

                /* Leave message case: called when a user in this chatroom leaves the room */


                @Override
                public DataPacket<? extends IChatMessage> apply(Class<?> index,
                    DataPacket<Object> host, IChatroomToChatroomAdapter... params) {
                  ILeaveMessage leaveMessage = (ILeaveMessage) host.getData();

                  System.out.println("Existing adapters");
                  for (IChatroomToChatroomAdapter a : userChatroomAdapters) {
                    System.out.println("existing" + a.getChatroomID() + ", " + a.getUser()
                        + Integer.toString(a.hashCode()));
                  }
                  System.out.println("leaveMesssage" + leaveMessage.getAdapter().getChatroomID()
                      + ", " + leaveMessage.getAdapter().getUser()
                      + Integer.toString(leaveMessage.getAdapter().hashCode()));
                  userChatroomAdapters.remove(leaveMessage.getAdapter());
                  System.out.println("-----------------------");
                  System.out.println("user list: " + userChatroomAdapters);
                  view.displayMessage(String.format("%s left the room.", leaveMessage.getAdapter()
                      .getUser().toString()));
                  return new DataPacket<INullMessage>(INullMessage.class, NullMessage.SINGLETON);
                }

                @Override
                public void setCmd2ModelAdpt(ICmd2ModelAdapter cmd2ModelAdpt) {

                }

              });

          DonaldAlgo donaldAlgo = new DonaldAlgo();
          donaldAlgo.setCmd2ModelAdpt(new ICmd2ModelAdapter() {

            @Override
            public void addComponent(Component component) {
              view.addComponent(component);
            }

            @Override
            public void append(String text) {
              view.displayMessage(text);
            }

            @Override
            public IMixedDataDictionary getMixedDataDictionary() {
              return data;
            }

          });

          /*
           * The unknown message case that will be sent to other users that do not know how to
           * process
           */

          setCmd(DonaldMessage.class, donaldAlgo);

        }
      };

  /**
   * Constructs a chatroom instance from an existing unique chatroom ID and list of user adapters.
   * 
   * @param chatroomId the unique identifier for this chatroom instance
   * @param thisUser the local user's adapter
   * @param view the adapter to the chatroom's view
   * @param model the adapter to the model that manages this chatroom
   * @param userStubs the list of adapters to other users in this chatroom
   */
  public Chatroom(IChatroomID chatroomId, IUser thisUser, IChatroomToViewAdapter view,
      IChatroomToModelAdapter model, List<IChatroomToChatroomAdapter> userStubs) {
    this.thisUser = thisUser;
    this.view = view;
    this.userChatroomAdapters = userStubs;
    this.chatroomId = chatroomId;
    this.model = model;

    try {
      IChatroomToChatroomRemote thisChatroomStub =
          (IChatroomToChatroomRemote) UnicastRemoteObject.exportObject(thisRemote, 2101);
      this.thisAdapter = new ChatroomToChatroomAdapter(thisUser, chatroomId, thisChatroomStub);
      this.userChatroomAdapters.add(this.thisAdapter);

      IJoinChatroomMessage joinMessage = new JoinChatroomMessage(this.thisAdapter);

      for (IChatroomToChatroomAdapter existingUser : this.userChatroomAdapters) {
        if (existingUser.getUser().equals(this.thisUser)) {
          continue;
        }

        new Thread(() -> {
          try {
            existingUser.sendChatroomMessage(new DataPacket<IJoinChatroomMessage>(
                IJoinChatroomMessage.class, joinMessage), this.thisAdapter);
          } catch (RemoteException e) {
            removeBrokenAdapter(existingUser);
          }
        }).start();
      }
    } catch (RemoteException e) {
      e.printStackTrace();
      System.exit(-1);
    }
  }

  /**
   * Constructs a new chatroom instance with a new unique chatroom ID and an empty list of users
   * except for the local user.
   * 
   * @param name the name for this chatroom
   * @param thisUser the local user's adapter
   * @param view the adapter to the chatroom's view
   * @param model the adapter to the model that manages this chatroom
   */
  public Chatroom(String name, IUser thisUser, IChatroomToViewAdapter view,
      IChatroomToModelAdapter model) {
    this(new ChatroomID(thisUser.getIPAddress(), name), thisUser, view, model,
        new CopyOnWriteArrayList<IChatroomToChatroomAdapter>());
  }

  /**
   * Returns the unique identifier for this chatroom.
   * 
   * @return the unique identifier for this chatroom
   */
  public IChatroomID getChatroomID() {
    return this.chatroomId;
  }

  /**
   * Returns the name of this chatroom.
   * 
   * @return the name of this chatroom
   */
  public String getName() {
    return this.chatroomId.toString();
  }

  /**
   * Returns the list of user adapters of the members of this chatroom.
   * 
   * @return the list of user adapters of the members of this chatroom
   */
  public List<IChatroomToChatroomAdapter> getUserStubs() {
    return userChatroomAdapters;
  }

  /**
   * Installs the specified adapter as this chatroom's view adapter.
   * 
   * @param view the view adapter to install
   */
  public void installViewAdapter(IChatroomToViewAdapter view) {
    this.view = view;
  }

  /**
   * Asynchronously sends leave messages to all connected users in the chatroom and removes the
   * chatroom view from the GUI via an adapter. After all messages have been sent, the reference to
   * this chatroom is removed from the model that manages this chatroom via an adapter.
   */
  public void leaveRoom() {
    LeaveMessage leaveMessage = new LeaveMessage(this.thisAdapter);

    userChatroomAdapters.remove(thisAdapter);

    view.removeChatroom();

    for (IChatroomToChatroomAdapter room : userChatroomAdapters) {
      new Thread(() -> {
        try {
          room.sendChatroomMessage(
              new DataPacket<ILeaveMessage>(ILeaveMessage.class, leaveMessage), this.thisAdapter);
        } catch (RemoteException e) {

        }
      }).start();
    }

    model.removeChatroom(this);
  }

  /**
   * Removes the specified chatroom adapter from the list of adapters and displays an informative
   * message to the view informing that the chatroom has lost connection with the owner of that
   * adapter.
   * 
   * @param adapter the broken adapter to remove
   */
  private void removeBrokenAdapter(IChatroomToChatroomAdapter adapter) {
    view.displayMessage("Lost connection with " + adapter.getUser());
    userChatroomAdapters.remove(adapter);
  }

  /**
   * Asynchronously sends the specified message to all chatrooms in the
   * <code>userChatroomAdapters</code> list. If a message fails to send to any recipient, then the
   * adapter for that recipient is declared broken and handled by the
   * <code>removeBrokenAdapter</code> method.
   * 
   * @param message the message to send
   */
  public void sendMessage(String message) {
    TextMessage txtMessage = new TextMessage(message);

    for (IChatroomToChatroomAdapter room : userChatroomAdapters) {
      System.out.println("Sending message to " + room.getUser().toString());
      new Thread(() -> {
        try {
          room.sendChatroomMessage(new DataPacket<ITextMessage>(ITextMessage.class, txtMessage),
              thisAdapter);
        } catch (RemoteException e) {
          removeBrokenAdapter(room);
        }
      }).start();
    }
  }

  /**
   * Asynchronously sends the unknown message type to all chatrooms in the
   * <code>userChatroomAdapters</code> list. If a message fails to send to any recipient, then the
   * adapter for that recipient is declared broken and handled by the
   * <code>removeBrokenAdapter</code> method.
   */
  public void sendUnknown() {
    List<IChatroomToChatroomAdapter> removeLater = new ArrayList<IChatroomToChatroomAdapter>();

    for (IChatroomToChatroomAdapter room : userChatroomAdapters) {
      new Thread(() -> {
        try {
          room.sendChatroomMessage(new DataPacket<DonaldMessage>(DonaldMessage.class,
              new DonaldMessage()), this.thisAdapter);
        } catch (RemoteException e) {
          removeLater.add(room);
        }
      }).start();
    }
  }

  /**
   * Returns the name of this chatroom.
   * 
   * @return the name of this chatroom
   */
  public String toString() {
    return this.chatroomId.toString();
  }
}
