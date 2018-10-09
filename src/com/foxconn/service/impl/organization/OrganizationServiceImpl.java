package com.foxconn.service.impl.organization;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.foxconn.dao.organization.OrganizationDao;
import com.foxconn.pojo.trafficNews.TextNews;
import com.foxconn.service.organization.OrganizationService;

@Service("organizationServiceImpl")
public class OrganizationServiceImpl implements OrganizationService {
	@Autowired
	@Resource(name = "organizationDao")
	private OrganizationDao organizationDao;

	@Override
	public List<TextNews> getOrgList(TextNews textNews) {
		return organizationDao.getOrgList(textNews);
	}
}