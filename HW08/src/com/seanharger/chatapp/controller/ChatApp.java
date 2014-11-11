package com.seanharger.chatapp.controller;

import java.util.List;

import com.seanharger.chatapp.model.ChatAppModel;
import com.seanharger.chatapp.model.IModelToViewAdapter;
import com.seanharger.chatapp.model.chatroom.Chatroom;
import com.seanharger.chatapp.model.chatroom.IChatroomToViewAdapter;
import com.seanharger.chatapp.view.IViewToModelAdapter;
import com.seanharger.chatapp.view.MainFrame;
import com.seanharger.chatapp.view.roompanel.ChatroomPanel;
import com.seanharger.chatapp.view.roompanel.IViewToChatroomAdapter;

import common.chatroom.IChatroomID;

public class ChatApp {

  private MainFrame mainFrame;
  private ChatAppModel model;

  public ChatApp() {
    model = new ChatAppModel(new IModelToViewAdapter() {

      @Override
      public IChatroomToViewAdapter makeChatroomToViewAdapter(Chatroom room) {
        ChatroomPanel chatroomPanel =
            mainFrame.makeChatroomPanel(room.getName(), IViewToChatroomAdapter.NULL_OBJECT);
        chatroomPanel.installAdapter(new IViewToChatroomAdapter() {

          @Override
          public void send(String message) {
            room.sendMessage(message);
          }

          @Override
          public void leave() {
            room.leaveRoom();

          }

        });

        return new IChatroomToViewAdapter() {

          @Override
          public void displayMessage(String message) {
            chatroomPanel.appendTextToFrame(message);
          }

          @Override
          public void removeChatroom() {
            mainFrame.removeChatroomPanel(chatroomPanel);
          }

        };
      }

      @Override
      public void setChatroomOptions(List<IChatroomID> chatrooms) {
        mainFrame.setChatroomList(chatrooms);
      }

      @Override
      public void showInformationDialog(String title, String message) {
        mainFrame.showInformationDialog(title, message);
      }

      @Override
      public boolean displayQuestionMessage(String title, String question) {
        return mainFrame.displayQuestionMessage(title, question);
      }

      @Override
      public void showErrorDialog(String title, String errorMessage) {
        mainFrame.showErrorDialog(title, errorMessage);
      }

    });

    mainFrame = new MainFrame(new IViewToModelAdapter() {

      @Override
      public void createChatroom(String name) {
        model.makeRoom(name);
      }

      @Override
      public void joinChatroom(String ipAddress) {
        model.getChatrooms(ipAddress);
      }

      @Override
      public void requestToJoin(IChatroomID chatroomId) {
        model.requestToJoin(chatroomId);
      }

    });

  }

  public void start() {
    mainFrame.start();
    model.start();
  }

  public static void main(String[] args) {
    (new ChatApp()).start();
  }
}
