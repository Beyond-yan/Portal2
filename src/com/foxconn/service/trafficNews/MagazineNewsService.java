package com.foxconn.service.trafficNews;

import java.util.List;

import com.foxconn.pojo.trafficNews.MagazineNews;
import com.foxconn.pojo.trafficNews.Yearbook;

/**
 * @author F3228761
 * @date : Jun 21, 2013 3:25:58 PM
 */
public interface MagazineNewsService {

	public List<MagazineNews> getMagazineNewsByID(String magNum);

	public MagazineNews getContent(String contentID);

	public MagazineNews getMagazineInfo(String magNum);

	public List<String> getMagazineNumList();

	public List<Yearbook> getYearbookList();
	
	public String getMagazineNumber(String magazineId);
}