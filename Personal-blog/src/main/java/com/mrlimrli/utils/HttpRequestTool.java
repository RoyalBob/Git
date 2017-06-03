package com.mrlimrli.utils;

import javax.servlet.http.HttpServletRequest;

public class HttpRequestTool {
	
	//或者用户真实ip
	public static String getRemoteIP(HttpServletRequest request) {
		String ip="";
		if (request.getHeader("x-forwarded-for") == null) {
			ip= request.getRemoteAddr();
		}else{
			ip= request.getHeader("x-forwarded-for");
		}
		if(ip!=null&&ip.indexOf(",")>-1){
			ip=(ip.split(","))[0];
		}
		return ip;
	}
}
