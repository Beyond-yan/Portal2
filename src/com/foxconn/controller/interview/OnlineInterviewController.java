package com.foxconn.controller.interview;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.foxconn.util.TreeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.foxconn.pojo.interview.Interview;
import com.foxconn.pojo.interview.InterviewPlan;
import com.foxconn.pojo.interview.InterviewScenePhotos;
import com.foxconn.pojo.trafficNews.NewsComment;
import com.foxconn.service.interview.InterviewPlanService;
import com.foxconn.service.interview.InterviewService;
import com.foxconn.service.trafficNews.NewsCommentService;
import com.foxconn.service.trafficNews.TextNewsService;
import com.foxconn.util.DateUtil;

@Controller
@RequestMapping("onlineinterview.do")
public class OnlineInterviewController {

	@Autowired
	@Resource(name = "textNewsServiceImpl")
	private TextNewsService textNewsServiceImpl;
	@Autowired
	@Resource(name = "interviewServiceImpl")
	private InterviewService interviewServiceImpl;
	
	@Autowired
	@Resource(name = "interviewPlanServiceImpl")
	private InterviewPlanService interviewPlanServiceImpl;
	
	@Autowired
	@Resource(name = "newsCommentServiceImpl")
	private NewsCommentService newsCommentServiceImpl;

	@Value("${interview.page.size}")
	private int pageSize;

	@Value("${portal.content.server}")
	private String contentServer;

	@RequestMapping(params = "action=getOnlineInterviewList")
	public String getOnlineInterviewList(HttpServletRequest request, Model model)
			throws Exception {

		String strCurpage = request.getParameter("curpage");
		int curpage;
//		if (null == strCurpage || "".equals(strCurpage.trim())){
//			curpage = 1;
//		}
//		else{
////			curpage = Integer.parseInt(strCurpage);
//			try{
//				curpage = Integer.parseInt(strCurpage);
//			}catch(Exception e){
//				curpage = 1;
//			}
//		}
		try{
			curpage = Integer.parseInt(strCurpage);
		}catch(Exception e){
			curpage = 1;
		}
		List<Interview> interviewList = interviewServiceImpl.getInterviewList(
				curpage, pageSize);

		int cnt = interviewServiceImpl.getInterviewCount();

		model.addAttribute("interviewList", interviewList);
		model.addAttribute("prourl", contentServer);

		model.addAttribute("count", cnt);
		model.addAttribute("pagesize", pageSize);
		model.addAttribute("curpage", curpage);

		return "onlineinterview/onlineinterviewlist";
	}

	@RequestMapping(params = "action=getGuestInfo")
	public String getGuestInfo(HttpServletRequest request,
			@RequestParam("interviewId") String interviewId, Model model)
			throws Exception {

		Interview interview = new Interview();
		interview.setInterviewId(interviewId);
		Interview guestInfoList = interviewServiceImpl.getGuestInfo(interview);
		
		// 获取现场图片
		InterviewScenePhotos interviewScenePhotos = new InterviewScenePhotos();
		interviewScenePhotos.setScenePhotosId(guestInfoList.getScenePhotosId());
		List<InterviewScenePhotos> scenePhotosList 
				= interviewServiceImpl.getInterviewScenePhotos(interviewScenePhotos);
		
		/* 获取评论List */
		NewsComment newsComment = new NewsComment();
		newsComment.setNEWS_ID(interviewId);
		newsComment.setCOMMENT_CATEGORY("2");
		List<NewsComment> newsCommentList = new ArrayList<NewsComment>();
		try {
			newsCommentList = newsCommentServiceImpl.getNewsCommentListFromNewsId(newsComment);
		} catch (Exception e) {
			e.printStackTrace();
		}
		for(int i=0;i<newsCommentList.size();i++){
			NewsComment comment=newsCommentList.get(i);
			comment.setId(comment.getCOMMENT_ID());
			comment.setParentId(comment.getPARENT_ID());
		}
		List<NewsComment> treelist=TreeUtils.formatTree(newsCommentList,false);
		model.addAttribute("newsCommentList", treelist);
		model.addAttribute("commentCategory", 2);
		model.addAttribute("newsID", interviewId);
		model.addAttribute("prourl", contentServer);
		model.addAttribute("guestInfoList", guestInfoList);
		model.addAttribute("scenePhotosList", scenePhotosList);
		model.addAttribute("guestId", guestInfoList.getInterviewId());
		model.addAttribute("guestDesc", guestInfoList.getGuestDesc());
		model.addAttribute("guestPhotosUrl", guestInfoList.getGuestPhotosUrl());
		model.addAttribute("videoPhotosUrl", guestInfoList.getVideoPhotosUrl());
		model.addAttribute("interviewTitle", guestInfoList.getInterviewTitle());
		model.addAttribute("interviewSummary", guestInfoList.getInterviewSummary());
		model.addAttribute("interviewContent", guestInfoList.getInterviewContent());
		model.addAttribute("interviewPhotosUrl", guestInfoList.getInterviewPhotosUrl());
		model.addAttribute("interviewPhotosName", guestInfoList.getInterviewPhotosName());

		return "onlineinterview/onlineInterviewDetail";
	}
	
	@RequestMapping(params = "action=getSubList")
	public String GetSubList (Model model)throws Exception{
		List<Interview> interviewList = interviewServiceImpl.getInterviewList(1, 8);
		model.addAttribute("interviewList", interviewList);
		return "common/interviewSubList";
	}
	
	/* 新增访谈计划20160810 */
	@RequestMapping(params = "action=getInterviewsPlanList")
	public String GetInterviewsPlanList(Model model) throws Exception{  
		
		List<InterviewPlan> interviewPlanList = interviewPlanServiceImpl.getInterviewPlanList();
		model.addAttribute("interviewPlanList", interviewPlanList);
		return "onlineinterview/onlineInterviewPlanList";
	}
}