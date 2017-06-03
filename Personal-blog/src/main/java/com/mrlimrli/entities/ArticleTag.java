package com.mrlimrli.entities;

import java.io.Serializable;
import java.util.Date;

public class ArticleTag implements Serializable {
	/**
	 * 文章标签实体类
	 */
	private static final long serialVersionUID = -1500912854150258191L;
	
	private Integer id;
	private Integer article_id;
	private Integer tag_id;
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
	public Integer getTag_id() {
		return tag_id;
	}
	public void setTag_id(Integer tag_id) {
		this.tag_id = tag_id;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
}
