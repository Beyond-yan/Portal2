package com.foxconn.service.trafficNews;

import java.util.List;
import com.foxconn.pojo.trafficNews.NewsComment;

public interface NewsCommentService {
	public List<NewsComment> getNewsCommentListFromNewsId(NewsComment newsComment) throws Exception;
	public void addNewsComment(NewsComment newsComment) throws Exception;
}
