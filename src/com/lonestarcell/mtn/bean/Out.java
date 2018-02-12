package com.lonestarcell.mtn.bean;

import java.io.Serializable;

public class Out implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String msg = "Default error message.";
	private int statusCode = 100; 
	
	private BData<?> data;

	public BData<?> getData() {
		return data;
	}

	public void setData(BData<?> data) {
		this.data = data;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) { // out =
		this.msg = msg;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	
	
	
	
	

}
