package com.seanharger.chatapp.model.message.chat;

import common.message.chat.ITextMessage;

public class TextMessage implements ITextMessage {

  private static final long serialVersionUID = 2471798252842661688L;

  private String text;
  
  public TextMessage(String text) {
    this.text = text;
  }
  
  @Override
  public String getText() {
    return this.text;
  }

}