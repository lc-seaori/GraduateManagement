package com.benson.graduate.news.model;

/**
 * 新闻实体
 * @author Benson
 */
public class NewsNn {
	
	private int id ;   //主键
	private String createName;   //创建人名称
	private String createTime;   //创建时间
	private int viewCount;		//浏览数目
	private String title;		//新闻标题
	private String summary;		//新闻简介
	private String content;		//新闻内容
	private String keyWords;	//新闻关键字
	private String coverImg;	//封面图片
	private int newsType; 		//新闻类型
	private int platePid;		//所属父版块id
	private int plateId;		//所属板块id
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCreateName() {
		return createName;
	}
	public void setCreateName(String createName) {
		this.createName = createName;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public int getViewCount() {
		return viewCount;
	}
	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getKeyWords() {
		return keyWords;
	}
	public void setKeyWords(String keyWords) {
		this.keyWords = keyWords;
	}
	public String getCoverImg() {
		return coverImg;
	}
	public void setCoverImg(String coverImg) {
		this.coverImg = coverImg;
	}
	public int getNewsType() {
		return newsType;
	}
	public void setNewsType(int newsType) {
		this.newsType = newsType;
	}
	public int getPlatePid() {
		return platePid;
	}
	public void setPlatePid(int platePid) {
		this.platePid = platePid;
	}
	public int getPlateId() {
		return plateId;
	}
	public void setPlateId(int plateId) {
		this.plateId = plateId;
	}
	
}
