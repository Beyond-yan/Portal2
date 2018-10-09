package com.foxconn.dao.organization;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

import com.foxconn.pojo.trafficNews.TextNews;

@Repository("organizationDao")
public class OrganizationDao {
	@Autowired
	@Resource(name = "sqlMapClientTemplate")
	private SqlMapClientTemplate sqlMapClientTemplate;

	@SuppressWarnings("unchecked")
	public List<TextNews> getOrgList(TextNews textNews) {
		return sqlMapClientTemplate.queryForList("Organization.getOrgList",
				textNews);
	}
}