package com.la.web.chat.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "messages")
public class Message {

	@Id
	private String id;

	private String roomId;
	private String sender; // define quien esta enviando la informacion
	private String content; // es el contenido de la conversacion o el mensaje en si
	private LocalDateTime timeStamp; // la fecha del mensaje

	/**
	 * 
	 */
	public Message() {
	}

	/**
	 * @param id
	 * @param roomId
	 * @param sender
	 * @param content
	 * @param timeStamp
	 */
	public Message(String id, String roomId, String sender, String content, LocalDateTime timeStamp) {
		this.id = id;
		this.roomId = roomId;
		this.sender = sender;
		this.content = content;
		this.timeStamp = timeStamp;
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
	 * return the value of the propertie id
	 *
	 * @return the id
	 */
	public String getId() {
		return id;
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
	 * return the value of the propertie timeStamp
	 *
	 * @return the timeStamp
	 */
	public LocalDateTime getTimeStamp() {
		return timeStamp;
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
	 * set the value of the proppertie id
	 *
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
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

	/**
	 * set the value of the proppertie timeStamp
	 *
	 * @param timeStamp the timeStamp to set
	 */
	public void setTimeStamp(LocalDateTime timeStamp) {
		this.timeStamp = timeStamp;
	}

}
