package com.foxconn.dao.trafficconference;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

import com.foxconn.pojo.index.PhotoInfo;
import com.foxconn.pojo.opencatalog.Catalog;
import com.foxconn.pojo.trafficNews.PhotosInfo;

@Repository("hyzt2018Dao")
public class Hyzt2018Dao {

	@Resource(name = "sqlMapClientTemplate")
	private SqlMapClientTemplate sqlMapClientTemplate;

	@SuppressWarnings("unchecked")
	public List<Catalog> getIndexNewsList(Map<String, Object> map) {
		return sqlMapClientTemplate.queryForList("Hyzt2018SQL.getIndexNewsList", map);
	}

	@SuppressWarnings("unchecked")
	public List<PhotoInfo> getPhotos() {
		return this.sqlMapClientTemplate.queryForList("Hyzt2018SQL.getIndexPhotosNews");
	}

	@SuppressWarnings("unchecked")
	public List<PhotosInfo> getPictureList() {
		return this.sqlMapClientTemplate.queryForList("Hyzt2018SQL.getPictureList");
	}

}