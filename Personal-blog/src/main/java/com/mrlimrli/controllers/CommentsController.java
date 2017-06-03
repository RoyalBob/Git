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
import com.mrlimrli.dto.ArticleCommentsDTO;
import com.mrlimrli.services.CommentsService;
import com.mrlimrli.utils.StringTools;

@Controller
public class CommentsController {
	
	@Resource(name = "commentsService")
	private CommentsService commentsService;
	
	/**
	 * 获取未审核评论
	 */
	@RequestMapping(value = "/admin/unCheckComments/{pageNum}")
	public String getUnCheckComments(HttpServletRequest request, ModelMap map, @PathVariable String pageNum) throws Exception {
		if (!StringTools.isNumeric(pageNum)) {
			return "redirect:/admin/unCheckComments/1";
		}
		int pageSize = 5;
		List<ArticleCommentsDTO> comments = commentsService.getUnCheckComments(Integer.parseInt(pageNum), pageSize);
		int amount = commentsService.countByStatus(Param.ARTICLE_COMMENT_UNCHECK);
		int pageCount = (amount/pageSize) == 0 ? 1 : (amount/pageSize) + 1;
		map.put("code", "200");
		map.put("pageNum", pageNum);
		map.put("pageSize", pageSize);
		map.put("amount", amount);
		map.put("pageCount", pageCount);
		map.put("comments", comments);
		map.put("msg", "获取成功");
		return "/admin/unCheckComments";
	}
	
	/**
	 * 获取已通过审核评论
	 */
	@RequestMapping(value = "/admin/passComments/{pageNum}")
	public String getPassComments(HttpServletRequest request, ModelMap map, @PathVariable String pageNum) throws Exception {
		if (!StringTools.isNumeric(pageNum)) {
			return "redirect:/admin/passComments/1";
		}
		int pageSize = 5;
		List<ArticleCommentsDTO> comments = commentsService.getPassComments(Integer.parseInt(pageNum), pageSize);
		int amount = commentsService.countByStatus(Param.ARTICLE_COMMENT_PASS);
		int pageCount = (amount/pageSize) == 0 ? 1 : (amount/pageSize) + 1;
		map.put("code", "200");
		map.put("pageNum", pageNum);
		map.put("pageSize", pageSize);
		map.put("amount", amount);
		map.put("pageCount", pageCount);
		map.put("comments", comments);
		map.put("msg", "获取成功");
		return "/admin/passComments";
	}
	
	/**
	 * 获取未通过审核评论
	 */
	@RequestMapping(value = "/admin/unPassComments/{pageNum}")
	public String getUnPassComments(HttpServletRequest request, ModelMap map, @PathVariable String pageNum) throws Exception {
		if (!StringTools.isNumeric(pageNum)) {
			return "redirect:/admin/unPassComments/1";
		}
		int pageSize = 5;
		List<ArticleCommentsDTO> comments = commentsService.getUnPassComments(Integer.parseInt(pageNum), pageSize);
		int amount = commentsService.countByStatus(Param.ARTICLE_COMMENT_UNPASS);
		int pageCount = (amount/pageSize) == 0 ? 1 : (amount/pageSize) + 1;
		map.put("code", "200");
		map.put("pageNum", pageNum);
		map.put("pageSize", pageSize);
		map.put("amount", amount);
		map.put("pageCount", pageCount);
		map.put("comments", comments);
		map.put("msg", "获取成功");
		return "/admin/unPassComments";
	}
	
	/**
	 * 通过审核
	 */
	@ResponseBody
	@RequestMapping(value = "/admin/pass")
	public HashMap<String, Object> passCom(HttpServletRequest request, String commentIds) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		if (StringTools.isEmpty(commentIds)) {
			map.put("code", "321");
			map.put("msg", "评论id不能为空");
			return map;
		}
		int setTotal = commentsService.pass(commentIds);
		int amount = commentsService.countByStatus(Param.ARTICLE_COMMENT_UNPASS);
		map.put("code", "200");
		map.put("amount", amount);
		map.put("setTotal", setTotal);
		map.put("url", "/admin/passComments/1");
		map.put("msg", "获取成功");
		return map;
	}
	
	/**
	 * 不通过审核
	 */
	@ResponseBody
	@RequestMapping(value = "/admin/unPass")
	public HashMap<String, Object> unPassCom(HttpServletRequest request, String commentIds) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		if (StringTools.isEmpty(commentIds)) {
			map.put("code", "321");
			map.put("msg", "评论id不能为空");
			return map;
		}
		int setTotal = commentsService.unPass(commentIds);
		int amount = commentsService.countByStatus(Param.ARTICLE_COMMENT_UNPASS);
		map.put("code", "200");
		map.put("amount", amount);
		map.put("setTotal", setTotal);
		map.put("url", "/admin/unPassComments/1");
		map.put("msg", "获取成功");
		return map;
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping(value = "/admin/delete")
	public HashMap<String, Object> deleteCom(HttpServletRequest request, String commentIds) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		if (StringTools.isEmpty(commentIds)) {
			map.put("code", "321");
			map.put("msg", "评论id不能为空");
			return map;
		}
		int setTotal = commentsService.delete(commentIds);
		int amount = commentsService.countByStatus(Param.ARTICLE_COMMENT_UNPASS);
		map.put("code", "200");
		map.put("amount", amount);
		map.put("setTotal", setTotal);
		map.put("url", "/admin/unCheckComments/1");
		map.put("msg", "获取成功");
		return map;
	}
	
	/**
	 * 回复评论
	 */
	@ResponseBody
	@RequestMapping(value = "/admin/replyComment")
	public HashMap<String, Object> replyComment(HttpServletRequest request, String commentId, String replyComment) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		if (StringTools.isEmpty(commentId)) {
			map.put("code", "321");
			map.put("msg", "回复的评论id不能为空");
			return map;
		}
		if (StringTools.isEmpty(replyComment)) {
			map.put("code", "322");
			map.put("msg", "回复内容不能为空");
			return map;
		}
		int returnCode = commentsService.replyComment(Integer.parseInt(commentId), replyComment);
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
