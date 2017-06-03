package com.mrlimrli.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringTools {

	/**
	 * 判空
	 */
	public static boolean isEmpty(String str) {
		if (null == str || "".equals(str))
			return true;
		return false;
	}

	/**
	 * 判空（多参）
	 */
	public static boolean isEmpty(String... strs) {
		for (String str : strs) {
			if (isEmpty(str)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 去除html标签
	 */
	public static String removeHtmlTag(String str) {
		if (isEmpty(str)) {
			return "";
		}
		// 定义script的正则表达式
		String regEx_script = "<script[^>]*?>[\\s\\S]*?<\\/script>";
		// 定义style的正则表达式
		String regEx_style = "<style[^>]*?>[\\s\\S]*?<\\/style>";
		// 定义HTML标签的正则表达式
		String regEx_html = "<[^>]+>";

		Pattern p_script = Pattern.compile(regEx_script,Pattern.CASE_INSENSITIVE);
		Matcher m_script = p_script.matcher(str);
		str = m_script.replaceAll(""); // 过滤script标签

		Pattern p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
		Matcher m_style = p_style.matcher(str);
		str = m_style.replaceAll(""); // 过滤style标签

		Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
		Matcher m_html = p_html.matcher(str);
		str = m_html.replaceAll(""); // 过滤html标签

		return str.trim(); // 返回文本字符串
	}
	
	/**
	 * 判读是否为数字
	 */
	public static boolean isNumeric(String str){ 
	    Pattern pattern = Pattern.compile("[0-9]*"); 
	    return pattern.matcher(str).matches();    
	 } 
	
}
