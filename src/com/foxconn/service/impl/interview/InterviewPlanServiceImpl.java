package com.foxconn.service.impl.interview;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.foxconn.dao.interview.InterviewPlanDao;
import com.foxconn.pojo.interview.InterviewPlan;
import com.foxconn.service.interview.InterviewPlanService;

@Service("interviewPlanServiceImpl")
public class InterviewPlanServiceImpl implements InterviewPlanService {
	@Resource(name = "interviewPlanDao")
	private InterviewPlanDao interviewPlanDao;

	@Override
	public List<InterviewPlan> getInterviewPlanList()throws Exception {
		return interviewPlanDao.getInterviewPlanList();
	}
}