package com.foxconn.pojo.trafficNews;

import java.util.List;

public class NewsCommendInfo {
   private String commendID;
   private String commendUserIP;
   private String newsID;
   
   private List<NewsCommendInfo> newsCommendInfoList;

public String getCommendID() {
	return commendID;
}

public void setCommendID(String commendID) {
	this.commendID = commendID;
}


public String getCommendUserIP() {
	return commendUserIP;
}

public void setCommendUserIP(String commendUserIP) {
	this.commendUserIP = commendUserIP;
}

public String getNewsID() {
	return newsID;
}

public void setNewsID(String newsID) {
	this.newsID = newsID;
}

public List<NewsCommendInfo> getNewsCommendInfoList() {
	return newsCommendInfoList;
}

public void setNewsCommendInfoList(List<NewsCommendInfo> newsCommendInfoList) {
	this.newsCommendInfoList = newsCommendInfoList;
}
   
   
   
}
