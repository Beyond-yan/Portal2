package com.foxconn.controller.opencatalog;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.foxconn.util.LocalPropertyPhase;
import com.foxconn.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.foxconn.pojo.opencatalog.*;
import com.foxconn.pojo.trafficNews.TextNews;
import com.foxconn.service.opencatalog.*;
import com.foxconn.service.trafficNews.TextNewsService;
import com.sun.xml.internal.fastinfoset.Encoder;

/*
 * rewrited by Cube @130905
 * */
@Controller
@RequestMapping("/opencatalog.do")
public class OpenCatalogController {

	@Autowired
	@Resource(name = "catalogServiceImpl")
	private CatalogService catalogServiceImpl;
	@Resource(name = "textNewsServiceImpl")
	private TextNewsService textNewsServiceImpl;

	@RequestMapping(params = "action=initPublishCatalog")
	public String initPublishCatalog(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String initURL = request.getParameter("initURL");
			
		//网站导航跳转参数
		String jumpLabel = request.getParameter("jumpLabel") == null ? "" : request.getParameter("jumpLabel");
		if (null != initURL && !initURL.trim().equals("")) {

			initURL = initURL + ".html";
			// System.out.print("initURL:" + initURL);
			model.addAttribute("initURL", initURL);
		} else {
			Catalog initcatalog = catalogServiceImpl.getInitCatelogDetail();
			model.addAttribute("initURL", initcatalog.getUrl());
		}
		
		model.addAttribute("jumpLabel", jumpLabel);
		return "opencatalog/openCatalog";
	}
	
	//added by Cube @131227
	@RequestMapping(params = "action=notice")
	public String getNotice(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String initURL = request.getParameter("initURL");
		if (null != initURL && !initURL.trim().equals("")) {

			initURL = initURL + ".html";
			// System.out.print("initURL:" + initURL);
			System.out.println("initUrl:"+initURL);
			initURL = initURL.replaceAll( "&", "&amp;" );
			initURL = initURL.replaceAll( "\"", "&quot;" );
			initURL = initURL.replaceAll( "<", "&lt;" );
			initURL = initURL.replaceAll( ">", "&gt;" );
			
			model.addAttribute("initURL", initURL);
		} else {
			Catalog initcatalog = catalogServiceImpl.getInitCatelogDetail();
			model.addAttribute("initURL", initcatalog.getUrl());
		}
		return "opencatalog/notice";
	}

	@RequestMapping(params = "action=leftMenu")
	public String getCatalog(HttpServletRequest request,
			HttpServletResponse response) {
		List<Catalog> menuNodes = catalogServiceImpl.getCatalog();
		StringBuilder sb = new StringBuilder();
		String result = "";
		sb.append("[");
		String[] multiCatalog=LocalPropertyPhase.readData("portal.multiCatalog.id").split(",");

		if (menuNodes != null && menuNodes.size() > 0) {
			for (int i = 0; i < menuNodes.size(); i++) {
				String Id=menuNodes.get(i).getId();
				boolean skip=false;
				for(int j=0;j<multiCatalog.length;j++){
					if(StringUtils.isEmpty(multiCatalog[j])){
						continue;
					}else if(Id.contains(multiCatalog[j])&&!Id.equals(multiCatalog[j])){
						skip=true;
					}
				}
				if(skip){
					continue;
				}
				sb.append("{");
				sb.append("id:" + menuNodes.get(i).getId());// MenuId
				sb.append(",pId:" + menuNodes.get(i).getpId());// ParentId
				sb.append(",name:\"" + menuNodes.get(i).getName() + "\"");// MenuName
				
				if(menuNodes.get(i).getUrl().startsWith("http"))
				   sb.append(",url:\"" + menuNodes.get(i).getUrl() + "\"");
				else
					 sb.append(",url:\""+request.getContextPath()+menuNodes.get(i).getUrl()+"\"");
				
				sb.append(",target:\"" + menuNodes.get(i).getTarget() + "\"");
				sb.append(",open:\"" + menuNodes.get(i).getOpen() + "\"");
				sb.append("},");
			}
			result = sb.substring(0, sb.length() - 1);
			result += "]";
		}
//		System.out.println("---rsult:"+result);
		try {
			request.setAttribute("menuNodes",
					URLDecoder.decode(result, Encoder.UTF_8));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "opencatalog/leftMenu";
	}

	@RequestMapping(params = "action=getCatalogList")
	public String getCatalogList(HttpServletRequest request, Model model,
			HttpServletResponse response) {

		String Id = request.getParameter("Id") == null ? "" : request.getParameter("Id");

		Catalog catalog = new Catalog();
		catalog.setId(Id);

		String[] multiCatalog=LocalPropertyPhase.readData("portal.multiCatalog.id").split(",");
		for(int i=0;i<multiCatalog.length;i++){
			if(StringUtils.isEmpty(multiCatalog[i])){
				continue;
			}else if(Id!=null&&Id.contains(multiCatalog[i])){
				Catalog catalog1=new Catalog();
				catalog1.setId(multiCatalog[i]);
				List <Catalog> catalogMenuList=catalogServiceImpl.getMultiTopmenu(catalog1);//获取多个标题及其链接
				List<Catalog> catalogList;
				if(!Id.equals(multiCatalog[i])){
					catalogList= catalogServiceImpl.getCatalogList(catalog);//获取单个列表
				}else{
					catalogList= catalogServiceImpl.getLikeCatalogList(catalog);//获取该组合的所有列表
				}
				Catalog topmenu1 = catalogServiceImpl.gettopmenu(catalog);
				model.addAttribute("topName", topmenu1.getName());
				model.addAttribute("catalogMenuList", catalogMenuList);
				model.addAttribute("catalogList", catalogList);
				return "opencatalog/getMultiCatalogList";
			}
		}

		Catalog topmenu1 = catalogServiceImpl.gettopmenu(catalog);
		List<Catalog> catalogList = catalogServiceImpl.getCatalogList(catalog);

		String topmenu = topmenu1.getName();
		model.addAttribute("topName", topmenu);
		model.addAttribute("catalogList", catalogList);
		return "opencatalog/getCatalogList";
	}
	
	/**
	 * 法律法规列表请求方法
	 * @param request
	 * @param model
	 * @param response
	 * @return
	 */
	@RequestMapping(params = "action=getCatalogLists")
	public String getCatalogLists(HttpServletRequest request, Model model,
			HttpServletResponse response) {
		
		String Id = request.getParameter("Id") == null ? "" : request.getParameter("Id");
		
		Catalog catalog = new Catalog();
		catalog.setId(Id);
		List<Catalog> catalogList = catalogServiceImpl.getCatalogList(catalog);
		Catalog topmenu1 = catalogServiceImpl.gettopmenu(catalog);
		String topmenu = topmenu1.getName();
		model.addAttribute("topName", topmenu);
		model.addAttribute("catalogList", catalogList);
		return "opencatalog/getCatalogListToFlfg";
	}

	/**
	 * 法律法规详情页获取方法
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(params = "action=getTextNewsDetails")
	public String getTextNewsDetails(HttpServletRequest request, Model model) {
		String newsID = request.getParameter("newsID") == null ? "" : request.getParameter("newsID");
		
		TextNews textNews = new TextNews();
		textNews.setNewsID(newsID);
		TextNews TextNews1 = textNewsServiceImpl.getTextNewsDetail(textNews);

		model.addAttribute("topName", TextNews1.getNewsTitle());
		model.addAttribute("newsTitle", TextNews1.getNewsTitle());
		model.addAttribute("newsID", TextNews1.getNewsID());
		model.addAttribute("newsContent", com.foxconn.util.ServerPathConvet
				.decodeConvertContent(TextNews1.getNewsContent()));
		model.addAttribute("modifyDate", TextNews1.getEntryDate());
		
		return "opencatalog/CatalogDetailToFlfg";
	}
	
	
	
	@RequestMapping(params = "action=getTextNewsDetail")
	public String getTextNewsDetail(HttpServletRequest request, Model model) {
		String newsID = request.getParameter("newsID") == null ? "" : request.getParameter("newsID");
	
		TextNews textNews = new TextNews();
		textNews.setNewsID(newsID);
		TextNews TextNews1 = textNewsServiceImpl.getTextNewsDetail(textNews);
		
		model.addAttribute("topName", TextNews1.getNewsTitle());
		model.addAttribute("newsTitle", TextNews1.getNewsTitle());
		model.addAttribute("newsID", TextNews1.getNewsID());
		model.addAttribute("newsContent", com.foxconn.util.ServerPathConvet
				.decodeConvertContent(TextNews1.getNewsContent()));
		model.addAttribute("modifyDate", TextNews1.getEntryDate());
		
		/*首页交通政务信息 跳转到 公告公示详情页面的处理*/
		String news = request.getParameter("news") == null ? "" : request.getParameter("news");
		if(!"".equals(news)){
			return "opencatalog/CatalogDetailNew";
		}
		
		return "opencatalog/CatalogDetail";
	}
	

	
	
	//交通规费/交通信用/行政处罚
	@RequestMapping(params = "action=otherJump")
	public String TrafficFees(HttpServletRequest request,Model model){
		String type = request.getParameter("type");
		if(type != null && !"".equals(type)){
			if(type.equals("trafficFees")){
				return "opencatalog/trafficFees";
			}else if(type.equals("trafficCredit")){
				model.addAttribute("topName", "交通信用");
				return "opencatalog/trafficCredit";
			}else if(type.equals("trafficPunish")){
				model.addAttribute("topName", "行政处罚");
				return "opencatalog/trafficCredit";
			}
		} 
		return null;
	}
}