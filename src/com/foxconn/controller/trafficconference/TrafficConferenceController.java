/**
 * 交通会议专题控制器
 * 2017/3/9 GuoY
 */

package com.foxconn.controller.trafficconference;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.foxconn.util.IPHelper;
import com.foxconn.util.JsonUtil;
import com.foxconn.pojo.index.PhotoInfo;
import com.foxconn.pojo.opencatalog.Catalog;
import com.foxconn.pojo.trafficNews.TextNews;
import com.foxconn.pojo.trafficNews.UserReadRecord;
import com.foxconn.pojo.trafficconference.ProjectPhotos;
import com.foxconn.service.impl.trafficconference.TrafficConferenceImpl;
import com.foxconn.service.opencatalog.CatalogService;
import com.foxconn.service.trafficNews.TextNewsService;

@Controller
@RequestMapping("/hyzl.do")
public class TrafficConferenceController {

	@Autowired
	@Resource(name = "catalogServiceImpl")
	private CatalogService catalogServiceImpl;

	@Resource(name = "textNewsServiceImpl")
	private TextNewsService textNewsServiceImpl;

	@Resource(name = "trafficConferenceImpl")
	private TrafficConferenceImpl trafficConferenceImpl;

	private Map<String, String> map = new HashMap<>();

	private TrafficConferenceController() {
		map.put("011101", "011106");
		map.put("011102", "011107");
		map.put("011103", "011108");
		map.put("011104", "011109");
		map.put("011105", "0111010");
	}

	@RequestMapping(params = "action=index")
	public String jumpToGocDocument(HttpServletRequest request, Model model) {
		return "trafficconference/trafficConference";
	}

	/**
	 * 获取首页新闻列表
	 * @param request
	 * @param reaponse
	 * @throws Exception 
	 */
	@RequestMapping(params = "action=getIndexNewsList")
	public void getIndexNewsList(HttpServletRequest request, HttpServletResponse response) {

		List<Catalog> TextNews1 = new ArrayList<>();
		List<Catalog> TextNews3 = new ArrayList<>();
		List<Catalog> TextNews4 = new ArrayList<>();
		List<Catalog> TextNews5 = new ArrayList<>();
		List<PhotoInfo> photoList = new ArrayList<>();

		HashMap<String, Object> map = new HashMap<>();
		map.put("rownum", 5);
		map.put("newsStatus", 7);

		try {
			photoList = trafficConferenceImpl.getPhotos();
			map.put("programType", "011101");
			TextNews1 = trafficConferenceImpl.getIndexNewsList(map);
			map.put("programType", "011103");
			TextNews3 = trafficConferenceImpl.getIndexNewsList(map);
			map.put("programType", "011104");
			TextNews4 = trafficConferenceImpl.getIndexNewsList(map);
			map.put("programType", "011105");
			TextNews5 = trafficConferenceImpl.getIndexNewsList(map);
		} catch (Exception e) {
			e.printStackTrace();
		}

		response.setCharacterEncoding("UTF-8");
		try {
			response.getWriter()
					.write("{\"TextNews1\":" + JsonUtil.list2json(TextNews1) + ",\"TextNews3\":" + JsonUtil.list2json(TextNews3) + ",\"TextNews4\":"
							+ JsonUtil.list2json(TextNews4) + ",\"TextNews5\":" + JsonUtil.list2json(TextNews5) + ",\"photoList\":"
							+ JsonUtil.list2json(photoList) + "}");
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
	@RequestMapping(params = "action=newsList")
	public String jobsList(HttpServletRequest request, Model model) {

		String programType = request.getParameter("programType") == null ? "" : request.getParameter("programType");
		// 查询列表
		Catalog catalog = new Catalog();
		catalog.setId(programType);
		List<Catalog> catalogList = catalogServiceImpl.getCatalogList(catalog);
		// 查询菜单
		catalog.setId(map.get(programType));
		Catalog topmenu1 = catalogServiceImpl.gettopmenu(catalog);

		model.addAttribute("programType", programType);
		model.addAttribute("catalogList", catalogList);
		model.addAttribute("topName", topmenu1.getName());

		return "trafficconference/newsList";
	}

	/**
	 * 获取文章详情
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(params = "action=newsDetail")
	public String newsDetail(HttpServletRequest request, Model model) {

		String newsID = request.getParameter("newsID") == null ? "" : request.getParameter("newsID");
		String programType = request.getParameter("programType") == null ? "" : request.getParameter("programType");

		// 访问量
		UserReadRecord userReadRecord = new UserReadRecord();
		userReadRecord.setNewsID(newsID);
		IPHelper iphelper = new IPHelper();
		userReadRecord.setUserIP(iphelper.getIPAddress(request));
		textNewsServiceImpl.insertReadRecord(userReadRecord);
		// 查询详情
		TextNews textNews = new TextNews();
		textNews.setNewsID(newsID);
		TextNews TextNewS = textNewsServiceImpl.getTextNewsDetail(textNews);
		// 查询菜单
		Catalog catalog = new Catalog();
		catalog.setId(map.get(programType));
		Catalog topNenu = catalogServiceImpl.gettopmenu(catalog);

		model.addAttribute("programType", programType);
		model.addAttribute("topName", topNenu.getName());
		model.addAttribute("newsID", TextNewS.getNewsID());
		model.addAttribute("newsTitle", TextNewS.getNewsTitle());
		model.addAttribute("entryDate", TextNewS.getEntryDate());
		model.addAttribute("readRecordCount", TextNewS.getReadRecordCount());
		model.addAttribute("newsSourceName", TextNewS.getNewsSourceName());
		model.addAttribute("newsContent", com.foxconn.util.ServerPathConvet.decodeConvertContent(TextNewS.getNewsContent()));

		return "trafficconference/newsDetail";
	}

	/**
	 * 获取图片列表
	 * @param request
	 * @param model
	 */
	@RequestMapping(params = "action=getPhotoList")
	public void getPhotoList(HttpServletRequest request, HttpServletResponse response) {
		List<ProjectPhotos> photos = new ArrayList<ProjectPhotos>();
		try {
			photos = trafficConferenceImpl.getPictureList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		response.setCharacterEncoding("UTF-8");
		try {
			response.getWriter().write("{\"photos\":" + JsonUtil.list2json(photos) + "}");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// @RequestMapping(params = "action=videoList")
	// public String videoList(HttpServletRequest request, Model model) {
	// return "trafficconference/videoList";
	// }

}
