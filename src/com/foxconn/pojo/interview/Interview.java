package com.foxconn.pojo.interview;

import java.util.List;

public class Interview {
	private String  interviewId;
	private String interviewTitle;
	private String interviewPhotosUrl;
	private String interviewPhotosName;
	private String interviewContent;
	private String entryUser;
	private String entryDate;
	private int status;
	private String interviewDate;
	private String interviewSummary;
	private String guestName;
	private String guestPhotosUrl;
	private String guestDesc;
	private List<Interview> getInterviewList;
	private List<Interview> getGuestInfo;
	private String scenePhotosId;
	private String videoPhotosUrl;
	
	public String getInterviewPhotosName() {
		if (interviewPhotosName != null) {
			int last = interviewPhotosName.lastIndexOf(".");
			interviewPhotosName = interviewPhotosName.substring(0, last);
		}
		return interviewPhotosName;
	}
	public void setInterviewPhotosName(String interviewPhotosName) {
		this.interviewPhotosName = interviewPhotosName;
	}
	public String getVideoPhotosUrl() {
		return videoPhotosUrl;
	}
	public void setVideoPhotosUrl(String videoPhotosUrl) {
		this.videoPhotosUrl = videoPhotosUrl;
	}
	public String getScenePhotosId() {
		return scenePhotosId;
	}
	public void setScenePhotosId(String scenePhotosId) {
		this.scenePhotosId = scenePhotosId;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	public String getInterviewId() {
		return interviewId;
	}
	public void setInterviewId(String interviewId) {
		this.interviewId = interviewId;
	}
	public String getInterviewTitle() {
		return interviewTitle;
	}
	public void setInterviewTitle(String interviewTitle) {
		this.interviewTitle = interviewTitle;
	}
	public String getInterviewPhotosUrl() {
		return interviewPhotosUrl;
	}
	public void setInterviewPhotosUrl(String interviewPhotosUrl) {
		this.interviewPhotosUrl = interviewPhotosUrl;
	}
	public String getInterviewContent() {
		return interviewContent;
	}
	public void setInterviewContent(String interviewContent) {
		this.interviewContent = interviewContent;
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
	public String getInterviewDate() {
		return interviewDate;
	}
	public void setInterviewDate(String interviewDate) {
		this.interviewDate = interviewDate;
	}
	public String getInterviewSummary() {
		return interviewSummary;
	}
	public void setInterviewSummary(String interviewSummary) {
		this.interviewSummary = interviewSummary;
	}
	public String getGuestName() {
		return guestName;
	}
	public void setGuestName(String guestName) {
		this.guestName = guestName;
	}
	public String getGuestPhotosUrl() {
		return guestPhotosUrl;
	}
	public void setGuestPhotosUrl(String guestPhotosUrl) {
		this.guestPhotosUrl = guestPhotosUrl;
	}
	public String getGuestDesc() {
		return guestDesc;
	}
	public void setGuestDesc(String guestDesc) {
		this.guestDesc = guestDesc;
	}
	
	public List<Interview> getGetInterviewList() {
		return getInterviewList;
	}
	public void setGetInterviewList(List<Interview> getInterviewList) {
		this.getInterviewList = getInterviewList;
	}
	public List<Interview> getGetGuestInfo() {
		return getGuestInfo;
	}
	public void setGetGuestInfo(List<Interview> getGuestInfo) {
		this.getGuestInfo = getGuestInfo;
	}
	
    

}
