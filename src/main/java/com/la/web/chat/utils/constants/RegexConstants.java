package com.mx.mcsv.ecommerce.comunes.constants;

/**
 * regex
 */
public class RegexConstants {

	public static final String EMAIL = "^[_A-z0-9-]+(\\.[_A-z0-9-]+)*@[A-z0-9-]+(\\.[A-z0-9-]+)*(\\.[A-z]{2,4})$";

	public static final String NUMERIC_UNTIL_10 = "^[\\\\d]{1,10}$";

	public static final String ALFANUMERIC_255 = "^.{1,255}$";

	public static final String ALFANUMERIC_20 = "^.{1,20}$";

	public static final String SKU_PRODCUT = "^[A-Za-z]{4}-\\d{5}$";

}
