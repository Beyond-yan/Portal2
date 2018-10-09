package com.foxconn.pojo.trafficNews;

public class SubjectNews {

	/*
	 * 专栏id
	 */
	private String subjectId;
	/*
	 * 专栏标题
	 */
	private String subjectTitle;
	/*
	 * 专栏封面图片
	 */
	private String subjectPhotosName;
	/*
	 * 专栏封面图片地址
	 */
	private String subjectPhotosUrl;
	/*
	 * 专栏地址
	 */
	private String subjectUrl;
	/*
	 * 录入人
	 */
	private String entryUser;
	/*
	 * 录入时间
	 */
	private String entryDate;
	/*
	 * 修改人
	 */
	private String modifyUser;
	/*
	 * 修改时间
	 */
	private String modifyDate;
	
	private String IFRAME_URL;
	
	public String getIFRAME_URL() {
		return IFRAME_URL;
	}
	public void setIFRAME_URL(String iFRAME_URL) {
		IFRAME_URL = iFRAME_URL;
	}
	public String getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}
	public String getSubjectTitle() {
		return subjectTitle;
	}
	public void setSubjectTitle(String subjectTitle) {
		this.subjectTitle = subjectTitle;
	}
	public String getSubjectPhotosName() {
		return subjectPhotosName;
	}
	public void setSubjectPhotosName(String subjectPhotosName) {
		this.subjectPhotosName = subjectPhotosName;
	}
	public String getSubjectPhotosUrl() {
		return subjectPhotosUrl;
	}
	public void setSubjectPhotosUrl(String subjectPhotosUrl) {
		this.subjectPhotosUrl = subjectPhotosUrl;
	}
	public String getSubjectUrl() {
		return subjectUrl;
	}
	public void setSubjectUrl(String subjectUrl) {
		this.subjectUrl = subjectUrl;
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
	
	
}
