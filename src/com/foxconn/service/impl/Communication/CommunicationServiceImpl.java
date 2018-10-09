package com.foxconn.service.impl.Communication;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.foxconn.dao.communication.CommunicationDao;
import com.foxconn.pojo.Communication.Opinion;
import com.foxconn.pojo.Communication.Topic;
import com.foxconn.service.Communication.CommunicationService;

@Service("communicationServiceImpl")
public class CommunicationServiceImpl implements CommunicationService {

	@Autowired
	@Resource(name = "communicationDao")
	private CommunicationDao communicationDao;

	@Override
	public List<Topic> getTopicList(int curpage, int pagesize) {
		int rStart = (curpage - 1) * pagesize + 1;
		int rEnd = curpage * pagesize;
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("rStart", rStart);
		map.put("rEnd", rEnd);
		return communicationDao.getTopicList(map);
	}

	@Override
	public int getTopicCount() {
		return communicationDao.getTopicCount();
	}

	@Override
	public Topic getTopicDetails(String topicID) {
		return communicationDao.getTopicDetails(topicID);
	}

	@Override
	public void insertOpinion(Opinion opinion) {
		communicationDao.insertOpinion(opinion);
	}

	@Override
	public List<Opinion> getOpinionList(String topicID, int curpage,
			int pagesize) {
		int rStart = (curpage - 1) * pagesize + 1;
		int rEnd = curpage * pagesize;
		Map<String, String> map = new HashMap<String, String>();
		map.put("rStart", Integer.toString(rStart));
		map.put("rEnd", Integer.toString(rEnd));
		map.put("topicID", topicID);
		return communicationDao.getOpinionList(map);
	}

	@Override
	public int getOpinionCount(Map<String, String> map) {
		return communicationDao.getOpinionCount(map);
	}
}