package yn4_jjc7.client.model.message.chat;

import provided.datapacket.ADataPacketAlgoCmd;
import provided.datapacket.DataPacket;
import common.chatroom.IChatroomToChatroomAdapter;
import common.message.chat.IChatMessage;
import common.message.chat.ISendCmdMessage;

/**
 * Concrete wrapper for the <code>ISendCmdMessage</code>.
 * 
 * @author Jayson Carter, Sean Harger
 *
 */
public class SendCmdMessage implements ISendCmdMessage {

  /** Serial ID */
  private static final long serialVersionUID = 6698485597098711444L;

  /** The class that the enclosed command is designed to process */
  private Class<?> cmdId;
  /** The command to process messages of class <code>cmdId</code> */
  private ADataPacketAlgoCmd<DataPacket<? extends IChatMessage>, ?, IChatroomToChatroomAdapter> cmd;

  /**
   * Creates a new send command message with the class that the command is designed to process and
   * the comamnd itself.
   * 
   * @param cmdId the class of the messages that the command processes
   * @param cmd the command itself
   */
  public SendCmdMessage(Class<?> cmdId,
      ADataPacketAlgoCmd<DataPacket<? extends IChatMessage>, ?, IChatroomToChatroomAdapter> cmd) {
    this.cmdId = cmdId;
    this.cmd = cmd;
  }

  @Override
  public ADataPacketAlgoCmd<DataPacket<? extends IChatMessage>, ?, IChatroomToChatroomAdapter> getCmd() {
    return this.cmd;
  }

  @Override
  public Class<?> getCmdID() {
    return this.cmdId;
  }

}
