package com.foxconn.dao.interview;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;
import com.foxconn.pojo.interview.InterviewPlan;
import com.googlecode.ehcache.annotations.Cacheable;

@Repository("interviewPlanDao")
public class InterviewPlanDao{
	
	@Resource(name = "sqlMapClientTemplate")
	private SqlMapClientTemplate sqlMapClientTemplate;

	@SuppressWarnings("unchecked")
	public List<InterviewPlan> getInterviewPlanList() {
		return this.sqlMapClientTemplate.queryForList(
				"InterviewPlan.getInterviewPlan");
	}

}
