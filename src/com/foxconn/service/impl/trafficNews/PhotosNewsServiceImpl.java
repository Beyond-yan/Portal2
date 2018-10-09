package com.foxconn.service.impl.trafficNews;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foxconn.dao.trafficNews.PhotosNewsDao;
import com.foxconn.pojo.trafficNews.PhotosNews;
import com.foxconn.pojo.trafficNews.PhotosInfo;
import com.foxconn.service.trafficNews.PhotosNewsService;

@Service("photosNewsServiceImpl")
public class PhotosNewsServiceImpl implements PhotosNewsService {
	@Autowired
	@Resource(name = "photosNewsDao")
	private PhotosNewsDao photosNewsDao;

	@Override
	public List<PhotosNews> getPhotosNewsList() {

		return photosNewsDao.getPhotosNewsList();
	}

	@Override
	public List<PhotosNews> getPhotosNewsListByPage(int iNum) {
		return photosNewsDao.getPhotosNewsListByPage(iNum);
	}

	@Override
	public List<PhotosInfo> getPhotosInfoList(PhotosInfo photosInfo) {

		return photosNewsDao.getPhotosInfoList(photosInfo);
	}

	@Override
	public PhotosInfo getPhotosInfoDetail(PhotosInfo photosInfo) {
		return photosNewsDao.getPhotosInfoDetail(photosInfo);
	}

	@Override
	public int getPhotosInfoListCount(PhotosInfo photosInfo) {
		return photosNewsDao.getPhotosInfoListCount(photosInfo);
	}

	@Override
	public PhotosNews getPhotoNewsByID(PhotosNews photosNews) {
		return photosNewsDao.getPhotoNewsByID(photosNews);
	}

	@Override
	public List<PhotosNews> getLatestPhotoNews() {
		return photosNewsDao.getLatestPhotoNews();
	}

	@Override
	public List<PhotosNews> getRecommendPhotoNews() {
		return photosNewsDao.getRecommendPhotoNews();
	}

	@Override
	public List<PhotosNews> getCollectionPhotoNews() {
		return photosNewsDao.getCollectionPhotoNews();
	}

	@Override
	public void updateReadCount(PhotosNews photosNews) {
		photosNewsDao.updateReadCount(photosNews);
	}
	
	
	@Override
	public int queryPhotosNewsCount(Map<String,Object> map) {
		return photosNewsDao.queryPhotosNewsCount(map);
	}
	@Override
	public List<PhotosNews> queryPhotoNewsList(Map<String,Object> map) {
		return photosNewsDao.queryPhotoNewsList(map);
	}
	

}
