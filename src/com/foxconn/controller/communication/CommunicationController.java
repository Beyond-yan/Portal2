package com.foxconn.controller.communication;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.foxconn.pojo.Communication.Opinion;
import com.foxconn.pojo.Communication.Topic;
import com.foxconn.service.Communication.CommunicationService;
import com.foxconn.util.IPHelper;


@Controller
@RequestMapping("communication.do")
public class CommunicationController {

	@Value("${portal.page.size}")
	private int pageSize;

	@Value("${opinion.page.size}")
	private int opinionPageSize;

	@Autowired
	@Resource(name = "communicationServiceImpl")
	private CommunicationService communicationServiceImpl;

	@RequestMapping(params = "action=getTopicList")
	public String getTopicList(HttpServletRequest request, Model model) {

		String strCurpage = request.getParameter("curpage");
		int curpage;
//		if (null == strCurpage || "".equals(strCurpage.trim()))
//			curpage = 1;
//		else
//			try{
//				curpage = Integer.parseInt(strCurpage);
//			}catch(Exception e){
//				curpage = 1;
//			}
		
		try{
			curpage = Integer.parseInt(strCurpage);
		}catch(Exception e){
			curpage = 1;
		}

		List<Topic> list = communicationServiceImpl.getTopicList(curpage,
				pageSize);
		int cnt = communicationServiceImpl.getTopicCount();

		model.addAttribute("list", list);
		model.addAttribute("count", cnt);
		model.addAttribute("pagesize", pageSize);
		model.addAttribute("curpage", curpage);

		return "communication/topicList";
	}

	@RequestMapping(params = "action=getTopicDetails")
	public String getTopicDetails( HttpServletRequest request, Model model) {

		String topicID = request.getParameter("topicID") == null ? "" : request.getParameter("topicID");
		String strCurpage = request.getParameter("curpage");
		
		String[] topicIDstrCurpage = topicID.split("_");
		if(topicIDstrCurpage.length > 1){
			topicID = topicIDstrCurpage[0];
			strCurpage = topicIDstrCurpage[1];
		}
		
		// 征集主题
		Topic topic = communicationServiceImpl.getTopicDetails(topicID);
		//2014/08/13
		topic.setOPTION_CONTENT(com.foxconn.util.ServerPathConvet.decodeConvertContent(topic.getOPTION_CONTENT()));
		
		int curpage;
//		if (null == strCurpage || "".equals(strCurpage.trim()))
//			curpage = 1;
//		else
//			try{
//				curpage = Integer.parseInt(strCurpage);
//			}catch(Exception e){
//				curpage = 1;
//			}
		try{
			curpage = Integer.parseInt(strCurpage);
		}catch(Exception e){
			curpage = 1;
		}

		// 意见列表
		List<Opinion> list = communicationServiceImpl.getOpinionList(topicID,
				curpage, opinionPageSize);
		Map<String, String> map = new HashMap<String, String>();
		map.put("topicID", topicID);
		map.put("status", "1");
		int cnt = communicationServiceImpl.getOpinionCount(map);
		map.put("status", null);
		int participantsCnt = communicationServiceImpl.getOpinionCount(map);

		model.addAttribute("topic", topic);
		model.addAttribute("participantsCnt", participantsCnt);
		model.addAttribute("list", list);
		model.addAttribute("count", cnt);
		model.addAttribute("pagesize", opinionPageSize);
		model.addAttribute("curpage", curpage);

		return "communication/topicDetails";
	}

	@RequestMapping(params = "action=insertOpinion")
	public void insertOpinion(HttpServletRequest request,
			@RequestParam("topicID") String topicID,
			@RequestParam("userName") String userName,
			@RequestParam("userTel") String userTel,
			@RequestParam("userDept") String userDept,
			@RequestParam("userMail") String userMail,
			@RequestParam("userCommend") String userCommend,
			HttpServletResponse response) {
		
		String modifyUser = request.getParameter("modifyUser") == null ? "" : request.getParameter("modifyUser");
		String validateCode = request.getParameter("validateCode");
		
		if( null != validateCode && validateCode.equals(request.getSession().getAttribute("NEWSCOMMENT"))){
			IPHelper ipHelper = new IPHelper();
			String ip = ipHelper.getIPAddress(request);
			Opinion opinion = new Opinion();
			opinion.setID(java.util.UUID.randomUUID().toString());
			try {
				opinion.setOPTION_ID(java.net.URLDecoder.decode(topicID, "UTF-8"));
				opinion.setUSER_NAME(java.net.URLDecoder.decode(userName, "UTF-8"));
				opinion.setUSER_DEPT(java.net.URLDecoder.decode(userDept, "UTF-8"));
				opinion.setUSER_MAIL(java.net.URLDecoder.decode(userMail, "UTF-8"));
				opinion.setUSE_COMMEND(java.net.URLDecoder.decode(userCommend, "UTF-8"));
				opinion.setUSER_TEL(java.net.URLDecoder.decode(userTel, "UTF-8"));
				opinion.setUSER_IP(java.net.URLDecoder.decode(ip, "UTF-8"));
				opinion.setMODIFY_USER(modifyUser);
				
				communicationServiceImpl.insertOpinion(opinion);
				response.setCharacterEncoding("UTF-8");
				response.getWriter().write("{\"status\":"+"0}");	
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}else{
			response.setCharacterEncoding("UTF-8");
			try {
				response.getWriter().write("{\"status\":"+"1}");		
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}