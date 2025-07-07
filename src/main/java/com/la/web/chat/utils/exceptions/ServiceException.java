package com.la.web.chat.utils.exceptions;

import java.util.Map;

import com.la.web.chat.utils.enums.MethodEnum;

/**
 * service exception
 */
public class ServiceException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * status
	 */
	private final int status;

	/*
	 * path
	 */
	private final String path;

	/**
	 * method
	 */
	private final MethodEnum method;

	/**
	 * validation errors
	 */
	private Map<String, String> validationErrors;

	/**
	 * 
	 */
	public ServiceException(String message, int status, String path, MethodEnum method) {
		super(message);
		this.status = status;
		this.path = path;
		this.method = method;
	}

	/**
	 * @param status
	 * @param path
	 * @param method
	 * @param validationErrors
	 */
	public ServiceException(String message, int status, String path, MethodEnum method,
			Map<String, String> validationErrors) {
		super(message);
		this.status = status;
		this.path = path;
		this.method = method;
		this.validationErrors = validationErrors;
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
	 * return the value of the property validationErrors
	 *
	 * @return the validationErrors
	 */
	public Map<String, String> getValidationErrors() {
		return validationErrors;
	}

}
