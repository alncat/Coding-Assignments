package yn4_jjc7.client.model.message.chat.unknown;

import map.MapPanel;
import provided.datapacket.ADataPacketAlgoCmd;
import provided.datapacket.DataPacket;

import common.ICmd2ModelAdapter;
import common.chatroom.IChatroomToChatroomAdapter;
import common.message.NullMessage;
import common.message.chat.IChatMessage;

/**
 * Our unknown message implementation. Displays a "Donald duck" message in the chatroom and adds a picture of the famous
 * Disney icon as well.
 * 
 * @author Jayson Carter, Sean Harger
 *
 */
public class MapAlgo extends
		ADataPacketAlgoCmd<DataPacket<? extends IChatMessage>, Object, IChatroomToChatroomAdapter> {

	/** Serial ID */
	private static final long serialVersionUID = 858596597081344949L;

	/** The adapter that this unkown message uses to interact with the system it is installed on */
	private transient ICmd2ModelAdapter cmd2ModelAdpt;
	
	public MapAlgo(ICmd2ModelAdapter cmd2ModelAdpt) {
		this.cmd2ModelAdpt = cmd2ModelAdpt;
	}

	@Override
	public DataPacket<? extends IChatMessage> apply(Class<?> index, DataPacket<Object> host,
			IChatroomToChatroomAdapter... params) {
		cmd2ModelAdpt.append("Displayign map\n");
		MapPanel mp = new MapPanel();
		cmd2ModelAdpt.addComponent(mp);
		return new DataPacket<NullMessage>(NullMessage.class, NullMessage.SINGLETON);
	}

	@Override
	public void setCmd2ModelAdpt(ICmd2ModelAdapter cmd2ModelAdpt) {
		this.cmd2ModelAdpt = cmd2ModelAdpt;
	}

}
