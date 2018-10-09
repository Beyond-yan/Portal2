package com.foxconn.service.impl.opencatalog;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.foxconn.dao.opencatalog.*;
import com.foxconn.pojo.opencatalog.*;
import com.foxconn.service.opencatalog.*;

@Service("catalogServiceImpl")
public class CatalogServiceImpl implements CatalogService {

	@Resource(name = "catalogDAO")
	private CatalogDAO catalogDao;

	@Override
	public List<Catalog> getCatalog() {

		return catalogDao.getCatalog();
	}

	@Override
	public List<Catalog> getCatalogList(Catalog catalog) {

		return catalogDao.getCatalogList(catalog);
	}
	@Override
	public List<Catalog> getLikeCatalogList(Catalog catalog) {

		return catalogDao.getLikeCatalogList(catalog);
	}
	@Override
	public List<Catalog> getPageCatalogList(Catalog catalog) {
		
		return catalogDao.getPageCatalogList(catalog);
	}

	@Override
	public Catalog getTextNewsDetail(Catalog textNews) {
		return catalogDao.getTextNewsDetail(textNews);
	}

	@Override
	public Catalog gettopmenu(Catalog catalog) {
		return catalogDao.gettopmenu(catalog);
	}

	@Override
	public Catalog getInitCatelogDetail() {
		return catalogDao.getInitCatelogDetail();
	}

	@Override
	public List<Catalog> getMultiTopmenu(Catalog catalog) {
		return catalogDao.getMultiTopmenu(catalog);
	}

	@Override
	public List<Catalog> getMajorProjectCatalog() {
		return catalogDao.getMajorProjectCatalog();
	}
}
