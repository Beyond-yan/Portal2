package com.foxconn.service.impl.trafficconference;


import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.foxconn.dao.trafficNews.TrafficConferenceDao;
import com.foxconn.pojo.index.PhotoInfo;
import com.foxconn.pojo.opencatalog.Catalog;
import com.foxconn.pojo.trafficconference.ProjectPhotos;
import com.foxconn.service.trafficconference.TrafficConferenceService;

@Service("trafficConferenceImpl")
public class TrafficConferenceImpl implements TrafficConferenceService {

	@Resource(name = "trafficConferenceDao")
	private TrafficConferenceDao trafficConferenceDao;

	@Override
	public List<Catalog> getIndexNewsList(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		return trafficConferenceDao.getIndexNewsList(map);
	}

	@Override
	public List<PhotoInfo> getPhotos() throws Exception {
		// TODO Auto-generated method stub
		return trafficConferenceDao.getPhotos();
	}

	@Override
	public List<ProjectPhotos> getPictureList() throws Exception {
		// TODO Auto-generated method stub
		return trafficConferenceDao.getPictureList();
	}
	


}
