package com.foxconn.dao.leader;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.foxconn.pojo.leader.Leader;
import com.googlecode.ehcache.annotations.Cacheable;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

@Repository("leaderDao")
public class LeaderDao {

	@Resource(name = "sqlMapClientTemplate")
	private SqlMapClientTemplate sqlMapClientTemplate;

	/**
	 * 获取重庆交通新闻列表 F3228777 2013-06-21
	 */
	@SuppressWarnings("unchecked")
	@Cacheable(cacheName = "portalCache")
	public List<Leader> getLeaderInfo(Leader leader) throws Exception {
		List<Leader> LeaderInfoList = this.sqlMapClientTemplate.queryForList(
				"Leader.getLeaderInfo", leader);
		return LeaderInfoList;
	}

	@SuppressWarnings("unchecked")
//	@Cacheable(cacheName = "portalCache")
	public List<Leader> getLeaderActivate(Map<String, String> map) throws Exception {
		List<Leader> LeaderList = this.sqlMapClientTemplate.queryForList(
				"Leader.getLeaderActivate", map);
		return LeaderList;
	}

	@SuppressWarnings("unchecked")
	@Cacheable(cacheName = "portalCache")
	public Leader getLeaderResume(Leader leader) throws Exception {
		List<Leader> LeaderResume = this.sqlMapClientTemplate.queryForList(
				"Leader.getLeaderResume", leader);
		if(LeaderResume!=null&&LeaderResume.size()>0){
			return LeaderResume.get(0);
		}else{
			return new Leader();
		}
		
	}
	
	public int getActivateCount(String leaderId){
		return (Integer) this.sqlMapClientTemplate
				.queryForObject("Leader.getActivateCount",leaderId);
	}

}
