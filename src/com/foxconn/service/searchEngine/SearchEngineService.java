package com.foxconn.service.searchEngine;

import java.io.IOException;
import java.util.List;

import com.foxconn.pojo.opencatalog.PublicArchive;
import com.foxconn.pojo.trafficNews.KeyWordNews;

public interface SearchEngineService {
	


	public List<KeyWordNews> getNewsSearchInfo(String keyStr, int curpage, int pageSize, String programType, String orderby, String startTime, String endTime, String wordPosition);
	
	public String call(String url) throws IOException;
	
	public List<PublicArchive> getPublicArchives(int startIndex, int pageSize);

}
