package com.foxconn.controller.trafficNews;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.foxconn.pojo.trafficNews.MagazineNews;
import com.foxconn.pojo.trafficNews.Yearbook;
import com.foxconn.service.index.IndexService;
import com.foxconn.service.trafficNews.MagazineNewsServiceImpl;
import com.foxconn.util.FileUtils;
import com.foxconn.util.LocalPropertyPhase;
import com.foxconn.util.ServerPathConvet;

@Controller
@RequestMapping("/magazineNews.do")
public class MagazineNewsController {
	@Autowired
	@Resource(name = "magazineNewsImpl")
	private MagazineNewsServiceImpl magazineNewsImpl;

	@Autowired
	@Resource(name = "indexServiceImpl")
	private IndexService indexServiceImpl;

	@Value("${portal.content.server}")
	private String magazinePath;

	@RequestMapping(params = "action=getMagazineNews")
	public String getMagazineNews(HttpServletRequest request, Model model)
			throws UnsupportedEncodingException {

		//added by Cube @140305
		String magNum = request.getParameter("magazineNum");
		if (null == magNum || "".equals(magNum)) {
			MagazineNews magazineNews = indexServiceImpl.getIndexMagazineNews();
			magNum = magazineNews.getMagazineNum();
		}

		// 获取杂志的大纲目录
		List<MagazineNews> magazineNewsList = magazineNewsImpl
				.getMagazineNewsByID(magNum);

		// 获取杂志的第一节的内容
		MagazineNews firstContent = new MagazineNews();
		if (magazineNewsList.size() > 0) {
			firstContent = magazineNewsList.get(0);
			firstContent.setMagazineContent(com.foxconn.util.ServerPathConvet
					.decodeConvertContent(firstContent.getMagazineContent()));
		}

		// 获取杂志的基本信息
		MagazineNews magazineInfo = new MagazineNews();
		magazineInfo = magazineNewsImpl.getMagazineInfo(magNum);
		
		if (null == magazineInfo) {
			model.addAttribute("result", 1);
			model.addAttribute("message", "没有本月杂志");
			return "magazinenews/magazineNews";
		}
		

		// 获取杂志的期数区间
		List<String> magazineNumList = magazineNewsImpl.getMagazineNumList();
		String minMon = "";
		String maxMon = "";
		if (magazineNumList.size() == 2) {
			minMon = magazineNumList.get(0);
			maxMon = magazineNumList.get(1);
		}
		if (magazineNumList.size() == 1) {
			maxMon = minMon = magazineNumList.get(0);
		}

		if (null != magazineInfo) {
			model.addAttribute("magazineNum",
					convertMagazineNum(magazineInfo.getMagazineNum()));
			model.addAttribute("magazineTitle", magazineInfo.getMagazineTitle());
			model.addAttribute("photoURL",
					magazinePath + magazineInfo.getPhotoURL());
			model.addAttribute("fileURL",
					magazinePath + magazineInfo.getFileURL());
			model.addAttribute("iniMon", magazineInfo.getMagazineNum());
		}

		model.addAttribute("firstContent", firstContent);
		model.addAttribute("magazineNewsList", magazineNewsList);
		model.addAttribute("minMon", minMon);
		model.addAttribute("maxMon", maxMon);
		model.addAttribute("magazineNumList", magazineNumList);
		return "magazinenews/magazineNews";
	}

	@ResponseBody
	@RequestMapping(params = "action=getContent")
	public MagazineNews getContent(Model model,
			@RequestParam("contentID") String contentID,
			HttpServletResponse response) {
		MagazineNews content = magazineNewsImpl.getContent(contentID);
		String magContent = content.getMagazineContent();
		/*
		 * magContent.replace("\r", "<br/>"); magContent.replace("\n", "<br/>");
		 */
		// magContent.replace("\"", "\\\"");
		content.setMagazineContent(ServerPathConvet
				.decodeConvertContent(magContent));
		return content;

		/*
		 * response.setCharacterEncoding("UTF-8"); try {
		 * response.getWriter().write(JsonUtil.transferObjectToJSon(content)); }
		 * catch (Exception e) { e.printStackTrace(); }
		 */

	}

	@RequestMapping(params = "action=downloadMagazine")
	public void downloadMagazine(Model model,
			@RequestParam("magazineURL") String magazineURL,
			HttpServletResponse response, HttpServletRequest request) {
		try {
			String magazineU = LocalPropertyPhase.readData("portal.magazine.url");
			String magazineIP = LocalPropertyPhase.readData("portal.magazine.ip");
			magazineURL = magazineURL.replace(magazineU, magazineIP);
			System.out.println("杂志下载===>"+magazineURL);
			FileUtils.downLoadFile(response, request, magazineURL);
		} catch (Exception e) {
//			e.printStackTrace();
		}

	}

	public String convertMagazineNum(String magazineNum) {
		String subYear = magazineNum.substring(0, magazineNum.indexOf("."));
		String subMon = magazineNum.substring(magazineNum.indexOf(".") + 1);
		switch (Integer.parseInt(subMon)) {
		case 1:
			subMon = "一";
			break;
		case 2:
			subMon = "二";
			break;
		case 3:
			subMon = "三";
			break;
		case 4:
			subMon = "四";
			break;
		case 5:
			subMon = "五";
			break;
		case 6:
			subMon = "六";
			break;
		case 7:
			subMon = "七";
			break;
		case 8:
			subMon = "八";
			break;
		case 9:
			subMon = "九";
			break;
		case 10:
			subMon = "十";
			break;
		case 11:
			subMon = "十一";
			break;
		case 12:
			subMon = "十二";
			break;

		default:
			break;
		}
		return subYear + "年第" + subMon + "期";
	}

	@RequestMapping(params = "action=getYearbookList")
	public String getYearbookList(Model model) {
		List<Yearbook> list = magazineNewsImpl.getYearbookList();
		model.addAttribute("contentServer", magazinePath);
		model.addAttribute("list", list);
		return "magazinenews/yearbookList";
	}
}