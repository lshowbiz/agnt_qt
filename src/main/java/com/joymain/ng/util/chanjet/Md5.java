package com.joymain.ng.util.chanjet;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5 {


	public static String encodeMD5(String str){
		if (null == str) {
			return null;
		}
		StringBuilder sb = new StringBuilder();
		try {
			MessageDigest code = MessageDigest.getInstance("MD5");
			code.update(str.getBytes());
			byte[] bs = code.digest();
			for (int i = 0; i < bs.length; i++) {
				int v = bs[i] & 0xFF;
				if (v < 16) {
					sb.append(0);
				}
				sb.append(Integer.toHexString(v));
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}


	public static String Md5(String plainText) {
		StringBuffer buf = new StringBuffer();
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(plainText.getBytes());
			byte b[] = md.digest();
			int i;
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return buf.toString();
	}

}
