package com.mrlimrli.controllers;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mrlimrli.entities.Tag;
import com.mrlimrli.services.TagService;
import com.mrlimrli.utils.StringTools;

@Controller
public class TagController {
	
	@Resource(name = "tagService")
	private TagService tagService;
	
	/**
	 * 标签页跳转
	 */
	@RequestMapping("/admin/tag")
	public String getTags(HttpServletRequest request, ModelMap map) throws Exception {
		List<Tag> tags = tagService.geTags();
		map.put("code", "200");
		map.put("tags", tags);
		map.put("msg", "获取成功");
		return "/admin/tag";
	}
	
	/**
	 * 增加标签跳转
	 */
	@RequestMapping("/admin/toAddTag")
	public String toAddTag() throws Exception {
		return "/admin/addTag";
	}
	
	/**
	 * 修改标签跳转
	 */
	@RequestMapping("/admin/editTag/{tagId}")
	public String editTag(HttpServletRequest request, ModelMap map, @PathVariable String tagId) throws Exception {
		if (!StringTools.isNumeric(tagId)) {
			return "/admin/tag";
		}
		Tag tag = tagService.getTag(Integer.parseInt(tagId));
		map.put("code", "200");
		map.put("tag", tag);
		map.put("msg", "获取成功");
		return "/admin/editTag";
	}
	
	/**
	 * 保存标签
	 */
	@ResponseBody
	@RequestMapping("/admin/saveTag")
	public HashMap<String, Object> saveTag(HttpServletRequest request, String tagName) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		if (StringTools.isEmpty(tagName)) {
			map.put("code", "321");
			map.put("msg", "标签名字不能为空");
			return map;
		}
		String returnCode = tagService.addTag(tagName);
		if ("200".equals(returnCode)) {
			map.put("code", "200");
			map.put("url", "/admin/tag");
			map.put("msg", "保存成功");
			return map;
		} else {
			map.put("code", "322");
			map.put("msg", "保存失败，服务器错误");
			return map;
		}
	}
	
	/**
	 * 修改标签
	 */
	@ResponseBody
	@RequestMapping("/admin/updateTag")
	public HashMap<String, Object> updateTag(HttpServletRequest request, String tagId, String tagName) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		if (StringTools.isEmpty(tagId)) {
			map.put("code", "321");
			map.put("msg", "标签id不能为空");
			return map;
		}
		if (StringTools.isEmpty(tagName)) {
			map.put("code", "322");
			map.put("msg", "标签名字不能为空");
			return map;
		}
		String returnCode = tagService.updateTag(Integer.parseInt(tagId), tagName);
		if ("200".equals(returnCode)) {
			map.put("code", "200");
			map.put("url", "/admin/tag");
			map.put("msg", "修改成功");
			return map;
		} else {
			map.put("code", "323");
			map.put("msg", "修改失败，服务器错误");
			return map;
		}
	}
	
	/**
	 * 删除标签
	 */
	@ResponseBody
	@RequestMapping("/admin/delTag")
	public HashMap<String, Object> delTag(HttpServletRequest request, String tagIds) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		if (StringTools.isEmpty(tagIds)) {
			map.put("code", "321");
			map.put("msg", "标签id不能为空");
			return map;
		}
		Integer setTotal = tagService.deleteTag(tagIds);
		map.put("code", "200");
		map.put("setTotal", setTotal);
		map.put("url", "/admin/tag");
		map.put("msg", "修改成功");
		return map;
	}
	
}