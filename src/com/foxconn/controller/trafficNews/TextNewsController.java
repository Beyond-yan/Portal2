package com.foxconn.controller.trafficNews;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashSet;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.foxconn.util.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.foxconn.job.WordSegmentTool;
import com.foxconn.pojo.trafficNews.KeyWordNews;
import com.foxconn.pojo.trafficNews.NewsCommendInfo;
import com.foxconn.pojo.trafficNews.NewsComment;
import com.foxconn.pojo.trafficNews.TextNews;
import com.foxconn.pojo.trafficNews.UserReadRecord;
import com.foxconn.service.searchEngine.SearchEngineService;
import com.foxconn.service.trafficNews.MagazineNewsServiceImpl;
import com.foxconn.service.trafficNews.NewsCommentService;
import com.foxconn.service.trafficNews.TextNewsService;

@Controller
@RequestMapping("textNews.do")
public class TextNewsController {

	@Value("${portal.page.size}")
	private int pageSize;

	
	@Resource(name = "magazineNewsImpl")
	private MagazineNewsServiceImpl magazineNewsImpl;


	@Autowired
	@Resource(name = "textNewsServiceImpl")
	private TextNewsService textNewsServiceImpl;
	
	@Autowired
	@Resource(name = "newsCommentServiceImpl")
	private NewsCommentService newsCommentServiceImpl;
	
	/**
	 * by H2603045
	 * @param model
	 * @return
	 */
	@Autowired
	@Resource(name = "searchEngineServiceImpl")
	private SearchEngineService searchEngineService;

	@RequestMapping(params = "action=getNewsRank")
	public String getNewsRank(Model model) {
		model.addAttribute("str", "server string!");
		return "textnews/newsRank";
	}

	// added by Cube @130826
	@RequestMapping(params = "action=getSubList")
	public String getSubList(Model model) {
		TextNews textNews = new TextNews();
		textNews.setProgramType("010101");
		textNews.setNewsStatusID(7);
		List<TextNews> weekList = textNewsServiceImpl.getHotNewsList(textNews, "week", 10);
		List<TextNews> monthList = textNewsServiceImpl.getHotNewsList(textNews, "month", 10);
		model.addAttribute("weekList", weekList);
		model.addAttribute("monthList", monthList);
		return "common/subList";
	}

	/**
	 * 到新闻列表页面 F3228777 2013-06-21
	 * 
	 * @param programType
	 *            ：对应关系表TRAFFIC_PAGE_NEWS_REF_T栏位page_id,
	 *            而不是新闻表TRAFFIC_TEXT_NEWS_INFO_T栏位program_type
	 */
	@RequestMapping(params = "action=getCQTrafficNewsList")
	public String getCQTrafficNewsList(HttpServletRequest request, 
			@RequestParam("curpage") String curpage, 
			@RequestParam("programType") String programType,
			Model model) {
		
		int intcurpage;
		try{
			intcurpage = Integer.parseInt(curpage);
		}catch(Exception e){
			intcurpage = 1;
		}
		
		TextNews textNews = new TextNews();
		textNews.setProgramType(programType);
		textNews.setNewsStatusID(7);
		String programTypeName = textNewsServiceImpl.selectProgramTypeName(programType);

		List<TextNews> trafficNewsList = textNewsServiceImpl.getCQTrafficNewsList(textNews, pageSize, intcurpage);
//		List<TextNews> trafficNewsList = textNewsServiceImpl.getCQTrafficNewsList(textNews, pageSize, Integer.parseInt(curpage));
		int trafficNewsCount = textNewsServiceImpl.getCQTrafficNewsListCount(textNews);
		
		model.addAttribute("trafficNewsList", trafficNewsList);
		model.addAttribute("programType", programType);
		model.addAttribute("programTypeName", programTypeName);
		model.addAttribute("count", trafficNewsCount);
		model.addAttribute("pagesize", pageSize);
		model.addAttribute("curpage", curpage);
		return "textnews/trafficnewslist";
	}
	@RequestMapping(params = "action=getNewsJsonList")
	public void getNewsJsonList(HttpServletRequest request,
									   @RequestParam("curpage") String curpage,
										@RequestParam("pagesize") String pagesize,
									   @RequestParam("programType") String programType,
									   Model model,HttpServletResponse response) {

		int intcurpage;
		try{
			intcurpage = Integer.parseInt(curpage);
		}catch(Exception e){
			intcurpage = 1;
		}
		int psize;
		try{
			psize = Integer.parseInt(pagesize);
		}catch(Exception e){
			psize = 20;
		}
		TextNews textNews = new TextNews();
		textNews.setProgramType(programType);
		textNews.setNewsStatusID(7);
		List<TextNews> trafficNewsList = textNewsServiceImpl.getCQTrafficNewsList(textNews, psize, intcurpage);
		response.setCharacterEncoding("utf-8");
		try {
			response.getWriter().write(JsonUtil.toJsonString(trafficNewsList));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 获取重庆交通新闻列表 F3228777 2013-06-21
	 * 
	 * @param programType
	 *            ：对应关系表TRAFFIC_PAGE_NEWS_REF_T栏位page_id,
	 *            而不是新闻表TRAFFIC_TEXT_NEWS_INFO_T栏位program_type
	 */

	@RequestMapping(params = "action=getCourseListByGrade")
	public void getClassNoByGrade(@RequestParam("type") String type, Model model, HttpServletResponse response) {
		TextNews textNews = new TextNews();
		textNews.setProgramType("010101");
		textNews.setNewsStatusID(7);
		List<TextNews> list = textNewsServiceImpl.getHotNewsList(textNews, type, 10);
		response.setCharacterEncoding("utf-8");
		try {
			response.getWriter().write(JsonUtil.toJsonString(list));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取本周或者本月热点新闻列表(type : week表示本周，month表示本月 默认情况下为本周) 按浏览人数倒序取前10条
	 * 
	 * @param type
	 * */
	@RequestMapping(params = "action=getHotNewsList")
	public void getHotNewsList(HttpServletRequest request, @RequestParam("type") String type, HttpServletResponse response, Model model) {
		TextNews textNews = new TextNews();
		textNews.setProgramType("010101");
		textNews.setNewsStatusID(7);
		List<TextNews> hotNewsList = textNewsServiceImpl.getHotNewsList(textNews, type, 10);
		response.setCharacterEncoding("utf-8");
		try {
			response.getWriter().write(JsonUtil.toJsonString(hotNewsList));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param programType
	 *            ：对应关系表TRAFFIC_PAGE_NEWS_REF_T栏位page_id,
	 *            而不是新闻表TRAFFIC_TEXT_NEWS_INFO_T栏位program_type
	 */
	@RequestMapping(params = "action=getTextNewsDetail")
	public String getTextNewsDetail(@RequestParam("newsID") String newsID, @RequestParam("programType") String programType, HttpServletRequest request, Model model) {
		if (programType == null || "".equals(programType)) {
			programType = "010101";
		}
		TextNews textNews = new TextNews();
		textNews.setNewsID(newsID);
		TextNews TextNews1 = textNewsServiceImpl.getTextNewsDetail(textNews);

		UserReadRecord userReadRecord = new UserReadRecord();
		userReadRecord.setNewsID(newsID);
		IPHelper iphelper = new IPHelper();
		userReadRecord.setUserIP(iphelper.getIPAddress(request));
		textNewsServiceImpl.insertReadRecord(userReadRecord);

		/* 获取评论List */
		String newsId = TextNews1.getNewsID();
		NewsComment newsComment = new NewsComment();
		newsComment.setNEWS_ID(newsId);
		newsComment.setCOMMENT_CATEGORY("1");
		List<NewsComment> newsCommentList = new ArrayList<NewsComment>();
		try {
			newsCommentList = newsCommentServiceImpl.getNewsCommentListFromNewsId(newsComment);
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<BaseTreeGrid> list = new ArrayList<BaseTreeGrid>();

		for(int i=0;i<newsCommentList.size();i++){
			NewsComment comment=newsCommentList.get(i);
			comment.setId(comment.getCOMMENT_ID());
			comment.setParentId(comment.getPARENT_ID());
		}
		List<NewsComment> treelist=TreeUtils.formatTree(newsCommentList,false);
		model.addAttribute("newsCommentList", treelist);
		model.addAttribute("readRecordCount", TextNews1.getReadRecordCount());
		model.addAttribute("newsTitle", TextNews1.getNewsTitle());
		model.addAttribute("textNewsTitle", TextNews1.getTextNewsTitle());
		model.addAttribute("newsKeyWord", TextNews1.getNewsKeyWord());
		model.addAttribute("newsID", TextNews1.getNewsID());

		model.addAttribute("newsContent", TextNews1.getNewsContent() == null ? TextNews1.getNewsContent() : com.foxconn.util.ServerPathConvet.decodeConvertContent(TextNews1.getNewsContent()));
		model.addAttribute("subNewsTitle", TextNews1.getSubNewsTitle());
		model.addAttribute("newsSource", String.valueOf(TextNews1.getNewsSource()));
		model.addAttribute("newsSourceName", TextNews1.getNewsSourceName());
		model.addAttribute("entryDate", TextNews1.getEntryDate());

		model.addAttribute("commentCategory", 1);
		model.addAttribute("programType", programType);
		//禁止复制
		model.addAttribute("isBan",TextNews1.getIsBan());
		
		String programTypeName = textNewsServiceImpl.selectProgramTypeName(programType);
		model.addAttribute("programTypeName", programTypeName);
		return "textnews/textnewsdetail";

	}

	@RequestMapping(params = "action=addCommend")
	public String addCommend(@RequestParam("newsID") String newsID, @RequestParam("programType") String programType, HttpServletRequest request, Model model) {


		if (programType == null || "".equals(programType)) {
			programType = "010101";
		}
		TextNews textNews2 = new TextNews();
		textNews2.setNewsID(newsID);
		TextNews TextNews1 = textNewsServiceImpl.getTextNewsDetail(textNews2);
		
		/* 获取评论List */
		String newsId = TextNews1.getNewsID();
		NewsComment newsComment = new NewsComment();
		newsComment.setNEWS_ID(newsId);
		newsComment.setCOMMENT_CATEGORY("1");
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
		model.addAttribute("readRecordCount", TextNews1.getReadRecordCount());
		model.addAttribute("newsTitle", TextNews1.getNewsTitle());
		model.addAttribute("newsKeyWord", TextNews1.getNewsKeyWord());
		model.addAttribute("newsID", TextNews1.getNewsID());
		model.addAttribute("newsContent", TextNews1.getNewsContent() == null ? TextNews1.getNewsContent() : com.foxconn.util.ServerPathConvet.decodeConvertContent(TextNews1.getNewsContent()));
		
		model.addAttribute("subNewsTitle", TextNews1.getSubNewsTitle());
		model.addAttribute("newsSource", String.valueOf(TextNews1.getNewsSource()));
		model.addAttribute("newsSourceName", TextNews1.getNewsSourceName());
		model.addAttribute("entryDate", TextNews1.getEntryDate());

		model.addAttribute("commentCategory", 1);
		model.addAttribute("programType", programType);
		String programTypeName = textNewsServiceImpl.selectProgramTypeName(programType);
		model.addAttribute("programTypeName", programTypeName);

		return "textnews/textnewsdetail";
	}

	@RequestMapping(params = "action=validateIsCommend")
	public void validateIsCommend(HttpServletRequest request, @RequestParam("newsID") String newsID, @RequestParam("flag") String flag,HttpServletResponse response) {
		String msg = "";
		NewsCommendInfo newsCommendInfo = new NewsCommendInfo();
		newsCommendInfo.setNewsID(newsID);

		IPHelper iphelper = new IPHelper();
		newsCommendInfo.setCommendUserIP(iphelper.getIPAddress(request));
		int iCount = textNewsServiceImpl.getNewsCommendInfoCount(newsCommendInfo);
		if (iCount > 0) {
			msg = "1";
		} else {
			TextNews textNews = new TextNews();
			textNews.setNewsID(newsID);
			if (flag.equals("1")) {
				textNewsServiceImpl.updateEffective(textNews);
			} else if (flag.equals("2")) {
				textNewsServiceImpl.updateEmotional(textNews);
			} else if (flag.equals("3")) {
				textNewsServiceImpl.updateHappy(textNews);
			} else if (flag.equals("4")) {
				textNewsServiceImpl.updateBoring(textNews);
			} else if (flag.equals("5")) {
				textNewsServiceImpl.updateSad(textNews);
			} else if (flag.equals("6")) {
				textNewsServiceImpl.updateAngry(textNews);
			} else if (flag.equals("7")) {
				textNewsServiceImpl.updateNonsense(textNews);// 超扯 新版美工提供頁面沒有
			} else {
				textNewsServiceImpl.updateFear(textNews); // 害怕 新版美工提供頁面沒有
			}
			textNewsServiceImpl.insertNewsCommendInfo(newsCommendInfo);
			msg = "0";
		}
		response.setCharacterEncoding("UTF-8");
		try {
			response.getWriter().write(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * by H2603045
	 * @param request
	 * @param pageIndex
	 * @param programType
	 * @param model
	 * @return
	 */
	@RequestMapping(params = "action=getSearchNewsList")
	public String getSearchNewsList(HttpServletRequest request, HttpServletResponse response) {
			
		String word = request.getParameter("word") == null ? "" : request.getParameter("word");
		String pageIndex = request.getParameter("curpage") == null ? "" : request.getParameter("curpage");
		String programType = request.getParameter("programType") == null ? "" : request.getParameter("programType");
		String orderby = request.getParameter("orderby") == null ? "" : request.getParameter("orderby");
		String startTime = request.getParameter("startTime") == null ? "" : request.getParameter("startTime");
		String endTime = request.getParameter("endTime") == null ? "" : request.getParameter("endTime");
		String wordPosition = request.getParameter("wordPosition") == null ? "" : request.getParameter("wordPosition");


		int curpage ;
//		int curpage = (Integer.parseInt(pageIndex)-1)*pageSize;
		try{
			curpage = (Integer.parseInt(pageIndex)-1)*pageSize;
		}catch(Exception e){
			curpage = 1;
		}

		try {
			word = java.net.URLDecoder.decode(word, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		if(programType.isEmpty()){
			programType = "-1";
		}
		if(word.equals("请输入关键字")) {
			word = "";
		}
		
		List<KeyWordNews> trafficNewsList = searchEngineService.getNewsSearchInfo(word, curpage, pageSize,programType,orderby,startTime,endTime,wordPosition);
		for(int i = 0; i < trafficNewsList.size(); i++){
			if("杂志".equals(trafficNewsList.get(i).getType())){
				trafficNewsList.get(i).setUrl("magazines/" + trafficNewsList.get(i).getProgram_type());
			} else if("图片".equals(trafficNewsList.get(i).getType())){
				trafficNewsList.get(i).setUrl("photoNews/" + trafficNewsList.get(i).getId());
			} else if("视频".equals(trafficNewsList.get(i).getType())){
				trafficNewsList.get(i).setUrl("videoNews/" + trafficNewsList.get(i).getId());
			} else if("领导信息".equals(trafficNewsList.get(i).getType())){
				trafficNewsList.get(i).setUrl("leaders/" + trafficNewsList.get(i).getId() + "_1");
			} else if("访谈信息".equals(trafficNewsList.get(i).getType())){
				trafficNewsList.get(i).setUrl("interviews/" + trafficNewsList.get(i).getId());
			} else{ // 新闻
				trafficNewsList.get(i).setUrl("articles/" + trafficNewsList.get(i).getProgram_type() + "/" + trafficNewsList.get(i).getId());
			}
		}
		
		int trafficNewsCount = 0;
		if(trafficNewsList.size() > 0) trafficNewsCount = trafficNewsList.get(0).getCount();
		// 关键词变红
		String reg = "[^\u4e00-\u9fa5]";
		String[] keyList= word.split(" ");
		for (KeyWordNews WordNews : trafficNewsList) {
			for (int i = 0; i < keyList.length; i++) {
				WordNews.setTitle(WordNews.getTitle().replace(keyList[i].replaceAll(reg, ""), 
						"<font style='color:#c00;' >"+keyList[i].replaceAll(reg, "")+"</font>"));
				WordNews.setContent(WordNews.getContent().replace(keyList[i].replaceAll(reg, ""),
						"<font style='color:#c00;' >"+keyList[i].replaceAll(reg, "")+"</font>"));
			}
		}

		request.setAttribute("word", word);
		request.setAttribute("commentCategory", 1);
		request.setAttribute("pagesize", pageSize);
		request.setAttribute("pageIndex", pageIndex);
		request.setAttribute("count", trafficNewsCount);
		request.setAttribute("programType", programType);
		request.setAttribute("orderby", orderby);
		request.setAttribute("startTime", startTime);
		request.setAttribute("endTime", endTime);
		request.setAttribute("wordPosition", wordPosition);
		request.setAttribute("trafficNewsList", trafficNewsList);
		return "textnews/searchnewslist";
	}
	
	/**
	 * 
	 * @param programType
	 *            ：对应关系表TRAFFIC_PAGE_NEWS_REF_T栏位page_id,
	 *            而不是新闻表TRAFFIC_TEXT_NEWS_INFO_T栏位program_type
	 */
	@RequestMapping(params = "action=getTextSearchDetail")
	public String getTextSearchDetail(@RequestParam("newsID") String newsID, @RequestParam("programType") String programType, HttpServletRequest request, Model model) {
		if (programType == null || "".equals(programType)) {
			programType = "010101";
		}
		TextNews textNews = new TextNews();
		textNews.setNewsID(newsID);
		TextNews TextNews1 = textNewsServiceImpl.getTextNewsDetail(textNews);
		String word = request.getParameter("keyWord");
		String wordValue = "";
		String keyWord = "";
		System.out.println(word);
		
		WordSegmentTool wordSegmentTool= new WordSegmentTool();
		if(StringUtils.isNotBlank(word)){
			try {
				wordValue = new String(word.getBytes("ISO8859_1"), "UTF-8");
				keyWord = wordSegmentTool.segmentWord(wordValue);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println(keyWord);

		UserReadRecord userReadRecord = new UserReadRecord();
		userReadRecord.setNewsID(newsID);
		IPHelper iphelper = new IPHelper();
		userReadRecord.setUserIP(iphelper.getIPAddress(request));
		textNewsServiceImpl.insertReadRecord(userReadRecord);

		/* 获取评论List */
		String newsId = TextNews1.getNewsID();
		NewsComment newsComment = new NewsComment();
		newsComment.setNEWS_ID(newsId);
		newsComment.setCOMMENT_CATEGORY("1");
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
		model.addAttribute("commentCategory", 1);
		model.addAttribute("newsCommentList", treelist);
		model.addAttribute("readRecordCount", TextNews1.getReadRecordCount());
		model.addAttribute("newsTitle", TextNews1.getNewsTitle());
		model.addAttribute("newsKeyWord", TextNews1.getNewsKeyWord());
		model.addAttribute("newsID", TextNews1.getNewsID());
		model.addAttribute("newsContent", TextNews1.getNewsContent() == null ? TextNews1.getNewsContent() : com.foxconn.util.ServerPathConvet.decodeConvertContent(TextNews1.getNewsContent()));
		model.addAttribute("subNewsTitle", TextNews1.getSubNewsTitle());
		model.addAttribute("newsSource", String.valueOf(TextNews1.getNewsSource()));
		model.addAttribute("newsSourceName", TextNews1.getNewsSourceName());
		model.addAttribute("entryDate", TextNews1.getEntryDate());
		model.addAttribute("keyWord", keyWord);
		model.addAttribute("programType", programType);
		//禁止复制
		model.addAttribute("isBan",TextNews1.getIsBan());
		
		String programTypeName = textNewsServiceImpl.selectProgramTypeName(programType);
		model.addAttribute("programTypeName", programTypeName);
		return "textnews/textsearchdetail";

	}
	
	/* 新闻评论 20160812 */
	@RequestMapping(params="action=newsCommentSave")
	public void NewsCommentSave(HttpServletRequest request, HttpServletResponse response) {
		String msg="";	
		String validateCode = request.getParameter("validateBox");
		if(validateCode.equals(request.getSession().getAttribute("NEWSCOMMENT"))){
			String NEWS_ID = request.getParameter("NEWS_ID") == null ? "" : request.getParameter("NEWS_ID");
			String COMMENT_CONTENT = request.getParameter("COMMENT_CONTENT") == null ? "" : request.getParameter("COMMENT_CONTENT");
			String COMMENT_IPADDRS = new IPHelper().getIPAddress(request);
			String COMMENT_TIME = DateUtil.getFormatDateString(Calendar.getInstance().getTime());
			String COMMENT_CATEGORY = request.getParameter("COMMENT_CATEGORY");
			
			System.out.println("IP地址===》"+COMMENT_IPADDRS);
			NewsComment newsComment = new NewsComment();
			newsComment.setNEWS_ID(NEWS_ID);
			newsComment.setCOMMENT_CONTENT(COMMENT_CONTENT);
			newsComment.setCOMMENT_IPADDRS(COMMENT_IPADDRS);
			newsComment.setCOMMENT_TIME(COMMENT_TIME);
			newsComment.setCOMMENT_CATEGORY(COMMENT_CATEGORY);
			
			try {
				newsCommentServiceImpl.addNewsComment(newsComment);
				msg = "发表成功，管理员会马上审核!";
			} catch (Exception e1) {
				msg = "发表失败，请稍后再试!";
				e1.printStackTrace();
				
			}
		}else{
			msg="验证码不正确，请重新输入!";
		}
		response.setCharacterEncoding("utf-8");
		try {
			response.getWriter().write(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}