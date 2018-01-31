package com.lonestarcell.mtn.bean;

import java.io.Serializable;

public class ExportLedger  implements Serializable {
	
	
	private static final long serialVersionUID = 7431320759077668024L;
	// private static Logger log = LogManager.getLogger();
	
	//private String accNo, name,  amount, date;
	private String column1, column2,  column3, date;

	public String getColumn1() {
		return column1;
	}

	public void setColumn1(String column1) {
		this.column1 = column1;
	}

	public String getColumn2() {
		return column2;
	}

	public void setColumn2(String column2) {
		this.column2 = column2;
	}

	public String getColumn3() {
		return column3;
	}

	public void setColumn3(String column3) {
		this.column3 = column3;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	

}
