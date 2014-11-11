package common.message.chat;

import common.chatroom.IChatroomToChatroomAdapter;

/**
 * Data type for ILeaveChatroom message, which indicates that the sender is
 * leaving the chatroom. This particular message should be sent through the
 * corresponding IChatroomAdapter. It should be responded to with a NullMessage
 *
 * @author Wilson Beebe
 *
 */
public interface ILeaveMessage extends IChatMessage {
	
    /**
     * Gets the adapter to the chatroom that sent the message
     *
     * @return the adapter to the chatroom that sent the message
     */
    public IChatroomToChatroomAdapter getAdapter() ;
    
}