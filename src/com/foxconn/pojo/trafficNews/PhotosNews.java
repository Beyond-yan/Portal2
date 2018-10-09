package com.foxconn.pojo.trafficNews;

import java.util.List;

public class PhotosNews {
	private String photosNewsID;
	private String photosNewsTitle;
	private String subPhotosNewsTitle;
    private String photosNewsSource;
    private String photosNewsDesc;
    private String mainPhotosName;
    private String mainPhotosUrl;
    private String mainPhotosID;
    private String mainPhotosDesc;
    
    private String ENTRY_DATE;
    
    
    public String getENTRY_DATE() {
		return ENTRY_DATE;
	}

	public void setENTRY_DATE(String eNTRY_DATE) {
		ENTRY_DATE = eNTRY_DATE;
	}

	private List<PhotosNews> photosNewsList;

	public String getPhotosNewsID() {
		return photosNewsID;
	}

	public void setPhotosNewsID(String photosNewsID) {
		this.photosNewsID = photosNewsID;
	}

	public String getPhotosNewsTitle() {
		return photosNewsTitle;
	}

	public void setPhotosNewsTitle(String photosNewsTitle) {
		this.photosNewsTitle = photosNewsTitle;
	}

	public String getSubPhotosNewsTitle() {
		return subPhotosNewsTitle;
	}

	public void setSubPhotosNewsTitle(String subPhotosNewsTitle) {
		this.subPhotosNewsTitle = subPhotosNewsTitle;
	}

	public String getPhotosNewsSource() {
		return photosNewsSource;
	}

	public void setPhotosNewsSource(String photosNewsSource) {
		this.photosNewsSource = photosNewsSource;
	}

	public String getPhotosNewsDesc() {
		return photosNewsDesc;
	}

	public void setPhotosNewsDesc(String photosNewsDesc) {
		this.photosNewsDesc = photosNewsDesc;
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

	public String getMainPhotosID() {
		return mainPhotosID;
	}

	public void setMainPhotosID(String mainPhotosID) {
		this.mainPhotosID = mainPhotosID;
	}

	public String getMainPhotosDesc() {
		return mainPhotosDesc;
	}

	public void setMainPhotosDesc(String mainPhotosDesc) {
		this.mainPhotosDesc = mainPhotosDesc;
	}

	public List<PhotosNews> getPhotosNewsList() {
		return photosNewsList;
	}

	public void setPhotosNewsList(List<PhotosNews> photosNewsList) {
		this.photosNewsList = photosNewsList;
	}
    
    
}
