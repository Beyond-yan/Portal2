package com.foxconn.dao.trafficNews;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

import com.foxconn.pojo.trafficNews.PhotosNews;
import com.foxconn.pojo.trafficNews.PhotosInfo;
import com.googlecode.ehcache.annotations.Cacheable;

@Repository("photosNewsDao")
public class PhotosNewsDao {
	@Resource(name = "sqlMapClientTemplate")
	private SqlMapClientTemplate sqlMapClientTemplate;

	/**
	 * 獲取圖片集新聞列表
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<PhotosNews> getPhotosNewsList() {
		return this.sqlMapClientTemplate
				.queryForList("PhotosNews.getPhotosNewsList");
	}

	@SuppressWarnings("unchecked")
	@Cacheable(cacheName = "portalCache")
	public List<PhotosNews> getPhotosNewsListByPage(int iNum) {
		return this.sqlMapClientTemplate.queryForList(
				"PhotosNews.getPhotosNewsListByPage", iNum);
	}

	/**
	 * 獲取圖片新聞內容列表
	 * 
	 * @param photosInfo
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<PhotosInfo> getPhotosInfoList(PhotosInfo photosInfo) {
		List<PhotosInfo> photosInfoList = this.sqlMapClientTemplate
				.queryForList("PhotosNews.getPhotosInfoList", photosInfo);
		return photosInfoList;
	}

	@Cacheable(cacheName = "portalCache")
	public PhotosInfo getPhotosInfoDetail(PhotosInfo photosInfo) {
		@SuppressWarnings("unchecked")
		List<PhotosInfo> photosInfoList = this.sqlMapClientTemplate
				.queryForList("PhotosNews.getPhotosInfoDetail", photosInfo);
		if (photosInfoList != null && photosInfoList.size() > 0) {
			return photosInfoList.get(0);
		} else {
			return new PhotosInfo();
		}
	}

	@Cacheable(cacheName = "portalCache")
	public int getPhotosInfoListCount(PhotosInfo photosInfo) {
		return (Integer) this.sqlMapClientTemplate.queryForObject(
				"PhotosNews.getPhotosInfoListCount", photosInfo);
	}

	@Cacheable(cacheName = "portalCache")
	public PhotosNews getPhotoNewsByID(PhotosNews photosNews) {
		return (PhotosNews) this.sqlMapClientTemplate.queryForObject(
				"PhotosNews.getPhotoNewsByID", photosNews);
	}

	@SuppressWarnings("unchecked")
	@Cacheable(cacheName = "portalCache")
	public List<PhotosNews> getLatestPhotoNews() {
		return this.sqlMapClientTemplate
				.queryForList("PhotosNews.getLatestPhotoNews");
	}

	@SuppressWarnings("unchecked")
	public List<PhotosNews> getRecommendPhotoNews() {
		return this.sqlMapClientTemplate
				.queryForList("PhotosNews.getRecommendPhotoNews");
	}

	@SuppressWarnings("unchecked")
	public List<PhotosNews> getCollectionPhotoNews() {
		return this.sqlMapClientTemplate
				.queryForList("PhotosNews.getCollectionPhotoNews");
	}

	public void updateReadCount(PhotosNews photosNews) {
		this.sqlMapClientTemplate.update("PhotosNews.updateReadCount",
				photosNews);
	}
	
	public int queryPhotosNewsCount(Map<String,Object> map) {
		return  (Integer) sqlMapClientTemplate.queryForObject("PhotosNews.queryPhotosNewsCount",map);
	}
	@SuppressWarnings("unchecked")
	public List<PhotosNews> queryPhotoNewsList(Map<String,Object> map) {
		return sqlMapClientTemplate.queryForList("PhotosNews.queryPhotoNewsList",map);
	}
	
}
