package com.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationUtils {
	public static boolean isEmpty(String param) {
		boolean isEmpty = false;
		if (param == "" || param.trim().length() <= 0) {
			isEmpty = true;
		}
		return isEmpty;
	}

	public static boolean isValidEmailAddress(String email) {
		Pattern pattern;
		Matcher matcher;
		pattern = Pattern.compile(
				"^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$");
		matcher = pattern.matcher(email);
		if (matcher.matches() && (email.endsWith(".com") || email.endsWith(".co.in") || email.endsWith(".in")))
			return true;
		return false;
	}
}
