package com.foxconn.pojo.opencatalog;

import java.util.List;

/**
 * @author F3219058
 * @version 1.0
 * @Title 菜单/新闻目录
 * @Date 2013/7/5
 * */
public class Catalog {
	private String id;
	private String pId;
	private String name;
	private String url;
	private String target;
	private String open;
	private String newsTitle;
	private String entryDate;
	private String newsID;
	private String entryuser;
	private String modifyDate;
	private String newsStatusName;
	private String newsContent;
	private String programType;
	private String newsSummary;
	private List<Catalog> catalog;

	public String getNewsSummary() {
		return newsSummary;
	}

	public void setNewsSummary(String newsSummary) {
		this.newsSummary = newsSummary;
	}

	public String getOpen() {
		return open;
	}
	
	public void setOpen(String open) {
		this.open = open;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getpId() {
		return pId;
	}

	public void setpId(String pId) {
		this.pId = pId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public List<Catalog> getCatalog() {
		return catalog;
	}

	public void setCatalog(List<Catalog> catalog) {
		this.catalog = catalog;
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

	public String getNewsID() {
		return newsID;
	}

	public void setNewsID(String newsID) {
		this.newsID = newsID;
	}

	public String getEntryuser() {
		return entryuser;
	}

	public void setEntryuser(String entryuser) {
		this.entryuser = entryuser;
	}


	public String getNewsStatusName() {
		return newsStatusName;
	}

	public void setNewsStatusName(String newsStatusName) {
		this.newsStatusName = newsStatusName;
	}

	public String getProgramType() {
		return programType;
	}

	public void setProgramType(String programType) {
		this.programType = programType;
	}

	public String getNewsContent() {
		return newsContent;
	}

	public void setNewsContent(String newsContent) {
		this.newsContent = newsContent;
	}

	public String getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}


}
