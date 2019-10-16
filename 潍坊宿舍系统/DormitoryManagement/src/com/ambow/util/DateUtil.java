package com.ambow.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DateUtil {

	public static java.sql.Date parseSqlDate(String str) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date datetime = sdf.parse(str);
		long times = datetime.getTime();
		java.sql.Date date = new java.sql.Date(times);
		return date;
	}
}
