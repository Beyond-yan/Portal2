package com.foxconn.service.organization;

import java.util.List;

import com.foxconn.pojo.trafficNews.TextNews;

public interface OrganizationService {

	public List<TextNews> getOrgList(TextNews textNews);
}
