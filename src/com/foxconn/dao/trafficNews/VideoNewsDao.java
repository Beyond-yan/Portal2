package com.foxconn.dao.trafficNews;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;
import com.foxconn.pojo.trafficNews.VideoNews;

@Repository("videoNewsDao")
public class VideoNewsDao {

	@Resource(name = "sqlMapClientTemplate")
	private SqlMapClientTemplate sqlMapClientTemplate;

	@SuppressWarnings("unchecked")
	public List<VideoNews> getVideoNewsList() {

		return this.sqlMapClientTemplate
				.queryForList("VideoNews.getVideoNewsList");
	}

	@SuppressWarnings("unchecked")
	public List<VideoNews> getVideoList(int size) {

		List<VideoNews> videoList = new ArrayList<VideoNews>();
		if (size == 3) {
			videoList = this.sqlMapClientTemplate.queryForList(
					"VideoNews.getHotVideoNewsList", 0, size);
		} else {
			videoList = this.sqlMapClientTemplate.queryForList(
					"VideoNews.getFreshVideoNewsList", 0, size);
		}
		return videoList;
	}

	public VideoNews getVideoNameByID(String newsID) {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("newsID", newsID);
		return (VideoNews) this.sqlMapClientTemplate.queryForObject(
				"VideoNews.getVideoNameByID", paramMap);
	}

	public VideoNews getVideoByID(String videoNewsId) {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("videoNewsId", videoNewsId);
		return (VideoNews) this.sqlMapClientTemplate.queryForObject(
				"VideoNews.getVideoByID", paramMap);
	}

	public void updateVideoReadcount(String newsID, int count) {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("newsID", newsID);
		paramMap.put("readCount", count);
		this.sqlMapClientTemplate.update("VideoNews.updateVideoReadcount",
				paramMap);
	}

	// added by Cube @130828
	@SuppressWarnings("unchecked")
	public List<VideoNews> getLatestVideoNews() {
		return this.sqlMapClientTemplate
				.queryForList("VideoNews.getLatestVideoNews");
	}

	@SuppressWarnings("unchecked")
	public List<VideoNews> getRecommendVideoNews() {
		return this.sqlMapClientTemplate
				.queryForList("VideoNews.getRecommendVideoNews");
	}

	@SuppressWarnings("unchecked")
	public List<VideoNews> getCollectionVideoNews() {
		return this.sqlMapClientTemplate
				.queryForList("VideoNews.getCollectionVideoNews");
	}
	@SuppressWarnings("unchecked")
	public List<VideoNews> getAllVideoNews(int curpage, int pageSize) {
		
		List<VideoNews> list =  this.sqlMapClientTemplate.queryForList("VideoNews.getAllVideoNews",(curpage-1)*pageSize,pageSize);
		if(list!=null&&list.size()>0){
			((VideoNews)list.get(0)).setTotalCount(Integer.parseInt(sqlMapClientTemplate.queryForObject("VideoNews.getVideoNewsTotalCount").toString()));
		}
		return list;
		/*return this.sqlMapClientTemplate
				.queryForList("VideoNews.getAllVideoNews");
		*/
		
	}
/*	@SuppressWarnings("unchecked")
	public List<VideoNews> getAllVideoNewsByPage(int skipToPage,int pageSize) {
		List<VideoNews> list =  this.sqlMapClientTemplate.queryForList("VideoNews.getAllVideoNews");
		if(list!=null&&list.size()>0){
			((VideoNews)list.get(0)).setTotalCount(Integer.parseInt(sqlMapClientTemplate.queryForObject("VideoNews.getVideoNewsTotalCount").toString()));
		}
		return list;
	}*/

	public int getVideoNewsCount() {
		return Integer.parseInt(sqlMapClientTemplate.queryForObject("VideoNews.getVideoNewsTotalCount").toString());
	}
	
	
	/*
	public List<Adm> getAllAdmByPage(Adm adm, int skipToPage,int pageSize) {

		List<Adm> list = this.getSqlMapClientTemplate().queryForList("CmsAdm.getAllAdmByPage", adm,(skipToPage-1)*pageSize,pageSize);

		if(list!=null&&list.size()>0)

		((Adm)list.get(0)).setTotalResults(Integer.parseInt(getSqlMapClientTemplate().queryForObject("CmsAdm.getAllAdmByPageForCount", adm).toString()));

		return list;

		}*/


}
