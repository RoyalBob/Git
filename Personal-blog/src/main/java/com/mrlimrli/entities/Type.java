package com.mrlimrli.entities;

import java.io.Serializable;

public class Type implements Serializable {

	/**
	 * 类型实体
	 */
	private static final long serialVersionUID = -1007897225824921796L;
	
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
