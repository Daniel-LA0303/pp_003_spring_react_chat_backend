package com.mx.mcsv.ecommerce.comunes.enums;

import com.mx.mcsv.ecommerce.comunes.constants.RegexConstants;

/**
 * validation enum
 */
public enum ValidationEnum {

	ALFANUMERIC_20(RegexConstants.ALFANUMERIC_20, "Must be 20-position alphanumeric"),
	ALFANUMERIC_255(RegexConstants.ALFANUMERIC_255, "Must be 255-position alphanumeric"),
	EMAIL(RegexConstants.EMAIL, "Must be a valid email address of up to 50 positions"),
	SKU_PRODUCT(RegexConstants.SKU_PRODCUT, "This is a not valid sku.");

	private final String regex;

	private final String message;

	ValidationEnum(String regex, String message) {
		this.regex = regex;
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public String getRegex() {
		return regex;
	}
}
