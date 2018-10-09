package com.foxconn.controller.trafficNews;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.foxconn.pojo.trafficNews.PhotosInfo;
import com.foxconn.pojo.trafficNews.PhotosNews;
import com.foxconn.service.trafficNews.PhotosNewsService;
import com.foxconn.util.PageUtils;

@Controller
@RequestMapping("PhotosNews.do")
public class PhotosNewsController {

	@Autowired
	@Resource(name = "photosNewsServiceImpl")
	private PhotosNewsService photosNewsServiceImpl;

	@Value("${portal.content.server}")
	private String contentServer;

	/**
	 * 
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(params = "action=getPhotosNewsList")
	public String getPhotosNewsList(HttpServletRequest request, Model model) {
		List<PhotosNews> latestList = photosNewsServiceImpl
				.getLatestPhotoNews();
		List<PhotosNews> recommendList = photosNewsServiceImpl
				.getRecommendPhotoNews();
		List<PhotosNews> collectionList = photosNewsServiceImpl
				.getCollectionPhotoNews();

		model.addAttribute("latestList", latestList);
		model.addAttribute("recommendList", recommendList);
		model.addAttribute("collectionList", collectionList);
		model.addAttribute("contentServer", contentServer);
		return "photosnews/photosnewslist";
	}

	// modified by Cube @130828
	@RequestMapping(params = "action=getPhotosInfoList")
	public String getPhotosInfoList(
			@RequestParam("photosNewsID") String photosNewsID,
			HttpServletRequest request, Model model) {
		
		PhotosNews photosNews = new PhotosNews();
		photosNews.setPhotosNewsID(photosNewsID);
		
		photosNewsServiceImpl.updateReadCount(photosNews);
		
		photosNews = photosNewsServiceImpl.getPhotoNewsByID(photosNews);

		PhotosInfo photosInfo = new PhotosInfo();
		photosInfo.setPhotosNewsID(photosNewsID);
		List<PhotosInfo> photosInfoList = photosNewsServiceImpl
				.getPhotosInfoList(photosInfo);

		model.addAttribute("photosNews", photosNews);
		model.addAttribute("photosInfoList", photosInfoList);
		model.addAttribute("contentServer", contentServer);

		return "photosnews/photosinfolist";
	}

	@RequestMapping(params = "action=getAllPhotosNewsList")
	public String getAllPhotosNewsList(HttpServletRequest request, Model model,		
				@RequestParam("pageIndex") int pageIndex,	@RequestParam("limitCount") int limitCount) {
		
		Map<String,Object> param = new HashMap<String, Object>();
		//得到总记录条数
		int photosNewsCount = this.photosNewsServiceImpl.queryPhotosNewsCount(param);
		PageUtils page = new PageUtils(photosNewsCount, pageIndex, limitCount);
		param.put("limitBegin", page.getLimitBegin());
		param.put("limitEnd", page.getLimitEnd());
		//得到请求的记录列表
		List<PhotosNews> photosNewsList=this.photosNewsServiceImpl.queryPhotoNewsList(param);
		
		model.addAttribute("photosNewsList", photosNewsList);//得到当前要显示的记录列表
		model.addAttribute("photosNewsCount", photosNewsCount);//总记录条数
		model.addAttribute("contentServer", contentServer);
		model.addAttribute("pageCount", page.getPageCount());//页面大小
		model.addAttribute("pageIndex", page.getPageIndex());//更新当前页
		model.addAttribute("limitCount", limitCount);//更新当前页
		
		return "photosnews/allphotosnewslist";
	}
	
}
