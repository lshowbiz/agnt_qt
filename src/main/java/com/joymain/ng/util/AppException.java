package com.joymain.ng.util;

/**
 * 自定义异常类
 * @author Aidy.Q
 *
 */
public class AppException extends java.lang.RuntimeException {
	private String errMsg = null;
	private Exception errEx = null;

	public void printStackTrace() {
		if (errEx != null) {
			errEx.printStackTrace();
		} else {
			super.printStackTrace();
		}

	}

	public String getMessage() {
		if (errEx != null) {
			return errEx.getMessage();
		} else {
			if (errMsg != null)
				return errMsg;
			else
				return super.getMessage();
		}
	}

	public String getErrMsg() {
		if (errMsg != null)
			return errMsg;
		else if (errEx != null)
			return errEx.getMessage();
		else
			return "没有指定错误信息";
	}

	public Exception getErrEx() {
		return errEx;
	}

	public AppException() {
	}

	public AppException(String msg) {
		this.errMsg = msg;
	}

	public AppException(Exception ex) {
		this.errEx = ex;
	}

	public AppException(String msg, Exception ex) {
		this.errMsg = msg;
		this.errEx = ex;
	}
}

