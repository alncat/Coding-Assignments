package yn4_jjc7.client.model.message.chat.unknown;

import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import provided.datapacket.ADataPacketAlgoCmd;
import provided.datapacket.DataPacket;

import common.ICmd2ModelAdapter;
import common.chatroom.IChatroomAdapter;
import common.message.NullMessage;
import common.message.chat.IChatMessage;

/**
 * Our unknown message implementation. Displays a "Donald duck" message in the chatroom and adds a picture of the famous
 * Disney icon as well.
 * 
 * @author Jayson Carter, Sean Harger
 */
public class DonaldAlgo
		extends ADataPacketAlgoCmd<DataPacket<? extends IChatMessage>, Object, IChatroomAdapter> {

	/** Serial ID */
	private static final long serialVersionUID = 8229090220907590550L;

	/** The adapter that this unknown message uses to interact with the system it is installed on */
	private transient ICmd2ModelAdapter cmd2ModelAdpt;

	/** URL of the Donald Duck picture */
	private final String donaldUrl =
			"http://img1.wikia.nocookie.net/__cb20121120233839/scratchpad/images/1/1e/Donald_Duck.gif";

	/**
	 * Constructor for DonaldAlgo
	 * @param cmd2ModelAdpt TODO comments
	 */
	public DonaldAlgo(ICmd2ModelAdapter cmd2ModelAdpt) {
		this.cmd2ModelAdpt = cmd2ModelAdpt;
	}

	@Override
	public DataPacket<? extends IChatMessage> apply(Class<?> index, DataPacket<Object> host,
			IChatroomAdapter... params) {

		cmd2ModelAdpt.append("Donald Duck\n");

		JLabel photo;
		try {
			photo = new JLabel(new ImageIcon(new URL(donaldUrl)));
			cmd2ModelAdpt.addComponent(photo, "Donald Duck");
		} catch (MalformedURLException e) {
			System.err.println("Could not get Donald image.");
		}
		return new DataPacket<NullMessage>(NullMessage.class, NullMessage.SINGLETON);
	}

	@Override
	public void setCmd2ModelAdpt(ICmd2ModelAdapter cmd2ModelAdpt) {
		this.cmd2ModelAdpt = cmd2ModelAdpt;
	}

}
