package com.mrlimrli.dto;

import java.util.Date;

public class MessageReplyDTO {
	private Integer message_id;
	private String name;
	private String email;
	private String homepage;
	private String message;
	private String status;
	private Date message_createtime;
	private String reply;
	private Date reply_createtime;
	public Integer getMessage_id() {
		return message_id;
	}
	public void setMessage_id(Integer message_id) {
		this.message_id = message_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getHomepage() {
		return homepage;
	}
	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getMessage_createtime() {
		return message_createtime;
	}
	public void setMessage_createtime(Date message_createtime) {
		this.message_createtime = message_createtime;
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
