package com.foxconn.pojo.index;
/**
 * @author F3228761
 * @date : Jun 21, 2013 4:50:52 PM
 */
public class PhotoInfo {
	private String photosNewsID;
	private String photoURL;
	private String photoOrder;
	private String photoDesc;
	private String photoID;
	private String photoTitle;	
	private int isTextNews;
	private String programType;
	
	
	public String getProgramType() {
		return programType;
	}
	public void setProgramType(String programType) {
		this.programType = programType;
	}
	public int getIsTextNews() {
		return isTextNews;
	}
	public void setIsTextNews(int isTextNews) {
		this.isTextNews = isTextNews;
	}
	public String getPhotoTitle() {
		return photoTitle;
	}
	public void setPhotoTitle(String photoTitle) {
		this.photoTitle = photoTitle;
	}
	public String getPhotosNewsID() {
		return photosNewsID;
	}
	public void setPhotosNewsID(String photoNewsID) {
		this.photosNewsID = photoNewsID;
	}
	public String getPhotoURL() {
		return photoURL;
	}
	public void setPhotoURL(String photoURL) {
		this.photoURL = photoURL;
	}
	public String getPhotoOrder() {
		return photoOrder;
	}
	public void setPhotoOrder(String photoOrder) {
		this.photoOrder = photoOrder;
	}
	public String getPhotoDesc() {
		return photoDesc;
	}
	public void setPhotoDesc(String photoDesc) {
		this.photoDesc = photoDesc;
	}
	public String getPhotoID() {
		return photoID;
	}
	public void setPhotoID(String photoID) {
		this.photoID = photoID;
	}
	
	

}
