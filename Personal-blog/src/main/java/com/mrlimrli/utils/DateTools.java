package com.mrlimrli.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTools {
	
	public static String currentTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(new Date());
	}
	
	public static String currentDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(new Date());
	}
	
	public static String formatDate(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String newDate = null;
		newDate = sdf.format(date);
		System.out.println(newDate.toString());
		return newDate;
	}
	
	public static void main(String[] args) {
		System.out.println(formatDate(new Date()));
	}
}
