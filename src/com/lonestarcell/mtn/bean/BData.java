package com.lonestarcell.mtn.bean;

import java.io.Serializable;

public class BData <T> implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private T data;

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
	

}
