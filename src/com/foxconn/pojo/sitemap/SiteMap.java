package com.foxconn.pojo.sitemap;

import java.util.ArrayList;
import java.util.List;

public class SiteMap {
	private String id;
	private String name;
	private String url;
	private String pid;
	private String isClick;
	private String isDelete;
	private String mapStyle;
	private String sotr;
	
	List<SiteMap> data = new ArrayList<SiteMap>();
	
	public String getIsClick() {
		return isClick;
	}
	public void setIsClick(String isClick) {
		this.isClick = isClick;
	}
	public List<SiteMap> getData() {
		return data;
	}
	public void setData(List<SiteMap> data) {
		this.data = data;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	
	public String getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}
	public String getMapStyle() {
		return mapStyle;
	}
	public void setMapStyle(String mapStyle) {
		this.mapStyle = mapStyle;
	}
	public String getSotr() {
		return sotr;
	}
	public void setSotr(String sotr) {
		this.sotr = sotr;
	}
}
