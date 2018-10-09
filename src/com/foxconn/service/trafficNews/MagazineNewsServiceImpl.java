package com.foxconn.service.trafficNews;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foxconn.dao.trafficNews.MagazineNewsDao;
import com.foxconn.pojo.trafficNews.MagazineNews;
import com.foxconn.pojo.trafficNews.Yearbook;
import com.foxconn.service.trafficNews.MagazineNewsService;

/**
 * @author F3228761
 * @date : Jun 21, 2013 3:25:41 PM
 */
@Service("magazineNewsImpl")
public class MagazineNewsServiceImpl implements MagazineNewsService {

	@Autowired
	@Resource(name = "magazineNewsDao")
	private MagazineNewsDao magazineNewsDao;

	@Override
	public List<MagazineNews> getMagazineNewsByID(String magNum) {
		return magazineNewsDao.getMagazineNewsByID(magNum);
	}

	@Override
	public MagazineNews getMagazineInfo(String magNum) {
		return magazineNewsDao.getMagazineInfo(magNum);
	}

	@Override
	public List<String> getMagazineNumList() {
		return magazineNewsDao.getMagazineNumList();
	}

	@Override
	public MagazineNews getContent(String contentID) {
		return magazineNewsDao.getContent(contentID);
	}

	@Override
	public List<Yearbook> getYearbookList() {
		return magazineNewsDao.getYearbookList();
	}

	@Override
	public String getMagazineNumber(String magazineId) {
		return magazineNewsDao.getMagazineNumber(magazineId);
	}
}