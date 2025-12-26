package com.la.web.chat.utils.dtos.room;

public class CreateRoomRequest {

	private String roomId;

	private String userId;

	/**
	 * 
	 */
	public CreateRoomRequest() {
	}

	/**
	 * @param roomId
	 * @param userId
	 */
	public CreateRoomRequest(String roomId, String userId) {
		this.roomId = roomId;
		this.userId = userId;
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
	 * return the value of the propertie userId
	 *
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
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
	 * set the value of the proppertie userId
	 *
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

}