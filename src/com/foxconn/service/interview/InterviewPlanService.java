package com.foxconn.service.interview;

import java.util.List;
import com.foxconn.pojo.interview.InterviewPlan;

public interface InterviewPlanService {
	public List<InterviewPlan> getInterviewPlanList() throws Exception;
}