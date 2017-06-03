package com.mrlimrli.dto;

import java.util.Date;

import com.mrlimrli.entities.CommentReply;

public class ArticleCommentsReplyDTO {
	private Integer comment_id;
	private Integer article_id;
	private String nickname;
	private String email;
	private String comment;
	private String status;
	private CommentReply commentReply;
	private Date comment_createtime;
	private String reply;
	private Date reply_createtime;
	public Integer getComment_id() {
		return comment_id;
	}
	public void setComment_id(Integer comment_id) {
		this.comment_id = comment_id;
	}
	public Integer getArticle_id() {
		return article_id;
	}
	public void setArticle_id(Integer article_id) {
		this.article_id = article_id;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public CommentReply getCommentReply() {
		return commentReply;
	}
	public void setCommentReply(CommentReply commentReply) {
		this.commentReply = commentReply;
	}
	public Date getComment_createtime() {
		return comment_createtime;
	}
	public void setComment_createtime(Date comment_createtime) {
		this.comment_createtime = comment_createtime;
	}
	public String getReply() {
		return reply;
	}
	public void setReply(String reply) {
		this.reply = reply;
	}
	public Date getReply_createtime() {
		return reply_createtime;
	}
	public void setReply_createtime(Date reply_createtime) {
		this.reply_createtime = reply_createtime;
	}
}
