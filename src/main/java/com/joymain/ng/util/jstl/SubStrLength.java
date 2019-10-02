package com.joymain.ng.util.jstl;

import com.joymain.ng.util.MeteorUtil;

public class SubStrLength {
	public static String abbreviate(String str, int width, String ellipsis) {
		if (str == null || "".equals(str)) {
			return "";
		}

		int d = 0; // byte length
		int n = 0; // char length
		for (; n < str.length(); n++) {
			d = (int) str.charAt(n) > 256 ? d + 2 : d + 1;
			if (d > width) {
				break;
			}
		}

		if (d > width) {
			n = n - ellipsis.length() / 2;
			return str.substring(0, n > 0 ? n : 0) + ellipsis;
		}

		return str = str.substring(0, n);
	}
	
	public static boolean isAbbreviate(String str, int width) {
		if (str == null || "".equals(str)) {
			return false;
		}

		if(MeteorUtil.length(str)>=width){
			return true;
		}
		return false;
	}
}
