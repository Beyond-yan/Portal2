package com.foxconn.dao.index;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

import com.foxconn.pojo.Communication.Topic;
import com.foxconn.pojo.index.PhotoInfo;
import com.foxconn.pojo.questionaire.SURVEY_QUESTIONAIRE;
import com.foxconn.pojo.trafficNews.MagazineNews;
import com.foxconn.pojo.trafficNews.TextNews;
import com.foxconn.pojo.trafficNews.VideoNews;
import com.googlecode.ehcache.annotations.Cacheable;

/**
 * @author F3228761
 * @date : Jun 21, 2013 3:25:03 PM
 */
@Repository("indexDao")
public class IndexDao {

	@Autowired
	@Resource(name = "sqlMapClientTemplate")
	private SqlMapClientTemplate sqlMapClientTemplate;

	@SuppressWarnings("unchecked")
	@Cacheable(cacheName = "portalCache")
	public List<PhotoInfo> getPhotos() {
		return this.sqlMapClientTemplate
				.queryForList("IndexSQL.getIndexPhotosNews");
	}

	@SuppressWarnings("unchecked")
	@Cacheable(cacheName = "portalCache")
	public List<TextNews> getUpToDateAnnouncement(String programType) {
		
		return this.sqlMapClientTemplate.queryForList(
				"IndexSQL.getUpToDateAnnouncement", programType);
	}

	@SuppressWarnings("unchecked")
	public List<TextNews> getIndexTextNews() {
		return this.sqlMapClientTemplate
				.queryForList("IndexSQL.getIndexTextNews");
	}
	
	@SuppressWarnings("unchecked")
	public List<TextNews> getIndexNews() {
		return this.sqlMapClientTemplate
				.queryForList("IndexSQL.getIndexNews");
	}

	@Cacheable(cacheName = "portalCache")
	public MagazineNews getIndexMagazineNews() {
		
		return (MagazineNews) this.sqlMapClientTemplate
				.queryForObject("IndexSQL.getIndexMagazineNews");
	}

	@Cacheable(cacheName = "portalCache")
	public VideoNews getIndexVideoNews() {
		
		return (VideoNews) this.sqlMapClientTemplate
				.queryForObject("IndexSQL.getIndexVideo");
	}

	@Cacheable(cacheName = "portalCache")
	public Topic getIndexTopic() {
		
		return (Topic) this.sqlMapClientTemplate
				.queryForObject("IndexSQL.getIndexTopic");
	}

	@Cacheable(cacheName = "portalCache")
	public SURVEY_QUESTIONAIRE getIndexQuestionaire() {
		
		return (SURVEY_QUESTIONAIRE) this.sqlMapClientTemplate
				.queryForObject("IndexSQL.getIndexQuestionaire");
	}
}