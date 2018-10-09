package com.foxconn.dao.interview;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

import com.foxconn.pojo.interview.Interview;
import com.foxconn.pojo.interview.InterviewScenePhotos;
import com.googlecode.ehcache.annotations.Cacheable;

import java.util.Map;

@Repository("interviewDao")
public class InterviewDao {

	@Resource(name = "sqlMapClientTemplate")
	private SqlMapClientTemplate sqlMapClientTemplate;

	@SuppressWarnings("unchecked")
	public List<Interview> getInterviewList(Map<String, Integer> map) {
		List<Interview> interviewList = this.sqlMapClientTemplate.queryForList(
				"Interview.getInterviewList", map);
		return interviewList;
	}

	@SuppressWarnings("unchecked")
	@Cacheable(cacheName = "portalCache")
	public Interview getGuestInfo(Interview interview) {
		List<Interview> guestList = this.sqlMapClientTemplate.queryForList(
				"Interview.getGuestInfo", interview);
		if(guestList!=null&&guestList.size()>0){
			return guestList.get(0);
		}else{
			return new Interview();
		}
		
		
	}

	@Cacheable(cacheName = "portalCache")
	public int getInterviewCount() {
		return (Integer) this.sqlMapClientTemplate
				.queryForObject("Interview.getInterviewCount");
	}
	
	@SuppressWarnings("unchecked")
	public List<InterviewScenePhotos> getInterviewScenePhotos(InterviewScenePhotos interviewScenePhotos) {
		return this.sqlMapClientTemplate.
				queryForList( "Interview.getInterviewScenePhotos", interviewScenePhotos);
	}
}