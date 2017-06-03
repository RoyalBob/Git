package com.mrlimrli.services;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mrlimrli.common.Param;
import com.mrlimrli.dao.IArticleCommentsDao;
import com.mrlimrli.dao.ICommentReplyDao;
import com.mrlimrli.dto.ArticleCommentsDTO;
import com.mrlimrli.dto.ArticleCommentsReplyDTO;
import com.mrlimrli.entities.ArticleComments;
import com.mrlimrli.entities.CommentReply;
import com.mrlimrli.utils.StringTools;

@Service("commentsService")
public class CommentsService {
	
	@Resource(name = "articleCommentsDao")
	private IArticleCommentsDao articleCommentsDao;
	
	@Resource(name = "commentReplyDao")
	private ICommentReplyDao commentReplyDao;
	
	/**
	 * @author ljiun
	 * @date 2015年5月10日下午11:40:24
	 * @param pageNum
	 * @param pageSize
	 * @throws Exception
	 * @return List<ArticleComments>
	 * @description 获取未通过审核评论
	 */
	public List<ArticleCommentsDTO> getUnPassComments(Integer pageNum, Integer pageSize) throws Exception {
		int begin = (pageNum - 1) * pageSize;
		List<ArticleCommentsDTO> comments = articleCommentsDao.getByStatusAndPage(Param.ARTICLE_COMMENT_UNPASS, begin, pageSize);
		for (ArticleCommentsDTO comment :comments) {
			String content = comment.getComment();
			content = StringTools.removeHtmlTag(content);
			if (content.length() > 105) {
				content = content.substring(0, 105) + "...";
			}
			comment.setComment(content);
		}
		return comments;
	}
	
	/**
	 * @author ljiun
	 * @date 2015年5月10日下午11:41:11
	 * @param pageNum
	 * @param pageSize
	 * @throws Exception
	 * @return List<ArticleComments>
	 * @description 获取已通过审核评论
	 */
	public List<ArticleCommentsDTO> getPassComments(Integer pageNum, Integer pageSize) throws Exception {
		int begin = (pageNum - 1) * pageSize;
		List<ArticleCommentsDTO> comments = articleCommentsDao.getByStatusAndPage(Param.ARTICLE_COMMENT_PASS, begin, pageSize);
		for (ArticleCommentsDTO comment :comments) {
			String content = comment.getComment();
			content = StringTools.removeHtmlTag(content);
			if (content.length() > 105) {
				content = content.substring(0, 105) + "...";
			}
			comment.setComment(content);
		}
		return comments;
	}
	
	/**
	 * @author ljiun
	 * @date 2015年5月10日下午11:41:40
	 * @param pageNum
	 * @param pageSize
	 * @throws Exception
	 * @return List<ArticleComments>
	 * @description 获取未审核评论
	 */
	public List<ArticleCommentsDTO> getUnCheckComments(Integer pageNum, Integer pageSize) throws Exception {
		int begin = (pageNum - 1) * pageSize;
		List<ArticleCommentsDTO> comments = articleCommentsDao.getByStatusAndPage(Param.ARTICLE_COMMENT_UNCHECK, begin, pageSize);
		for (ArticleCommentsDTO comment :comments) {
			String content = comment.getComment();
			content = StringTools.removeHtmlTag(content);
			if (content.length() > 105) {
				content = content.substring(0, 105) + "...";
			}
			comment.setComment(content);
		}
		return comments;
	}
	
	
	/**
	 * @author ljiun
	 * @date 2015年5月10日下午11:47:38
	 * @param commentId
	 * @return Integer
	 * @throws Exception 
	 * @description 通过审核
	 */
	public Integer pass(String commentIds) throws Exception {
		String[] idArr = commentIds.split(",");
		int setTotal = 0;
		for (int i = 0; i < idArr.length; i++) {
			if (StringTools.isEmpty(idArr[i])) {
				continue;
			}
			int comment_id = Integer.parseInt(idArr[i]);
			int code = articleCommentsDao.updateStatusById(comment_id, Param.ARTICLE_COMMENT_PASS);
			if (code > 0) {
				setTotal = setTotal + 1;
			}
		}
		return setTotal;
	}
	
	/**
	 * @author ljiun
	 * @date 2015年5月10日下午11:50:21
	 * @param commentId
	 * @throws Exception
	 * @return String
	 * @description 不通过审核
	 */
	public Integer unPass(String commentIds) throws Exception {
		String[] idArr = commentIds.split(",");
		int setTotal = 0;
		for (int i = 0; i < idArr.length; i++) {
			if (StringTools.isEmpty(idArr[i])) {
				continue;
			}
			int comment_id = Integer.parseInt(idArr[i]);
			int code = articleCommentsDao.updateStatusById(comment_id, Param.ARTICLE_COMMENT_UNPASS);
			if (code > 0) {
				setTotal = setTotal + 1;
			}
		}
		return setTotal;
	}
	
	/**
	 * @author ljiun
	 * @date 2015年5月10日下午11:50:59
	 * @param commentId
	 * @throws Exception
	 * @return String
	 * @description 删除评论
	 */
	public Integer delete(String commentIds) throws Exception {
		String[] idArr = commentIds.split(",");
		int setTotal = 0;
		for (int i = 0; i < idArr.length; i++) {
			if (StringTools.isEmpty(idArr[i])) {
				continue;
			}
			int comment_id = Integer.parseInt(idArr[i]);
			int code = articleCommentsDao.delById(comment_id);
			if (code > 0) {
				setTotal = setTotal + 1;
			}
		}
		return setTotal;
	}
	
	/**
	 * @author ljiun
	 * @date 2015年5月10日下午11:54:59
	 * @param commentId
	 * @throws Exception
	 * @return String
	 * @description 计算某种状态评论的数量
	 */
	public Integer countByStatus(String status) throws Exception {
		return articleCommentsDao.countByStatus(status);
	}
	
	/**
	 * @author ljiun
	 * @date 2015年5月11日下午3:18:46
	 * @param articleId
	 * @throws Exception
	 * @return Integer
	 * @description 获取文章的评论数
	 */
	public Integer countArticleComments(Integer articleId, String status) throws Exception {
		return articleCommentsDao.countByArticleId(articleId, status);
	}
	
	/**
	 * @author ljiun
	 * @date 2015年5月11日下午3:59:51
	 * @param articleId
	 * @throws Exception
	 * @return List<ArticleCommentsReplyDTO>
	 * @description 获取文章评论和回复
	 */
	public List<ArticleCommentsReplyDTO> getCommentsAndReply(Integer articleId) throws Exception {
		return articleCommentsDao.getCommentsAndReply(articleId);
	}
	
	/**
	 * @author ljiun
	 * @date 2015年5月11日下午6:02:05
	 * @param commentId
	 * @param replyComment
	 * @throws Exception
	 * @return Integer
	 * @description 回复评论
	 */
	public Integer replyComment(Integer commentId, String replyComment) throws Exception {
		CommentReply commentReply = new CommentReply();
		commentReply.setComment_id(commentId);
		commentReply.setReply(replyComment);
		return commentReplyDao.add(commentReply);
	}
	
	/**
	 * @author ljiun
	 * @date 2015年5月13日下午6:25:32
	 * @throws Exception
	 * @return Integer
	 * @description 获取今日评论数
	 */
	public Integer countTodayComments() throws Exception {
		return articleCommentsDao.countTodayComments();
	}
	
	/**
	 * @author ljiun
	 * @date 2015年5月14日下午10:07:17
	 * @param name
	 * @param email
	 * @param comment
	 * @throws Exception
	 * @return String
	 * @description 增加评论
	 */
	public String addComment(String articleId, String name, String email, String comment) throws Exception {
		ArticleComments articleComments = new ArticleComments();
		articleComments.setArticle_id(Integer.parseInt(articleId));
		articleComments.setNickname(name);
		articleComments.setEmail(email);
		articleComments.setComment(comment);
		articleComments.setStatus(Param.ARTICLE_COMMENT_UNCHECK);
		if (articleCommentsDao.add(articleComments) > 0) {
			return "200";
		} else {
			return "321";
		}
	}
}
