package com.foxconn.service.trafficconference;

import java.util.List;
import java.util.Map;

import com.foxconn.pojo.index.PhotoInfo;
import com.foxconn.pojo.opencatalog.Catalog;
import com.foxconn.pojo.trafficNews.PhotosInfo;

public interface Hyzt2018Service {

	public List<Catalog> getIndexNewsList(Map<String, Object> map) throws Exception;

	public List<PhotoInfo> getPhotos() throws Exception;

	public List<PhotosInfo> getPictureList() throws Exception;

}
