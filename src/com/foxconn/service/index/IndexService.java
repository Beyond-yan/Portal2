package com.foxconn.service.index;

import java.util.List;

import com.foxconn.pojo.Communication.Topic;
import com.foxconn.pojo.index.PhotoInfo;
import com.foxconn.pojo.questionaire.SURVEY_QUESTIONAIRE;
import com.foxconn.pojo.trafficNews.MagazineNews;
import com.foxconn.pojo.trafficNews.TextNews;
import com.foxconn.pojo.trafficNews.VideoNews;

/**
 * @author F3228761
 * @date : Jun 21, 2013 3:25:58 PM
 */
public interface IndexService {

	public List<PhotoInfo> getPhotos();

	public List<TextNews> getIndexTextNews();
	
	public List<TextNews> getIndexNews();

	public MagazineNews getIndexMagazineNews();

	public VideoNews getIndexVideoNews();

	public Topic getIndexTopic();

	public SURVEY_QUESTIONAIRE getIndexQuestionaire();
	
	public  List fetchNews();
	
	

	/**
	 * 获得最新的政务公告
	 * 
	 * @return
	 */
	public List<TextNews> getUpToDateAnnouncementList(String programType);

	public StringBuilder setTextNewsLayout();
	public StringBuilder setTextNewsLayout2();
	public StringBuilder setFetchNewsLayout(List<String> newList);

}
