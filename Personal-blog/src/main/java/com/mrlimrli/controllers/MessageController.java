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

import com.mrlimrli.common.Param;
import com.mrlimrli.entities.Message;
import com.mrlimrli.entities.MessageReply;
import com.mrlimrli.services.MessageService;
import com.mrlimrli.utils.StringTools;

@Controller
public class MessageController {
	
	@Resource(name = "messageService")
	private MessageService messageService;
	
	/**
	 * 未审核留言
	 */
	@RequestMapping("/admin/unCheckMessages/{pageNum}")
	public String getUnCheckMessage(HttpServletRequest request, ModelMap map, @PathVariable String pageNum) throws Exception {
		if (!StringTools.isNumeric(pageNum)) {
			return "redirect:/unCheckMessages/1";
		}
		int pageSize = 5;
		List<Message> messages = messageService.getByStatus(Param.MESSAGE_UNCHECK, Integer.parseInt(pageNum), pageSize);
		int amount = messageService.countByStatus(Param.MESSAGE_UNCHECK);
		int pageCount = (amount/pageSize) == 0 ? 1 : (amount/pageSize) + 1;
		map.put("code", "200");
		map.put("pageNum", pageNum);
		map.put("pageSize", pageSize);
		map.put("messages", messages);
		map.put("amount", amount);
		map.put("pageCount", pageCount);
		map.put("msg", "获取成功");
		return "/admin/unCheckMessages";
	}
	
	/**
	 * 未通过审核留言
	 */
	@RequestMapping("/admin/unPassMessages/{pageNum}")
	public String getUnPassMessage(HttpServletRequest request, ModelMap map, @PathVariable String pageNum) throws Exception {
		if (!StringTools.isNumeric(pageNum)) {
			return "redirect:/unPassMessages/1";
		}
		int pageSize = 5;
		List<Message> messages = messageService.getByStatus(Param.MESSAGE_UNPASS, Integer.parseInt(pageNum), pageSize);
		int amount = messageService.countByStatus(Param.MESSAGE_UNPASS);
		int pageCount = (amount/pageSize) == 0 ? 1 : (amount/pageSize) + 1;
		map.put("code", "200");
		map.put("pageNum", pageNum);
		map.put("pageSize", pageSize);
		map.put("messages", messages);
		map.put("amount", amount);
		map.put("pageCount", pageCount);
		map.put("msg", "获取成功");
		return "/admin/unPassMessages";
	}
	
	/**
	 * 已通过审核留言
	 */
	@RequestMapping("/admin/passMessages/{pageNum}")
	public String getPassMessage(HttpServletRequest request, ModelMap map, @PathVariable String pageNum) throws Exception {
		if (!StringTools.isNumeric(pageNum)) {
			return "redirect:/passMessages/1";
		}
		int pageSize = 5;
		List<Message> messages = messageService.getByStatus(Param.MESSAGE_PASS, Integer.parseInt(pageNum), pageSize);
		int amount = messageService.countByStatus(Param.MESSAGE_PASS);
		int pageCount = (amount/pageSize) == 0 ? 1 : (amount/pageSize) + 1;
		map.put("code", "200");
		map.put("pageNum", pageNum);
		map.put("pageSize", pageSize);
		map.put("messages", messages);
		map.put("amount", amount);
		map.put("pageCount", pageCount);
		map.put("msg", "获取成功");
		return "/admin/passMessages";
	}
	
	/**
	 * 通过审核
	 */
	@ResponseBody
	@RequestMapping(value = "/admin/passMsg")
	public HashMap<String, Object> pass(HttpServletRequest request, String messageIds) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		if (StringTools.isEmpty(messageIds)) {
			map.put("code", "321");
			map.put("msg", "留言id不能为空");
			return map;
		}
		int setTotal = messageService.pass(messageIds);
		int amount = messageService.countByStatus(Param.MESSAGE_PASS);
		map.put("code", "200");
		map.put("amount", amount);
		map.put("setTotal", setTotal);
		map.put("url", "/admin/passMessages/1");
		map.put("msg", "获取成功");
		return map;
	}
	
	/**
	 * 不通过审核
	 */
	@ResponseBody
	@RequestMapping(value = "/admin/unPassMsg")
	public HashMap<String, Object> unPass(HttpServletRequest request, String messageIds) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		if (StringTools.isEmpty(messageIds)) {
			map.put("code", "321");
			map.put("msg", "留言id不能为空");
			return map;
		}
		int setTotal = messageService.unPass(messageIds);
		int amount = messageService.countByStatus(Param.MESSAGE_UNPASS);
		map.put("code", "200");
		map.put("amount", amount);
		map.put("setTotal", setTotal);
		map.put("url", "/admin/unPassMessages/1");
		map.put("msg", "获取成功");
		return map;
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping(value = "/admin/deleteMsg")
	public HashMap<String, Object> delete(HttpServletRequest request, String messageIds) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		if (StringTools.isEmpty(messageIds)) {
			map.put("code", "321");
			map.put("msg", "留言id不能为空");
			return map;
		}
		int setTotal = messageService.delete(messageIds);
		int amount = messageService.countByStatus(Param.MESSAGE_UNCHECK);
		map.put("code", "200");
		map.put("amount", amount);
		map.put("setTotal", setTotal);
		map.put("url", "/admin/unCheckMessages/1");
		map.put("msg", "获取成功");
		return map;
	}
	
	/**
	 * 获取留言
	 */
	@RequestMapping("/admin/getMessage/{messageId}")
	public String getMessage(HttpServletRequest request, ModelMap map, @PathVariable String messageId) throws Exception {
		if (!StringTools.isNumeric(messageId)) {
			return "redirect:/admin/unCheckMessages";
		}
		Message message = messageService.getMessage(Integer.parseInt(messageId));
		MessageReply messageReply = messageService.getReply(Integer.parseInt(messageId));
		map.put("code", "200");
		map.put("message", message);
		map.put("messageReply", messageReply);
		map.put("msg", "获取成功");
		return "/admin/readMessage";
	}
	
	/**
	 * 回复留言
	 */
	@ResponseBody
	@RequestMapping(value = "/admin/replyMessage")
	public HashMap<String, Object> replyMessage(HttpServletRequest request, String messageId, String replyMessage) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		if (StringTools.isEmpty(messageId)) {
			map.put("code", "321");
			map.put("msg", "回复的留言id不能为空");
			return map;
		}
		if (StringTools.isEmpty(replyMessage)) {
			map.put("code", "322");
			map.put("msg", "回复内容不能为空");
			return map;
		}
		int returnCode = messageService.replyMessage(Integer.parseInt(messageId), replyMessage);
		if (returnCode > 0) {
			map.put("code", "200");
			map.put("msg", "回复成功");
		} else {
			map.put("code", "323");
			map.put("msg", "回复失败，服务器错误");
		}
		return map;
	}
	
}
