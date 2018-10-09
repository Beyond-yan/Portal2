package com.foxconn.pojo.interview;

/**
 * 在线访谈现场图片列表
 * @author Administrator
 *
 */
public class InterviewScenePhotos {
	
	private String id;
	private String scenePhotosId;
	private String scenePhotosName;
	private String scenePhotosUrl;
	private String scenePhotosPath;
	private String createDate;
	private String isDel;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getScenePhotosId() {
		return scenePhotosId;
	}
	public void setScenePhotosId(String scenePhotosId) {
		this.scenePhotosId = scenePhotosId;
	}
	public String getScenePhotosName() {
		if (scenePhotosName != null) {
			int last = scenePhotosName.lastIndexOf(".");
			scenePhotosName = scenePhotosName.substring(0, last);
		}
		return scenePhotosName;
	}
	public void setScenePhotosName(String scenePhotosName) {
		this.scenePhotosName = scenePhotosName;
	}
	public String getScenePhotosUrl() {
		return scenePhotosUrl;
	}
	public void setScenePhotosUrl(String scenePhotosUrl) {
		this.scenePhotosUrl = scenePhotosUrl;
	}
	public String getScenePhotosPath() {
		return scenePhotosPath;
	}
	public void setScenePhotosPath(String scenePhotosPath) {
		this.scenePhotosPath = scenePhotosPath;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getIsDel() {
		return isDel;
	}
	public void setIsDel(String isDel) {
		this.isDel = isDel;
	}
}
