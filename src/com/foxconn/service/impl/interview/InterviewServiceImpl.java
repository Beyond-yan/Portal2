package com.foxconn.service.impl.interview;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.foxconn.dao.interview.InterviewDao;
import com.foxconn.pojo.interview.Interview;
import com.foxconn.pojo.interview.InterviewScenePhotos;
import com.foxconn.service.interview.InterviewService;
import com.googlecode.ehcache.annotations.Cacheable;

@Service("interviewServiceImpl")
public class InterviewServiceImpl implements InterviewService {
	@Resource(name = "interviewDao")
	private InterviewDao interviewDao;

	@Override
	@Cacheable(cacheName = "portalCache")
	public List<Interview> getInterviewList(int curpage, int pagesize)
			throws Exception {
		//System.out.println("getInterviewList");
		int rStart = (curpage - 1) * pagesize + 1;
		int rEnd = curpage * pagesize;
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("rStart", rStart);
		map.put("rEnd", rEnd);
		return interviewDao.getInterviewList(map);
	}

	@Override
	public Interview getGuestInfo(Interview interview) throws Exception {
		return interviewDao.getGuestInfo(interview);
	}

	@Override
	public int getInterviewCount() {
		return interviewDao.getInterviewCount();
	}

	@Override
	public List<InterviewScenePhotos> getInterviewScenePhotos(
			InterviewScenePhotos interviewScenePhotos) {
		// TODO Auto-generated method stub
		return interviewDao.getInterviewScenePhotos(interviewScenePhotos);
	}
}