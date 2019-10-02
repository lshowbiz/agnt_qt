package com.joymain.ng.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.springframework.validation.BindingResult;

import com.joymain.ng.Constants;

/**
 * 字符串操作工具类 String Utility Class This is used to encode passwords
 * programmatically
 * 
 * <p>
 * <a h ref="StringUtil.java.html"><i>View Source</i></a>
 * </p>
 * 
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 */
public class StringUtil {
	// ~ Static fields/initializers
	// =============================================

	private final static Log log = LogFactory.getLog(StringUtil.class);

	// ~ Methods
	// ================================================================

	/**
	 * 用指定的加密算法加密String Encode a string using algorithm specified in web.xml and
	 * return the resulting encrypted password. If exception, the plain
	 * credentials string is returned
	 * 
	 * @param password
	 *            Password or other credentials to use in authenticating this
	 *            username
	 * @param algorithm
	 *            Algorithm used to do the digest
	 * 
	 * @return encypted password based on the algorithm.
	 */
	public static String encodePassword(String password, String algorithm) {
		byte[] unencodedPassword = password.getBytes();

		MessageDigest md = null;

		try {
			// first create an instance, given the provider
			md = MessageDigest.getInstance(algorithm);
		} catch (Exception e) {
			log.error("Exception: " + e);

			return password;
		}

		md.reset();

		// call the update method one or more times
		// (useful when you don't know the size of your data, eg. stream)
		md.update(unencodedPassword);

		// now calculate the hash
		byte[] encodedPassword = md.digest();

		StringBuffer buf = new StringBuffer();

		for (int i = 0; i < encodedPassword.length; i++) {
			if ((encodedPassword[i] & 0xff) < 0x10) {
				buf.append("0");
			}

			buf.append(Long.toString(encodedPassword[i] & 0xff, 16));
		}

		return buf.toString();
	}

	/**
	 * 获得有效的字符串,即obj为空时，返回“”，不为空，则作toString操作
	 * 
	 * @param Obj
	 * @return 有效的字符串
	 */
	public static String getAvailStr(Object Obj) {
		if (Obj == null)
			return "";
		else
			return Obj.toString();
	}

	/**
	 * 获得有效的字符串,即obj为空时，返回value，不为空，则作toString操作
	 * 
	 * @param Obj
	 * @return 有效的字符串
	 */
	public static String getAvailStr(Object Obj, String value) {
		if (Obj == null)
			return value;
		else
			return Obj.toString();
	}

	/**
	 * 转换 GBK 字符串 to 8859-1
	 * 
	 * @param str
	 *            : the string want to convert
	 * @return String : the string after convert
	 */

	public static String GBK2ISO(String str) {
		if (str != null && str.length() > 0) {
			try {
				byte[] byteTmp = str.getBytes("GBK");
				str = new String(byteTmp, "ISO-8859-1");
			} catch (java.io.UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return str;
	}

	/**
	 * 将ISO-8859-1 格式的字符传转换为 GBK
	 * 
	 * @param str
	 * @return ""
	 */
	public static String ISO2GBK(String str) {
		if (str != null && str.length() > 0) {
			try {
				byte[] byteTmp = str.getBytes("ISO-8859-1");
				str = new String(byteTmp, "GBK");
			} catch (java.io.UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return str;
	}

	/**
	 * 将utf-8 格式的字符传转换为 GBK
	 * 
	 * @param str
	 * @return ""
	 */
	public static String UTF2GBK(String str) {
		if (str != null && str.length() > 0) {
			try {
				byte[] byteTmp = str.getBytes("utf-8");
				str = new String(byteTmp, "GBK");
			} catch (java.io.UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return str;
	}

	/**
	 * 
	 * @param str原始字符串
	 * @param charset
	 * @param rep
	 *            替换字符
	 * @param length
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String getFLString(String str, String charset, String rep, int length) throws UnsupportedEncodingException {
		str = StringUtil.getAvailStr(str, "");
		int rlength = StringUtil.getStringCharLength(str, charset);
		StringBuffer appendBuffer = new StringBuffer("");
		if (rlength < length) {
			for (int i = 0; i < (length - rlength); i++) {
				appendBuffer.append(rep);
			}
		}
		return str + appendBuffer.toString();
	}

	public static String getPreFLString(String str, String charset, String rep, int length) throws UnsupportedEncodingException {

		int rlength = StringUtil.getStringCharLength(str, charset);
		StringBuffer appendBuffer = new StringBuffer("");
		if (rlength < length) {
			for (int i = 0; i < (length - rlength); i++) {
				appendBuffer.append(rep);
			}
		}
		return appendBuffer.toString() + str;
	}

	/**
	 * 
	 * @param str
	 * @param charset
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static int getStringCharLength(String str, String charset) throws UnsupportedEncodingException {
		int ret = 0;
		if (StringUtils.isNotBlank(str)) {
			byte[] byteTmp;
			if (StringUtils.isNotBlank(charset)) {
				byteTmp = str.getBytes(charset);
			} else {
				byteTmp = str.getBytes();
			}
			ret = byteTmp.length;
		}
		return ret;
	}

	/**
	 * 将str从字符集chFrom转换到字符集chTo
	 * 
	 * @param str
	 * @param chFrom
	 * @param chTo
	 * @return str
	 */
	public static String convertCharset(String str, String chFrom, String chTo) {
		if (chTo == null || chTo.trim().length() == 0)
			return str;
		if (str != null && str.length() > 0) {
			try {
				byte[] byteTmp;
				if (chFrom == null || chFrom.length() == 0)
					byteTmp = str.getBytes();
				else
					byteTmp = str.getBytes(chFrom);
				str = new String(byteTmp, chTo);
			} catch (Exception e) {
				System.out.println(" converting  " + chFrom + " to " + chTo + " error" + e.toString());
				e.printStackTrace();
			}
		}
		return str;
	}

	/**
	 * 返回字符串的右边的count个字符
	 * 
	 * @param str
	 *            原字符串
	 * @param count
	 *            取的字符个数
	 * @return 截取的字符串
	 */
	public static String getRight(String str, int count) {
		return StringUtil.getAvailStr(str.substring(str.length() - count, str.length()));
	}

	/**
	 * 返回字符串的左边的count个字符
	 * 
	 * @param str
	 *            原字符串
	 * @param count
	 *            取的字符个数
	 * @return 截取的字符串
	 */
	public static String getLeft(String str, int count) {
		if (isEmpty(str) || str.length() < count) {
			return StringUtil.getAvailStr(str);
		} else {
			return StringUtil.getAvailStr(str.substring(0, count));
		}
	}

	/**
	 * 构造html n 个空格 的字符串
	 * 
	 * @param n
	 *            参数：3
	 * @return 返回：&nbsp;&nbsp;&nbsp;
	 */
	private String space(int n) {
		String str = "";
		for (int i = 0; i < n; i++)
			str += "&nbsp;";
		return str;
	}

	/**
	 * 补0
	 * 
	 * @param str
	 *            参数：9
	 * @param len
	 *            参数：4
	 * @return 返回：0009
	 */
	public static String leadZeros(String str, int len) {
		if (str == null || str.length() == 0) {
			str = "";
			for (int i = 0; i < len; i++) {
				str += "0";
			}
			return str;
		} else {
			str = str.trim();
			int strLen = str.length();
			for (int i = 0; i < len - strLen; i++) {
				str = "0" + str;
			}
			return str;
		}
	}

	/**
	 * 以提供的字符往后补位
	 * 
	 * @param str
	 *            参数：9
	 * @param len
	 *            参数：4
	 * @return 返回：9000
	 */
	public static String endPadding(String str, char c, int len) {
		if (str == null || str.length() == 0) {
			str = "";
			for (int i = 0; i < len; i++) {
				str += c;
			}
			return str;
		} else {
			str = str.trim();
			int strLen = str.length();
			for (int i = 0; i < len - strLen; i++) {
				str += c;
			}
			return str;
		}
	}

	/**
	 * 降序排序字符串数组
	 * 
	 * @param strs
	 *            An array of String
	 * @return String[] the sorted array
	 */
	public static String[] sort(String[] strs) {
		int i = 0, j = 1, len = strs.length;
		if (len <= 1)
			return strs;
		String strTmp = null;
		for (i = 0; i < len - 1; i++) {
			for (j = i + 1; j < len; j++) {
				if (strs[i].compareTo(strs[j]) < 0) {
					strTmp = strs[i];
					strs[i] = strs[j];
					strs[j] = strTmp;
				}
			}
		}
		return strs;
	}

	/**
	 * 将字符传前补指定字符 c 到指定长度 length, 如将abcd以*号替换成8位的字符串,即****abcd
	 * 
	 * @param str
	 * @param length
	 * @param c
	 * @return String ****abcd
	 */
	public static String padding(String str, int length, char c) {
		int i, len;
		len = length - str.length();

		for (i = 0; i < len; i++) {
			str = c + str;
		}
		return str;
	}

	public static String firstCharToUpperCase(String srcStr) {
		return srcStr.substring(0, 1).toUpperCase().concat(srcStr.substring(1));
	}

	/**
	 * 替换特殊字符
	 * 
	 * @param inStr
	 * @return String
	 */
	public static String replaceSpecialChars(String inStr) {
		String outStr = "";
		if (inStr == null) {
			return outStr;
		} else {
			String[] replaceStr = { "\"", "\'" };
			String[] toStr = { "“", "‘" };
			for (int i = 0; i < replaceStr.length; i++) {
				if (i != 0) {
					inStr = outStr;
				}
				outStr = inStr.replaceAll(replaceStr[i], toStr[i]);
			}
			outStr = replaceMark(outStr, "\\", "＼");
			return outStr;
		}
	}

	/**
	 * 全局替换
	 * 
	 * @param str
	 *            用来替换的字符串
	 * @param destStr
	 *            需要被查找替换的字符串
	 * @param srcStr
	 *            需要进行此操作的源字符串
	 * @return String
	 */
	public static String replaceMark(String str, String destStr, String srcStr) {
		// 返回值
		StringBuffer retVal = new StringBuffer();
		// 记录查找到相似字符的位置
		int findStation = str.indexOf(destStr);
		int resumStation = 0;
		while (findStation > -1) {
			retVal.append(str.substring(resumStation, findStation));
			retVal.append(srcStr);
			resumStation = findStation + destStr.length();
			findStation = str.indexOf(destStr, resumStation);
		}
		retVal.append(str.substring(resumStation));
		return retVal.toString();
	}

	/**
	 * 数组转换成字符串
	 * 
	 * @param strs
	 *            An array of String
	 * @return String
	 */
	public static String getString(String[] strs) {
		String strTmp = "";
		if (strs == null) {
			return "";
		}
		for (int i = 0; i < strs.length; i++) {
			if (!strs[i].equals("")) {
				strTmp += strs[i] + ",";
			}
		}
		strTmp = strTmp.substring(0, strTmp.length() - 1);

		return strTmp;
	}

	/**
	 * null 或者 "" 返回 true 否则 返回 false
	 * 
	 * @return boolean
	 */
	public static boolean isEmpty(String str) {
		if (str == null) {
			return true;
		} else {
			if (str.equals("")) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 是否存在指定字符
	 * 
	 * @param regEx正则表达式
	 * @param targetString
	 * @return
	 */
	public static boolean isSpecialCharsIn(String regEx, String targetString) {
		/*
		 * if(srcString.length<1 || StringUtil.isEmpty(targetString)){ return
		 * false; } for (int i = 0; i < srcString.length; i++) { if
		 * (targetString.indexOf(srcString[i])>=0) { return true; } } return
		 * false;
		 */
		Pattern pattern = Pattern.compile(regEx);
		Matcher matcher = pattern.matcher(targetString);
		return matcher.find();
	}

	/**
	 * 判断某个字符串数组里是否包含有某个字符串
	 * 
	 * @param srcString
	 *            String[]
	 * @param targetString
	 *            String
	 * @param ignoreCase
	 *            boolean
	 * @return
	 */
	public static boolean hasString(String[] srcString, String targetString, boolean ignoreCase) {
		if (srcString == null || srcString.length == 0)
			return false;

		boolean has = false;
		for (int i = 0; i < srcString.length; i++) {
			if (ignoreCase) {
				if (srcString[i].equalsIgnoreCase(targetString)) {
					has = true;
					break;
				}
			} else {
				if (srcString[i].equals(targetString)) {
					has = true;
					break;
				}
			}
		}
		return has;
	}

	private final static String[] hex = { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "0A", "0B", "0C", "0D", "0E", "0F", "10", "11", "12", "13", "14", "15", "16",
			"17", "18", "19", "1A", "1B", "1C", "1D", "1E", "1F", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "2A", "2B", "2C", "2D", "2E", "2F", "30", "31", "32",
			"33", "34", "35", "36", "37", "38", "39", "3A", "3B", "3C", "3D", "3E", "3F", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "4A", "4B", "4C", "4D", "4E",
			"4F", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59", "5A", "5B", "5C", "5D", "5E", "5F", "60", "61", "62", "63", "64", "65", "66", "67", "68", "69", "6A",
			"6B", "6C", "6D", "6E", "6F", "70", "71", "72", "73", "74", "75", "76", "77", "78", "79", "7A", "7B", "7C", "7D", "7E", "7F", "80", "81", "82", "83", "84", "85", "86",
			"87", "88", "89", "8A", "8B", "8C", "8D", "8E", "8F", "90", "91", "92", "93", "94", "95", "96", "97", "98", "99", "9A", "9B", "9C", "9D", "9E", "9F", "A0", "A1", "A2",
			"A3", "A4", "A5", "A6", "A7", "A8", "A9", "AA", "AB", "AC", "AD", "AE", "AF", "B0", "B1", "B2", "B3", "B4", "B5", "B6", "B7", "B8", "B9", "BA", "BB", "BC", "BD", "BE",
			"BF", "C0", "C1", "C2", "C3", "C4", "C5", "C6", "C7", "C8", "C9", "CA", "CB", "CC", "CD", "CE", "CF", "D0", "D1", "D2", "D3", "D4", "D5", "D6", "D7", "D8", "D9", "DA",
			"DB", "DC", "DD", "DE", "DF", "E0", "E1", "E2", "E3", "E4", "E5", "E6", "E7", "E8", "E9", "EA", "EB", "EC", "ED", "EE", "EF", "F0", "F1", "F2", "F3", "F4", "F5", "F6",
			"F7", "F8", "F9", "FA", "FB", "FC", "FD", "FE", "FF" };
	private final static byte[] val = { 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
			0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x00, 0x01, 0x02,
			0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x0A, 0x0B, 0x0C, 0x0D, 0x0E, 0x0F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
			0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x0A, 0x0B, 0x0C, 0x0D, 0x0E, 0x0F, 0x3F, 0x3F, 0x3F, 0x3F,
			0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
			0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
			0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
			0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
			0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
			0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F };

	/**
	 * 模拟Javascript escape函数
	 * 
	 * @param s
	 * @return
	 */
	public static String escape(String s) {
		StringBuffer sbuf = new StringBuffer();
		int len = s.length();
		for (int i = 0; i < len; i++) {
			int ch = s.charAt(i);
			if (ch == ' ') {// space : map to '+'
				sbuf.append('+');
			} else if ('A' <= ch && ch <= 'Z') {// 'A'..'Z' : as it was
				sbuf.append((char) ch);
			} else if ('a' <= ch && ch <= 'z') {// 'a'..'z' : as it was
				sbuf.append((char) ch);
			} else if ('0' <= ch && ch <= '9') {// '0'..'9' : as it was
				sbuf.append((char) ch);
			} else if (ch == '-' || ch == '_' // unreserved : as it was
					|| ch == '.' || ch == '!' || ch == '~' || ch == '*' || ch == '\'' || ch == '(' || ch == ')') {
				sbuf.append((char) ch);
			} else if (ch <= 0x007F) {// other ASCII : map to %XX
				sbuf.append('%');
				sbuf.append(hex[ch]);
			} else {// unicode : map to %uXXXX
				sbuf.append('%');
				sbuf.append('u');
				sbuf.append(hex[(ch >>> 8)]);
				sbuf.append(hex[(0x00FF & ch)]);
			}
		}
		return sbuf.toString();
	}

	/**
	 * 模拟Javascript unescape函数
	 * 
	 * @param s
	 * @return
	 */
	public static String unescape(String s) {
		StringBuffer sbuf = new StringBuffer();
		int i = 0;
		int len = s.length();
		while (i < len) {
			int ch = s.charAt(i);
			if (ch == '+') {// + : map to ' '
				sbuf.append(' ');
			} else if ('A' <= ch && ch <= 'Z') {// 'A'..'Z' : as it was
				sbuf.append((char) ch);
			} else if ('a' <= ch && ch <= 'z') {// 'a'..'z' : as it was
				sbuf.append((char) ch);
			} else if ('0' <= ch && ch <= '9') {// '0'..'9' : as it was
				sbuf.append((char) ch);
			} else if (ch == '-' || ch == '_' // unreserved : as it was
					|| ch == '.' || ch == '!' || ch == '~' || ch == '*' || ch == '\'' || ch == '(' || ch == ')') {
				sbuf.append((char) ch);
			} else if (ch == '%') {
				int cint = 0;
				if ('u' != s.charAt(i + 1)) { // %XX : map to ascii(XX)
					cint = (cint << 4) | val[s.charAt(i + 1)];
					cint = (cint << 4) | val[s.charAt(i + 2)];
					i += 2;
				} else {// %uXXXX : map to unicode(XXXX)
					cint = (cint << 4) | val[s.charAt(i + 2)];
					cint = (cint << 4) | val[s.charAt(i + 3)];
					cint = (cint << 4) | val[s.charAt(i + 4)];
					cint = (cint << 4) | val[s.charAt(i + 5)];
					i += 5;
				}
				sbuf.append((char) cint);
			}
			i++;
		}
		return sbuf.toString();
	}

	/** 判断一个对象是否包含有除空格以外的其它字符串 */
	public static boolean isObjectBlank(Object obj) {
		if (obj == null)
			return true;

		int strLen;
		if ((strLen = obj.toString().length()) == 0)
			return true;

		for (int i = 0; i < strLen; i++)
			if (!Character.isWhitespace(obj.toString().charAt(i)))
				return false;

		return true;
	}

	public static boolean isObjectNotBlank(Object obj) {
		return !isObjectBlank(obj);
	}

	public static String cutString(String strSrc, int count) {

		String rlt = strSrc;

		if (!blankOrNull(strSrc)) {
			if (strSrc.length() > count) {
				rlt = strSrc.substring(0, count);
			}
		}

		return rlt;
	}

	/**
	 * 判断一个字符是否为整数
	 * 
	 * @param value
	 *            String
	 * @return boolean
	 */
	public static boolean isInteger(String value) {
		return (formatInt(value) != null);
	}

	/**
	 * 判断是否为Double
	 * 
	 * @param value
	 *            String
	 * @return boolean
	 */
	public static boolean isDouble(String value) {
		return (formatDouble(value) != null);
	}

	/**
	 * 是否为电子邮件
	 * 
	 * @param value
	 *            String
	 * @return boolean
	 */
	public static boolean isEmail(String value) {
		if (null == value || value.trim().length() == 0) {
			return false;
		}
		Pattern pa = Pattern
				.compile("^(([0-9a-zA-Z]+)|([0-9a-zA-Z]+[_.0-9a-zA-Z-]*[_.0-9a-zA-Z]+))@([a-zA-Z0-9-]+[.])+([a-zA-Z]{2}|net|NET|com|COM|gov|GOV|mil|MIL|org|ORG|edu|EDU|int|INT|cn|CN|cc|CC)$");
		Matcher ma = pa.matcher(value);
		return ma.matches();
	}

	/**
	 * 是否为电话
	 * 
	 * @param value
	 *            String
	 * @return boolean
	 */
	public static boolean isPhone(String value) {
		if (null == value || value.trim().length() == 0) {
			return false;
		}
		Pattern pa = Pattern.compile("^(([(0-9)]+)|([0-9-]+))(([0-9-]+)|([0-9]+))([0-9])$");
		Matcher ma = pa.matcher(value);
		return ma.matches();
	}

	/**
	 * 把一个字符转换成double类
	 * 
	 * @param value
	 *            String
	 * @return Double
	 */
	public static double formatDouble(Double value, double inc) {
		try {
			return value.doubleValue();
		} catch (Exception e) {
			return inc;
		}
	}

	/**
	 * 把一个字符转换成Double类
	 * 
	 * @param value
	 *            String
	 * @return Double
	 */
	public static Double formatDouble(String value) {
		try {
			return new Double(value);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 把一个Double转换成整数
	 * 
	 * @param value
	 *            String
	 * @return Integer
	 */
	public static Integer formatInt(Double value) {
		try {
			return value.intValue();
		} catch (Exception e) {
			return null;
		}

	}

	/**
	 * 把一个字符转换成整数类
	 * 
	 * @param value
	 *            String
	 * @return Integer
	 */
	public static Integer formatInt(String value) {
		try {
			return new Integer(value);
		} catch (Exception e) {
			return null;
		}

	}

	/**
	 * 把一个字符转换成整数类
	 * 
	 * @param value
	 *            String
	 * @return Integer
	 */
	public static Long formatLong(String value) {
		try {
			return new Long(value);
		} catch (Exception e) {
			return null;
		}

	}

	/**
	 * 将 str 转换成 整数 默认 inc
	 * 
	 * @param str
	 * @param inc
	 * @return Integer
	 */
	public static Integer formatInt(String str, int inc) {
		Integer reVal = formatInt(str);
		if (reVal == null) {
			reVal = new Integer(inc);
		}
		return reVal;
	}

	public static Double formatDouble(String str, double inc) {
		Double reVal = formatDouble(str);
		if (reVal == null) {
			reVal = new Double(inc);
		}
		return reVal;
	}

	/**
	 * 取进位总数
	 * 
	 * @param num
	 *            参数：9
	 * @return 返回：10
	 */
	public static long upNumber(double num) {
		long reVal = 0;
		String inc = "0";
		while (true) {
			reVal = formatLong("1" + inc).longValue();
			if (reVal <= num) {
				inc = inc + "0";
			} else {
				break;
			}
		}
		return reVal;
	}

	/**
	 * 输入的校验日期是否合法，日期字符格串式必须为"yyyy-MM-dd"
	 * 
	 * @param date
	 *            2005-12-13 需要校验的日期
	 */
	public static boolean isDate(String date) {
		if (date == null || date.length() != 10) {
			return false;
		} else {
			String dates[] = date.split("-");
			if (dates.length != 3)
				return false;
			int yy = formatInt(dates[0], 0).intValue();
			int mm = formatInt(dates[1], 0).intValue();
			int dd = formatInt(dates[2], 0).intValue();
			if (yy > 3000 || yy < 1000) {
				return false;
			}
			if (mm > 12 || mm < 1) {
				return false;
			}
			switch (mm) {
			case 1:
			case 3:
			case 5:
			case 7:
			case 8:
			case 10:
			case 12:
				if (dd > 31 || dd < 1) {
					return false;
				} else {
					return true;
				}
			case 2:
				if ((yy % 4 == 0 && yy % 100 != 0) || yy % 400 == 0) {
					if (dd > 29 || dd < 1) {
						return false;
					} else {
						return true;
					}
				} else {
					if (dd > 28 || dd < 1) {
						return false;
					} else {
						return true;
					}
				}
			default:
				if (dd > 30 || dd < 1) {
					return false;
				} else {
					return true;
				}
			}
		}
	}

	/**
	 * 判断是否为时间
	 * 
	 * @param value
	 *            格式：HH:mm:ss
	 * @return boolean
	 */
	public static boolean isTime(String value) {
		String[] tmp = value.split(":");
		if (tmp.length != 3)
			return false;
		int h = formatInt(tmp[0], -1).intValue();
		int m = formatInt(tmp[1], -1).intValue();
		int s = formatInt(tmp[2], -1).intValue();
		if (h >= 24 || h < 0)
			return false;
		if (m >= 60 || h < 0)
			return false;
		if (s >= 60 || h < 0)
			return false;

		return true;
	}

	/**
	 * 判断是否为日期时间
	 * 
	 * @param value
	 *            格式： yyyy-MM-dd HH:mm:ss
	 * @return boolean
	 */
	public static boolean isDateTime(String value) {
		String tmp[] = value.split(" ");
		if (tmp.length != 2)
			return false;
		if (!isDate(tmp[0]))
			return false;
		if (!isTime(tmp[1]))
			return false;
		return true;
	}

	/**
	 * 获取操作系统目录分割符：unix ：/ windows : \\ 通过获得系统属性构造属性类 prop
	 */
	public static String getFileSeparator() {
		Properties prop = new Properties(System.getProperties());
		// 在标准输出中输出系统属性的内容
		String _file = "/";
		if (!prop.getProperty("file.separator").equals("/")) {
			_file = "\\";
		}
		return _file;
	}

	/**
	 * 字符串是否空或者NULL
	 * 
	 * @param str
	 *            strig to check
	 * @return the check result
	 */
	public static boolean blankOrNull(String str) {
		boolean rlt = true;

		if (str != null) {
			if (str.trim().length() > 0) {
				rlt = false;
			}
		}

		return rlt;
	}

	/**
	 * 判断是否下线
	 * 
	 * @param topIndex
	 * @param underIndex
	 * @return
	 */
	public static boolean getCheckIsDownline(String topIndex, String underIndex) {
		boolean flag = true;

		if (underIndex.length() < topIndex.length()) {
			flag = false;
		}
		String rmemberIndexTmp = StringUtil.getLeft(underIndex, topIndex.length());
		if (!topIndex.equals(rmemberIndexTmp)) {// 不为会员的下级组织
			flag = false;
		}
		/*
		 * String tmpIndex = StringUtil.getLeft(underIndex, topIndex.length());
		 * if(underIndex.length() < topIndex.length() ||
		 * !topIndex.equals(tmpIndex) ){ flag=false; }
		 */
		return flag;
	}

	/**
	 * 检测是否中策会员
	 * 
	 * @param defSysUser
	 * @return
	 */
	public static boolean getCheckIsUnlimitUser(String userCode) {
		boolean unlimitUserFlag = false;
		Set valueSets = Constants.sysListMap.get("memberno.unlimit.user").entrySet();
		if (valueSets != null) {
			Iterator iter = valueSets.iterator();
			while (iter.hasNext()) {
				Map.Entry entry = (Map.Entry) iter.next();
				String curUserCode = (String) entry.getKey();
				String loginUserCode = userCode;
				if (curUserCode.equals(loginUserCode)) {
					unlimitUserFlag = true;
				}
			}
		}
		return unlimitUserFlag;
	}

	public static String getErrorsFormat(BindingResult errors, String msg, String field, String fieldMsg) {
		String errorStr = MessageFormat.format(LocaleUtil.getLocalText(msg), new String[] { LocaleUtil.getLocalText(fieldMsg) });
		if (errors != null) {
			// errors.rejectValue("firstName",
			// LocaleUtil.getLocalText("isNotNull", new Object[]{
			// LocaleUtil.getLocalText("miMember.firstName")}));
			errors.rejectValue(field, LocaleUtil.getLocalText(msg, new Object[] { LocaleUtil.getLocalText(fieldMsg) }));
		}
		return errorStr;
	}

	public static String getErrors(BindingResult errors, String msg, String field) {
		String errorStr = "";
		errorStr = LocaleUtil.getLocalText(msg);
		if (errors != null) {
			if (StringUtil.isEmpty(field)) {
				errors.reject(errorStr);
			} else {
				// errors.rejectValue(field,errorStr,errorStr);
				errors.rejectValue(field, LocaleUtil.getLocalText(msg));
			}

		}
		return errorStr;
	}

	public static String getErrorsFormatCode(BindingResult errors, String msg, String field, String fieldMsg, String defCharacterCoding) {
		String errorStr = MessageFormat.format(LocaleUtil.getLocalText(defCharacterCoding, msg), new String[] { LocaleUtil.getLocalText(defCharacterCoding, fieldMsg) });
		if (errors != null) {
			// errors.rejectValue("firstName",
			// LocaleUtil.getLocalText("isNotNull", new Object[]{
			// LocaleUtil.getLocalText("miMember.firstName")}));
			errors.rejectValue(field, LocaleUtil.getLocalText(defCharacterCoding, msg, msg, new Object[] { LocaleUtil.getLocalText(defCharacterCoding, fieldMsg) }));
		}
		return errorStr;
	}

	public static String getErrorsCode(BindingResult errors, String msg, String field, String defCharacterCoding) {
		String errorStr = "";
		errorStr = LocaleUtil.getLocalText(defCharacterCoding, msg);
		if (errors != null) {
			if (StringUtil.isEmpty(field)) {
				errors.reject(errorStr);
			} else {
				errors.rejectValue(field, errorStr);
			}

		}
		return errorStr;
	}

	/**
	 * 生成N位随机数字
	 * 
	 * @param length
	 * @return
	 */
	public static String generateCode(int length) {
		int count = 0;
		char str[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
		StringBuffer pwd = new StringBuffer("");
		Random r = new Random();
		while (count < length) {
			int i = Math.abs(r.nextInt(10));
			if (i >= 0 && i < str.length) {
				pwd.append(str[i]);
				count++;
			}
		}
		return pwd.toString();
	}

	/**
	 * 
	 * 从 Unicode 形式的字符串转换成对应的编码的特殊字符串。 如 "\u9EC4" to "黄".
	 * 
	 * Converts encoded \\uxxxx to unicode chars
	 * 
	 * and changes special saved chars to their original forms
	 * 
	 * 
	 * 
	 * @param in
	 * 
	 *            Unicode编码的字符数组。
	 * 
	 * @param off
	 * 
	 *            转换的起始偏移量。
	 * 
	 * @param len
	 * 
	 *            转换的字符长度。
	 * 
	 * @param convtBuf
	 * 
	 *            转换的缓存字符数组。
	 * 
	 * @return 完成转换，返回编码前的特殊字符串。
	 */

	public static String fromEncodedUnicode(char[] in, int off, int len) {

		char aChar;

		char[] out = new char[len]; // 只短不长

		int outLen = 0;

		int end = off + len;

		while (off < end) {

			aChar = in[off++];

			if (aChar == '\\') {

				aChar = in[off++];

				if (aChar == 'u') {

					// Read the xxxx

					int value = 0;

					for (int i = 0; i < 4; i++) {

						aChar = in[off++];

						switch (aChar) {

						case '0':

						case '1':

						case '2':

						case '3':

						case '4':

						case '5':

						case '6':

						case '7':

						case '8':

						case '9':

							value = (value << 4) + aChar - '0';

							break;

						case 'a':

						case 'b':

						case 'c':

						case 'd':

						case 'e':

						case 'f':

							value = (value << 4) + 10 + aChar - 'a';

							break;

						case 'A':

						case 'B':

						case 'C':

						case 'D':

						case 'E':

						case 'F':

							value = (value << 4) + 10 + aChar - 'A';

							break;

						default:

							throw new IllegalArgumentException("Malformed \\uxxxx encoding.");

						}

					}

					out[outLen++] = (char) value;

				} else {

					if (aChar == 't') {

						aChar = '\t';

					} else if (aChar == 'r') {

						aChar = '\r';

					} else if (aChar == 'n') {

						aChar = '\n';

					} else if (aChar == 'f') {

						aChar = '\f';

					}

					out[outLen++] = aChar;

				}

			} else {

				out[outLen++] = (char) aChar;

			}

		}

		return new String(out, 0, outLen);

	}

	/**
	 * 处理字符串 Add By WuCF 可能传递过来的是字符串”null“，需要过滤
	 * 
	 * @param oldStr
	 * @return
	 */
	public static String dealStr(String oldStr) {
		String returnStr = "";
		if (oldStr == null || "null".equals(oldStr)) {
			returnStr = "";
		} else {
			returnStr = oldStr;
		}
		return returnStr;
	}

	/**
	 * 返回分页单位 Add By WuCF
	 * 
	 * @param request
	 * @return
	 */
	public static Integer dealPageSize(HttpServletRequest request) {
		String pageSizeSelectStr = request.getParameter("pageSizeSelect");
		pageSizeSelectStr = StringUtil.dealStr(pageSizeSelectStr);
		Integer pageSize = 10;
		if (!StringUtil.isEmpty(request.getParameter("pageSize")) && !"null".equals(request.getParameter("pageSize"))) {
			pageSize = Integer.parseInt(request.getParameter("pageSize"));
		}
		// 如果有新选择分页单位，则需要重新
		if (!StringUtil.isEmpty(pageSizeSelectStr) && !"null".equals(pageSizeSelectStr)) {
			pageSize = Integer.parseInt(pageSizeSelectStr);
		}
		request.setAttribute("pageSize", pageSize);
		return pageSize;
	}

	public static boolean getIsSeachTime(String companyCode) {

		Date sDate = null;
		Date eDate = null;
		Date curDate = new Date();
		try {

			String stime = ConfigUtil.getConfigValue(companyCode, "os.st.stime");
			String etime = ConfigUtil.getConfigValue(companyCode, "os.st.etime");

			String curDay = DateUtil.getDateTime("yyyy-MM-dd", curDate);

			stime = curDay + " " + stime;
			etime = curDay + " " + etime;
			sDate = DateUtil.convertStringToDate("yyyy-MM-dd HH:mm:ss", stime);
			eDate = DateUtil.convertStringToDate("yyyy-MM-dd HH:mm:ss", etime);
		} catch (Exception e) {

		}

		if (sDate.before(eDate)) {

			if (sDate != null && curDate.after(sDate) && eDate != null && curDate.before(eDate)) {
				return true;
			}
		} else {
			if ((sDate != null && curDate.after(sDate)) || (eDate != null && curDate.before(eDate))) {
				return true;
			}
		}

		return false;
	}

	/**
	 * 单独处理参数，因为有可能传递的参数值不存在的情况，则不需要传递setParameter，否则会报错!
	 * 
	 * @param query
	 * @param parameter
	 * @param value
	 */
	public static void dealSetParameter(Query query, String parameter, String value) {
		if (!StringUtils.isEmpty(value) && !"null".equals(value)) {
			query.setParameter(parameter, value);
		}
	}

	/**
	 * 判断某个字符串是否存在于数组中
	 * 
	 * @param stringArray
	 *            原数组
	 * @param source
	 *            查找的字符串
	 * @return 是否找到
	 */
	public static boolean contains(String[] stringArray, String source) {
		// 转换为list
		List<String> tempList = Arrays.asList(stringArray);
		if (tempList.contains(source)) {// 利用list的包含方法,进行判断
			return true;
		} else {
			return false;
		}
	}
}