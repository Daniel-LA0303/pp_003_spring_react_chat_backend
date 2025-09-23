package com.la.web.chat.utils.dtos.auth;

public class UserDTO {

	private String username;

	private String email;

	private String password; // Debe estar encriptada

	/**
	 * 
	 */
	public UserDTO() {
	}

	/**
	 * @param username
	 * @param email
	 * @param password
	 */
	public UserDTO(String username, String email, String password) {
		this.username = username;
		this.email = email;
		this.password = password;
	}

	/**
	 * return the value of the propertie email
	 *
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * return the value of the propertie password
	 *
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * return the value of the propertie username
	 *
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * set the value of the proppertie email
	 *
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * set the value of the proppertie password
	 *
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * set the value of the proppertie username
	 *
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

}
