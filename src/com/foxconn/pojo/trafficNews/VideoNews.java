package com.foxconn.pojo.trafficNews;

public class VideoNews {

	/**
	 * UUid
	 */
	private String videoNewsId;
	
	/**
	 * 视频新闻名称
	 */
	private String videoNewsName;
	
	/**
	 * 状态 (未审核/已审核/拟稿中/已发表/退回...)
	 */
	private String videoNewsStatus;
	
	/**
	 * 创建人员
	 */
	private String entryUser;
	
	/**
	 * 创建日期
	 */
	private String entryDate;
	
	/**
	 * 修改人员
	 */
	private String modifyUser;
	
	/**
	 * 修改日期
	 */
	private String modifyDate;
	
	/**
	 * 版面位置
	 */
	private String sectionPosition;
	
	/**
	 * 审核人
	 */
	private String approvaler;
	
	/**
	 * 录入时间
	 */
	private String recordDate;
	
	/**
	 * 发稿人
	 */
	private String pressPerson;
	
	/**
	 * 文档来源
	 */
	private String videoNewsSource;
	
	/**
	 * 视频说明
	 */
	private String videoDesc;
	
	/**
	 * 视频新闻副标题
	 */
	private String subVideoNewsTitle;
	
	/**
	 * 封面图片名称
	 */
	private String mainPhotosName;
	
	/**
	 * 封面图片地址
	 */
	private String mainPhotosUrl;

	/**
	 * 阅读人数
	 */
	private int readCount;
	/**
	 * 用于分页
	 */
	private int totalCount;// 数据总条数
	private int pageIndex;//
	private int pageSize;//
	
	private String videoUrl;
	
	public String getVideoUrl() {
		return videoUrl;
	}

	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}

	public String getVideoNewsId() {
		return videoNewsId;
	}

	public void setVideoNewsId(String videoNewsId) {
		this.videoNewsId = videoNewsId;
	}

	public String getVideoNewsName() {
		return videoNewsName;
	}

	public void setVideoNewsName(String videoNewsName) {
		this.videoNewsName = videoNewsName;
	}

	public String getVideoNewsStatus() {
		return videoNewsStatus;
	}

	public void setVideoNewsStatus(String videoNewsStatus) {
		this.videoNewsStatus = videoNewsStatus;
	}

	public String getEntryUser() {
		return entryUser;
	}

	public void setEntryUser(String entryUser) {
		this.entryUser = entryUser;
	}

	public String getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}

	public String getModifyUser() {
		return modifyUser;
	}

	public void setModifyUser(String modifyUser) {
		this.modifyUser = modifyUser;
	}

	public String getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}

	public String getSectionPosition() {
		return sectionPosition;
	}

	public void setSectionPosition(String sectionPosition) {
		this.sectionPosition = sectionPosition;
	}

	public String getApprovaler() {
		return approvaler;
	}

	public void setApprovaler(String approvaler) {
		this.approvaler = approvaler;
	}

	public String getRecordDate() {
		return recordDate;
	}

	public void setRecordDate(String recordDate) {
		this.recordDate = recordDate;
	}

	public String getPressPerson() {
		return pressPerson;
	}

	public void setPressPerson(String pressPerson) {
		this.pressPerson = pressPerson;
	}

	public String getVideoNewsSource() {
		return videoNewsSource;
	}

	public void setVideoNewsSource(String videoNewsSource) {
		this.videoNewsSource = videoNewsSource;
	}

	public String getVideoDesc() {
		return videoDesc;
	}

	public void setVideoDesc(String videoDesc) {
		this.videoDesc = videoDesc;
	}

	public String getSubVideoNewsTitle() {
		return subVideoNewsTitle;
	}

	public void setSubVideoNewsTitle(String subVideoNewsTitle) {
		this.subVideoNewsTitle = subVideoNewsTitle;
	}

	public String getMainPhotosName() {
		return mainPhotosName;
	}

	public void setMainPhotosName(String mainPhotosName) {
		this.mainPhotosName = mainPhotosName;
	}

	public String getMainPhotosUrl() {
		return mainPhotosUrl;
	}

	public void setMainPhotosUrl(String mainPhotosUrl) {
		this.mainPhotosUrl = mainPhotosUrl;
	}

	public int getReadCount() {
		return readCount;
	}

	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}	
}