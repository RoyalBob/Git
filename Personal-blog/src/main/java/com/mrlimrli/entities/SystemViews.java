package com.mrlimrli.entities;

import java.io.Serializable;
import java.util.Date;

public class SystemViews implements Serializable {

	/**
	 * 系统数据统计实体
	 */
	private static final long serialVersionUID = 2700373033361915135L;
	
	private Integer id;
	private Integer views;
	private String ip;
	private Date createtime;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getViews() {
		return views;
	}
	public void setViews(Integer views) {
		this.views = views;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
}
