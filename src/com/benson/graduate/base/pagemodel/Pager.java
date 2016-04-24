package com.benson.graduate.base.pagemodel;

import java.io.Serializable;
import java.util.List;

import com.benson.graduate.utils.CastUtil;

public class Pager implements Serializable{
	
	private static final long serialVersionUID = -1049501833953262279L;
	
	public Pager() {}

	public Pager(int pageNumber, int pageSize, long totalRecord, List recordList) {
		long totalPage = 0L;
		if (pageSize != 0)
			totalPage = totalRecord % (long) pageSize != 0L ? totalRecord / (long) pageSize + 1L
					: totalRecord / (long) pageSize;
		if (pageNumber <= 1)
			pageNumber = 1;
		if (totalRecord <= 0L)
			pageNumber = 0;
		if ((long) pageNumber > totalPage)
			pageNumber = CastUtil.castInt(Long.valueOf(totalPage));
		this.totalPage = totalPage;
		this.pageSize = pageSize;
		this.totalRecord = totalRecord;
		this.pageNumber = pageNumber;
		this.recordList = recordList;
	}

	public long getTotalPage() {
		return totalPage;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public int getPageSize() {
		return pageSize;
	}

	public long getTotalRecord() {
		return totalRecord;
	}

	public List getRecordList() {
		return recordList;
	}

	public boolean isFirstPage() {
		return pageNumber == 1;
	}

	public boolean isLastPage() {
		return (long) pageNumber == totalPage;
	}

	public boolean isPrevPage() {
		return pageNumber > 1 && (long) pageNumber <= totalPage;
	}

	public boolean isNextPage() {
		return (long) pageNumber < totalPage;
	}

	private int pageNumber;
	private int pageSize;
	private long totalRecord;
	private long totalPage;
	private List recordList;
}
