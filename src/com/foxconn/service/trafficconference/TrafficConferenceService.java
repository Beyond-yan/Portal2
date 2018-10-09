package com.foxconn.service.trafficconference;

import java.util.List;
import java.util.Map;

import com.foxconn.pojo.index.PhotoInfo;
import com.foxconn.pojo.opencatalog.Catalog;
import com.foxconn.pojo.trafficconference.ProjectPhotos;


public interface TrafficConferenceService {

	public List<Catalog> getIndexNewsList(Map<String,Object> map) throws Exception;
	public List<PhotoInfo> getPhotos()throws Exception;
	public List<ProjectPhotos> getPictureList() throws Exception;
	
}
