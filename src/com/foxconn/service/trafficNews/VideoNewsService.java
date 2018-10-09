package com.foxconn.service.trafficNews;

import java.util.List;

import com.foxconn.pojo.trafficNews.VideoNews;

public interface VideoNewsService {

	List<VideoNews> getVideoNewsList();

	List<VideoNews> getVideoList(int size);

	VideoNews getVideoNameByID(String newsID);

	void updateVideoReadcount(String newsID, int count);

	VideoNews getVideoByID(String videoNewsId);

	List<VideoNews> getLatestVideoNews();

	List<VideoNews> getRecommendVideoNews();

	List<VideoNews> getCollectionVideoNews();

	List<VideoNews> getAllVideoNews(int curpage, int pageSize);

	int getVideoNewsCount();
}
