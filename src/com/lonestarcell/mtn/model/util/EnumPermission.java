package com.lonestarcell.mtn.model.util;

public enum EnumPermission {
	SYS_ACCESS ( ( short ) 1 ),
	USER_VIEW( ( short ) 2 ),
	USER_VIEW_DETAILS( ( short ) 3 ),
	USER_EXPORT ( ( short ) 4 ),
	USER_ACTIVATE_BLOCK ( ( short ) 5 ),
	USER_EXPIRE_PASSWORD ( ( short ) 6 ),
	USER_CANCEL_LOGIN_SESSION ( (short) 7 ),
	USER_SET_RESET_PASSWORD ( ( short ) 8 ),
	USER_CHANGE_PROFILE ( ( short )9 ),
	USER_ROLE_MANAGE ( ( short ) 10 ),
	DASH_SUBSCRIBER_STAT( ( short ) 11 ),
	DASH_MERCHANT_STAT( ( short )12 ),
	DASH_TRANSACTION_STAT (( short ) 13 ),
	DASH_SYS_USER_STAT ( (short ) 14 ),
	REPORT_VIEW_TRANSACTION( ( short ) 15 ),
	REPORT_EXPORT_TRANSACTION( (short) 16 ),
	REPORT_VIEW_MERCHANT( (short ) 17 ),
	REPORT_EXPORT_MERCHANT( (short) 18 ),
	REPORT_VIEW_SUBSCRIBER_REG( (short) 19 ),
	REPORT_EXPORT_SUBSCRIBER_REG( ( short ) 20 ),
	REPORT_VIEW_LEDGER( (short) 21 ),
	REPORT_EXPORT_LEDGER( (short)  22 ),
	DASH_VIEW( ( short ) 23 ),
	USER_MANAGE( ( short ) 24 ),
	REPORT_TRANSACTION( ( short ) 25 ),
	REPORT_SUBSCRIBER_REG( ( short ) 26 ),
	REPORT_LEDGER( ( short ) 27 ),
	USER_ADD ( ( short ) 28 );
	public short val;
	EnumPermission( Short val ){
		this.val = val;
	}
}
