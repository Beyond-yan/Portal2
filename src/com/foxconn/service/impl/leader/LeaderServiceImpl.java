package com.foxconn.service.impl.leader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.foxconn.dao.leader.LeaderDao;
import com.foxconn.pojo.leader.Leader;
import com.foxconn.service.leader.LeaderService;

@Service("leaderServiceImpl")
public class LeaderServiceImpl implements LeaderService {

	@Resource(name = "leaderDao")
	private LeaderDao leaderDao;

	@Override
	public List<Leader> getLeaderInfo(Leader leader) throws Exception {

		return leaderDao.getLeaderInfo(leader);
	}

	@Override
	public List<Leader> getLeaderActivate(String leaderId,int curpage,int pagesize) throws Exception {
		
		int rStart = (curpage - 1) * pagesize + 1;
		int rEnd = curpage * pagesize;
		Map<String, String> map = new HashMap<String, String>();
		map.put("rStart", Integer.toString(rStart));
		map.put("rEnd", Integer.toString(rEnd));
		map.put("leaderId", leaderId);
		return leaderDao.getLeaderActivate(map);
	}

	public int getActivateCount(String leaderId){
		return leaderDao.getActivateCount(leaderId);
	}
	
	public Leader getLeaderResume(Leader leader) throws Exception {

		return leaderDao.getLeaderResume(leader);
	}
}