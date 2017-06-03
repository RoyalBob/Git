package com.mrlimrli.controllers;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mrlimrli.entities.User;
import com.mrlimrli.services.CommentsService;
import com.mrlimrli.services.MessageService;
import com.mrlimrli.services.SystemService;
import com.mrlimrli.services.UserService;
import com.mrlimrli.utils.DateTools;
import com.mrlimrli.utils.StringTools;


@Controller
public class UserController {
	
	@Resource(name = "userService")
	private UserService userService;
	
	@Resource(name = "systemService")
	private SystemService systemService;
	
	@Resource(name = "commentsService")
	private CommentsService commentsService;
	
	@Resource(name = "messageService")
	private MessageService messageService;
	
	/**
	 * 进入后台主页
	 */
	@RequestMapping("/admin")
	public String admin(HttpServletRequest request, ModelMap map) throws Exception {
		//获取今日访问次数
		int viewsToday = systemService.getViewsToday();
		//获取历史访问次数
		int viewsHistory = systemService.getViewsHistory();
		//获取今日留言数
		int todayMsgCount = messageService.countTodayMessages();
		//获取今日评论数
		int todayCommentCount = commentsService.countTodayComments();
		map.put("viewsToday", viewsToday);
		map.put("viewsHistory", viewsHistory);
		map.put("todayMsgCount", todayMsgCount);
		map.put("todayCommentCount", todayCommentCount);
		return "/admin/admin";
	}
	
	/**
	 * 进入后台登录页
	 */
	@RequestMapping("/admin/signin")
	public String signin(Model model) throws Exception {
		String date = DateTools.currentDate();
		model.addAttribute("date", date);
		return "admin/signin";
	}
	
	/**
	 * 登录
	 */
	@RequestMapping(value = "/admin/login", method = RequestMethod.POST)
	public String login(HttpServletRequest request, String username, String password) throws Exception {
		if (StringTools.isEmpty(username, password)) {
			return "redirect:/admin/signin";
		}
		User user = userService.login(username, password);
		if (user == null) {
			return "redirect:/admin/signin";
		} else {
			userService.setSession(request, user);
		}
		return "redirect:/admin";
	}
	
	/**
	 * 注销
	 */
	@RequestMapping(value = "/admin/logout")
	public String logout(HttpServletRequest request, String username, String password) throws Exception {
		request.getSession().removeAttribute("loggedUser");
		return "redirect:/admin/signin";
	}
	
}
