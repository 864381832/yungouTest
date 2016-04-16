package com.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	/*
	 * 获取查询的时间段
	 */
	public static String[] getBuyTime(String buyTime) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		String[] date = new String[2];
		try {
			long buyTimeDate = formatter.parse(buyTime).getTime();
			Date BTime = new Date(buyTimeDate - 60 * 60 * 1000);
			Date ETime = new Date(buyTimeDate + 1000);
			date[0] = formatter.format(BTime).replace(" ", "%20");
			date[1] = formatter.format(ETime).replace(" ", "%20");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return date;
	}

	public static String getDateFormatStringToInt(String buyTime) {
		return buyTime.split(" ", 2)[1].replace(":", "").replace(".", "");
	}
}
