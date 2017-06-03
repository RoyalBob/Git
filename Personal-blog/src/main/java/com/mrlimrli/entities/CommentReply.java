package com.mrlimrli.entities;

import java.io.Serializable;
import java.util.Date;

public class CommentReply implements Serializable {

	/**
	 * 评论回复实体
	 */
	private static final long serialVersionUID = -4401509791022921599L;
	
	private Integer id;
	private Integer comment_id;	
	private String reply;
	private Date createtime;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getComment_id() {
		return comment_id;
	}
	public void setComment_id(Integer comment_id) {
		this.comment_id = comment_id;
	}
	public String getReply() {
		return reply;
	}
	public void setReply(String reply) {
		this.reply = reply;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
}
