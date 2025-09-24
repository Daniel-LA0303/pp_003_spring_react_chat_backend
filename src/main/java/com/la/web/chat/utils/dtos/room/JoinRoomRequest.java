package com.la.web.chat.utils.dtos.room;

public class JoinRoomRequest {

	private String userId;

	/**
	 * 
	 */
	public JoinRoomRequest() {
	}

	/**
	 * @param userId
	 */
	public JoinRoomRequest(String userId) {
		this.userId = userId;
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
	 * set the value of the proppertie userId
	 *
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

}