package com.benson.graduate.common.page;
@SuppressWarnings("unused")
public class PaginationCustom extends Pagination {
	
	/**
	 * 当前页
	 */
	private int page = 1;

	/**
	 * 每页记录数
	 */
	private int rows = 20;
	
	
	private int startRow = 0;
	
	private int order = 1;
	
	public int getPage() {
		return page;
	}

	
	public void setPage(int page) {
		this.page = page;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getStartRow() {
		
		return (page-1)*rows;
	}

	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}


	public int getOrder() {
		return order;
	}

}
