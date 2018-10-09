package com.foxconn.pojo.trafficNews;

import  java.util.List;

public class PhotosInfo {
	private String photosID;
	private String photosName;
	private String photosDesc;
	private String photosUrl;
	private int photosOrder;
	private String photosNewsID;
	private String isMainPhotos;
	private String photosNewsName;
	private String photosNewsDesc;
	
	private int iSNum;  //非資料庫欄位
	private int iENum;  //非資料庫欄位
	
    private List<PhotosInfo> photosInfoList;

	public String getPhotosID() {
		return photosID;
	}

	public void setPhotosID(String photosID) {
		this.photosID = photosID;
	}

	public String getPhotosName() {
		return photosName;
	}

	public void setPhotosName(String photosName) {
		this.photosName = photosName;
	}

	public String getPhotosDesc() {
		return photosDesc;
	}

	public void setPhotosDesc(String photosDesc) {
		this.photosDesc = photosDesc;
	}

	public String getPhotosUrl() {
		return photosUrl;
	}

	public void setPhotosUrl(String photosUrl) {
		this.photosUrl = photosUrl;
	}

	public int getPhotosOrder() {
		return photosOrder;
	}

	public void setPhotosOrder(int photosOrder) {
		this.photosOrder = photosOrder;
	}

	public String getPhotosNewsID() {
		return photosNewsID;
	}

	public void setPhotosNewsID(String photosNewsID) {
		this.photosNewsID = photosNewsID;
	}

	public String getIsMainPhotos() {
		return isMainPhotos;
	}

	public void setIsMainPhotos(String isMainPhotos) {
		this.isMainPhotos = isMainPhotos;
	}

	public String getPhotosNewsName() {
		return photosNewsName;
	}

	public void setPhotosNewsName(String photosNewsName) {
		this.photosNewsName = photosNewsName;
	}

	public String getPhotosNewsDesc() {
		return photosNewsDesc;
	}

	public void setPhotosNewsDesc(String photosNewsDesc) {
		this.photosNewsDesc = photosNewsDesc;
	}

	public List<PhotosInfo> getPhotosInfoList() {
		return photosInfoList;
	}

	public int getiSNum() {
		return iSNum;
	}

	public void setiSNum(int iSNum) {
		this.iSNum = iSNum;
	}

	public int getiENum() {
		return iENum;
	}

	public void setiENum(int iENum) {
		this.iENum = iENum;
	}

	public void setPhotosInfoList(List<PhotosInfo> photosInfoList) {
		this.photosInfoList = photosInfoList;
	}
    
    

}
