package com.foxconn.dao.communication;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

import com.foxconn.pojo.Communication.Opinion;
import com.foxconn.pojo.Communication.Topic;
import com.googlecode.ehcache.annotations.Cacheable;

@Repository("communicationDao")
public class CommunicationDao {

	@Resource(name = "sqlMapClientTemplate")
	private SqlMapClientTemplate sqlMapClientTemplate;

	@SuppressWarnings("unchecked")
	public List<Topic> getTopicList(Map<String, Integer> map) {

		return this.sqlMapClientTemplate.queryForList(
				"Communication.getTopicList", map);

	}

	@Cacheable(cacheName = "portalCache")
	public int getTopicCount() {

		return (Integer) this.sqlMapClientTemplate
				.queryForObject("Communication.getTopicCount");

	}

	@Cacheable(cacheName = "portalCache")
	public Topic getTopicDetails(String topicID) {

		return (Topic) this.sqlMapClientTemplate.queryForObject(
				"Communication.getTopicDetails", topicID);

	}

	public void insertOpinion(Opinion opinion) {

		this.sqlMapClientTemplate
				.insert("Communication.insertOpinion", opinion);
	}

	@SuppressWarnings("unchecked")
	public List<Opinion> getOpinionList(Map<String, String> map) {

		return this.sqlMapClientTemplate.queryForList(
				"Communication.getOpinionList", map);

	}

	public int getOpinionCount(Map<String, String> map) {

		return (Integer) this.sqlMapClientTemplate.queryForObject(
				"Communication.getOpinionCount", map);

	}
}