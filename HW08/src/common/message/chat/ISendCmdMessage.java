package common.message.chat;

import provided.datapacket.ADataPacketAlgoCmd;
import provided.datapacket.DataPacket;

import common.chatroom.IChatroomToChatroomAdapter;

/**
 * Message for sending the lambda for an unknown command.
 *
 * @author Wilson Beebe, Jake Kornblau, Derek Peirce
 */
public interface ISendCmdMessage extends IChatMessage {

    /**
     * The command being sent.
     *
     * @return the command that was sent.
     */
    public ADataPacketAlgoCmd<DataPacket<? extends IChatMessage>, ?, IChatroomToChatroomAdapter> getCmd();

}