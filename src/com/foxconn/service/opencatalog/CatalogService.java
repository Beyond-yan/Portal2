package com.foxconn.service.opencatalog;

import java.util.List;
import com.foxconn.pojo.opencatalog.*;

public interface CatalogService {
	public List<Catalog> getMajorProjectCatalog();
	
	public List<Catalog> getCatalog();

	public List<Catalog> getCatalogList(Catalog catalog);
	
	public List<Catalog> getPageCatalogList(Catalog catalog);

	Catalog getTextNewsDetail(Catalog textNews);

	Catalog gettopmenu(Catalog catalog);

	public Catalog getInitCatelogDetail();

	List<Catalog> getMultiTopmenu(Catalog catalog);

	List<Catalog> getLikeCatalogList(Catalog catalog);
}