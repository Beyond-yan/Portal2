package com.foxconn.service.trafficNews;

import java.util.List;

import com.foxconn.pojo.trafficNews.SubjectNews;

public interface SubjectNewsService {

	List<SubjectNews> getSubjectNewsList(int pageSize,int curPage);

	SubjectNews getSubjectNewsDetail(String subjectId);

	int isExistSubjectShowOnIndex();

	SubjectNews getSubjectInfoForShowOnIndex();

	int getSubjectNewsListCount();

}
