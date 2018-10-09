package com.foxconn.dao.trafficNews;

import java.util.HashMap;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;
import com.foxconn.pojo.trafficNews.SubjectNews;

@Repository("subjectNewsDao")
public class SubjectNewsDao {

	@Resource(name = "sqlMapClientTemplate")
	private SqlMapClientTemplate sqlMapClientTemplate;

	@SuppressWarnings("unchecked")
	public List<SubjectNews> getSubjectNewsList(int pageSize, int curPage) {

		return this.sqlMapClientTemplate.queryForList(
				"SubjectNews.getSubjectNewsList", (curPage - 1) * pageSize,
				pageSize);
	}

	public SubjectNews getSubjectNewsDetail(String subjectId) {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("subjectId", subjectId);
		return (SubjectNews) this.sqlMapClientTemplate.queryForObject(
				"SubjectNews.getSubjectInfoByID", paramMap);
	}

	public int isExistSubjectShowOnIndex() {

		return (Integer) this.sqlMapClientTemplate
				.queryForObject("SubjectNews.isExistSubjectShowOnIndex");
	}

	public SubjectNews getSubjectInfoForShowOnIndex() {

		return (SubjectNews) this.sqlMapClientTemplate
				.queryForObject("SubjectNews.getSubjectInfoForShowOnIndex");
	}

	public int getSubjectNewsListCount() {

		return (Integer) this.sqlMapClientTemplate
				.queryForObject("SubjectNews.getSubjectNewsListCount");
	}
}