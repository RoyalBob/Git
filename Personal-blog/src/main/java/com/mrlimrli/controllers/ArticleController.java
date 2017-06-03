package com.mrlimrli.controllers;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.mrlimrli.common.Param;
import com.mrlimrli.dto.ArticleCommentsReplyDTO;
import com.mrlimrli.dto.ArticleRecomDTO;
import com.mrlimrli.dto.ArticleTagDTO;
import com.mrlimrli.dto.ArticleTypeDTO;
import com.mrlimrli.dto.LoggedUser;
import com.mrlimrli.entities.Article;
import com.mrlimrli.entities.Tag;
import com.mrlimrli.entities.Type;
import com.mrlimrli.services.ArticleService;
import com.mrlimrli.services.CommentsService;
import com.mrlimrli.services.TagService;
import com.mrlimrli.services.TypeService;
import com.mrlimrli.utils.StringTools;

@Controller
public class ArticleController {
	
	@Resource(name = "articleService")
	private ArticleService articleService;
	
	@Resource(name = "tagService")
	private TagService tagService;
	
	@Resource(name = "typeService")
	private TypeService typeService;
	
	@Resource(name = "commentsService")
	private CommentsService commentsService;
	
	@Value("${uploadPath}")
	private String uploadPath;
	
	/**
	 * 发表文章页
	 */
	@RequestMapping("/admin/postArticle")
	public String toPostArticle() throws Exception {
		return "/admin/postArticle";
	}
	
	/**
	 * 后台获取文章
	 */
	@RequestMapping("/admin/getArticle/{articleId}")
	public String getAdminArticle(HttpServletRequest request, ModelMap map, @PathVariable String articleId) throws Exception {
		if (StringTools.isEmpty(articleId)) {
			return "redirect:/admin/publishedArticle";
		}
		if (!StringTools.isNumeric(articleId)) {
			return "redirect:/admin/publishedArticle";
		}
		Article article = articleService.getArticle(Integer.parseInt(articleId));
		int commentAmount = commentsService.countArticleComments(Integer.parseInt(articleId), Param.ARTICLE_COMMENT_PASS);
		List<ArticleCommentsReplyDTO> commentsAndReply = commentsService.getCommentsAndReply(Integer.parseInt(articleId));
		map.put("code", "200");
		map.put("article", article);
		map.put("commentsAndReply", commentsAndReply);
		map.put("commentAmount", commentAmount);
		map.put("msg", "获取成功");
		return "/admin/readArticle";
	}
	
	/**
	 * 获取编辑文章
	 */
	@RequestMapping("/admin/toEditArticle/{articleId}")
	public String toEditArticle(HttpServletRequest request, ModelMap map, @PathVariable String articleId) throws Exception {
		if (StringTools.isEmpty(articleId)) {
			return "redirect:/admin/publishedArticle/1";
		}
		if (!StringTools.isNumeric(articleId)) {
			return "redirect:/admin/publishedArticle/1";
		}
		Article article = articleService.getArticle(Integer.parseInt(articleId));
		map.put("code", "200");
		map.put("article", article);
		map.put("msg", "获取成功");
		return "/admin/editArticle";
	}
	
	/**
	 * 保存文章
	 */
	@ResponseBody
	@RequestMapping(value = "/admin/saveArticle")
	public HashMap<String, Object> saveArticle(HttpServletRequest request, String title, String content) throws Exception {
		HashMap<String , Object> map = new HashMap<String, Object>();
		if (StringTools.isEmpty(title)) {
			map.put("code", "321");
			map.put("msg", "标题不能为空");
			return map;
		}
		if (StringTools.isEmpty(content)) {
			map.put("code", "322");
			map.put("msg", "内容不能为空");
			return map;
		}
		LoggedUser loggedUser = (LoggedUser) request.getSession().getAttribute("loggedUser");
		if (loggedUser == null) {
			map.put("code", "324");
			map.put("url", "/admin/signin");
			map.put("msg", "未登录");
			return map;
		}
		String returnCode = articleService.addArticle(loggedUser.getRealname(), title, content);
		if ("200".equals(returnCode)) {
			map.put("code", "200");
			map.put("url", "/admin/unPublishArticle/1");
			map.put("msg", "保存成功");
			return map;
		} else {
			map.put("code", "323");
			map.put("msg", "保存失败，服务器错误");
			return map;
		}
	}
	
	/**
	 * 修改文章
	 */
	@ResponseBody
	@RequestMapping(value = "/admin/editArticle", method = RequestMethod.POST)
	public HashMap<String, Object> editArticle(HttpServletRequest request, String articleId, String title, String content, String isPublish) throws Exception {
		HashMap<String , Object> map = new HashMap<String, Object>();	
		if (StringTools.isEmpty(articleId, title, content, isPublish)) {
			map.put("code", "321");
			map.put("msg", "参数不能为空");
			return map;
		}
		String returnCode = articleService.editArticle(Integer.parseInt(articleId), title, content, isPublish);
		if ("200".equals(returnCode)) {
			if ("1".equals(isPublish)) {
				map.put("code", "200");
				map.put("url", "/admin/publishedArticle/1");
				map.put("msg", "修改并发布成功");
			} else {
				map.put("code", "200");
				map.put("url", "/admin/unPublishArticle/1");
				map.put("msg", "修改并保存成功");
			}
		}
		if ("322".equals(returnCode)) {
			map.put("code", "322");
			map.put("url", "/admin/publishedArticle");
			map.put("msg", "要编辑的文章不存在");
		}
		if ("323".equals(returnCode)) {
			map.put("code", "323");
			map.put("url", "/admin/publishedArticle");
			map.put("msg", "编辑失败，服务器错误");
		}
		return map;
	}
	
	/**
	 * 发布文章
	 */
	@ResponseBody
	@RequestMapping(value = "/admin/publishArticle", method = RequestMethod.POST)
	public HashMap<String, Object> publishArticle(HttpServletRequest request, String title, String content) throws Exception {
		HashMap<String , Object> map = new HashMap<String, Object>();
		if (StringTools.isEmpty(title)) {
			map.put("code", "321");
			map.put("msg", "标题不能为空");
			return map;
		}
		if (StringTools.isEmpty(content)) {
			map.put("code", "322");
			map.put("msg", "内容不能为空");
			return map;
		}
		LoggedUser loggedUser = (LoggedUser) request.getSession().getAttribute("loggedUser");
		if (loggedUser == null) {
			map.put("code", "324");
			map.put("url", "/admin/signin");
			map.put("msg", "未登录");
			return map;
		}
		String returnCode = articleService.publishArticle(loggedUser.getRealname(), title, content);
		if ("200".equals(returnCode)) {
			map.put("code", "200");
			map.put("url", "/admin/publishedArticle/1");
			map.put("msg", "发布成功");
			return map;
		} else {
			map.put("code", "323");
			map.put("msg", "发布失败，服务器错误");
			return map;
		}
	}
	
	/**
	 * 已发布文章
	 */
	@RequestMapping("/admin/publishedArticle/{pageNum}")
	public String publishedArticle(HttpServletRequest request, ModelMap map, @PathVariable String pageNum) throws Exception {
		if (!StringTools.isNumeric(pageNum)) {
			return "redirect:/admin/publishedArticle/1";
		}
		int pageSize = 5;
		List<Article> articles = articleService.getPublishedArticles(Integer.parseInt(pageNum), pageSize);
		List<Tag> tags = tagService.geTags();
		List<Type> types = typeService.getTypes();
		int amount = articleService.countArticles(Param.ARTICLE_POSTED);
		int pageCount = (amount/pageSize) == 0 ? 1 : (amount/pageSize) + 1;
 		map.put("articles", articles);
		map.put("tags", tags);
		map.put("types", types);
		map.put("pageCount", pageCount);
		map.put("amount", amount);
		map.put("pageNum", pageNum);
		map.put("pageSize", pageSize);
		return "/admin/publishedArticle";
	}
	
	/**
	 * 未发布文章
	 */
	@RequestMapping("/admin/unPublishArticle/{pageNum}")
	public String unPublishArticle(HttpServletRequest request, ModelMap map, @PathVariable String pageNum) throws Exception {
		if (!StringTools.isNumeric(pageNum)) {
			return "redirect:/admin/unPublishArticle/1";
		}
		int pageSize = 5;
		List<Article> articles = articleService.getUnPublishArticles(Integer.parseInt(pageNum), pageSize);
		List<Tag> tags = tagService.geTags();
		List<Type> types = typeService.getTypes();
		int amount = articleService.countArticles(Param.ARTICLE_UNPOST);
		int pageCount = (amount/pageSize) == 0 ? 1 : (amount/pageSize) + 1;
		map.put("articles", articles);
		map.put("tags", tags);
		map.put("types", types);
		map.put("amount", amount);
		map.put("pageCount", pageCount);
		map.put("pageNum", pageNum);
		map.put("pageSize", pageSize);
		return "/admin/unPublishArticle";
	}

	/**
	 * 设置推荐文章
	 */
	@ResponseBody
	@RequestMapping(value = "/admin/setRecomrticle", method = RequestMethod.POST)
	public HashMap<String, Object> setRecomrticle(HttpServletRequest request, String articleIds) throws Exception {
		HashMap<String , Object> map = new HashMap<String, Object>();
		if (StringTools.isEmpty(articleIds)) {
			map.put("code", "321");
			map.put("msg", "文章id不能为空");
			return map;
		}
		Integer setTotal = articleService.setRecomArticle(articleIds);
		map.put("code", "200");
		map.put("setTotal", setTotal);
		map.put("url", "/admin/publishedArticle/1");
		map.put("msg", "设置成功");
		return map;
	} 

	/**
	 * 取消设置推荐文章
	 */
	@ResponseBody
	@RequestMapping(value = "/admin/cancelRecomArticle", method = RequestMethod.POST)
	public HashMap<String, Object> cancelRecomArticle(HttpServletRequest request, String articleIds) throws Exception {
		HashMap<String , Object> map = new HashMap<String, Object>();
		if (StringTools.isEmpty(articleIds)) {
			map.put("code", "321");
			map.put("msg", "文章id不能为空");
			return map;
		}
		Integer cancelTotal = articleService.cancelRecomArticle(articleIds);
		map.put("code", "200");
		map.put("cancelTotal", cancelTotal);
		map.put("url", "/admin/recomArticle");
		map.put("msg", "设置成功");
		return map;
	} 
	
	/**
	 * 获取推荐文章
	 */
	@RequestMapping(value = "/admin/recomArticle")
	public String getRecomArticles(HttpServletRequest request, ModelMap map) throws Exception {
		List<ArticleRecomDTO> articles = articleService.getRecomArticles();
		int amount = articleService.countRecomArticles();
		map.put("code", "200");
		map.put("amount", amount);
		map.put("articles", articles);
		map.put("msg", "获取成功");
		return "/admin/recomArticle";
	}
	
	/**
	 * 获取最新5篇文章
	 */
	@RequestMapping(value = "/admin/recentArticle")
	public String getRecentArticles(HttpServletRequest request, ModelMap map) throws Exception {
		List<Article> articles = articleService.getRecentArticles();
		int amount = 7;
		map.put("code", "200");
		map.put("amount", amount);
		map.put("articles", articles);
		map.put("msg", "获取成功");
		return "/admin/recentArticle";
	}
	
	/**
	 * 文章废纸篓页
	 */
	@RequestMapping("/admin/trashArticle")
	public String getTrashArticle(HttpServletRequest request, ModelMap map) throws Exception {
		List<Article> articles = articleService.getTrashArticle();
		int amount = articleService.countTrashArticles();
		map.put("code", "200");
		map.put("amount", amount);
		map.put("articles", articles);
		map.put("msg", "获取成功");
		return "/admin/trashArticle";
	}
	
	/**
	 * 移动文章到废纸篓
	 */
	@ResponseBody
	@RequestMapping(value = "/admin/addTrashArticle", method = RequestMethod.POST)
	public HashMap<String, Object> addTrashArticle(HttpServletRequest request, String articleIds) throws Exception {
		HashMap<String , Object> map = new HashMap<String, Object>();
		if (StringTools.isEmpty(articleIds)) {
			map.put("code", "321");
			map.put("msg", "文章id不能为空");
			return map;
		}
		Integer delTotal = articleService.addTrashArticle(articleIds);
		map.put("code", "200");
		map.put("delTotal", delTotal);
		map.put("url", "/admin/publishedArticle/1");
		map.put("msg", "设置成功");
		return map;
	}
	
	/**
	 * 文章移出废纸篓
	 */
	@ResponseBody
	@RequestMapping(value = "/admin/cancelTrashArticle", method = RequestMethod.POST)
	public HashMap<String, Object> cancelTrashArticle(HttpServletRequest request, String articleIds) throws Exception {
		HashMap<String , Object> map = new HashMap<String, Object>();
		if (StringTools.isEmpty(articleIds)) {
			map.put("code", "321");
			map.put("msg", "文章id不能为空");
			return map;
		}
		Integer cancelTotal = articleService.cancelTrashArticle(articleIds);
		map.put("code", "200");
		map.put("cancelTotal", cancelTotal);
		map.put("url", "/admin/trashArticle");
		map.put("msg", "设置成功");
		return map;
	}
	
	/**
	 * 彻底删除文章
	 */
	@ResponseBody
	@RequestMapping(value = "/admin/delTrashArticle", method = RequestMethod.POST)
	public HashMap<String, Object> delTrashArticle(HttpServletRequest request, String articleIds) throws Exception {
		HashMap<String , Object> map = new HashMap<String, Object>();
		if (StringTools.isEmpty(articleIds)) {
			map.put("code", "321");
			map.put("msg", "文章id不能为空");
			return map;
		}
		Integer delTotal = articleService.delTrashArticle(articleIds);
		map.put("code", "200");
		map.put("delTotal", delTotal);
		map.put("url", "/admin/trashArticle");
		map.put("msg", "设置成功");
		return map;
	}
	
	/**
	 * 设置文章标签
	 */
	@ResponseBody
	@RequestMapping(value = "/admin/setArticleTag", method = RequestMethod.POST)
	public HashMap<String, Object> setArticleTag(HttpServletRequest request, String articleIds, String tagId) throws Exception {
		HashMap<String , Object> map = new HashMap<String, Object>();
		if (StringTools.isEmpty(articleIds)) {
			map.put("code", "321");
			map.put("msg", "文章id不能为空");
			return map;
		}
		if (StringTools.isEmpty(tagId)) {
			map.put("code", "322");
			map.put("msg", "标签id不能为空");
			return map;
		}
		int setTotal = articleService.setArticleTag(articleIds, tagId);
		map.put("code", "200");
		map.put("setTotal", setTotal);
		map.put("url", "/admin/publishedArticle/1");
		map.put("msg", "设置成功");
		return map;
	}
	
	/**
	 * 设置文章类型
	 */
	@ResponseBody
	@RequestMapping(value = "/admin/setArticleType", method = RequestMethod.POST)
	public HashMap<String, Object> setArticleType(HttpServletRequest request, String articleIds, String typeId) throws Exception {
		HashMap<String , Object> map = new HashMap<String, Object>();
		if (StringTools.isEmpty(articleIds)) {
			map.put("code", "321");
			map.put("msg", "文章id不能为空");
			return map;
		}
		if (StringTools.isEmpty(typeId)) {
			map.put("code", "322");
			map.put("msg", "类型id不能为空");
			return map;
		}
		int setTotal = articleService.setArticleType(articleIds, typeId);
		map.put("code", "200");
		map.put("setTotal", setTotal);
		map.put("url", "/admin/publishedArticle/1");
		map.put("msg", "设置成功");
		return map;
	}
	
	/**
	 * 改变文章状态
	 */
	@ResponseBody
	@RequestMapping(value = "/admin/changeArticleToPost", method = RequestMethod.POST)
	public HashMap<String, Object> changeArticleToPost(HttpServletRequest request, String articleIds) throws Exception {
		HashMap<String , Object> map = new HashMap<String, Object>();
		if (StringTools.isEmpty(articleIds)) {
			map.put("code", "321");
			map.put("msg", "文章id不能为空");
			return map;
		}
		Integer changeTotal = articleService.changeArticleToPost(articleIds);
		map.put("code", "200");
		map.put("changeTotal", changeTotal);
		map.put("url", "/admin/publishedArticle/1");
		map.put("msg", "设置成功");
		return map;
	}
	
	/**
	 * 按类型获取文章
	 */
	@RequestMapping(value = "/admin/getArticleByType/{typeId}/{pageNum}")
	public String getArticleByType(HttpServletRequest request, ModelMap map, @PathVariable String typeId, @PathVariable String pageNum) throws Exception {
		if (!StringTools.isNumeric(typeId)) {
			return "redirect:/admin/publishedArticle/1";
		}
		if (!StringTools.isNumeric(pageNum)) {
			return "redirect:/admin/publishedArticle/1";
		}
		if (StringTools.isEmpty(pageNum)) {
			pageNum = "1";
		}
		int pageSize = 5;
		List<ArticleTypeDTO> articleTypeDTOs = articleService.getArticleByTypeId(Integer.parseInt(pageNum), pageSize, Integer.parseInt(typeId));
		List<Tag> tags = tagService.geTags();
		List<Type> types = typeService.getTypes();
		int amount = articleService.countTypeArticles(Integer.parseInt(typeId), Param.ARTICLE_POSTED);
		int pageCount = (amount/pageSize) == 0 ? 1 : (amount/pageSize) + 1;
		map.put("code", "200");
		map.put("articleTypeDTOs", articleTypeDTOs);
		map.put("typeId", typeId);
		map.put("tags", tags);
		map.put("types", types);
		map.put("amount", amount);
		map.put("pageCount", pageCount);
		map.put("pageNum", pageNum);
		map.put("pageSize", pageSize);
		map.put("msg", "获取成功");
		return "/admin/typeArticle";
	}
	
	/**
	 * 将文章移出某类型
	 */
	@ResponseBody
	@RequestMapping(value = "/admin/removeArticleType", method = RequestMethod.POST)
	public HashMap<String, Object> removeArticleType(HttpServletRequest request, String articleIds, String typeId) throws Exception {
		HashMap<String , Object> map = new HashMap<String, Object>();
		if (StringTools.isEmpty(articleIds)) {
			map.put("code", "321");
			map.put("msg", "文章id不能为空");
			return map;
		}
		if (StringTools.isEmpty(typeId)) {
			map.put("code", "321");
			map.put("msg", "类型id不能为空");
			return map;
		}
		Integer removeTotal = articleService.removeArticleType(articleIds, Integer.parseInt(typeId));
		map.put("code", "200");
		map.put("removeTotal", removeTotal);
		map.put("url", "/admin/getArticleByType/" + typeId + "/1");
		map.put("msg", "设置成功");
		return map;
	} 
	
	/**
	 * 按标签获取文章
	 */
	@RequestMapping(value = "/admin/getArticleByTag/{tagId}/{pageNum}")
	public String getArticleByTag(HttpServletRequest request, ModelMap map, @PathVariable String tagId, @PathVariable String pageNum) throws Exception {
		if (!StringTools.isNumeric(tagId)) {
			return "redirect:/admin/publishedArticle/1";
		}
		if (!StringTools.isNumeric(pageNum)) {
			return "redirect:/admin/publishedArticle/1";
		}
		int pageSize = 5;
		List<ArticleTagDTO> articleTagDTOs = articleService.getArticleByTagId(Integer.parseInt(pageNum), pageSize, Integer.parseInt(tagId));
		List<Tag> tags = tagService.geTags();
		List<Type> types = typeService.getTypes();
		int amount = articleService.countTagArticles(Integer.parseInt(tagId), Param.ARTICLE_POSTED);
		int pageCount = (amount/pageSize) == 0 ? 1 : (amount/pageSize) + 1;
		map.put("code", "200");
		map.put("articleTagDTOs", articleTagDTOs);
		map.put("tagId", tagId);
		map.put("tags", tags);
		map.put("types", types);
		map.put("amount", amount);
		map.put("pageCount", pageCount);
		map.put("pageNum", pageNum);
		map.put("pageSize", pageSize);
		map.put("msg", "获取成功");
		return "/admin/tagArticle";
	}
	
	/**
	 * 将文章移出某标签
	 */
	@ResponseBody
	@RequestMapping(value = "/admin/removeArticleTag", method = RequestMethod.POST)
	public HashMap<String, Object> removeArticleTag(HttpServletRequest request, String articleIds, String tagId) throws Exception {
		HashMap<String , Object> map = new HashMap<String, Object>();
		if (StringTools.isEmpty(articleIds)) {
			map.put("code", "321");
			map.put("msg", "文章id不能为空");
			return map;
		}
		if (StringTools.isEmpty(tagId)) {
			map.put("code", "321");
			map.put("msg", "标签id不能为空");
			return map;
		}
		Integer removeTotal = articleService.removeArticleTag(articleIds, Integer.parseInt(tagId));
		map.put("code", "200");
		map.put("removeTotal", removeTotal);
		map.put("url", "/admin/getArticleByTag/" + tagId + "/1");
		map.put("msg", "设置成功");
		return map;
	}
	
	/**
	 * @author ljiun
	 * @date 2015年5月10日下午5:30:22
	 * @param upload
	 * @param request
	 * @param response
	 * @throws Exception
	 * @return void
	 * @description 上传文章图片
	 */
	@RequestMapping(value = "/admin/addArticleImage", method = RequestMethod.POST)
	public void addArticleImage(@RequestParam(value = "upload") MultipartFile upload, HttpServletRequest request, HttpServletResponse response) throws Exception{
    	String[] realName = com.mrlimrli.utils.FileUploadTools.uploadFile(upload, uploadPath + "/article");
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
    	String date = sdf.format(new Date());
    	String filelink = "/static/images/upload/article/" + date + "/" + realName[1];
    	String callback = request.getParameter("CKEditorFuncNum");
    	PrintWriter out = response.getWriter();
    	// 返回“图像”选项卡并显示图片  
    	out.println("<script type=\"text/javascript\">");  
    	out.println("window.parent.CKEDITOR.tools.callFunction(" + callback + ",'" + filelink + "','')"); // 相对路径用于显示图片  
    	out.println("</script>");
    	out.close();
	}
	
}
