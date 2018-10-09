package com.foxconn.pojo.trafficNews;

import java.util.List;

public class TextNews {
	private String newsID;
	private String newsTitle;
	private String subNewsTitle;
	private String textNewsTitle;
	private String newsContent;
	private int newsSource;
	private String newsSourceName;
	private String entryUser;
	private String entryDate;
	private String modifyUser;
	private String modifyDate;
	private String newsSummary;
	private int sectionPosition;
	private int effective;
	private int emotional;
	private int happy;
	private int nonsense;
	private int boring;
	private int fear;
	private int sad;
	private int angry;
	private int effectiveCount;
	private int emotionalCount;
	private int happyCount;
	private int nonsenseCount;
	private int boringCount;
	private int fearCount;
	private int sadCount;
	private int angryCount;
	private int readRecordCount;
	private String newsKeyWord;
	private String programType;
	private int newsStatusID;
	// 新增加一个禁止复制
	private String isBan;
	// 排序栏位
	private String STICK_STATE;

	
	public String getSTICK_STATE() {
		return STICK_STATE;
	}

	public void setSTICK_STATE(String sTICK_STATE) {
		STICK_STATE = sTICK_STATE;
	}

	private List<TextNews> textNewsList;

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

	public String getSubNewsTitle() {
		return subNewsTitle;
	}

	public void setSubNewsTitle(String subNewsTitle) {
		this.subNewsTitle = subNewsTitle;
	}

	public String getTextNewsTitle() {
		return textNewsTitle;
	}

	public void setTextNewsTitle(String textNewsTitle) {
		this.textNewsTitle = textNewsTitle;
	}

	public String getNewsContent() {
		return newsContent;
	}

	public void setNewsContent(String newsContent) {
		this.newsContent = newsContent;
	}

	public int getNewsSource() {
		return newsSource;
	}

	public void setNewsSource(int newsSource) {
		this.newsSource = newsSource;
	}

	public String getNewsSourceName() {
		return newsSourceName;
	}

	public void setNewsSourceName(String newsSourceName) {
		this.newsSourceName = newsSourceName;
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

	public int getEffective() {
		return effective;
	}

	public void setEffective(int effective) {
		this.effective = effective;
	}

	public int getEmotional() {
		return emotional;
	}

	public void setEmotional(int emotional) {
		this.emotional = emotional;
	}

	public int getHappy() {
		return happy;
	}

	public void setHappy(int happy) {
		this.happy = happy;
	}

	public int getNonsense() {
		return nonsense;
	}

	public void setNonsense(int nonsense) {
		this.nonsense = nonsense;
	}

	public int getBoring() {
		return boring;
	}

	public void setBoring(int boring) {
		this.boring = boring;
	}

	public int getFear() {
		return fear;
	}

	public void setFear(int fear) {
		this.fear = fear;
	}

	public int getSad() {
		return sad;
	}

	public void setSad(int sad) {
		this.sad = sad;
	}

	public int getAngry() {
		return angry;
	}

	public void setAngry(int angry) {
		this.angry = angry;
	}

	public int getEffectiveCount() {
		return effectiveCount;
	}

	public void setEffectiveCount(int effectiveCount) {
		this.effectiveCount = effectiveCount;
	}

	public int getEmotionalCount() {
		return emotionalCount;
	}

	public void setEmotionalCount(int emotionalCount) {
		this.emotionalCount = emotionalCount;
	}

	public int getHappyCount() {
		return happyCount;
	}

	public void setHappyCount(int happyCount) {
		this.happyCount = happyCount;
	}

	public int getNonsenseCount() {
		return nonsenseCount;
	}

	public void setNonsenseCount(int nonsenseCount) {
		this.nonsenseCount = nonsenseCount;
	}

	public int getBoringCount() {
		return boringCount;
	}

	public void setBoringCount(int boringCount) {
		this.boringCount = boringCount;
	}

	public int getFearCount() {
		return fearCount;
	}

	public void setFearCount(int fearCount) {
		this.fearCount = fearCount;
	}

	public int getSadCount() {
		return sadCount;
	}

	public void setSadCount(int sadCount) {
		this.sadCount = sadCount;
	}

	public int getAngryCount() {
		return angryCount;
	}

	public void setAngryCount(int angryCount) {
		this.angryCount = angryCount;
	}

	public int getReadRecordCount() {
		return readRecordCount;
	}

	public void setReadRecordCount(int readRecordCount) {
		this.readRecordCount = readRecordCount;
	}

	public List<TextNews> getTextNewsList() {
		return textNewsList;
	}

	public void setTextNewsList(List<TextNews> textNewsList) {
		this.textNewsList = textNewsList;
	}

	public String getNewsSummary() {
		return newsSummary;
	}

	public void setNewsSummary(String newsSummary) {
		this.newsSummary = newsSummary;
	}

	public int getSectionPosition() {
		return sectionPosition;
	}

	public void setSectionPosition(int sectionPosition) {
		this.sectionPosition = sectionPosition;
	}

	public String getNewsKeyWord() {
		return newsKeyWord;
	}

	public void setNewsKeyWord(String newsKeyWord) {
		this.newsKeyWord = newsKeyWord;
	}

	public String getProgramType() {
		return programType;
	}

	public void setProgramType(String programType) {
		this.programType = programType;
	}

	public int getNewsStatusID() {
		return newsStatusID;
	}

	public void setNewsStatusID(int newsStatusID) {
		this.newsStatusID = newsStatusID;
	}

	public String getIsBan() {
		return isBan;
	}

	public void setIsBan(String isBan) {
		this.isBan = isBan;
	}

}
