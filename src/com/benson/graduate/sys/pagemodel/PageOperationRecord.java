package com.benson.graduate.sys.pagemodel;

import java.io.Serializable;

public class PageOperationRecord implements Serializable {

	private static final long serialVersionUID = 5636246353706887076L;

	// 原来model存在的属性
	private Integer id; // 主键，自增
	private String operationPerson; // 操作者
	private String operationName; // 操作名称
	private String operationUrl; // 操作路径
	private String operationDescription; // 操作描述

	// 需要转换的属性
	private String pageOperationTime;

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

	public String getPageOperationTime() {
		return pageOperationTime;
	}

	public void setPageOperationTime(String pageOperationTime) {
		this.pageOperationTime = pageOperationTime;
	}

}
