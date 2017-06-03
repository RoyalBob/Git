package com.mrlimrli.common;

public class Param {
	
	/**
	 * 文章状态
	 */
	public static final String ARTICLE_UNPOST = "1"; //未发布
	public static final String ARTICLE_POSTED = "2";//已发布
	public static final String ARTICLE_DELETE = "3";//已删除
	
	/**
	 * 文章评论状态
	 */
	public static final String ARTICLE_COMMENT_PASS = "1"; //通过
	public static final String ARTICLE_COMMENT_UNPASS = "2";//未通过
	public static final String ARTICLE_COMMENT_UNCHECK = "3";//未审核
	
	/**
	 * 留言评论状态
	 */
	public static final String MESSAGE_PASS = "1"; //通过
	public static final String MESSAGE_UNPASS = "2";//未通过
	public static final String MESSAGE_UNCHECK = "3";//未审核
	
	/**
	 * 公告状态
	 */
	public static final String ANNOUNCEMENT_SHOW = "1"; //显示
	public static final String ANNOUNCEMENT_DISPLAY = "2";//不显示
	
	
	public static final String LOGGEDUSER_VALIDATE = "e45469c9dabab42c108c24e54833b80202db34f0";
}
