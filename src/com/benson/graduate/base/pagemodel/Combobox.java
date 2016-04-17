package com.benson.graduate.base.pagemodel;

import java.io.Serializable;

/**
 * 多选框传递数据的实体
 * @author benson
 *
 */
public class Combobox implements Serializable{

	private static final long serialVersionUID = 2767087221828844286L;
	
	private Integer id;
	private String text;
	private boolean selected;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public boolean isSelected() {
		return selected;
	}
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	
	

}
