package com.seanharger.chatapp.view.roompanel;

public interface IViewToChatroomAdapter {

  public void send(String message);

  public void leave();

  public static final IViewToChatroomAdapter NULL_OBJECT = new IViewToChatroomAdapter() {

    @Override
    public void send(String message) {

    }

    @Override
    public void leave() {

    }

  };
}
