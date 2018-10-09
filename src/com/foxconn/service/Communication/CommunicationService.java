package com.foxconn.service.Communication;

import java.util.List;
import java.util.Map;

import com.foxconn.pojo.Communication.Opinion;
import com.foxconn.pojo.Communication.Topic;

public interface CommunicationService {
	List<Topic> getTopicList(int curpage, int pagesize);

	int getTopicCount();

	Topic getTopicDetails(String topicID);

	void insertOpinion(Opinion opinion);

	List<Opinion> getOpinionList(String topicID, int curpage, int pagesize);

	int getOpinionCount(Map<String, String> map);
}
