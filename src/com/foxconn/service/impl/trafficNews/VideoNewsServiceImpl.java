package com.foxconn.service.impl.trafficNews;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.foxconn.dao.trafficNews.VideoNewsDao;
import com.foxconn.pojo.trafficNews.VideoNews;
import com.foxconn.service.trafficNews.VideoNewsService;

@Service("videoNewsServiceImpl")
public class VideoNewsServiceImpl implements VideoNewsService {

	@Autowired
	@Resource(name = "videoNewsDao")
	private VideoNewsDao videoNewsDao;

	/**
	 * 获取视频新闻列表
	 */
	@Override
	public List<VideoNews> getVideoNewsList() {

		return videoNewsDao.getVideoNewsList();
	}

	/**
	 * 获取视频列表 若size为3则获取最热门的3条记录 若size为8则获取时间最新的8条记录
	 */
	@Override
	public List<VideoNews> getVideoList(int size) {

		List<VideoNews> videoNewsList = videoNewsDao.getVideoList(size);
		String dateStr = "";
		if (videoNewsList.size() != 0) {
			for (VideoNews news : videoNewsList) {
				dateStr = news.getModifyDate();
				news.setModifyDate(dateStr.substring(0, 10));
			}
		}
		return videoNewsList;
	}

	/**
	 * 播放视频
	 */
	@Override
	public VideoNews getVideoNameByID(String newsID) {

		return videoNewsDao.getVideoNameByID(newsID);
	}

	/**
	 * 更新视频阅读次数
	 */
	@Override
	public void updateVideoReadcount(String newsID, int count) {

		videoNewsDao.updateVideoReadcount(newsID, count);

	}

	@Override
	public VideoNews getVideoByID(String videoNewsId) {
		return videoNewsDao.getVideoByID(videoNewsId);
	}

	@Override
	public List<VideoNews> getLatestVideoNews() {
		return this.videoNewsDao.getLatestVideoNews();
	}

	@Override
	public List<VideoNews> getRecommendVideoNews() {
		return this.videoNewsDao.getRecommendVideoNews();
	}

	@Override
	public List<VideoNews> getCollectionVideoNews() {
		return this.videoNewsDao.getCollectionVideoNews();
	}
	@Override
	public List<VideoNews> getAllVideoNews(int curpage, int pageSize) {
		return this.videoNewsDao.getAllVideoNews(curpage, pageSize);
	}
	@Override
	public int getVideoNewsCount() {
		return this.videoNewsDao.getVideoNewsCount();
	}

}
