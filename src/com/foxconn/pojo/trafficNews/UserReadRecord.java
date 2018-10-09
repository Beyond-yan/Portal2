package com.foxconn.pojo.trafficNews;

import java.util.List;

public class UserReadRecord {
 private String newsID;  //新聞ID
 private String userIP;  //用戶IP
   
 private List<UserReadRecord> userReadRecordList;
  
public String getNewsID() {
	return newsID;
}
public void setNewsID(String newsID) {
	this.newsID = newsID;
}
public String getUserIP() {
	return userIP;
}
public void setUserIP(String userIP) {
	this.userIP = userIP;
}
public List<UserReadRecord> getUserReadRecordList() {
	return userReadRecordList;
}
public void setUserReadRecordList(List<UserReadRecord> userReadRecordList) {
	this.userReadRecordList = userReadRecordList;
}

}
