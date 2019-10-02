package com.joymain.ng.model;


import java.io.Serializable;
public class HttpMsg implements Serializable {
	private String code;   //	标识	code
	private String msg;   //	具体描述	msg
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}
