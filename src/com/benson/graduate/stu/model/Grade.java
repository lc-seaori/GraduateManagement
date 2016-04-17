package com.benson.graduate.stu.model;

/**
 *  年级实体 
 */
public class Grade {
	
	private Integer id;   //主键
	private String name;  //年纪名称
	private Integer seq;  //顺序，序列化
	private Integer is_greaduation;  //是否毕业班级
	
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
	public Integer getSeq() {
		return seq;
	}
	public void setSeq(Integer seq) {
		this.seq = seq;
	}
	public Integer getIs_greaduation() {
		return is_greaduation;
	}
	public void setIs_greaduation(Integer is_greaduation) {
		this.is_greaduation = is_greaduation;
	}
	
	
}
