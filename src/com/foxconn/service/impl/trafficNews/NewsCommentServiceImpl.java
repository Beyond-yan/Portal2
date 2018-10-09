package com.foxconn.service.impl.trafficNews;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.foxconn.dao.trafficNews.NewsCommentDao;
import com.foxconn.pojo.trafficNews.NewsComment;
import com.foxconn.service.trafficNews.NewsCommentService;

@Service("newsCommentServiceImpl")
public class NewsCommentServiceImpl implements NewsCommentService {

	@Autowired
	@Resource(name = "newsCommentDao")
	private NewsCommentDao newsCommentDao;

	@Override
	public List<NewsComment> getNewsCommentListFromNewsId(
			NewsComment newsComment){
		return newsCommentDao.getNewsCommentListFromNewsId(newsComment);
	}
	
	public void addNewsComment(NewsComment newsComment){
		newsCommentDao.addNewsComment(newsComment);
	}


}