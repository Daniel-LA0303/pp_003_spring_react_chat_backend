package com.la.web.chat.utils.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.la.web.chat.utils.constants.ExceptionsConstants;
import com.la.web.chat.utils.constants.PathsConstants;
import com.la.web.chat.utils.enums.ResponseStatus;
import com.la.web.chat.utils.response.ApiResponse;

/**
 * exceptions
 */
@RestControllerAdvice
public class GlobalExcepction {

	/**
	 * catch any exception
	 * 
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleException(Exception ex) {

		ApiResponse<String> response = new ApiResponse<>(ResponseStatus.INTERNAL_SERVER_ERROR.getHttpStatusCode(),
				PathsConstants.PATH_USER, null, ExceptionsConstants.MESSAGE_INTERNAL_SERVER_ERROR + ex.getMessage(),
				null, true);

		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	/**
	 * catch ServiceException
	 * 
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(ServiceException.class)
	public ResponseEntity<?> handleInventoryException(ServiceException ex) {

		ApiResponse<Map<String, String>> response = new ApiResponse<>(ex.getStatus(), ex.getPath(), ex.getMethod(),
				ex.getMessage(), ex.getValidationErrors(), true);

		return new ResponseEntity<>(response, HttpStatus.valueOf(ex.getStatus()));
	}

}
