package com.lonestarcell.mtn.bean;

public class Out {
	
	private String msg = "UNKNOWN ERROR OCCURED";
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

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	
	
	
	
	

}
