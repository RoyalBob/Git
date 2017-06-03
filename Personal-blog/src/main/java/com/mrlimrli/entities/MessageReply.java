package com.mrlimrli.entities;

import java.io.Serializable;
import java.util.Date;

public class MessageReply implements Serializable {
	
	/**
	 * 留言回复实体
	 */
	private static final long serialVersionUID = 1042450125367287154L;
	
	private Integer id;
	private Integer message_id;
	private String reply;
	private Date createtime;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getMessage_id() {
		return message_id;
	}
	public void setMessage_id(Integer message_id) {
		this.message_id = message_id;
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
