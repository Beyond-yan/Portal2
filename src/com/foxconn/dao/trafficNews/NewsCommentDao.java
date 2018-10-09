package com.foxconn.dao.trafficNews;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;
import com.foxconn.pojo.trafficNews.NewsComment;

@Repository("newsCommentDao")
public class NewsCommentDao {

	@Resource(name = "sqlMapClientTemplate")
	private SqlMapClientTemplate sqlMapClientTemplate;

	@SuppressWarnings("unchecked")
	public List<NewsComment> getNewsCommentListFromNewsId(NewsComment newsComment) {
		return this.sqlMapClientTemplate.queryForList(
				"NewsComment.getNewsCommentListFromNewsId", newsComment);
	}
	
	public void addNewsComment(NewsComment newsComment) {
		this.sqlMapClientTemplate.insert(
				"NewsComment.addNewsComment", newsComment);
	}
}