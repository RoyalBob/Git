package com.mrlimrli.entities;

import java.io.Serializable;

public class ArticleImage implements Serializable {
	/**
	 * 文章图片实体类
	 */
	private static final long serialVersionUID = -693408054314093594L;
	
	private Integer id;
	private Integer article_id;
	private String imgPath;
	private String quote_name;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getArticle_id() {
		return article_id;
	}
	public void setArticle_id(Integer article_id) {
		this.article_id = article_id;
	}
	public String getImgPath() {
		return imgPath;
	}
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
	public String getQuote_name() {
		return quote_name;
	}
	public void setQuote_name(String quote_name) {
		this.quote_name = quote_name;
	}
}
