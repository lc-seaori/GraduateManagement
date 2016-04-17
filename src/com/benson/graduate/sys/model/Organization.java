package com.benson.graduate.sys.model;

import java.util.HashSet;
import java.util.Set;

/**
 * 组织（部门）实体
 * @author benson
 */
public class Organization {
	private Integer id;		//id自增
	private String name;     //组织部门名字
	private String icon;	//部门图标
	private Organization organization;	//上级部门（组织）
	private Set<Organization> organizations=new HashSet<Organization>();	//下级部门或者组织
	private String description;		//组织描述
	
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
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public Organization getOrganization() {
		return organization;
	}
	public void setOrganization(Organization organization) {
		this.organization = organization;
	}
	public Set<Organization> getOrganizations() {
		return organizations;
	}
	public void setOrganizations(Set<Organization> organizations) {
		this.organizations = organizations;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
