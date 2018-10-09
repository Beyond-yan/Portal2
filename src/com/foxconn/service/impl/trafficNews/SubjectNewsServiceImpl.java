package com.foxconn.service.impl.trafficNews;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.foxconn.dao.trafficNews.SubjectNewsDao;
import com.foxconn.pojo.trafficNews.SubjectNews;
import com.foxconn.service.trafficNews.SubjectNewsService;

@Service("subjectNewsServiceImpl")
public class SubjectNewsServiceImpl implements SubjectNewsService {

	@Autowired
	@Resource(name = "subjectNewsDao")
	private SubjectNewsDao subjectNewsDao;

	@Override
	public List<SubjectNews> getSubjectNewsList(int pageSize, int curPage) {

		List<SubjectNews> subjectNewsList = subjectNewsDao.getSubjectNewsList(
				pageSize, curPage);
		String dateStr = "";
		if (subjectNewsList.size() != 0) {
			for (SubjectNews news : subjectNewsList) {
				dateStr = news.getModifyDate();
				if (dateStr == null) {
					news.setModifyDate("");

				} else {
					news.setModifyDate(dateStr.substring(0, 10));
				}

			}
		}

		return subjectNewsList;
	}

	@Override
	public int getSubjectNewsListCount() {

		return subjectNewsDao.getSubjectNewsListCount();
	}

	@Override
	public SubjectNews getSubjectNewsDetail(String subjectId) {

		return subjectNewsDao.getSubjectNewsDetail(subjectId);
	}

	@Override
	public int isExistSubjectShowOnIndex() {

		return subjectNewsDao.isExistSubjectShowOnIndex();
	}

	@Override
	public SubjectNews getSubjectInfoForShowOnIndex() {

		return subjectNewsDao.getSubjectInfoForShowOnIndex();
	}
}