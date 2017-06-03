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
import com.mrlimrli.dto.ArticleCommentsReplyDTO;
import com.mrlimrli.dto.ArticleDTO;
import com.mrlimrli.dto.ArticleRecomDTO;
import com.mrlimrli.dto.ArticleTagDTO;
import com.mrlimrli.dto.ArticleTypeDTO;
import com.mrlimrli.dto.MessageReplyDTO;
import com.mrlimrli.entities.Announcement;
import com.mrlimrli.entities.Article;
import com.mrlimrli.entities.Tag;
import com.mrlimrli.entities.Type;
import com.mrlimrli.services.AnnouncementService;
import com.mrlimrli.services.ArticleService;
import com.mrlimrli.services.CommentsService;
import com.mrlimrli.services.MessageService;
import com.mrlimrli.services.SystemService;
import com.mrlimrli.services.TagService;
import com.mrlimrli.services.TypeService;
import com.mrlimrli.utils.HttpRequestTool;
import com.mrlimrli.utils.StringTools;

@Controller
public class IndexController {
	
	@Resource(name = "articleService")
	private ArticleService articleService;
	
	@Resource(name = "tagService")
	private TagService tagService;
	
	@Resource(name = "typeService")
	private TypeService typeService;
	
	@Resource(name = "commentsService")
	private CommentsService commentsService;
	
	@Resource(name = "messageService")
	private MessageService messageService;
	
	@Resource(name = "announcementService")
	private AnnouncementService announcementService;
	
	@Resource(name = "systemService")
	private SystemService systemService;
	
	@RequestMapping("/index")
	public String index(HttpServletRequest request, ModelMap map) throws Exception {
		List<ArticleRecomDTO> recomArticles = articleService.getRecomArticles();
		List<Article> recentArticles = articleService.getRecentArticles();
		List<Tag> tags = tagService.geTags();
		List<Type> types = typeService.getTypes();
		String ip = HttpRequestTool.getRemoteIP(request);
		systemService.addViews(ip);
		map.put("recentArticles", recentArticles);
		map.put("recomArticles", recomArticles);
		map.put("typeName", "分类浏览");
		map.put("tags", tags);
		map.put("types", types);
		map.put("msg", "获取成功");
		map.put("code", "200");
		return "/index";
	}
	
	/**
	 * 获取第一页文章
	 */
	@RequestMapping("/blog")
	public String allBlog(HttpServletRequest request, ModelMap map) throws Exception {
		int pageNum = 1;
		int pageSize = 5;
		List<ArticleDTO> articles = articleService.getIndexArticles(pageNum, pageSize);
		List<Article> recentArticles = articleService.getRecentArticles();
		List<Tag> tags = tagService.geTags();
		List<Type> types = typeService.getTypes();
		int amount = articleService.countArticles(Param.ARTICLE_POSTED);
		int pageCount;
		if (amount / pageSize == 0) {
			pageCount = 1;
		} else {
			if (amount % pageSize == 0) {
				pageCount = amount/pageSize;
			} else {
				pageCount = amount/pageSize + 1;
			}
		}
		map.put("articles", articles);
		map.put("recentArticles", recentArticles);
		map.put("typeName", "分类浏览");
		map.put("tags", tags);
		map.put("types", types);
		map.put("amount", amount);
		map.put("pageCount", pageCount);
		map.put("pageNum", pageNum);
		map.put("pageParam", 5);
		map.put("msg", "获取成功");
		map.put("code", "200");
		return "/blog";
	}
	
	/**
	 * 分页获取文章
	 */
	@RequestMapping("/blog/{pageNum}")
	public String allBlogPage(HttpServletRequest request, ModelMap map, @PathVariable String pageNum) throws Exception {
		if (!StringTools.isNumeric(pageNum)) {
			return "redirect:/blog";
		}
		int pageSize = 5;
		int pagenum = Integer.parseInt(pageNum);
		int amount = articleService.countArticles(Param.ARTICLE_POSTED);
		int pageCount;
		if (amount / pageSize == 0) {
			pageCount = 1;
		} else {
			if (amount % pageSize == 0) {
				pageCount = amount/pageSize;
			} else {
				pageCount = amount/pageSize + 1;
			}
		}
		if (pagenum < 1) {
			return "redirect:/blog/" + 1;
		}
		if (pagenum > pageCount) {
			return "redirect:/blog/" + pageCount;
		}
		List<ArticleDTO> articles = articleService.getIndexArticles(pagenum, pageSize);
		List<Article> recentArticles = articleService.getRecentArticles();
		List<Tag> tags = tagService.geTags();
		List<Type> types = typeService.getTypes();
		map.put("articles", articles);
		map.put("recentArticles", recentArticles);
		map.put("typeName", "分类浏览");
		map.put("tags", tags);
		map.put("types", types);
		map.put("amount", amount);
		map.put("pageCount", pageCount);
		map.put("pageNum", pageNum);
		map.put("pageParam", 5);
		map.put("msg", "获取成功");
		map.put("code", "200");
		return "/blog";
	}
	
	/**
	 * 按类型分页获取文章第一页
	 */
	@RequestMapping("/blog/type/{typeId}")
	public String typeArticle(HttpServletRequest request, ModelMap map, @PathVariable String typeId) throws Exception {
		if (!StringTools.isNumeric(typeId)) {
			return "redirect:/typeBlog";
		}
		int pageNum = 1;
		int pageSize = 5;
		List<ArticleTypeDTO> articles = articleService.getArticleByTypeId(pageNum, pageSize, Integer.parseInt(typeId));
		List<Article> recentArticles = articleService.getRecentArticles();
		List<Tag> tags = tagService.geTags();
		List<Type> types = typeService.getTypes();
		int amount = articleService.countTypeArticles(Integer.parseInt(typeId), Param.ARTICLE_POSTED);
		int pageCount;
		if (amount / pageSize == 0) {
			pageCount = 1;
		} else {
			if (amount % pageSize == 0) {
				pageCount = amount/pageSize;
			} else {
				pageCount = amount/pageSize + 1;
			}
		}
		map.put("articles", articles);
		map.put("recentArticles", recentArticles);
		map.put("typeId", typeId);
		map.put("tags", tags);
		map.put("types", types);
		map.put("amount", amount);
		map.put("pageCount", pageCount);
		map.put("pageNum", pageNum);
		map.put("pageParam", 5);
		map.put("code", "200");
		map.put("msg", "获取成功");
		return "/typeBlog";
	}
	
	/**
	 * 按类型分页获取文章
	 */
	@RequestMapping("/blog/type/{typeId}/{pageNum}")
	public String typeArticlePage(HttpServletRequest request, ModelMap map, @PathVariable String typeId, @PathVariable String pageNum) throws Exception {
		if (!StringTools.isNumeric(typeId)) {
			return "redirect:/typeBlog";
		}
		if (!StringTools.isNumeric(pageNum)) {
			return "redirect:/typeBlog";
		}
		int pageSize = 5;
		int pagenum = Integer.parseInt(pageNum);
		int amount = articleService.countTypeArticles(Integer.parseInt(typeId), Param.ARTICLE_POSTED);
		int pageCount;
		if (amount / pageSize == 0) {
			pageCount = 1;
		} else {
			if (amount % pageSize == 0) {
				pageCount = amount/pageSize;
			} else {
				pageCount = amount/pageSize + 1;
			}
		}
		if (pagenum < 1) {
			return "redirect:/blog/type/" + typeId +  "/" + 1;
		}
		if (pagenum > pageCount) {
			return "redirect:/blog/type/" + typeId +  "/" + pageCount;
		}
		List<ArticleTypeDTO> articles = articleService.getArticleByTypeId(pagenum, pageSize, Integer.parseInt(typeId));
		if (articles == null || articles.size() == 0) {
			return "redirect:/blog/type/" + typeId;
		}
		List<Article> recentArticles = articleService.getRecentArticles();
		List<Tag> tags = tagService.geTags();
		List<Type> types = typeService.getTypes();
		map.put("articles", articles);
		map.put("recentArticles", recentArticles);
		map.put("typeId", typeId);
		map.put("tags", tags);
		map.put("types", types);
		map.put("amount", amount);
		map.put("pageCount", pageCount);
		map.put("pageNum", pageNum);
		map.put("pageParam", 5);
		map.put("code", "200");
		map.put("msg", "获取成功");
		return "/typeBlog";
	}
	
	/**
	 * 按类型分页获取文章第一页
	 */
	@RequestMapping("/blog/tag/{tagId}")
	public String tagArticle(HttpServletRequest request, ModelMap map, @PathVariable String tagId) throws Exception {
		if (!StringTools.isNumeric(tagId)) {
			return "redirect:/tagBlog";
		}
		int pageNum = 1;
		int pageSize = 5;
		List<ArticleTagDTO> articles = articleService.getArticleByTagId(pageNum, pageSize, Integer.parseInt(tagId));
		List<Article> recentArticles = articleService.getRecentArticles();
		List<Tag> tags = tagService.geTags();
		List<Type> types = typeService.getTypes();
		int amount = articleService.countTagArticles(Integer.parseInt(tagId), Param.ARTICLE_POSTED);
		int pageCount;
		if (amount / pageSize == 0) {
			pageCount = 1;
		} else {
			if (amount % pageSize == 0) {
				pageCount = amount/pageSize;
			} else {
				pageCount = amount/pageSize + 1;
			}
		}
		map.put("articles", articles);
		map.put("recentArticles", recentArticles);
		map.put("tagId", tagId);
		map.put("tags", tags);
		map.put("types", types);
		map.put("amount", amount);
		map.put("pageCount", pageCount);
		map.put("pageNum", pageNum);
		map.put("pageParam", 5);
		map.put("code", "200");
		map.put("msg", "获取成功");
		return "/tagBlog";
	}
	
	/**
	 * 按标签分页获取文章
	 */
	@RequestMapping("/blog/tag/{tagId}/{pageNum}")
	public String tagArticlePage(HttpServletRequest request, ModelMap map, @PathVariable String tagId, @PathVariable String pageNum) throws Exception {
		if (!StringTools.isNumeric(tagId)) {
			return "redirect:/tagBlog";
		}
		if (!StringTools.isNumeric(pageNum)) {
			return "redirect:/tagBlog";
		}
		int pageSize = 5;
		int pagenum = Integer.parseInt(pageNum);
		int amount = articleService.countTagArticles(Integer.parseInt(tagId), Param.ARTICLE_POSTED);
		int pageCount;
		if (amount / pageSize == 0) {
			pageCount = 1;
		} else {
			if (amount % pageSize == 0) {
				pageCount = amount/pageSize;
			} else {
				pageCount = amount/pageSize + 1;
			}
		}
		if (pagenum < 1) {
			return "redirect:/blog/tag/" + tagId +  "/" + 1;
		}
		if (pagenum > pageCount) {
			return "redirect:/blog/tag/" + tagId +  "/" + pageCount;
		}
		List<ArticleTagDTO> articles = articleService.getArticleByTagId(pagenum, pageSize, Integer.parseInt(tagId));
		if (articles == null || articles.size() == 0) {
			return "redirect:/blog/tag/" + tagId;
		}
		List<Article> recentArticles = articleService.getRecentArticles();
		List<Tag> tags = tagService.geTags();
		List<Type> types = typeService.getTypes();
		map.put("articles", articles);
		map.put("recentArticles", recentArticles);
		map.put("tagId", tagId);
		map.put("tags", tags);
		map.put("types", types);
		map.put("amount", amount);
		map.put("pageCount", pageCount);
		map.put("pageNum", pageNum);
		map.put("pageParam", 5);
		map.put("code", "200");
		map.put("msg", "获取成功");
		return "/tagBlog";
	}
	
	/**
	 * 获取文章详情
	 */
	@RequestMapping("/blog/detail/{articleId}")
	public String getArticleDetail(HttpServletRequest request, ModelMap map, @PathVariable String articleId) throws Exception {
		if (!StringTools.isNumeric(articleId)) {
			return "redirect:/discover";
		}
		Article article = articleService.getArticle(Integer.parseInt(articleId));
		if (article == null) {
			return "redirect:/discover";
		}
		int commentAmount = commentsService.countArticleComments(Integer.parseInt(articleId), Param.ARTICLE_COMMENT_PASS);
		articleService.addViews(Integer.parseInt(articleId));
		List<ArticleCommentsReplyDTO> commentsAndReply = commentsService.getCommentsAndReply(Integer.parseInt(articleId));
		List<Article> recentArticles = articleService.getRecentArticles();
		List<Tag> tags = tagService.geTags();
		List<Type> types = typeService.getTypes();
		map.put("code", "200");
		map.put("article", article);
		map.put("commentsAndReply", commentsAndReply);
		map.put("commentAmount", commentAmount);
		map.put("recentArticles", recentArticles);
		map.put("tags", tags);
		map.put("types", types);
		map.put("msg", "获取成功");
		return "/single";
	}
	
	/**
	 * 添加评论
	 */
	@ResponseBody
	@RequestMapping("/addComment")
	public HashMap<String, Object> addComment(HttpServletRequest request, String articleId, String name, String email, String comment) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		if (StringTools.isEmpty(articleId, name, comment)) {
			map.put("code", "321");
			map.put("msg", "错误");
			return map;
		}
		String returnCode = commentsService.addComment(articleId, name, email, comment);
		if ("200".equals(returnCode)) {
			map.put("code", "200");
			map.put("msg", "评论成功，等待审核");
			map.put("url", "/blog/detail/" + articleId);
		} else {
			map.put("code", "321");
			map.put("msg", "评论失败");
			map.put("url", "/blog/detail/" + articleId);
		}
		return map;
	}
	
	/**
	 * 留言（第一页）
	 */
	@RequestMapping("/message")
	public String message(HttpServletRequest request, ModelMap map) throws Exception {
		List<Article> recentArticles = articleService.getRecentArticles();
		int pageNum = 1;
		int pageSize = 2;
		int amount = messageService.countByStatus(Param.MESSAGE_PASS);
		int pageCount;
		if (amount / pageSize == 0) {
			pageCount = 1;
		} else {
			if (amount % pageSize == 0) {
				pageCount = amount/pageSize;
			} else {
				pageCount = amount/pageSize + 1;
			}
		}
		List<MessageReplyDTO> messagesAndReply = messageService.getMessagesAndReply(pageNum, pageSize);
		List<Tag> tags = tagService.geTags();
		List<Type> types = typeService.getTypes();
		map.put("messagesAndReply", messagesAndReply);
		map.put("recentArticles", recentArticles);
		map.put("typeName", "分类浏览");
		map.put("tags", tags);
		map.put("types", types);
		map.put("pageNum", pageNum);
		map.put("pageCount", pageCount);
		map.put("pageParam", 5);
		map.put("amount", amount);
		map.put("msg", "获取成功");
		map.put("code", "200");
		return "/message";
	}
	
	/**
	 * 留言（分页）
	 */
	@RequestMapping("/message/{pageNum}")
	public String messagePage(HttpServletRequest request, ModelMap map, @PathVariable String pageNum) throws Exception {
		if (!StringTools.isNumeric(pageNum)) {
			return "redirect:/message";
		}
		int pageSize = 2;
		int pagenum = Integer.parseInt(pageNum);
		int amount = messageService.countByStatus(Param.MESSAGE_PASS);
		int pageCount;
		if (amount / pageSize == 0) {
			pageCount = 1;
		} else {
			if (amount % pageSize == 0) {
				pageCount = amount/pageSize;
			} else {
				pageCount = amount/pageSize + 1;
			}
		}
		if (pagenum < 1) {
			return "redirect:/message/" + 1;
		}
		if (pagenum > pageCount) {
			return "redirect:/message/" + pageCount;
		}
		List<Article> recentArticles = articleService.getRecentArticles();
		List<MessageReplyDTO> messagesAndReply = messageService.getMessagesAndReply(pagenum, pageSize);
		List<Tag> tags = tagService.geTags();
		List<Type> types = typeService.getTypes();
		map.put("messagesAndReply", messagesAndReply);
		map.put("recentArticles", recentArticles);
		map.put("amount", amount);
		map.put("pageCount", pageCount);
		map.put("pageNum", pageNum);
		map.put("typeName", "分类浏览");
		map.put("pageParam", 5);
		map.put("tags", tags);
		map.put("types", types);
		map.put("msg", "获取成功");
		map.put("code", "200");
		return "/message";
	}
	
	/**
	 * 添加留言
	 */
	@ResponseBody
	@RequestMapping("/addMessage")
	public HashMap<String, Object> addMessage(HttpServletRequest request, String name, String email, String homepage, String content) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		if (StringTools.isEmpty(name, content)) {
			map.put("code", "321");
			map.put("msg", "错误");
			return map;
		}
		String returnCode = messageService.addMessage(name, email, homepage, content);
		if ("200".equals(returnCode)) {
			map.put("code", "200");
			map.put("msg", "留言成功，等待审核");
			map.put("url", "/message");
		} else {
			map.put("code", "321");
			map.put("msg", "评论失败，请稍后再试");
			map.put("url", "/message");
		}
		return map;
	}
	
	/**
	 * 关于
	 */
	@RequestMapping("/about")
	public String about(HttpServletRequest request, ModelMap map) throws Exception {
		Announcement announcement = announcementService.getAnnouncement();
		List<Article> recentArticles = articleService.getRecentArticles();
		List<Tag> tags = tagService.geTags();
		List<Type> types = typeService.getTypes();
		map.put("announcement", announcement);
		map.put("recentArticles", recentArticles);
		map.put("tags", tags);
		map.put("types", types);
		map.put("msg", "获取成功");
		map.put("code", "200");
		return "/about";
	}
	
}
