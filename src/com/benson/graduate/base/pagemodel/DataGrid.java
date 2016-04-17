package com.benson.graduate.base.pagemodel;

import java.util.List;

/**
 * easyui的datagrid模型
 * 
 * @author benson
 * 
 */
@SuppressWarnings("rawtypes")
public class DataGrid implements java.io.Serializable {
	
	private static final long serialVersionUID = -4610945984888639249L;
	
	private Long total;// 总记录数
	
	private List rows;// 每行记录
	private List footer;

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public List getRows() {
		return rows;
	}

	public void setRows(List rows) {
		this.rows = rows;
	}

	public List getFooter() {
		return footer;
	}

	public void setFooter(List footer) {
		this.footer = footer;
	}

}
