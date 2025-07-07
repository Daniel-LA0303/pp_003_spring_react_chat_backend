package com.la.web.chat.utils.response;

import java.time.LocalDateTime;

import com.la.web.chat.utils.enums.MethodEnum;

/**
 * general api response
 * 
 * @param <T>
 */
public class ApiResponse<T> {

	/**
	 * status
	 */
	private int status;

	/**
	 * path
	 */
	private String path;

	/**
	 * method
	 */
	private MethodEnum method;

	/**
	 * message
	 */
	private String message;

	/**
	 * data response
	 */
	private T data;

	/**
	 * error
	 */
	private Boolean error;

	/**
	 * timestamp
	 */
	private LocalDateTime timestamp;

	/**
	 * 
	 */
	public ApiResponse() {
	}

	/**
	 * @param status
	 * @param path
	 * @param method
	 * @param message
	 * @param data
	 * @param error
	 */
	public ApiResponse(int status, String path, MethodEnum method, String message, T data, Boolean error) {
		this.status = status;
		this.path = path;
		this.method = method;
		this.message = message;
		this.data = data;
		this.error = error;
		this.timestamp = LocalDateTime.now();
	}

	/**
	 * return the value of the property data
	 *
	 * @return the data
	 */
	public T getData() {
		return data;
	}

	/**
	 * return the value of the property error
	 *
	 * @return the error
	 */
	public Boolean getError() {
		return error;
	}

	/**
	 * return the value of the property message
	 *
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * return the value of the property method
	 *
	 * @return the method
	 */
	public MethodEnum getMethod() {
		return method;
	}

	/**
	 * return the value of the property path
	 *
	 * @return the path
	 */
	public String getPath() {
		return path;
	}

	/**
	 * return the value of the property status
	 *
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * return the value of the property timestamp
	 *
	 * @return the timestamp
	 */
	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	/**
	 * set the value of the property data
	 *
	 * @param data the data to set
	 */
	public void setData(T data) {
		this.data = data;
	}

	/**
	 * set the value of the property error
	 *
	 * @param error the error to set
	 */
	public void setError(Boolean error) {
		this.error = error;
	}

	/**
	 * set the value of the property message
	 *
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * set the value of the property method
	 *
	 * @param method the method to set
	 */
	public void setMethod(MethodEnum method) {
		this.method = method;
	}

	/**
	 * set the value of the property path
	 *
	 * @param path the path to set
	 */
	public void setPath(String path) {
		this.path = path;
	}

	/**
	 * set the value of the property status
	 *
	 * @param status the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}

	/**
	 * set the value of the property timestamp
	 *
	 * @param timestamp the timestamp to set
	 */
	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

}
