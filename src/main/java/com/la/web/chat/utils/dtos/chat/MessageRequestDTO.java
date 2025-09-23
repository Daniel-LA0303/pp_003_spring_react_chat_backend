package com.la.web.chat.utils.dtos.chat;

public class MessageRequestDTO {

	private String content;

	private String sender;

	private String roomId;

	/**
	 * 
	 */
	public MessageRequestDTO() {
	}

	/**
	 * @param content
	 * @param sender
	 * @param roomId
	 */
	public MessageRequestDTO(String content, String sender, String roomId) {
		this.content = content;
		this.sender = sender;
		this.roomId = roomId;
	}

	/**
	 * return the value of the propertie content
	 *
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * return the value of the propertie roomId
	 *
	 * @return the roomId
	 */
	public String getRoomId() {
		return roomId;
	}

	/**
	 * return the value of the propertie sender
	 *
	 * @return the sender
	 */
	public String getSender() {
		return sender;
	}

	/**
	 * set the value of the proppertie content
	 *
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * set the value of the proppertie roomId
	 *
	 * @param roomId the roomId to set
	 */
	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	/**
	 * set the value of the proppertie sender
	 *
	 * @param sender the sender to set
	 */
	public void setSender(String sender) {
		this.sender = sender;
	}

}
