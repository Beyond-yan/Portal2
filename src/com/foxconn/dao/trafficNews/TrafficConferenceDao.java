package com.foxconn.dao.trafficNews;


import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

import com.foxconn.pojo.index.PhotoInfo;
import com.foxconn.pojo.opencatalog.Catalog;
import com.foxconn.pojo.trafficconference.ProjectPhotos;

@Repository("trafficConferenceDao")
public class TrafficConferenceDao {

	@Resource(name = "sqlMapClientTemplate")
	private SqlMapClientTemplate sqlMapClientTemplate;

	
	@SuppressWarnings("unchecked")
	public List<Catalog> getIndexNewsList(Map<String,Object> map) {
		return sqlMapClientTemplate.queryForList("TrafficConferenceSQL.getIndexNewsList",map);
	}
	
	@SuppressWarnings("unchecked")
	public List<PhotoInfo> getPhotos() {
		return this.sqlMapClientTemplate.queryForList("TrafficConferenceSQL.getIndexPhotosNews");
	}
	
	@SuppressWarnings("unchecked")
	public List<ProjectPhotos> getPictureList(){
		return this.sqlMapClientTemplate.queryForList("TrafficConferenceSQL.getPictureList");
	}
	
}