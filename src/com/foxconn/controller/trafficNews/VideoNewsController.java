package com.foxconn.controller.trafficNews;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.foxconn.pojo.trafficNews.VideoNews;
import com.foxconn.service.trafficNews.VideoNewsService;

@Controller
@RequestMapping("videoNews.do")
public class VideoNewsController {

	@Autowired
	@Resource(name = "videoNewsServiceImpl")
	private VideoNewsService videoNewsServiceImpl;

	@Value("${portal.file.path.coverpicture}")
	private String coverPicPath;

	@Value("${portal.file.path.video}")
	private String videoPath;

	@Value("${portal.content.server}")
	private String contentServer;
	
	@Value("${portal.page.size}")
	private int pageSize;

	/**
	 * 跳转到视频新闻列表页面
	 * 
	 * @param request
	 * @param page
	 * @param count
	 * @param model
	 * @return
	 */
	@RequestMapping(params = "action=getVideoNewsList")
	public String getVideoNewsList(HttpServletRequest request, Model model) {

		List<VideoNews> latestList = videoNewsServiceImpl.getLatestVideoNews();
		model.addAttribute("latestList", latestList);
		model.addAttribute("contentServer", contentServer);
		return "videonews/videonewslist";
	}

	@RequestMapping(params = "action=showVideo")
	public String showVideo(HttpServletRequest request,
			@RequestParam("newsID") String newsID, Model model) {
		
		VideoNews videoNews = videoNewsServiceImpl.getVideoByID(newsID);
		model.addAttribute("videoName", videoNews.getVideoNewsName());
		model.addAttribute("videoID", newsID);
		model.addAttribute("videoPath", contentServer + videoNews.getVideoUrl());
		model.addAttribute("modifyDate", videoNews.getModifyDate());
		model.addAttribute("entryDate", videoNews.getEntryDate());
		model.addAttribute("videoNewsSource", videoNews.getVideoNewsSource());
		model.addAttribute("player", contentServer
				+ "resource/flowplayer-3.2.14/flowplayer-3.2.14.swf");
		model.addAttribute("contentServer", contentServer);

		// 每请求一次，将对应的视频新闻阅读次数加1	
		int countBefor = videoNews.getReadCount();
		int countAfter = countBefor + 1;
		videoNewsServiceImpl.updateVideoReadcount(newsID, countAfter);
		return "videonews/playvideo";
	}
	
	@RequestMapping(params = "action=getVideoSubList")
	public String getVideoSubList(HttpServletRequest request, Model model) {
	
		List<VideoNews> recommendList = videoNewsServiceImpl
				.getRecommendVideoNews();
		List<VideoNews> collectionList = videoNewsServiceImpl
				.getCollectionVideoNews();	
		model.addAttribute("recommendList", recommendList);
		model.addAttribute("collectionList", collectionList);	
		return "videonews/videoSubList";
	}
	/**
	 * H2602975   2014-9-22
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(params = "action=getVideoAllList")
	public String getVideoAllList(HttpServletRequest request,
			@RequestParam("curpage") String curpage, 
			@RequestParam("programType") String programType,
			Model model) {
		int intcurpage;
		try{
			intcurpage = Integer.parseInt(curpage);
		}catch(Exception e){
			intcurpage = 1;
		}
//	if(curpage==null){
//		curpage = "1";
//	}
//		List<VideoNews> collectionList = videoNewsServiceImpl
//				.getAllVideoNews(Integer.parseInt(curpage),pageSize);
		
		List<VideoNews> collectionList = videoNewsServiceImpl
				.getAllVideoNews(intcurpage,pageSize);	
		
		int videoNewsCount = videoNewsServiceImpl.getVideoNewsCount();
		
		
		//System.out.println("当前页"+curpage+"----总条数"+videoNewsCount);
		model.addAttribute("collectionList", collectionList);	
		model.addAttribute("count", videoNewsCount);
		model.addAttribute("programType", "03");
		model.addAttribute("pagesize", pageSize);
		model.addAttribute("curpage", curpage);
		return "videonews/videoAllList";
	}
}