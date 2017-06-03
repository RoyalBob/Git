package com.mrlimrli.dto;

import java.io.Serializable;
import java.util.Date;

public class ArticleDTO implements Serializable {
	/**
	 * 文章实体类
	 */
	private static final long serialVersionUID = -4132674741802590395L;
	
	private Integer id;
	private String title;
	private String content;
	private String author;
	private Integer views;
	private String status;
	private String image;
	private String quote_name;
	private Date createtime;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public Integer getViews() {
		return views;
	}
	public void setViews(Integer views) {
		this.views = views;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getQuote_name() {
		return quote_name;
	}
	public void setQuote_name(String quote_name) {
		this.quote_name = quote_name;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
}
