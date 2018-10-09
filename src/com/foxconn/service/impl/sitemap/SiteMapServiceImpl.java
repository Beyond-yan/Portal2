package com.foxconn.service.impl.sitemap;


import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.foxconn.dao.sitemap.SiteMapDao;
import com.foxconn.pojo.sitemap.SiteMap;
import com.foxconn.service.sitemap.SiteMapService;

@Service("siteMapServiceImpl")
public class SiteMapServiceImpl implements SiteMapService {

	@Resource(name = "siteMapDao")
	private SiteMapDao siteMapDao;
	
	@Override
	public List<SiteMap> getSiteMapList(SiteMap siteMap) throws Exception {
		return siteMapDao.getSiteMapList(siteMap);
	}
	
}
