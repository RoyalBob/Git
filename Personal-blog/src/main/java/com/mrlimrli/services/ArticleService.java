package com.mrlimrli.services;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mrlimrli.common.Param;
import com.mrlimrli.dao.IArticleDao;
import com.mrlimrli.dao.IArticleImageDao;
import com.mrlimrli.dao.IArticleRecomDao;
import com.mrlimrli.dao.IArticleTagDao;
import com.mrlimrli.dao.IArticleTypeDao;
import com.mrlimrli.dto.ArticleDTO;
import com.mrlimrli.dto.ArticleRecomDTO;
import com.mrlimrli.dto.ArticleTagDTO;
import com.mrlimrli.dto.ArticleTypeDTO;
import com.mrlimrli.entities.Article;
import com.mrlimrli.entities.ArticleImage;
import com.mrlimrli.entities.ArticleRecom;
import com.mrlimrli.entities.ArticleTag;
import com.mrlimrli.entities.ArticleType;
import com.mrlimrli.utils.StringTools;

@Service("articleService")
public class ArticleService {

	@Resource(name = "articleDao")
	private IArticleDao articleDao;
	
	@Resource(name = "articleRecomDao")
	private IArticleRecomDao articleRecomDao;
	
	@Resource(name = "articleTagDao")
	private IArticleTagDao articleTagDao;
	
	@Resource(name = "articleTypeDao")
	private IArticleTypeDao articleTypeDao;
	
	@Resource(name = "articleImageDao")
	private IArticleImageDao articleImageDao;
	
	/**
	 * @author ljiun
	 * @date 2015年5月8日下午11:40:51
	 * @param articleId
	 * @throws Exception
	 * @return Article
	 * @description 获取文章
	 */
	public Article getArticle(Integer articleId) throws Exception {
		Article article = articleDao.getArticle(articleId);
		return article;
	}
	
	/**
	 * @author ljiun
	 * @date 2015年5月9日上午12:07:07
	 * @param articleId
	 * @param title
	 * @param content
	 * @param isPublish
	 * @throws Exception
	 * @return String
	 * @description 修改文章
	 */
	public String editArticle(Integer articleId, String title, String content, String isPublish) throws Exception {
		Article article = articleDao.getArticle(articleId);
		if (article == null) {
			return "322";
		}
		article.setTitle(title);
		article.setContent(content);
		if ("1".equals(isPublish)) { //已发布
			article.setStatus(Param.ARTICLE_POSTED);
		} else { //未发布
			article.setStatus(Param.ARTICLE_UNPOST);
		}
		if (articleDao.update(article) > 0) {
			return "200";
		} else {
			return "323";
		}
	}
	
	/**
	 * @author ljiun
	 * @date 2015年5月7日下午5:28:42
	 * @param author
	 * @param title
	 * @param content
	 * @throws Exception
	 * @return String
	 * @description 添加文章
	 */
	public String addArticle(String author, String title, String content) throws Exception {
		Article article = new Article();
		article.setAuthor(author);
		article.setTitle(title);
		article.setContent(content);
		article.setViews(0);
		article.setStatus(Param.ARTICLE_UNPOST);
		if (articleDao.add(article) > 0) {
			ArticleImage articleImage = new ArticleImage();
			articleImage.setArticle_id(article.getId());
			articleImageDao.add(articleImage);
			return "200";
		} else {
			return "323";
		}
	}
	
	/**
	 * @author ljiun
	 * @date 2015年5月7日下午5:28:42
	 * @param author
	 * @param title
	 * @param content
	 * @throws Exception
	 * @return String
	 * @description 发布文章
	 */
	public String publishArticle(String author, String title, String content) throws Exception {
		Article article = new Article();
		article.setAuthor(author);
		article.setTitle(title);
		article.setContent(content);
		article.setViews(0);
		article.setStatus(Param.ARTICLE_POSTED);
		if (articleDao.add(article) > 0) {
			ArticleImage articleImage = new ArticleImage();
			articleImage.setArticle_id(article.getId());
			articleImageDao.add(articleImage);
			return "200";
		} else {
			return "323";
		}
	}
	
	/**
	 * @author ljiun
	 * @date 2015年5月8日上午11:00:29
	 * @param pageNum
	 * @param pageSize
	 * @throws Exception
	 * @return List<Article>
	 * @description 分页获取已发布文章
	 */
	public List<Article> getPublishedArticles(Integer pageNum, Integer pageSize) throws Exception {
		if (pageNum < 0) {
			pageNum = 1;
		}
		int begin = (pageNum - 1) * pageSize;
		List<Article> articles = articleDao.getArticles(begin, pageSize, Param.ARTICLE_POSTED);
		for (Article article :articles) {
			String content = article.getContent();
			content = StringTools.removeHtmlTag(content);
			if (content.length() > 105) {
				content = content.substring(0, 105) + "...";
			}
			article.setContent(content);
		}
		return articles;
	}
	
	/**
	 * @author ljiun
	 * @date 2015年5月8日上午11:00:29
	 * @param pageNum
	 * @param pageSize
	 * @throws Exception
	 * @return List<Article>
	 * @description 分页获取前台文章
	 */
	public List<ArticleDTO> getIndexArticles(Integer pageNum, Integer pageSize) throws Exception {
		if (pageNum < 0) {
			pageNum = 1;
		}
		int begin = (pageNum - 1) * pageSize;
		List<ArticleDTO> articles = articleDao.getIndexArticles(begin, pageSize, Param.ARTICLE_POSTED);
		for (ArticleDTO article :articles) {
			String content = article.getContent();
			content = StringTools.removeHtmlTag(content);
			if (content.length() > 405) {
				content = content.substring(0, 385) + "...";
			}
			article.setContent(content);
			ArticleImage articleImage = articleImageDao.getById(article.getId());
			if (!StringTools.isEmpty(articleImage.getImgPath())) {
				article.setImage(articleImage.getImgPath());
			} 
		}
		return articles;
	}
	
	/**
	 * @author ljiun
	 * @date 2015年5月8日上午11:00:29
	 * @param pageNum
	 * @param pageSize
	 * @throws Exception
	 * @return List<Article>
	 * @description 分页获取已发布文章
	 */
	public List<Article> getUnPublishArticles(Integer pageNum, Integer pageSize) throws Exception {
		if (pageNum < 0) {
			pageNum = 1;
		}
		int begin = (pageNum - 1) * pageSize;
		List<Article> articles =  articleDao.getArticles(begin, pageSize, Param.ARTICLE_UNPOST);
		for (Article article :articles) {
			String content = article.getContent();
			content = StringTools.removeHtmlTag(content);
			if (content.length() > 105) {
				content = content.substring(0, 105) + "...";
			}
			article.setContent(content);
		}
		return articles;
	}
	
	/**
	 * @author ljiun
	 * @date 2015年5月8日下午1:07:23
	 * @throws Exception
	 * @return Integer
	 * @description 获取某种状态的文章数量
	 */
	public Integer countArticles(String status) throws Exception {
		return articleDao.countArticles(status);
	}
	
	/**
	 * @author ljiun
	 * @date 2015年5月8日下午1:07:23
	 * @throws Exception
	 * @return Integer
	 * @description 获取某种类型的文章数量
	 */
	public Integer countTypeArticles(Integer typeId, String status) throws Exception {
		return articleDao.countTypeArticles(typeId, status);
	}
	
	public Integer countTagArticles(Integer tagId, String status) throws Exception {
		return articleDao.countTagArticles(tagId, status);
	}
	
	/**
	 * @author ljiun
	 * @date 2015年5月8日下午3:32:39
	 * @param articleIds
	 * @throws Exception
	 * @return Integer 设置成功篇数
	 * @description 设置为推荐文章
	 */
	public Integer setRecomArticle(String articleIds) throws Exception {
		String[] idArr = articleIds.split(",");
		int setTotal = 0;
		for (int i = 0; i < idArr.length; i++) {
			if (StringTools.isEmpty(idArr[i])) {
				continue;
			}
			int article_id = Integer.parseInt(idArr[i]);
			ArticleRecom articleRecom = articleRecomDao.getRecomArticle(article_id);
			if (articleRecom == null) {
				articleRecom = new ArticleRecom();
				articleRecom.setArticle_id(article_id);
				articleRecomDao.add(articleRecom);
				setTotal = setTotal + 1;
			}
		}
		return setTotal;
	}
	
	/**
	 * @author ljiun
	 * @date 2015年5月9日下午11:45:52
	 * @param articleIds
	 * @throws Exception
	 * @return Integer
	 * @description 取消设置为推荐文章
	 */
	public Integer cancelRecomArticle(String articleIds) throws Exception {
		String[] idArr = articleIds.split(",");
		int cancelTotal = 0;
		for (int i = 0; i < idArr.length; i++) {
			if (StringTools.isEmpty(idArr[i])) {
				continue;
			}
			int article_id = Integer.parseInt(idArr[i]);
			ArticleRecom articleRecom = articleRecomDao.getRecomArticle(article_id);
			if (articleRecom != null) {
				articleRecomDao.delRecomArticle(article_id);
				cancelTotal = cancelTotal + 1;
			}
		}
		return cancelTotal;
	}
	
	/**
	 * @author ljiun
	 * @date 2015年5月9日下午11:30:44
	 * @throws Exception
	 * @return List<ArticleRecomDTO>
	 * @description 获取推荐文章
	 */
	public List<ArticleRecomDTO> getRecomArticles() throws Exception {
		List<ArticleRecomDTO> articles =  articleRecomDao.getRecomArticles();
		for (ArticleRecomDTO article :articles) {
			String content = article.getContent();
			content = StringTools.removeHtmlTag(content);
			if (content.length() > 105) {
				content = content.substring(0, 105) + "...";
			}
			article.setContent(content);
		}
		return articles;
		
	}
	
	/**
	 * @author ljiun
	 * @date 2015年5月10日上午12:11:15
	 * @throws Exception
	 * @return List<Article>
	 * @description 获取最新的5篇文章
	 */
	public List<Article> getRecentArticles() throws Exception {
		List<Article> articles = articleDao.getRecentArticles(Param.ARTICLE_POSTED);
		for (Article article :articles) {
			String content = article.getContent();
			content = StringTools.removeHtmlTag(content);
			if (content.length() > 105) {
				content = content.substring(0, 105) + "...";
			}
			article.setContent(content);
		}
		return articles;
	}
	
	/**
	 * @author ljiun
	 * @date 2015年5月9日下午11:38:00
	 * @throws Exception
	 * @return Integer
	 * @description 计算推荐文章数量
	 */
	public Integer countRecomArticles() throws Exception {
		return articleRecomDao.countRecomArticles();
	}
	
	/**
	 * @author ljiun
	 * @date 2015年5月10日上午12:32:12
	 * @throws Exception
	 * @return Integer
	 * @description 计算废纸篓文章数量
	 */
	public Integer countTrashArticles() throws Exception {
		return articleDao.countArticles(Param.ARTICLE_DELETE);
	}
	
	/**
	 * @author ljiun
	 * @date 2015年5月10日上午12:29:29
	 * @throws Exception
	 * @return List<Article>
	 * @description 获取废纸篓文章
	 */
	public List<Article> getTrashArticle() throws Exception {
		List<Article> articles = articleDao.getArticleByStatus(Param.ARTICLE_DELETE);
		for (Article article :articles) {
			String content = article.getContent();
			content = StringTools.removeHtmlTag(content);
			if (content.length() > 105) {
				content = content.substring(0, 105) + "...";
			}
			article.setContent(content);
		}
		return articles;
	}
	
	/**
	 * @author ljiun
	 * @date 2015年5月8日下午3:58:44
	 * @param articleIds
	 * @throws Exception
	 * @return Integer
	 * @description 将文章放到废纸篓
	 */
	public Integer addTrashArticle(String articleIds) throws Exception {
		String[] idArr = articleIds.split(",");
		int setTotal = 0;
		for (int i = 0; i < idArr.length; i++) {
			if (StringTools.isEmpty(idArr[i])) {
				continue;
			}
			int article_id = Integer.parseInt(idArr[i]);
			if (articleDao.updateStatusById(article_id, Param.ARTICLE_DELETE) > 0) {
				articleTagDao.delByArticleId(article_id);
				articleTypeDao.delByArticleId(article_id);
				articleRecomDao.delRecomArticle(article_id);
				setTotal = setTotal + 1;
			}
		}
		return setTotal;
	}
	
	/**
	 * @author ljiun
	 * @date 2015年5月10日上午12:42:38
	 * @param articleIds
	 * @throws Exception
	 * @return Integer
	 * @description 文章移出废纸篓（移出后状态为未发布）
	 */
	public Integer cancelTrashArticle(String articleIds) throws Exception {
		String[] idArr = articleIds.split(",");
		int setTotal = 0;
		for (int i = 0; i < idArr.length; i++) {
			if (StringTools.isEmpty(idArr[i])) {
				continue;
			}
			int article_id = Integer.parseInt(idArr[i]);
			if (articleDao.updateStatusById(article_id, Param.ARTICLE_UNPOST) > 0) {
				setTotal = setTotal + 1;
			}
		}
		return setTotal;
	}
	
	/**
	 * @author ljiun
	 * @date 2015年5月10日上午12:46:07
	 * @param articleIds
	 * @throws Exception
	 * @return Integer
	 * @description 彻底删除文章
	 */
	public Integer delTrashArticle(String articleIds) throws Exception {
		String[] idArr = articleIds.split(",");
		int setTotal = 0;
		for (int i = 0; i < idArr.length; i++) {
			if (StringTools.isEmpty(idArr[i])) {
				continue;
			}
			int article_id = Integer.parseInt(idArr[i]);
			if (articleDao.delById(article_id) > 0) {
				setTotal = setTotal + 1;
			}
		}
		return setTotal;
	}
	
	/**
	 * @author ljiun
	 * @date 2015年5月9日下午4:13:44
	 * @param articleIds
	 * @throws Exception
	 * @return Integer
	 * @description 改变文章状态
	 */
	public Integer changeArticleToPost(String articleIds) throws Exception {
		String[] idArr = articleIds.split(",");
		int setTotal = 0;
		for (int i = 0; i < idArr.length; i++) {
			if (StringTools.isEmpty(idArr[i])) {
				continue;
			}
			int article_id = Integer.parseInt(idArr[i]);
			if (articleDao.updateStatusById(article_id, Param.ARTICLE_POSTED) > 0) {
				setTotal = setTotal + 1;
			}
		}
		return setTotal;
	}
	
	/**
	 * @author ljiun
	 * @date 2015年5月8日下午5:16:23
	 * @param articleIds
	 * @param tagId
	 * @throws Exception
	 * @return Integer
	 * @description 设置文章标签
	 */
	public Integer setArticleTag(String articleIds, String tagId) throws Exception {
		String[] idArr = articleIds.split(",");
		int setTotal = 0;
		for (int i = 0; i < idArr.length; i++) {
			if (StringTools.isEmpty(idArr[i])) {
				continue;
			}
			int article_id = Integer.parseInt(idArr[i]);
			int tag_id = Integer.parseInt(tagId);
			ArticleTag articleTag = articleTagDao.getByArticleIdTagId(article_id, tag_id);
			if (articleTag == null) {
				articleTag = new ArticleTag();
				articleTag.setArticle_id(article_id);
				articleTag.setTag_id(tag_id);
				articleTagDao.add(articleTag);
				setTotal = setTotal + 1;
			}
		}
		return setTotal;
	}
	
	/**
	 * @author ljiun
	 * @date 2015年5月8日下午5:16:23
	 * @param articleIds
	 * @param tagId
	 * @throws Exception
	 * @return Integer
	 * @description 设置文章类型
	 */
	public Integer setArticleType(String articleIds, String typeId) throws Exception {
		String[] idArr = articleIds.split(",");
		int setTotal = 0;
		for (int i = 0; i < idArr.length; i++) {
			if (StringTools.isEmpty(idArr[i])) {
				continue;
			}
			int article_id = Integer.parseInt(idArr[i]);
			int type_id = Integer.parseInt(typeId);
			ArticleType articleType = articleTypeDao.getByArticleIdTypeId(article_id, type_id);
			if (articleType == null) {
				articleType = new ArticleType();
				articleType.setArticle_id(article_id);
				articleType.setType_id(type_id);
				articleTypeDao.add(articleType);
				setTotal = setTotal + 1;
			}
		}
		return setTotal;
	}
	
	/**
	 * @author ljiun
	 * @date 2015年5月9日下午4:51:52
	 * @param pageNum
	 * @param pageSize
	 * @param typeId
	 * @throws Exception
	 * @return List<ArticleTypeDTO>
	 * @description 分页获取某类型的文章
	 */
	public List<ArticleTypeDTO> getArticleByTypeId(Integer pageNum, Integer pageSize, Integer typeId) throws Exception {
		int begin = (pageNum - 1) * pageSize;
		List<ArticleTypeDTO> articleTypeDTOs = articleDao.getArticleByTypeId(typeId, begin, pageSize);
		for (ArticleTypeDTO articleTypeDTO :articleTypeDTOs) {
			String content = articleTypeDTO.getContent();
			content = StringTools.removeHtmlTag(content);
			if (content.length() > 405) {
				content = content.substring(0, 385) + "...";
			}
			articleTypeDTO.setContent(content);
		}
		return articleTypeDTOs;
	}
	
	/**
	 * @author ljiun
	 * @date 2015年5月9日下午6:39:27
	 * @param articleIds
	 * @param typeId
	 * @throws Exception
	 * @return Integer
	 * @description 将文章移出某类型
	 */
	public Integer removeArticleType(String articleIds, Integer typeId) throws Exception {
		String[] idArr = articleIds.split(",");
		int setTotal = 0;
		for (int i = 0; i < idArr.length; i++) {
			if (StringTools.isEmpty(idArr[i])) {
				continue;
			}
			int article_id = Integer.parseInt(idArr[i]);
			ArticleType articleType = articleTypeDao.getByArticleIdTypeId(article_id, typeId);
			if (articleType != null) {
				articleTypeDao.delByArticleIdTypeId(article_id, typeId);
				setTotal = setTotal + 1;
			}
		}
		return setTotal;
	}
	
	/**
	 * @author ljiun
	 * @date 2015年5月9日下午7:12:41
	 * @param pageNum
	 * @param pageSize
	 * @param tagId
	 * @throws Exception
	 * @return List<ArticleTypeDTO>
	 * @description 分页获取某标签的文章
	 */
	public List<ArticleTagDTO> getArticleByTagId(Integer pageNum, Integer pageSize, Integer tagId) throws Exception {
		int begin = (pageNum - 1) * pageSize;
		List<ArticleTagDTO> articleTagDTOs = articleDao.getArticleByTagId(tagId, begin, pageSize);
		for (ArticleTagDTO articleTagDTO :articleTagDTOs) {
			String content = articleTagDTO.getContent();
			content = StringTools.removeHtmlTag(content);
			if (content.length() > 405) {
				content = content.substring(0, 385) + "...";
			}
			articleTagDTO.setContent(content);
		}
		return articleTagDTOs;
	}
	
	/**
	 * @author ljiun
	 * @date 2015年5月9日下午6:44:12
	 * @param articleIds
	 * @param tagId
	 * @throws Exception
	 * @return Integer
	 * @description 将文章移出某标签
	 */
	public Integer removeArticleTag(String articleIds, Integer tagId) throws Exception {
		String[] idArr = articleIds.split(",");
		int setTotal = 0;
		for (int i = 0; i < idArr.length; i++) {
			if (StringTools.isEmpty(idArr[i])) {
				continue;
			}
			int article_id = Integer.parseInt(idArr[i]);
			ArticleTag articleTag = articleTagDao.getByArticleIdTagId(article_id, tagId);
			if (articleTag != null) {
				articleTagDao.delByArticleIdTagId(article_id, tagId);
				setTotal = setTotal + 1;
			}
		}
		return setTotal;
	}
	
	public String addViews(Integer article_id) throws Exception {
		if (articleDao.addViews(article_id) > 0) {
			return "200";
		} else {
			return "323";
		}
	}
	
}
