package com.foxconn.service.impl.trafficconference;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.foxconn.dao.trafficconference.Hyzt2018Dao;
import com.foxconn.pojo.index.PhotoInfo;
import com.foxconn.pojo.opencatalog.Catalog;
import com.foxconn.pojo.trafficNews.PhotosInfo;
import com.foxconn.service.trafficconference.Hyzt2018Service;

@Service("hyzt2018ServiceImpl")
public class Hyzt2018ServiceImpl implements Hyzt2018Service {

	@Resource(name = "hyzt2018Dao")
	private Hyzt2018Dao hyzt2018Dao;

	@Override
	public List<Catalog> getIndexNewsList(Map<String, Object> map) throws Exception {
		return hyzt2018Dao.getIndexNewsList(map);
	}

	@Override
	public List<PhotoInfo> getPhotos() throws Exception {
		return hyzt2018Dao.getPhotos();
	}

	@Override
	public List<PhotosInfo> getPictureList() throws Exception {
		return hyzt2018Dao.getPictureList();
	}

}
