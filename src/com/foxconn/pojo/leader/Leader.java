package com.foxconn.pojo.leader;

import java.util.List;


public class Leader {
 private String newsID;
 private String newsTitle;
 private String entryDate;
 private String entryuser;
 private String newsStatusID;
 private String newsStatusName;
 private String leaderId;
 private String leaderName;
 private String leaderPost;
 private String leaderPhotosUrl;
 private String leaderResume;
 private String programType;
 private int status;
 
 private List<Leader> LeaderList;
 private List<Leader> getLeaderActivate;
 private List<Leader> getLeaderInfo;
 
public String getNewsID() {
	return newsID;
}
public void setNewsID(String newsID) {
	this.newsID = newsID;
}
public String getNewsTitle() {
	return newsTitle;
}
public void setNewsTitle(String newsTitle) {
	this.newsTitle = newsTitle;
}
public String getEntryDate() {
	return entryDate;
}
public void setEntryDate(String entryDate) {
	this.entryDate = entryDate;
}
public String getEntryuser() {
	return entryuser;
}
public void setEntryuser(String entryuser) {
	this.entryuser = entryuser;
}
public String getNewsStatusID() {
	return newsStatusID;
}
public void setNewsStatusID(String newsStatusID) {
	this.newsStatusID = newsStatusID;
}
public String getNewsStatusName() {
	return newsStatusName;
}
public void setNewsStatusName(String newsStatusName) {
	this.newsStatusName = newsStatusName;
}
public String getLeaderId() {
	return leaderId;
}
public void setLeaderId(String leaderId) {
	this.leaderId = leaderId;
}
public String getLeaderName() {
	return leaderName;
}
public void setLeaderName(String leaderName) {
	this.leaderName = leaderName;
}
public String getLeaderPost() {
	return leaderPost;
}
public void setLeaderPost(String leaderPost) {
	this.leaderPost = leaderPost;
}
public String getLeaderPhotosUrl() {
	return leaderPhotosUrl;
}
public void setLeaderPhotosUrl(String leaderPhotosUrl) {
	this.leaderPhotosUrl = leaderPhotosUrl;
}
public String getLeaderResume() {
	return leaderResume;
}
public void setLeaderResume(String leaderResume) {
	this.leaderResume = leaderResume;
}
public List<Leader> getLeaderList() {
	return LeaderList;
}
public void setLeaderList(List<Leader> leaderList) {
	LeaderList = leaderList;
}
public List<Leader> getGetLeaderActivate() {
	return getLeaderActivate;
}
public void setGetLeaderActivate(List<Leader> getLeaderActivate) {
	this.getLeaderActivate = getLeaderActivate;
}
public List<Leader> getGetLeaderInfo() {
	return getLeaderInfo;
}
public void setGetLeaderInfo(List<Leader> getLeaderInfo) {
	this.getLeaderInfo = getLeaderInfo;
}
public String getProgramType() {
	return programType;
}
public void setProgramType(String programType) {
	this.programType = programType;
}
public int getStatus() {
	return status;
}
public void setStatus(int status) {
	this.status = status;
}


}
