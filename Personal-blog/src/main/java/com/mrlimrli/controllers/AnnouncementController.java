package com.mrlimrli.controllers;

import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mrlimrli.entities.Announcement;
import com.mrlimrli.services.AnnouncementService;
import com.mrlimrli.utils.StringTools;

@Controller
public class AnnouncementController {
	
	@Resource(name = "announcementService")
	private AnnouncementService announcementService;
	
	/**
	 * 新增公告跳转
	 */
	@RequestMapping("/admin/addAnnouncement")
	public String addAnnouncement(HttpServletRequest request) throws Exception {
		return "/admin/addAnnouncement";
	}
	
	/**
	 * 获取公告
	 */
	@RequestMapping("/admin/announcement")
	public String getAnnouncement(HttpServletRequest request, ModelMap map) throws Exception {
		Announcement announcement = announcementService.getAnnouncement();
		map.put("code", "200");
		map.put("announcement", announcement);
		map.put("msg", "获取成功");
		return "/admin/announcement";
	}
	
	/**
	 * 编辑公告跳转
	 */
	@RequestMapping("/admin/editAnnouncement/{id}")
	public String toEditAbout(HttpServletRequest request, ModelMap map, @PathVariable String id) throws Exception {
		if (!StringTools.isNumeric(id)) {
			return "redirect:/admin/announcement";
		}
		Announcement announcement = announcementService.getAnnouncement();
		map.put("code", "200");
		map.put("id", id);
		map.put("announcement", announcement);
		map.put("msg", "获取成功");
		return "/admin/editAnnouncement";
	}
	
	/**
	 * 修改公告
	 */
	@ResponseBody
	@RequestMapping("/admin/updateAnnouncement")
	public HashMap<String, Object> updateAnnouncement(HttpServletRequest request, String id, String content) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		if (StringTools.isEmpty(id)) {
			map.put("code", "321");
			map.put("msg", "id不能为空");
			return map;
		}
		if (StringTools.isEmpty(content)) {
			map.put("code", "322");
			map.put("msg", "内容不能为空");
			return map;
		}
		String returnCode = announcementService.updateAnnouncement(Integer.parseInt(id), content);
		if ("200".equals(returnCode)) {
			map.put("code", "200");
			map.put("msg", "修改成功");
			map.put("url", "/admin/announcement");
			return map;
		} else {
			map.put("code", "323");
			map.put("msg", "修改失败，服务器错误");
			return map;
		}
	}
	
	/**
	 * 保存公告
	 */
	@ResponseBody
	@RequestMapping("/admin/saveAnnouncement")
	public HashMap<String, Object> saveAnnouncement(HttpServletRequest request, String content) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		if (StringTools.isEmpty(content)) {
			map.put("code", "321");
			map.put("msg", "内容不能为空");
			return map;
		}
		String returnCode = announcementService.saveAnnouncement(content);
		if ("200".equals(returnCode)) {
			map.put("code", "200");
			map.put("msg", "保存成功");
			map.put("url", "/admin/announcement");
			return map;
		} else {
			map.put("code", "323");
			map.put("msg", "保存失败，服务器错误");
			return map;
		}
	}
	
	/**
	 * @author ljiun
	 * @date 2015年5月12日下午4:46:38
	 * @param request
	 * @param content
	 * @throws Exception
	 * @return HashMap<String,Object>
	 * @description 删除公告
	 */
	@ResponseBody
	@RequestMapping("/admin/delAnnouncement")
	public HashMap<String, Object> delAnnouncement(HttpServletRequest request, String id) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		if (StringTools.isEmpty(id)) {
			map.put("code", "321");
			map.put("msg", "id不能为空");
			return map;
		}
		String returnCode = announcementService.delAnnouncement(Integer.parseInt(id));
		if ("200".equals(returnCode)) {
			map.put("code", "200");
			map.put("msg", "删除成功");
			map.put("url", "/admin/announcement");
			return map;
		} else {
			map.put("code", "322");
			map.put("msg", "删除失败，服务器错误");
			return map;
		}
	}
	
}
