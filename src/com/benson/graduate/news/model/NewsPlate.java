package com.benson.graduate.news.model;

/**
 * 新闻板块实体类
 * @author benson
 */
public class NewsPlate {
	
	private int id;		//主键
	private NewsPlate newsPlate;	//单向n对1的关系
	private String plateName;	//板块名字
	private int newsType;		//板块类型
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public NewsPlate getNewsPlate() {
		return newsPlate;
	}
	public void setNewsPlate(NewsPlate newsPlate) {
		this.newsPlate = newsPlate;
	}
	public String getPlateName() {
		return plateName;
	}
	public void setPlateName(String plateName) {
		this.plateName = plateName;
	}
	public int getNewsType() {
		return newsType;
	}
	public void setNewsType(int newsType) {
		this.newsType = newsType;
	}
}
