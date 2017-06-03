package com.mrlimrli.entities;

import java.io.Serializable;

public class ArticleType implements Serializable {
	/**
	 * 文章类型实体类
	 */
	private static final long serialVersionUID = -8795060178426679310L;
	
	private Integer id;
	private Integer article_id;
	private Integer type_id;
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
	public Integer getType_id() {
		return type_id;
	}
	public void setType_id(Integer type_id) {
		this.type_id = type_id;
	}
}
