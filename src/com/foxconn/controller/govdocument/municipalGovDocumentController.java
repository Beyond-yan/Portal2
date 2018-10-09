package com.foxconn.controller.govdocument;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tools.ant.taskdefs.condition.Http;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.foxconn.pojo.govdocument.municipalGovDocument;
import com.foxconn.service.impl.govdocument.municipalGovDocumentServiceImpl;
import com.foxconn.util.DateUtil;
import com.foxconn.util.JsonUtil;
import com.foxconn.util.ServerPathConvet;

@Controller
@RequestMapping("/govDocument.do")
public class municipalGovDocumentController {

	@Autowired
	@Resource(name = "municipalGovDocumentServiceImpl")
	private municipalGovDocumentServiceImpl govDocumentServiceImpl;
	
	@RequestMapping(params = "action=jumpToGocDocumentList")
	public String jumpToGocDocument(HttpServletRequest request, Model model){
		
		Integer count = null;
		municipalGovDocument govDocument = new municipalGovDocument();
		List<municipalGovDocument> govDocumentList = 
								new ArrayList<municipalGovDocument>();
		String pageIndex = request.getParameter("pageIndex");
		String pageSize = request.getParameter("pageSize");
		if(pageIndex == null){
			pageIndex = "1";
		} if(pageSize == null){
			pageSize = "12";
		}
		govDocument.setPageIndex(pageIndex);
		govDocument.setPageSize(pageSize);
		String publishDate = DateUtil.getFormatDateString(new Date());
		govDocument.setPUBLISH_TIME(publishDate);
		try{
			count = govDocumentServiceImpl.getMunicipalGovDocumentCount(govDocument);
			govDocumentList = govDocumentServiceImpl.getMunicipalGovDocument(govDocument);
		}catch(Exception e){
			e.printStackTrace();
		}
		//处理日期
		for(int i=0; i<govDocumentList.size(); i++){
			String dateStr = govDocumentList.get(i).getPUBLISH_TIME();
			String publishTime = DateUtil.getDateString(DateUtil.getDate(dateStr,"yyyy.MM.dd"));
			govDocumentList.get(i).setPUBLISH_TIME(publishTime);
		}
		model.addAttribute("govDocumentList", govDocumentList);
		model.addAttribute("pagesize", pageSize);
		model.addAttribute("curpage", pageIndex);
		model.addAttribute("count", count);
		return "govdocument/govdocumentlist";
	}
	
	@RequestMapping(params = "action=getGovDocumentList")
	public void getGovDocumentList(HttpServletRequest request, HttpServletResponse response){
		
		String pageSize = request.getParameter("pageSize");
		String pageIndex = request.getParameter("pageIndex");
		municipalGovDocument govDocument = new municipalGovDocument();
		if(pageIndex == null){
			pageIndex = "1";
		} if(pageSize == null){
			pageSize = "6";
		}
		govDocument.setPageIndex(pageIndex);
		govDocument.setPageSize(pageSize);
		String publishDate = DateUtil.getFormatDateString(new Date());
		govDocument.setPUBLISH_TIME(publishDate);
		
		List<municipalGovDocument> govDocumentList = 
								new ArrayList<municipalGovDocument>();
		try{
			govDocumentList = 
					govDocumentServiceImpl.getMunicipalGovDocument(govDocument);
		}catch(Exception e){
			e.printStackTrace();
		}
		response.setCharacterEncoding("UTF-8");
		try {
			response.getWriter().write(
					JsonUtil.list2json(govDocumentList));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(params = "action=queryGovDocumentDetail")
	public String queryGocDocumentDetail(HttpServletRequest request, Model model) throws Exception{
		
		String documentId = request.getParameter("documentId");
		municipalGovDocument govDocument = new municipalGovDocument();
		if(documentId != null){
			govDocument.setDOCUMENT_ID(documentId);
		}
		try{
			govDocument = govDocumentServiceImpl.getGovDocumentForID(govDocument);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		//修改#LOCALSERVER#
		String Content= ServerPathConvet.decodeConvertContent(
				govDocument.getDOCUMENT_CONTENT());
		govDocument.setDOCUMENT_CONTENT(Content);
		
		model.addAttribute("govDocument", govDocument);
		return "govdocument/govdocumentdetail";
	}
	
	@RequestMapping(params = "action=govDocumentSubList")
	public String govDocumentSubList(HttpServletRequest request, Model model){
		
		List<municipalGovDocument> govDocumentList = 
							new ArrayList<municipalGovDocument>();
		List<municipalGovDocument> govDocumentListS = 
							new ArrayList<municipalGovDocument>();
							
		municipalGovDocument govDocument = new municipalGovDocument();
		municipalGovDocument govDocumentS = new municipalGovDocument();
		String publishDate = DateUtil.getFormatDateString(new Date());
		
		govDocument.setPUBLISH_TIME(publishDate);
		govDocumentS.setPUBLISH_TIME(publishDate);
		govDocument.setPageIndex("1");
		govDocument.setPageSize("10");
		govDocumentS.setPageIndex("2");
		govDocumentS.setPageSize("10");
		try{
			govDocumentList = govDocumentServiceImpl.getMunicipalGovDocument(govDocument);
			govDocumentListS = govDocumentServiceImpl.getMunicipalGovDocument(govDocumentS);
		}catch(Exception e){
			e.printStackTrace();
		}
		model.addAttribute("weekList", govDocumentList);
		model.addAttribute("monthList", govDocumentListS);
		return "common/govDocumentSubList";
	}
	
	@RequestMapping(params = "action=loadReadNumner")
	public void loadReadNumner(HttpServletRequest request,HttpServletResponse response, Model model){
		String readNumber = request.getParameter("readNumber") == null ? "" : request.getParameter("readNumber");
		String documentId = request.getParameter("documentId") == null ? "" : request.getParameter("documentId");
		
		String message="";
		if(readNumber != null && !readNumber.isEmpty()){
			municipalGovDocument govDocumnet = new municipalGovDocument();
			try{
				govDocumnet.setDOCUMENT_ID(documentId);
				govDocumnet.setREAD_NUMBER((Integer.valueOf(readNumber)+1));
			}catch(Exception e){
				message="失败";
				govDocumnet = null;
				return ;
			}
			
			try{
				govDocumentServiceImpl.updateReadNumber(govDocumnet);
				message="成功";
			}catch(Exception e){
				message="失败";
				e.printStackTrace();
				return ;
			}
		}
		response.setCharacterEncoding("UTF-8");
		try {
			response.getWriter().write(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
