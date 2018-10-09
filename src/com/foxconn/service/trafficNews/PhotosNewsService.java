package com.foxconn.service.trafficNews;

import java.util.List;
import java.util.Map;

import com.foxconn.pojo.trafficNews.PhotosInfo;
import com.foxconn.pojo.trafficNews.PhotosNews;

public interface PhotosNewsService {

	List<PhotosNews> getPhotosNewsList();

	List<PhotosNews> getPhotosNewsListByPage(int iNum);

	List<PhotosInfo> getPhotosInfoList(PhotosInfo photosInfo);

	PhotosInfo getPhotosInfoDetail(PhotosInfo photosInfo);

	int getPhotosInfoListCount(PhotosInfo photosInfo);

	PhotosNews getPhotoNewsByID(PhotosNews photosNews);

	List<PhotosNews> getLatestPhotoNews();

	List<PhotosNews> getRecommendPhotoNews();

	List<PhotosNews> getCollectionPhotoNews();

	void updateReadCount(PhotosNews photosNews);
	
	/**
	 * 获取总条数
	 * @return
	 */
	int queryPhotosNewsCount(Map<String,Object> map);
	
	List<PhotosNews> queryPhotoNewsList(Map<String,Object> map);
}
