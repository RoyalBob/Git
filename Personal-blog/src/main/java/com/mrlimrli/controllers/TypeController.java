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

import com.mrlimrli.entities.Type;
import com.mrlimrli.services.TypeService;
import com.mrlimrli.utils.StringTools;

@Controller
public class TypeController {
	
	@Resource(name = "typeService")
	private TypeService typeService ;
	
	/**
	 * 类型页跳转
	 */
	@RequestMapping("/admin/type")
	public String getTypes(HttpServletRequest request, ModelMap map) throws Exception {
		List<Type> types = typeService.getTypes();
		map.put("code", "200");
		map.put("types", types);
		map.put("msg", "获取成功");
		return "/admin/type";
	}
	
	/**
	 * 增加类型跳转
	 */
	@RequestMapping("/admin/toAddType")
	public String toAddType() throws Exception {
		return "/admin/addType";
	}
	
	/**
	 * 修改类型跳转
	 */
	@RequestMapping("/admin/editType/{typeId}")
	public String editType(HttpServletRequest request, ModelMap map, @PathVariable String typeId) throws Exception {
		if (!StringTools.isNumeric(typeId)) {
			return "/admin/type";
		}
		Type type = typeService.getType(Integer.parseInt(typeId));
		map.put("code", "200");
		map.put("type", type);
		map.put("msg", "获取成功");
		return "/admin/editType";
	}
	
	/**
	 * 保存类型
	 */
	@ResponseBody
	@RequestMapping("/admin/saveType")
	public HashMap<String, Object> saveType(HttpServletRequest request, String typeName) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		if (StringTools.isEmpty(typeName)) {
			map.put("code", "321");
			map.put("msg", "类型名字不能为空");
			return map;
		}
		String returnCode = typeService.addType(typeName);
		if ("200".equals(returnCode)) {
			map.put("code", "200");
			map.put("url", "/admin/type");
			map.put("msg", "保存成功");
			return map;
		} else {
			map.put("code", "322");
			map.put("msg", "保存失败，服务器错误");
			return map;
		}
	}
	
	/**
	 * 修改类型
	 */
	@ResponseBody
	@RequestMapping("/admin/updateType")
	public HashMap<String, Object> updateType(HttpServletRequest request, String typeId, String typeName) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		if (StringTools.isEmpty(typeId)) {
			map.put("code", "321");
			map.put("msg", "类型id不能为空");
			return map;
		}
		if (StringTools.isEmpty(typeName)) {
			map.put("code", "322");
			map.put("msg", "类型名字不能为空");
			return map;
		}
		String returnCode = typeService.updateType(Integer.parseInt(typeId), typeName);
		if ("200".equals(returnCode)) {
			map.put("code", "200");
			map.put("url", "/admin/type");
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
	@RequestMapping("/admin/delType")
	public HashMap<String, Object> delType(HttpServletRequest request, String typeIds) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		if (StringTools.isEmpty(typeIds)) {
			map.put("code", "321");
			map.put("msg", "类型id不能为空");
			return map;
		}
		Integer setTotal = typeService.deleteType(typeIds);
		map.put("code", "200");
		map.put("setTotal", setTotal);
		map.put("url", "/admin/type");
		map.put("msg", "修改成功");
		return map;
	}
	
}
