package com.mx.mcsv.ecommerce.comunes.mappers;

import com.mx.mcsv.ecommerce.comunes.constants.ExceptionsConstants;
import com.mx.mcsv.ecommerce.comunes.enums.ResponseStatus;

/**
 * message formatter
 */
public class MessageFormatter {

	public static String formatMessage(ResponseStatus status, String resource) {

		switch (status) {
		case SUCCESS:
			return ExceptionsConstants.MESSAGE_SUCESS;
		case CREATED:
			return String.format(ExceptionsConstants.MESSAGE_CREATED, resource);
		case DELETED:
			return String.format(ExceptionsConstants.MESSAGE_DELETED, resource);
		case UPDATED:
			return String.format(ExceptionsConstants.MESSAGE_UPDATED, resource);
		case NOT_FOUND:
			return String.format(ExceptionsConstants.MESSAGE_NOT_FOUND, resource);
		case BAD_REQUEST:
			return String.format(ExceptionsConstants.MESSAGE_BAD_REQUEST, resource);
		case UNAUTHORIZED:
			return ExceptionsConstants.MESSAGE_UNAUTHORIZED;
		case INTERNAL_SERVER_ERROR:
			return String.format(ExceptionsConstants.MESSAGE_INTERNAL_SERVER_ERROR, resource);
		default:
			throw new IllegalArgumentException("Unexpected value: ");
		}

	}

}
