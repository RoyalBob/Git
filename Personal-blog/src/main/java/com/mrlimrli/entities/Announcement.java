package com.mrlimrli.entities;

import java.io.Serializable;
import java.util.Date;

public class Announcement implements Serializable {
	/**
	 * 公告实体类
	 */
	private static final long serialVersionUID = 5819580650339700611L;
	
	private Integer id;
	private String content;
	private String status;
	private Date createtime;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
}
