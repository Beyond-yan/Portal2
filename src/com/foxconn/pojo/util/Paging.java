/*
 * created by Cube @131012
 * */
package com.foxconn.pojo.util;

public class Paging {
	protected int curPage;
	protected int pageSize;
	protected int rEnd;
	protected int rStart;

	public int getCurPage() {
		return curPage;
	}

	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getrEnd() {
		return curPage * pageSize;
	}

	public int getrStart() {
		return (curPage - 1) * pageSize + 1;
	}
}
