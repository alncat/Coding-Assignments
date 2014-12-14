package yn4_jjc7.client.model.chatroom;

import java.net.Inet4Address;

import common.chatroom.IChatroomID;

/**
 * Concrete implementation of the unique chatroom identifier interface.
 */
public class ChatroomID implements IChatroomID {

	private static final long serialVersionUID = 2631838645808442211L;

	/** The IP address of the user that created the chatroom */
	private Inet4Address address;

	/** The name of the chatroom */
	private String name;

	/** The time that the chatroom id was generated */
	private long time;

	/**
	 * Creates a new chatroom id with the specified creator IP address and chatroom name.
	 * 
	 * @param address the IP address of the user that created the chatroom
	 * @param name the name of the chatroom
	 */
	public ChatroomID(Inet4Address address, String name) {
		this.address = address;
		this.name = name;
		this.time = System.currentTimeMillis();
	}

	/**
	 * Returns the IP address of the user that created this chatroom.
	 * 
	 * @return the IP address of the user that created this chatroom
	 */
	public Inet4Address getAddress() {
		return this.address;
	}

	@Override
	public String toString() {
		return name;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + (int) (time ^ (time >>> 32));
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ChatroomID other = (ChatroomID) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (time != other.time)
			return false;
		return true;
	}

}
