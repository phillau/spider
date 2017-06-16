package com.kingdee.apusic.spider.common.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author 陈庆钊
 * @Email parkme210@hotmail.com
 * @date 2010-10-23下午10:55:59
 * @Description TODO
 */
public class Time {

	/**
	 * @author:陈庆钊 功能：得到当前时间
	 * @return dateNowStr
	 */
	public static String getNow() {

		Date dateNow = new Date();

		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");

		String dateNowStr = dateFormat.format(dateNow);

		return dateNowStr;
	}

	public static String getTime() {
		Long l = System.currentTimeMillis();
		return l.toString();
	}

	public static String getLastDate(int day) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, day);
		String tomorrow = sdf.format(cal.getTime());
		return tomorrow;
	}

	public static String getDay() {
		Date dateNow = new Date();

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

		String dateNowStr = dateFormat.format(dateNow);

		return dateNowStr;
	}

	public static String getDateStr(Date date) {

		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");

		String dateNowStr = dateFormat.format(date);

		return dateNowStr;
	}

	public static void main(String[] args) {
		Time t = new Time();
		System.out.println(getDay());
	}
}
