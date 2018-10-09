package com.foxconn.dao.trafficNews;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

import com.foxconn.pojo.trafficNews.MagazineNews;
import com.foxconn.pojo.trafficNews.Yearbook;

/**
 * @author F3228761
 * @date : Jun 21, 2013 3:25:03 PM
 */
@Repository("magazineNewsDao")
public class MagazineNewsDao {

	@Autowired
	@Resource(name = "sqlMapClientTemplate")
	private SqlMapClientTemplate sqlMapClientTemplate;

	@SuppressWarnings("unchecked")
	public List<MagazineNews> getMagazineNewsByID(String magNum) {
		return sqlMapClientTemplate.queryForList(
				"MagazineNewsSQL.getMagazineNewsByID", magNum);

	}

	public MagazineNews getMagazineInfo(String magNum) {
		return (MagazineNews) sqlMapClientTemplate.queryForObject(
				"MagazineNewsSQL.getMagazineInfo", magNum);
	}

	@SuppressWarnings("unchecked")
	public List<String> getMagazineNumList() {
		return sqlMapClientTemplate
				.queryForList("MagazineNewsSQL.getMagazineNumList");
	}

	public MagazineNews getContent(String contentID) {
		return (MagazineNews) sqlMapClientTemplate.queryForObject(
				"MagazineNewsSQL.getContent", contentID);
	}

	@SuppressWarnings("unchecked")
	public List<Yearbook> getYearbookList() {
		return sqlMapClientTemplate
				.queryForList("MagazineNewsSQL.getYearbookList");
	}
	
	public String getMagazineNumber(String magazineId) {
		return sqlMapClientTemplate.queryForObject("MagazineNewsSQL.getMagazineNumber",magazineId).toString();
	}
}
