package com.la.web.chat.model;

import java.util.HashSet;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Las rooms seran los canales en si por lo que puede tener n usuarios roomId es
 * la parte del identificador, seria como el canal en si y ademas tendra sus
 * mensajes
 */
@Document(collection = "rooms")
public class Room {
	@Id
	private String id;// Mongo db : unique identifier

	private String roomId;

	@DBRef
	private Set<User> users = new HashSet<>();

	/**
	 * 
	 */
	public Room() {
	}

	/**
	 * @param id
	 * @param roomId
	 * @param users
	 */
	public Room(String id, String roomId, Set<User> users) {
		this.id = id;
		this.roomId = roomId;
		this.users = users;
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
	 * return the value of the propertie users
	 *
	 * @return the users
	 */
	public Set<User> getUsers() {
		return users;
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
	 * set the value of the proppertie users
	 *
	 * @param users the users to set
	 */
	public void setUsers(Set<User> users) {
		this.users = users;
	}

}
