package com.lonestarcell.mtn.model.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateFormatFacRuntime {

	public static Date toDate(String sDate) throws ParseRuntimeException {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return df.parse(sDate);
		} catch (Exception e) {
			throw new ParseRuntimeException(e.getMessage());
		}
	}

	public static Date toDateUpperBound(String sDate)
			throws ParseRuntimeException {

		try {
			// TODO Do I need to care about timezone/locale?
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Date date = df.parse(sDate);
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			cal.set(Calendar.HOUR, 23);
			cal.set(Calendar.MINUTE, 59);
			cal.set(Calendar.SECOND, 59);
			return cal.getTime();

		} catch (Exception ex) {

			ex.printStackTrace();
			throw new ParseRuntimeException(ex.getMessage());
		}
	}

	public static String toString(Date date) throws ParseRuntimeException {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			return df.format(date);

		} catch (Exception e) {
			throw new ParseRuntimeException(e.getMessage());
		}
	}

	public static String toStringDateOnly(Date date)
			throws ParseRuntimeException {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return df.format(date);
		} catch (Exception e) {
			throw new ParseRuntimeException(e.getMessage());
		}
	}

}
