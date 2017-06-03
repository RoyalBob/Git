package com.mrlimrli.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.mrlimrli.dto.LoggedUser;

public class Interceptor implements HandlerInterceptor {

	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		LoggedUser loggedUser = (LoggedUser) request.getSession().getAttribute("loggedUser");
		if (loggedUser == null) {
			response.sendRedirect("/admin/signin");
		} else {
			if (!Param.LOGGEDUSER_VALIDATE.equals(loggedUser.getValidate())) {
				response.sendRedirect("/admin/signin");
			} 
			return true;
		}
		return false;
	}

}
