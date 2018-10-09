package com.foxconn.util;

import java.util.List;

public class Page {
	public static final int DEFAULT_PAGE_SIZE = 10;
	private int pageIndex;
	private int pageSize;
	private long totalCount;
	private int pageCount;
	private List<?> result;

	public Page(int pageIndex, int pageSize,Long rowCount,List<?> result) {
		if (pageIndex < 1)
			pageIndex = 1;
		if (pageSize < 1)
			pageSize = 1;
		this.pageIndex = pageIndex;
		this.pageSize = pageSize;
		setTotalCount(rowCount) ;
		this.result = result;
	}

	public Page(int pageIndex) {
//		this(pageIndex, 20);
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	
	public Page(int pageIndex,int pageSize){
		this.pageIndex=pageIndex;
		this.pageSize=pageSize;
	}

	public int getPageIndex() {
		return this.pageIndex;
	}

	public int getPageSize() {
		return this.pageSize;
	}

	public int getPageCount() {
		return this.pageCount;
	}

	public long getTotalCount() {
		return this.totalCount;
	}

	public int getFirstResult() {
		return ((this.pageIndex - 1) * this.pageSize);
	}

	public boolean getHasPrevious() {
		return (this.pageIndex > 1);
	}

	public boolean getHasNext() {
		return (this.pageIndex < this.pageCount);
	}

	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
		this.pageCount = (int) (totalCount / this.pageSize + ((totalCount
				% this.pageSize == 0L) ? 0 : 1));

		if (totalCount == 0L) {
			this.pageIndex = 0;
		} else if (this.pageIndex > this.pageCount)
			this.pageIndex = this.pageCount;
	}

	public boolean isEmpty() {
		return (this.totalCount == 0L);
	}

	public List<?> getResult() {
		return result;
	}

	public void setResult(List<?> result) {
		this.result = result;
	}
	
	
}