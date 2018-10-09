package com.foxconn.dao.sitemap;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;
import com.foxconn.pojo.sitemap.SiteMap;

@Repository
public class SiteMapDao {

	@Resource(name = "sqlMapClientTemplate")
	private SqlMapClientTemplate sqlMapClientTemplate;
	
	@SuppressWarnings("unchecked")
	public List<SiteMap> getSiteMapList(SiteMap siteMap){
		return this.sqlMapClientTemplate.queryForList("siteMapSQL.getSiteMapList",siteMap);
	}

}
