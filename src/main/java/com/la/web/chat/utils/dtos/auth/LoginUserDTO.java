package com.la.web.chat.utils.dtos.auth;

public class LoginUserDTO {

	private String email;

	private String password; // Debe estar encriptada

	/**
	 * 
	 */
	public LoginUserDTO() {
	}

	/**
	 * @param email
	 * @param password
	 */
	public LoginUserDTO(String email, String password) {
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

}
