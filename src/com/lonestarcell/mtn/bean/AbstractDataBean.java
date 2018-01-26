package com.lonestarcell.mtn.bean;

import java.io.Serializable;

public abstract class AbstractDataBean implements IDataBean, Serializable {

	private static final long serialVersionUID = 1L;
	protected String date;
	protected String column1;
	protected String column2;
	protected String column3;
	protected String column4;
	protected String column5;
	protected String column6;
	protected String column7;
	protected String column8;
	protected String column9;
	protected String column10;
	protected String column11;
	
	public  String getColumn1(){
		return column1;
	}
	
	public String getColumn2() {
		return column2;
	}
	
	public String getColumn3() {
		return column3;
	}
	
	public String getColumn4() {
		return column4;
	}
	
	public String getColumn5() {
		return column5;
	}
	
	public String getColumn6() {
		return column6;
	}
	
	public String getColumn7() {
		return column7;
	}
	
	public String getColumn8() {
		return column8;
	}
	
	public String getColumn9() {
		return column9;
	}
	
	public String getColumn10() {
		return column10;
	}

	public String getColumn11() {
		return column11;
	}

	public String getDate() {
		return date;
	}
	
	
}
