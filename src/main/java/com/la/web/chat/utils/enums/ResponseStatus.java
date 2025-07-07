package com.mx.mcsv.ecommerce.comunes.enums;

/**
 * response status enum
 */
public enum ResponseStatus {
	SUCCESS(200), CREATED(201), DELETED(200), UPDATED(200), NOT_FOUND(404), BAD_REQUEST(400), UNAUTHORIZED(401),
	INTERNAL_SERVER_ERROR(500);

	private final int httpStatusCode;

	ResponseStatus(int httpStatusCode) {
		this.httpStatusCode = httpStatusCode;
	}

	public int getHttpStatusCode() {
		return httpStatusCode;
	}
}
