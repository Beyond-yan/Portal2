package com.foxconn.controller.questionaire;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.foxconn.pojo.Communication.Topic;
import com.foxconn.pojo.questionaire.SURVEY_OPTION;
import com.foxconn.pojo.questionaire.SURVEY_QUESTION;
import com.foxconn.pojo.questionaire.SURVEY_QUESTIONAIRE;
import com.foxconn.pojo.questionaire.SURVEY_ANSWER;
import com.foxconn.service.index.IndexService;
import com.foxconn.service.questionaire.QuestionaireService;
import com.foxconn.util.IPHelper;

@Controller
@RequestMapping("Questionaire.do")
public class QuestionaireController {
	@Value("${portal.page.size}")
	private int pageSize;

	@Autowired
	@Resource(name = "questionaireServiceImpl")
	private QuestionaireService questionaireService;
	
	@Autowired
	@Resource(name = "indexServiceImpl")
	private IndexService indexServiceImpl;

	@RequestMapping(params = "action=questionaireList")
	public String questionaireList(HttpServletRequest request,
			HttpServletResponse response, Model model) {

		String QTETitle = request.getParameter("QTETitle") == null ? "" : request.getParameter("QTETitle"); 

		String strCurpage = request.getParameter("curpage");
		int curpage;
//		if (null == strCurpage || "".equals(strCurpage.trim())){
//			curpage = 1;
//		}else{
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

		SURVEY_QUESTIONAIRE obj = new SURVEY_QUESTIONAIRE();
		obj.setQTE_TITLE(QTETitle);
		/*
		 * obj.setCurPage(curpage); obj.setPageSize(pageSize);
		 */
		List<SURVEY_QUESTIONAIRE> list = questionaireService
				.selQuestionaire(obj);
		int cnt = questionaireService.getQuestionaireCount(obj);

		model.addAttribute("QTETitle", QTETitle);
		model.addAttribute("count", cnt);
		model.addAttribute("list", list);
		model.addAttribute("pagesize", pageSize);
		model.addAttribute("curpage", curpage);

		return "questionaire/questionaireList";
	}

//	@RequestMapping (params = "action=qTest")
//	public void questionaireSts() {
//		int i = 1;
//		while (i <= 5000) {
//			questionaireService.selStatisticResultList("A3031B897B6C40C99AC72F0994699DF2");
//			System.out.println("执行问卷SQL第" + (i++) + "次");
//		}
//	}
	
	// 暂时屏蔽 20170510
//	@RequestMapping(params = "action=questionaireSts")
//	public String questionaireSts(HttpServletRequest request,
//			HttpServletResponse response, Model model) {
//		// 查询
//		String QTEID = request.getParameter("QTEID") == null ? "" : request.getParameter("QTEID");		
//	
//		List<SURVEY_ANSWER> statisticResultList = questionaireService
//				.selStatisticResultList(QTEID);
//
//		SURVEY_QUESTIONAIRE obj = new SURVEY_QUESTIONAIRE();
//		obj.setQTE_ID(QTEID);
//		SURVEY_QUESTIONAIRE surveyQuestionaire = questionaireService
//				.selQuestionaireByID(obj);
//		int iCount = questionaireService.selRespondantCount(QTEID);
//		String strResultList = "";
//
//		String QTE_QTN_ID = "";
//		int iSelectOpt = 65;
//		char charSelectOpt = (char) iSelectOpt;
//		int iQuestionItem = 1;
//		for (int i = 0; i < statisticResultList.size(); i++) {
//			if (QTE_QTN_ID.equals(statisticResultList.get(i).getQTE_QTN_ID())) {
//				iSelectOpt = iSelectOpt + 1;
//				charSelectOpt = (char) iSelectOpt;
//
//			} else {
//				iSelectOpt = 65;
//				charSelectOpt = (char) iSelectOpt;
//				if (i > 0) {
//					strResultList += "<br />";
//				}
//				strResultList = strResultList
//						+ "<li><table border='0' class='infoList'><tr><td> <b>"
//						+ String.valueOf(iQuestionItem) + "."
//						+ statisticResultList.get(i).getQTE_QTN_DES();
//				strResultList += "</b></td></tr></table></li>";
//				QTE_QTN_ID = statisticResultList.get(i).getQTE_QTN_ID();
//				iQuestionItem = iQuestionItem + 1;
//			}
//			if (iCount > 0) {
//				strResultList += "<li><table border='0' class='infoList'><tr><td width='55px'>选项";
//				strResultList = strResultList + charSelectOpt + "："; //
//				strResultList = strResultList
//						+ "</td><td width='305px' style='WORD-BREAK: break-all'>";
//				strResultList += statisticResultList.get(i).getQTE_OPN_DES();
//				strResultList += "</td><td width='250px'><div style='height: 16px; line-height: 16px; border: 0px #000 solid;background-color: #318df4;text-align:right;";
//				strResultList += "width:";
//				strResultList += getDivWidth(
//						Integer.valueOf(statisticResultList.get(i)
//								.getSUM_OPN_ID()), iCount, 250.0);
//				strResultList += "px;'>";//
//				strResultList = strResultList
//						+ "&nbsp;</div></td><td style='text-align:right;'>";
//				strResultList += formatBaiFenBi(
//						Integer.parseInt(statisticResultList.get(i)
//								.getSUM_OPN_ID()), iCount); //
//				strResultList = strResultList + "["
//						+ statisticResultList.get(i).getSUM_OPN_ID() + "/"
//						+ iCount + "]</td></tr></table></li>";
//			} else {
//				strResultList += "<li><table  class='infoList' border='0'><tr><td width='55px'>选项";
//				strResultList = strResultList + charSelectOpt + "："; //
//				strResultList = strResultList
//						+ "</td><td width='45%'style='WORD-BREAK: break-all'>";
//				strResultList += statisticResultList.get(i).getQTE_OPN_DES();
//				strResultList += "</td><td width='300px'>";
//				strResultList = strResultList
//						+ "&nbsp;</td><td style='text-align:right;'>";
//				strResultList += "0"; //
//				strResultList = strResultList + "</td></tr></table></li>";
//			}
//
//		}
//		model.addAttribute("strResultList", strResultList);
//		model.addAttribute("surveyQuestionaire", surveyQuestionaire);
//		model.addAttribute("iCount", iCount);
//		return "questionaire/questionaireSts";
//	}

//	private String formatBaiFenBi(int iPartCount, int iTotalCount) {
//		double dPTmp = (iPartCount * 100.0) / (iTotalCount);
//		return String.valueOf(new DecimalFormat("#.##%").format(dPTmp / 100));
//	}

	/**
	 * 動態div width
	 * 
	 * @param iPartCount
	 * @param iTotalCount
	 * @param dTotalDivWidth
	 * @return
	 */
	private String getDivWidth(int iPartCount, int iTotalCount,
			double dTotalDivWidth) {
		double dbTmp = (iPartCount * dTotalDivWidth) / (iTotalCount);
		String strTmp = String.valueOf(dbTmp);
		if (strTmp.indexOf(".") > -1) {
			int iTmp = Integer
					.valueOf(strTmp.substring(0, strTmp.indexOf(".")));

			if (Integer.valueOf(strTmp.substring(strTmp.indexOf(".") + 1,
					strTmp.indexOf(".") + 2)) > 5) {
				iTmp = iTmp + 1;
			}
			return String.valueOf(iTmp);
		} else {
			return strTmp;
		}
	}

	@RequestMapping(params = "action=questionList")
	public String questionList(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String QTEID = request.getParameter("QTEID");

		//added by Cube @140304
		if(null==QTEID||("").equals(QTEID)){
			SURVEY_QUESTIONAIRE questionaire = indexServiceImpl
					.getIndexQuestionaire();
			QTEID=questionaire.getQTE_ID();
		}
		
		SURVEY_QUESTIONAIRE questionaire = new SURVEY_QUESTIONAIRE();
		questionaire.setQTE_ID(QTEID);
		questionaire = questionaireService.selQuestionaireByID(questionaire);

		int number = questionaireService.selRespondantCount(QTEID);

		SURVEY_QUESTION question = new SURVEY_QUESTION();
		question.setQTE_ID(QTEID);
		List<SURVEY_QUESTION> questionList = questionaireService
				.selQuestion(question);

		SURVEY_OPTION option = new SURVEY_OPTION();
		option.setQTE_ID(QTEID);
		List<SURVEY_OPTION> optionList = questionaireService.selOption(option);
		
		//修改#LOCALSERVER#
		String content = questionaire.getQTE_DES();
		if(content != null && !content.equals("")){
			content= com.foxconn.util.ServerPathConvet.decodeConvertContent(questionaire.getQTE_DES());
			questionaire.setQTE_DES(content);
		}
		
		model.addAttribute("QTEID", QTEID);
		model.addAttribute("questionaire", questionaire);
		model.addAttribute("number", number);
		model.addAttribute("questionList", questionList);
		model.addAttribute("optionList", optionList);

		Cookie[] cookies = request.getCookies();
		if(cookies == null){
			//model.addAttribute("msg", "问卷已过期！");
		} else {
			for (Cookie cookie : cookies) {
				if ("msg".equalsIgnoreCase(cookie.getName())) {
					String cookieValue = cookie.getValue() == null ? "" : cookie.getValue();
					if ("success".equalsIgnoreCase(cookieValue))
						model.addAttribute("msg", "提交成功！");
					else if ("fail".equalsIgnoreCase(cookieValue))
						model.addAttribute("msg", "提交失败！");
					else if ("expires".equalsIgnoreCase(cookieValue))
						model.addAttribute("msg", "问卷已过期！");
					cookie.setMaxAge(0);
					response.addCookie(cookie);
					break;
				}
			}
		}

		return "questionaire/questionList";
	}

	@RequestMapping(params = "action=saveAnswers")
	public void saveAnswer(HttpServletRequest request,
			HttpServletResponse response, Model model) throws IOException {

		Map<String, String[]> mapPara = new HashMap<String, String[]>();
		mapPara.putAll(request.getParameterMap());
		if(mapPara==null||mapPara.size()==0||mapPara.get("QTEID")==null){
			return;
		}
		String QTEID = mapPara.get("QTEID")[0];
		
		String msg = "";

		SURVEY_QUESTIONAIRE questionaire = new SURVEY_QUESTIONAIRE();
		questionaire.setQTE_ID(QTEID);
		questionaire = questionaireService.selQuestionaireByID(questionaire);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
		Date nowdate = new java.util.Date();
		try {
			Date expires = sdf.parse(questionaire.getQTE_EXPIRES()
					+ " 23:59:59");
			if (nowdate.before(expires)) {
				IPHelper ipHelper = new IPHelper();
				String ip = ipHelper.getIPAddress(request);
				if (mapPara.containsKey("QTEID"))
					mapPara.remove("QTEID");
				if (mapPara.containsKey("action"))
					mapPara.remove("action");
				questionaireService.saveAnswers(ip, QTEID, mapPara);
				msg = "success";

			} else
				msg = "expires";

		} catch (ParseException e) {
			msg = "fail";
		}

		Cookie cookie = new Cookie("msg", msg);
		cookie.setSecure(true);
		response.addCookie(cookie);
		response.sendRedirect(request.getContextPath() + "/questionaires/"
				+ QTEID + ".html");
	}
	
	/**
	 * by H2603045
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(params = "action=getQuestionaireList")
	public String getQuestionaireList(HttpServletRequest request, Model model) {
		String strCurpage = request.getParameter("curpage");
		int curpage;
//		if (null == strCurpage || "".equals(strCurpage.trim())){
//			curpage = 1;
//		}else{
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
		List<SURVEY_QUESTIONAIRE> list = questionaireService.getQuestionaireList(curpage, pageSize);
		int count = questionaireService.getQuestionaireCountNoDel();
		
		model.addAttribute("list", list);
		model.addAttribute("count", count);
		model.addAttribute("pagesize", pageSize);
		model.addAttribute("curpage", curpage);
		
		return "questionaire/questionaireList";
	}
	
}
