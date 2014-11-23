package com.seanharger.chatapp.model.message.chat;

import common.message.chat.IRequestCmdMessage;

/**
 * Concrete wrapper for a <code>IRequestCmdMessage</cmd>.
 * 
 * @author Jayson Carter, Sean Harger
 *
 */
public class RequestCmdMessage implements IRequestCmdMessage {

  /** Serial ID */
  private static final long serialVersionUID = 2492950703515176918L;

  /** The class of that the command should be able to handle */
  private Class<?> messageId;

  /**
   * Creates a new request command message to handle a host with the specified class.
   * 
   * @param messageId the class that the command should be able to process
   */
  public RequestCmdMessage(Class<?> messageId) {
    this.messageId = messageId;
  }

  @Override
  public Class<?> getMessageID() {
    return this.messageId;
  }

}
