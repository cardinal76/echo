package it.clevercom.echo.common.util;

public class StringUtils {
	public static boolean isNotNullNotEmptyNotWhiteSpaceOnly(final String string) {
		return string != null && !string.isEmpty() && !string.trim().isEmpty();
	}
	
	public static boolean isNullEmptyWhiteSpaceOnly(final String string) {
		return (string == null) || (string.isEmpty()) || (string.trim().isEmpty());
	}
}
