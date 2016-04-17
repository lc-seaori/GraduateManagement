package com.benson.graduate.news.pagemodel;

/**
 * 新闻板块页面传输实体
 * @author benson
 */
public class PageNewsPlate {
	
	private int id;		//主键
	private String plateName ;  //板块名称
	private String parentPlateName ; //父级板块名称
	private String plateLevel ;		//板块级别
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPlateName() {
		return plateName;
	}
	public void setPlateName(String plateName) {
		this.plateName = plateName;
	}
	public String getParentPlateName() {
		return parentPlateName;
	}
	public void setParentPlateName(String parentPlateName) {
		this.parentPlateName = parentPlateName;
	}
	public String getPlateLevel() {
		return plateLevel;
	}
	public void setPlateLevel(String plateLevel) {
		this.plateLevel = plateLevel;
	}
}
