package com.foxconn.dao.opencatalog;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;
import com.foxconn.pojo.opencatalog.Catalog;

@Repository("catalogDAO")
public class CatalogDAO {

	@Autowired
	@Resource(name = "sqlMapClientTemplate")
	private SqlMapClientTemplate sqlMapClientTemplate;

	@SuppressWarnings("unchecked")
	public List<Catalog> getCatalog() {
		Map<String, String> map = new HashMap<String, String>();
		return sqlMapClientTemplate.queryForList("Catalog.getCatalog", map);
	}

	public List<Catalog> getCatalogList(Catalog catalog) {
		@SuppressWarnings("unchecked")
		List<Catalog> getCatalogList = this.sqlMapClientTemplate.queryForList(
				"Catalog.getCatalogList", catalog);
		
		return getCatalogList;
	}
	public List<Catalog> getLikeCatalogList(Catalog catalog) {
		@SuppressWarnings("unchecked")
		List<Catalog> getCatalogList = this.sqlMapClientTemplate.queryForList(
				"Catalog.getLikeCatalogList", catalog);

		return getCatalogList;
	}

	@SuppressWarnings("unchecked")
	public Catalog getTextNewsDetail(Catalog textNews) {
		List<Catalog> textNewsList = this.sqlMapClientTemplate.queryForList(
				"Catalog.getTextNewsDetail", textNews);
		if (textNewsList != null && textNewsList.size() > 0) {
			return textNewsList.get(0);
		} else {
			return new Catalog();
		}
	}

	@SuppressWarnings("unchecked")
	public Catalog gettopmenu(Catalog catalog) {
		List<Catalog> topmenuList = this.sqlMapClientTemplate.queryForList(
				"Catalog.gettopmenu", catalog);
		if(topmenuList !=null && topmenuList.size()>0){
			return topmenuList.get(0);
		}else{
			return null;
		}
		
//		return topmenuList.size()>0? topmenuList.get(0): new Catalog();
		
	}

	public Catalog getInitCatelogDetail() {
		return (Catalog) this.sqlMapClientTemplate
				.queryForObject("Catalog.getInitCatelogDetail");
	}
	
	@SuppressWarnings("unchecked")
	public List<Catalog> getMajorProjectCatalog() {
		Map<String, String> map = new HashMap<String, String>();
		return sqlMapClientTemplate.queryForList("Catalog.getMajorProjectCatalog", map);
	}
	
	public List<Catalog> getPageCatalogList(Catalog catalog) {
		@SuppressWarnings("unchecked")
		List<Catalog> getCatalogList = this.sqlMapClientTemplate.queryForList(
				"Catalog.getPageCatalogList", catalog);
		return getCatalogList;
	}

    public List<Catalog> getMultiTopmenu(Catalog catalog) {
		List<Catalog> getCatalogList = this.sqlMapClientTemplate.queryForList(
				"Catalog.getMultiTopmenu", catalog);
		return getCatalogList;
    }


}
