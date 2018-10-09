package com.foxconn.service.leader;

import java.util.List;

import com.foxconn.pojo.leader.Leader;

public interface LeaderService {
	public List<Leader> getLeaderInfo(Leader leader) throws Exception;

	//modified by Cube @131219
	public List<Leader> getLeaderActivate(String leaderId, int curpage,
			int pagesize) throws Exception;

	public Leader getLeaderResume(Leader leader) throws Exception;
	
	public int getActivateCount(String leaderId);
}
