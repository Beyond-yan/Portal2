package com.foxconn.util;

public class PageUtils {

	private int recoredCount;//总记录数
	private int pageCount = 1;//总页数
	private int pageIndex = 1;//当前页
	private int limitBegin;//开始索引
	private int limitCount = 20;//每页显示条数
	private int limitEnd;//结束索引
	
	
	/**
	 * 
	 * @param recoredCount  总记录数
	 * @param pageIndex 当前页数
	 * @param limitCount 每页显示条数
	 */
	public  PageUtils(int recoredCount,int pageIndex,int limitCount){
		
		if(recoredCount>=1){
			this.recoredCount=recoredCount;
		}
		
		if(pageIndex > 1){
			this.pageIndex=pageIndex;
		}
		
		if(limitCount > 0){
			this.limitCount=limitCount;
		}

		setPage();
	}
	
	public void setPage(){
		
		if(this.recoredCount >0){
			this.pageCount=this.recoredCount/this.limitCount;
		}
		
		if(this.recoredCount%limitCount!=0){
			this.pageCount++;
		}
		
		if(this.pageIndex>this.pageCount){
			this.pageIndex=this.pageCount;
		}
		
		this.limitBegin = (this.pageIndex-1) * limitCount ;
		this.limitEnd = this.pageIndex * limitCount ;
	}

	public int getRecoredCount() {
		return recoredCount;
	}

	public void setRecoredCount(int recoredCount) {
		this.recoredCount = recoredCount;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}


	public int getLimitBegin() {
		return limitBegin;
	}

	public void setLimitBegin(int limitBegin) {
		this.limitBegin = limitBegin;
	}
	
	public int getLimitCount() {
		return limitCount;
	}

	public void setLimitCount(int limitCount) {
		this.limitCount = limitCount;
	}


	public int getLimitEnd() {
		return limitEnd;
	}

	public void setLimitEnd(int limitEnd) {
		this.limitEnd = limitEnd;
	}
}
