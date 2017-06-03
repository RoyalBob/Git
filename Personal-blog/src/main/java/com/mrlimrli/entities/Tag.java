package com.mrlimrli.entities;

import java.io.Serializable;

public class Tag implements Serializable {

	/**
	 * 标签实体
	 */
	private static final long serialVersionUID = -2265970645778888963L;
	
	private Integer id;
	private String name;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
