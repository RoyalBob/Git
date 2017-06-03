package com.mrlimrli.entities;

import java.io.Serializable;
import java.util.Date;

public class ArticleRecom implements Serializable {
	/**
	 * 文章推荐实体类
	 */
	private static final long serialVersionUID = 8252730187196591918L;
	
	private Integer id;
	private Integer article_id;
	private Date createtime;
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
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
}
