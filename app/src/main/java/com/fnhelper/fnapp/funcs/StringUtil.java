/**
 * 
 */
package com.fnhelper.fnapp.funcs;

import java.security.MessageDigest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Eric
 * 
 */
public class StringUtil {

	/** 空字符 */
	public static final String NULL_STRING = "";
	/** 空字符串数组 */
	public static final String[] NULL_STRING_ARRAY = {};

	/**
	 * 半角转全角
	 * 
	 * @param input
	 *            String.
	 * @return 全角字符串.
	 */
	public static String b2q(String input) {
		char c[] = input.toCharArray();
		for (int i = 0; i < c.length; i++) {
			if (c[i] == ' ') {
				c[i] = '\u3000';
			} else if (c[i] < '\177') {
				c[i] = (char) (c[i] + 65248);

			}
		}
		return new String(c);
	}

	/**
	 * 全角转半角
	 * 
	 * @param input
	 *            String.
	 * @return 半角字符串
	 */
	public static String q2b(String input) {

		char c[] = input.toCharArray();
		for (int i = 0; i < c.length; i++) {
			if (c[i] == '\u3000') {
				c[i] = ' ';
			} else if (c[i] > '\uFF00' && c[i] < '\uFF5F') {
				c[i] = (char) (c[i] - 65248);

			}
		}
		String returnString = new String(c);

		return returnString;
	}

	/** 验证手机号码 */
	public static boolean isMobileNumber(String mobiles) {
		Pattern p = Pattern
				.compile("^((13[4-9])|(15[0-2,7-9])|(18[7-8]))\\d{8}$");
		Matcher m = p.matcher(mobiles);
		return m.matches();
	}

	/** 分割 */
	public static String[] split(String str, String separator) {
		if (str == null)
			return null;
		if (str.length() == 0)
			return NULL_STRING_ARRAY;
		if (separator == null)
			return null;
		int len = separator.length();
		if (len == 0) {
			String[] strs = { str };
			return strs;
		}
		int i = 0, j = 0, n = 1;
		while ((j = str.indexOf(separator, i)) >= 0) {
			i = j + len;
			n++;
		}
		String[] strs = new String[n];
		if (n == 1) {
			strs[0] = str;
			return strs;
		}
		i = j = n = 0;
		while ((j = str.indexOf(separator, i)) >= 0) {
			strs[n++] = (i == j) ? NULL_STRING : str.substring(i, j);
			i = j + len;
		}
		strs[n] = (i == str.length()) ? NULL_STRING : str.substring(i);
		return strs;
	}

	/** MD5 */
	public static String MD5(String str) {
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}

		char[] charArray = str.toCharArray();
		byte[] byteArray = new byte[charArray.length];

		for (int i = 0; i < charArray.length; i++) {
			byteArray[i] = (byte) charArray[i];
		}
		byte[] md5Bytes = md5.digest(byteArray);

		StringBuffer hexValue = new StringBuffer();
		for (int i = 0; i < md5Bytes.length; i++) {
			int val = ((int) md5Bytes[i]) & 0xff;
			if (val < 16) {
				hexValue.append("0");
			}
			hexValue.append(Integer.toHexString(val));
		}
		return hexValue.toString();
	}
}
