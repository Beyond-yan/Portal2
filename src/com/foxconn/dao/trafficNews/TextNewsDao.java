package com.foxconn.dao.trafficNews;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

import com.foxconn.pojo.trafficNews.TextNews;
import com.foxconn.pojo.trafficNews.UserReadRecord;
import com.foxconn.pojo.trafficNews.NewsCommendInfo;
import com.googlecode.ehcache.annotations.Cacheable;

@Repository("textNewsDao")
public class TextNewsDao {

	@Resource(name = "sqlMapClientTemplate")
	private SqlMapClientTemplate sqlMapClientTemplate;

	/**
	 * 获取重庆交通新闻列表 F3228777 2013-06-21
	 */
	@SuppressWarnings("unchecked")
	@Cacheable(cacheName = "portalCache")
	public List<TextNews> getCQTrafficNewsList(TextNews textNews, int pageSize,
			int curPage) {
		return this.sqlMapClientTemplate.queryForList(
				"TextNews.getCQTrafficNewsList", textNews, (curPage - 1)
						* pageSize, pageSize);

	}

	/**
	 * 获取重庆交通新闻列表记录总数 F3228777 2013-06-21
	 */
	@Cacheable(cacheName = "portalCache")
	public int getCQTrafficNewsListCount(TextNews textNews) {
		return (Integer) this.sqlMapClientTemplate.queryForObject(
				"TextNews.getCQTrafficNewsListCount", textNews);
	}

	/**
	 * 根据type获取热点新闻列表（type week:一周 month：一月）
	 * 
	 * @param type
	 * @param size
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Cacheable(cacheName = "portalCache")
	public List<TextNews> getHotNewsList(TextNews textNews, String type,
			int size) {
		List<TextNews> hotNewsList = new ArrayList<TextNews>();
		if (type.equalsIgnoreCase("week")) {
			hotNewsList = this.sqlMapClientTemplate.queryForList(
					"TextNews.getHotNewsListWeek", textNews, 0, size);
		} else {
			hotNewsList = this.sqlMapClientTemplate.queryForList(
					"TextNews.getHotNewsListMonth", textNews, 0, size);
		}
		return hotNewsList;
	}

	/**
	 * 獲取新聞詳情
	 * 
	 * @param textNews
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public TextNews getTextNewsDetail(TextNews textNews) {
		List<TextNews> textNewsList = this.sqlMapClientTemplate.queryForList(
				"TextNews.getTextNewsDetail", textNews);
		if (textNewsList != null && textNewsList.size() > 0) {
			return textNewsList.get(0);
		} else {
			return new TextNews();
		}
	}

	/**
	 * 瀏覽記錄記錄
	 * 
	 * @param textNews
	 */
	public void insertReadRecord(UserReadRecord userReadRecord) {
		// Traffic_Text_News_Info_T表欄位readCount加1
		this.sqlMapClientTemplate.update("TextNews.updateNewsReadCount",
				userReadRecord);
		// Traffic_User_Read_Record_T表中新增一筆
		this.sqlMapClientTemplate.insert("TextNews.insertReadRecord",
				userReadRecord);
	}

	public void insertNewsCommendInfo(NewsCommendInfo newsCommendInfo){
		this.sqlMapClientTemplate.insert("TextNews.insertNewsCommendInfo", newsCommendInfo);
	}
	
	public int getNewsCommendInfoCount(NewsCommendInfo newsCommendInfo){
		return (Integer) this.sqlMapClientTemplate.queryForObject("TextNews.getNewsCommendInfoCount", newsCommendInfo);
	}
	
	public void updateEffective(TextNews textNews) {
		this.sqlMapClientTemplate.update("TextNews.updateEffective", textNews);
	}

	public void updateEmotional(TextNews textNews) {
		this.sqlMapClientTemplate.update("TextNews.updateEmotional", textNews);
	}

	public void updateHappy(TextNews textNews) {
		this.sqlMapClientTemplate.update("TextNews.updateHappy", textNews);
	}

	public void updateNonsense(TextNews textNews) {
		this.sqlMapClientTemplate.update("TextNews.updateNonsense", textNews);
	}

	public void updateBoring(TextNews textNews) {
		this.sqlMapClientTemplate.update("TextNews.updateBoring", textNews);
	}

	public void updateFear(TextNews textNews) {
		this.sqlMapClientTemplate.update("TextNews.updateFear", textNews);
	}

	public void updateSad(TextNews textNews) {
		this.sqlMapClientTemplate.update("TextNews.updateSad", textNews);
	}

	public void updateAngry(TextNews textNews) {
		this.sqlMapClientTemplate.update("TextNews.updateAngry", textNews);
	}
	
	public String selectProgramTypeName(String resourceID) {
		return (String) this.sqlMapClientTemplate.queryForObject(
				"TextNews.selectProgramTypeName", resourceID);
	}
}