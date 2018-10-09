package com.foxconn.service.interview;

import java.util.List;

import com.foxconn.pojo.interview.Interview;
import com.foxconn.pojo.interview.InterviewScenePhotos;

public interface InterviewService {
	public List<Interview> getInterviewList(int curpage,int pagesize) throws Exception;

	public Interview getGuestInfo(Interview interview) throws Exception;

	int getInterviewCount();
	
	public List<InterviewScenePhotos> getInterviewScenePhotos(InterviewScenePhotos interviewScenePhotos);
}