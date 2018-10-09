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

import com.foxconn.pojo.trafficNews.SubjectNews;
import com.foxconn.service.trafficNews.SubjectNewsService;
import com.foxconn.service.trafficNews.TextNewsService;

@Controller
@RequestMapping("subjectNews.do")
public class SubjectNewsController {

	@Value("${portal.page.size}")
	private int pageSize;

	@Autowired
	@Resource(name = "subjectNewsServiceImpl")
	private SubjectNewsService subjectNewsServiceImpl;

	@Autowired
	@Resource(name = "textNewsServiceImpl")
	private TextNewsService textNewsServiceImpl;

	@RequestMapping(params = "action=getSubjectNewsList")
	public String getSubjectNewsList(HttpServletRequest request,
			@RequestParam("curpage") String curpage, Model model) {
		
		int intcurpage;
//		if (null == curpage || "".equals(curpage.trim())){
//			intcurpage = 1;
//		}else{
//			try{
//				intcurpage = Integer.parseInt(curpage);
//			}catch(Exception e){
//				intcurpage = 1;
//			}
//		}
		try{
			intcurpage = Integer.parseInt(curpage);
		}catch(Exception e){
			intcurpage = 1;
		}
		
		// 获取专题列表
		List<SubjectNews> subjectNewsList = subjectNewsServiceImpl
				.getSubjectNewsList(pageSize, intcurpage);
//		List<SubjectNews> subjectNewsList = subjectNewsServiceImpl
//				.getSubjectNewsList(pageSize, Integer.parseInt(curpage));
		int count = subjectNewsServiceImpl.getSubjectNewsListCount();
		model.addAttribute("subjectNewsList", subjectNewsList);
		model.addAttribute("count", count);
		model.addAttribute("pagesize", pageSize);
		model.addAttribute("curpage", curpage);
		return "subjectnews/subjectnewslist";
	}
}
