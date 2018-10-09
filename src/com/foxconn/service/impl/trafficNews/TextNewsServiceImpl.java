package com.foxconn.service.impl.trafficNews;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.foxconn.dao.trafficNews.TextNewsDao;
import com.foxconn.pojo.trafficNews.NewsCommendInfo;
import com.foxconn.pojo.trafficNews.TextNews;
import com.foxconn.pojo.trafficNews.UserReadRecord;
import com.foxconn.service.trafficNews.TextNewsService;

@Service("textNewsServiceImpl")
public class TextNewsServiceImpl implements TextNewsService {

	@Autowired
	@Resource(name = "textNewsDao")
	private TextNewsDao textNewsDao;

	/**
	 * 获取重庆交通新闻列表 F3228777 2013-06-21
	 */
	@Override
	public List<TextNews> getCQTrafficNewsList(TextNews textNews, int pageSize,
			int curPage) {
		List<TextNews> trafficNewsList = textNewsDao.getCQTrafficNewsList(
				textNews, pageSize, curPage);
		return trafficNewsList;
	}

	/**
	 * 获取重庆交通新闻列表记录总数 F3228777 2013-06-21
	 */
	@Override
	public int getCQTrafficNewsListCount(TextNews textNews) {

		return textNewsDao.getCQTrafficNewsListCount(textNews);

	}

	/**
	 * 获取本周或者本月热点新闻列表 按浏览人数倒序取前10条
	 */
	@Override
	public List<TextNews> getHotNewsList(TextNews textNews, String type,
			int size) {
		return textNewsDao.getHotNewsList(textNews, type, size);
	}

	/**
	 * 獲取新聞詳情
	 */
	@Override
	public TextNews getTextNewsDetail(TextNews textNews) {
		return textNewsDao.getTextNewsDetail(textNews);
	}

	/**
	 * 瀏覽記錄記錄
	 */
	@Override
	public void insertReadRecord(UserReadRecord userReadRecord) {

		textNewsDao.insertReadRecord(userReadRecord);
	}

	@Override
	public void updateEffective(TextNews textNews) {
		textNewsDao.updateEffective(textNews);
	}

	@Override
	public void updateEmotional(TextNews textNews) {
		textNewsDao.updateEmotional(textNews);
	}

	@Override
	public void updateHappy(TextNews textNews) {
		textNewsDao.updateHappy(textNews);
	}

	@Override
	public void updateNonsense(TextNews textNews) {
		textNewsDao.updateNonsense(textNews);
	}

	@Override
	public void updateBoring(TextNews textNews) {
		textNewsDao.updateBoring(textNews);
	}

	@Override
	public void updateFear(TextNews textNews) {
		textNewsDao.updateFear(textNews);
	}

	@Override
	public void updateSad(TextNews textNews) {
		textNewsDao.updateSad(textNews);
	}

	@Override
	public void updateAngry(TextNews textNews) {
		textNewsDao.updateAngry(textNews);
	}

	@Override
	public String selectProgramTypeName(String resourceID) {
		
		return textNewsDao.selectProgramTypeName(resourceID);
	}
	
	@Override
    public void insertNewsCommendInfo(NewsCommendInfo newsCommendInfo){
		textNewsDao.insertNewsCommendInfo(newsCommendInfo);
	}
	
    @Override
	public int getNewsCommendInfoCount(NewsCommendInfo newsCommendInfo){
    	return textNewsDao.getNewsCommendInfoCount(newsCommendInfo);
    }
}