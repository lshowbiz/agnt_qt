package com.joymain.ng.handle.movie;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class Param {

	private String _key;
	private Object _value;
	private String _enc;
	
	public String key() { return _key; }
	public Object value() { return _value; }
	
	public Object urlValue() { 
		try {
			return URLEncoder.encode(_value.toString(), _enc);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Param(String key, Object value) {
		_key = key;
		_value = value;
		_enc = "utf-8";
	}

	public Param(String key, Object value, String enc) {
		_key = key;
		_value = value;
		_enc = enc;
	}
	
	public String toString() {
		return key() + " = " + value();
	}

}
