package com.foxconn.controller.trafficconference;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.foxconn.pojo.index.PhotoInfo;
import com.foxconn.pojo.opencatalog.Catalog;
import com.foxconn.pojo.trafficNews.PhotosInfo;
import com.foxconn.pojo.trafficNews.TextNews;
import com.foxconn.pojo.trafficNews.UserReadRecord;
import com.foxconn.service.opencatalog.CatalogService;
import com.foxconn.service.trafficNews.TextNewsService;
import com.foxconn.service.trafficconference.Hyzt2018Service;
import com.foxconn.util.IPHelper;
import com.foxconn.util.JsonUtil;

/**
 * 2018年外网会议专题
 * @author GuoY
 * @date 2018年4月9日 下午3:44:47
 * @version V1.0
 */
@Controller
@RequestMapping("/hyzt2018.do")
public class Hyzt2018Controller {

	@Autowired
	@Resource(name = "catalogServiceImpl")
	private CatalogService catalogServiceImpl;

	@Resource(name = "textNewsServiceImpl")
	private TextNewsService textNewsServiceImpl;

	@Autowired
	private Hyzt2018Service hyzt2018Service;

	@RequestMapping(params = "action=index")
	public String jumpToGocDocument(HttpServletRequest request, Model model) {
		return "trafficconference/2018/index";
	}

	/**
	 * 获取首页新闻列表
	 * @param request
	 * @param reaponse
	 * @throws Exception 
	 */
	@RequestMapping(params = "action=mnews")
	public void getIndexMainNews(HttpServletRequest request, HttpServletResponse response) {

		List<Catalog> TextNews5 = new ArrayList<>();
		List<Catalog> TextNews6 = new ArrayList<>();
		List<PhotoInfo> photoList = new ArrayList<>();

		HashMap<String, Object> map = new HashMap<>();
		map.put("rownum", 5);
		map.put("newsStatus", 7);

		try {
			photoList = hyzt2018Service.getPhotos();
			map.put("programType", "0111010");
			TextNews6 = hyzt2018Service.getIndexNewsList(map);
			if (TextNews6.size() > 2) {
				TextNews5.add(TextNews6.get(0));
				TextNews5.add(TextNews6.get(1));
			} else {
				TextNews5 = TextNews6;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		response.setCharacterEncoding("UTF-8");
		try {
			response.getWriter().write("{\"TextNews5\":" + JsonUtil.list2json(TextNews5) + ",\"photoList\":" + JsonUtil.list2json(photoList) + "}");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取首页新闻列表
	 * @param request
	 * @param reaponse
	 * @throws Exception 
	 */
	@RequestMapping(params = "action=inews")
	public void getIndexNewsList(HttpServletRequest request, HttpServletResponse response) {

		List<Catalog> TextNews1 = new ArrayList<>();
		List<Catalog> TextNews3 = new ArrayList<>();
		List<Catalog> TextNews4 = new ArrayList<>();

		HashMap<String, Object> map = new HashMap<>();
		map.put("rownum", 5);
		map.put("newsStatus", 7);

		try {
			map.put("programType", "011106");
			TextNews1 = hyzt2018Service.getIndexNewsList(map);
			map.put("programType", "011108");
			TextNews3 = hyzt2018Service.getIndexNewsList(map);
			map.put("programType", "011109");
			TextNews4 = hyzt2018Service.getIndexNewsList(map);
		} catch (Exception e) {
			e.printStackTrace();
		}

		response.setCharacterEncoding("UTF-8");
		try {
			response.getWriter().write("{\"TextNews1\":" + JsonUtil.list2json(TextNews1) + ",\"TextNews3\":" + JsonUtil.list2json(TextNews3) + ",\"TextNews4\":"
					+ JsonUtil.list2json(TextNews4) + "}");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取文章列表
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(params = "action=list")
	public String jobsList(HttpServletRequest request, Model model) {

		String programType = request.getParameter("programType") == null ? "" : request.getParameter("programType");

		Catalog catalog = new Catalog();
		catalog.setId(programType);
		List<Catalog> catalogList = catalogServiceImpl.getCatalogList(catalog);
		Catalog topmenu1 = catalogServiceImpl.gettopmenu(catalog);
		String topmenu = topmenu1.getName();
		model.addAttribute("topName", topmenu);
		model.addAttribute("programType", programType);
		model.addAttribute("catalogList", catalogList);

		return "trafficconference/2018/newsList";
	}

	/**
	 * 获取文章详情
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(params = "action=detail")
	public String newsDetail(HttpServletRequest request, Model model) {

		String newsID = request.getParameter("newsID") == null ? "" : request.getParameter("newsID");
		String programType = request.getParameter("programType") == null ? "" : request.getParameter("programType");

		// 访问量
		UserReadRecord userReadRecord = new UserReadRecord();
		userReadRecord.setNewsID(newsID);
		IPHelper iphelper = new IPHelper();
		userReadRecord.setUserIP(iphelper.getIPAddress(request));
		textNewsServiceImpl.insertReadRecord(userReadRecord);

		TextNews textNews = new TextNews();
		textNews.setNewsID(newsID);
		TextNews TextNewS = textNewsServiceImpl.getTextNewsDetail(textNews);

		Catalog catalog = new Catalog();
		catalog.setId(programType);
		Catalog topNenu = catalogServiceImpl.gettopmenu(catalog);

		model.addAttribute("programType", programType);
		model.addAttribute("topName", topNenu.getName());
		model.addAttribute("newsID", TextNewS.getNewsID());
		model.addAttribute("newsTitle", TextNewS.getNewsTitle());
		model.addAttribute("entryDate", TextNewS.getEntryDate());
		model.addAttribute("readRecordCount", TextNewS.getReadRecordCount());
		model.addAttribute("newsSourceName", TextNewS.getNewsSourceName());
		model.addAttribute("newsContent", com.foxconn.util.ServerPathConvet.decodeConvertContent(TextNewS.getNewsContent()));

		return "trafficconference/2018/newsDetail";
	}

	/**
	 * 获取图片列表
	 * @param request
	 * @param model
	 */
	@ResponseBody
	@RequestMapping(params = "action=plist")
	public List<PhotosInfo> getPhotoList(HttpServletRequest request, HttpServletResponse response) {
		List<PhotosInfo> photos = new ArrayList<PhotosInfo>();
		try {
			photos = hyzt2018Service.getPictureList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return photos;
	}
}
