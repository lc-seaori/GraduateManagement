package com.benson.graduate.sys.model;

import java.util.Date;

public class OperationRecord {
	
	private Integer id;			//主键，自增
	private String operationPerson;		//操作者
	private String operationName;		//操作名称
	private String operationUrl;		//操作路径
	private String operationDescription;	//操作描述
	private Date operationTime=new Date();		//操作时间
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getOperationPerson() {
		return operationPerson;
	}
	public void setOperationPerson(String operationPerson) {
		this.operationPerson = operationPerson;
	}
	public String getOperationName() {
		return operationName;
	}
	public void setOperationName(String operationName) {
		this.operationName = operationName;
	}
	public String getOperationUrl() {
		return operationUrl;
	}
	public void setOperationUrl(String operationUrl) {
		this.operationUrl = operationUrl;
	}
	public String getOperationDescription() {
		return operationDescription;
	}
	public void setOperationDescription(String operationDescription) {
		this.operationDescription = operationDescription;
	}
	public Date getOperationTime() {
		return operationTime;
	}
	public void setOperationTime(Date operationTime) {
		this.operationTime = operationTime;
	}
	
}
