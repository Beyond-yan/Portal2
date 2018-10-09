package com.foxconn.controller.index;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.security.KeyStore.Entry;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.foxconn.pojo.Communication.Topic;
import com.foxconn.pojo.index.PhotoInfo;
import com.foxconn.pojo.interview.Interview;
import com.foxconn.pojo.mailList.MailList;
import com.foxconn.pojo.nationwideNews.NationwideNews;
import com.foxconn.pojo.questionaire.SURVEY_QUESTIONAIRE;
import com.foxconn.pojo.sitemap.SiteMap;
import com.foxconn.pojo.trafficNews.MagazineNews;
import com.foxconn.pojo.trafficNews.SubjectNews;
import com.foxconn.pojo.trafficNews.TextNews;
import com.foxconn.service.impl.sitemap.SiteMapServiceImpl;
//import com.foxconn.pojo.trafficNews.VideoNews;
import com.foxconn.service.index.IndexService;
import com.foxconn.service.interview.InterviewService;
import com.foxconn.service.trafficNews.SubjectNewsService;
import com.foxconn.servlet.HtmlGenerator;
import com.foxconn.util.JsonUtil;
import com.googlecode.ehcache.annotations.Cacheable;

/**
 * @author F3228761
 * @date : Jun 21, 2013 3:25:03 PM rewrited by Cube @130829
 */
@Controller
@RequestMapping("/index.do")
public class IndexController {
	@Autowired
	@Resource(name = "indexServiceImpl")
	private IndexService indexServiceImpl;

	@Resource(name = "subjectNewsServiceImpl")
	private SubjectNewsService subjectNewsServiceImpl;

	@Autowired
	@Resource(name = "interviewServiceImpl")
	private InterviewService interviewServiceImpl;
	
	@Autowired
	@Resource(name = "siteMapServiceImpl")
	private SiteMapServiceImpl siteMapServiceImpl;

	@Value("${portal.content.server}")
	private String contentServer;

	@Value("${portal.recentlymail.html}")
	private String recentlyMailHtml;

	@Value("${portal.recentlymail.url}")
	private String recentlyMailUrl;

	@Value("${portal.nationwidenews.html}")
	private String nationwideNewsHtml;

	@Value("${portal.nationwidenews.url}")
	private String nationWideNewsUrl;

	@Value("${service.hall.site}")
	private String serviceHallSite;

	@Value("${roadCondition.url}")
	private String roadCondition;
	
	//added by Cube @140731
	//国省干道数据接口
	@Value("${roadConditionGSGD.url}")
	private String roadConditionGSGD;
	
	
	@RequestMapping(params = "action=home")
	public String home(Model model) {
		
		List<PhotoInfo> photosList = new ArrayList<PhotoInfo>();
		StringBuilder sb = new StringBuilder();
		StringBuilder sb2 = new StringBuilder();
		StringBuilder sb3 = new StringBuilder();
		SubjectNews subjectNews = null;
		Topic topic = null;
		SURVEY_QUESTIONAIRE questionaire = null;
		String s = "1";
		long startTimes = System.currentTimeMillis();
		 List newsList = new ArrayList<String>();
		try {
			
			photosList = indexServiceImpl.getPhotos();
			s += ",2";
			sb = indexServiceImpl.setTextNewsLayout();
			
			s += ",3";
			/*autor yanbo start*/
			sb2 = indexServiceImpl.setTextNewsLayout2();
			newsList = indexServiceImpl.fetchNews(); //新闻抓取
			sb3= indexServiceImpl.setFetchNewsLayout(newsList);
			/*autor yanbo end*/
			
			/*
			 * MagazineNews magazineNews = indexServiceImpl.getIndexMagazineNews();
			 * magazineNews.setPhotoURL(contentServer + magazineNews.getPhotoURL());
			 * String cnMagNum = magazineNews.getMagazineNum().replace(".", "年第") +
			 * "期";
			 * 
			 * VideoNews videoNews = indexServiceImpl.getIndexVideoNews(); videoNews
			 * .setMainPhotosUrl(contentServer + videoNews.getMainPhotosUrl());
			 */

			subjectNews = subjectNewsServiceImpl.getSubjectInfoForShowOnIndex();
			s += ",4";

			topic = indexServiceImpl.getIndexTopic();
			s += ",5";
			questionaire = indexServiceImpl.getIndexQuestionaire();
			s += ",6";
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(this.getClass()).error("首页查询错误！",e);
		}
		long endTimes = System.currentTimeMillis();
		System.out.println("=======================end========================="+s+",useTimes:"+(endTimes - startTimes));

		model.addAttribute("subjectNews", subjectNews);
		model.addAttribute("photosList", photosList);
		model.addAttribute("textNewsList", sb);
		model.addAttribute("textNewsList2", sb2);
		/*
		 * model.addAttribute("magazineNews", magazineNews);
		 * model.addAttribute("videoNews", videoNews);
		 * model.addAttribute("cnMagNum", cnMagNum);
		 */
		model.addAttribute("contentServer", contentServer);

		model.addAttribute("topic", topic);
		model.addAttribute("questionaire", questionaire);
		model.addAttribute("serviceHallSite", serviceHallSite);
		model.addAttribute("news", sb3);
		
		
		return "../../index";
	}

	@RequestMapping(params = "action=getCQNews")
	public String getCQNews(Model model) {
		StringBuilder sb = indexServiceImpl.setTextNewsLayout();
		model.addAttribute("textNewsList", sb);
		return "common/cqNews";
	}
	
	//added by Cube @140305
	@RequestMapping(params = "action=getRCScroller")
	public String getRCScroller(Model model) {
		return "common/RCScroller";
	}

	/*
	 * @RequestMapping(params = "action=getNavigationBar") public String
	 * getNavigationBar(Model model) { String dateView; Calendar ca =
	 * Calendar.getInstance(); int year = ca.get(Calendar.YEAR);// 获取年份 dateView
	 * = Integer.toString(year) + "年"; int month = ca.get(Calendar.MONTH) + 1;//
	 * 获取月份 dateView += Integer.toString(month) + "月"; int day =
	 * ca.get(Calendar.DATE);// 获取日 dateView += Integer.toString(day) + "日";
	 * dateView += " 星期"; int dayOfWeek = ca.get(Calendar.DAY_OF_WEEK); switch
	 * (dayOfWeek) { case 1: dateView += "日"; break; case 2: dateView += "一";
	 * break; case 3: dateView += "二"; break; case 4: dateView += "三"; break;
	 * case 5: dateView += "四"; break; case 6: dateView += "五"; break; case 7:
	 * dateView += "六"; break; } model.addAttribute("dateView", dateView);
	 * return "/common/navigation"; }
	 */

	// added by Cube @131114
	@RequestMapping(params = "action=getHeader")
	public String getHeader(Model model) {
		String dateView;
		Calendar ca = Calendar.getInstance();
		int year = ca.get(Calendar.YEAR);// 获取年份
		dateView = Integer.toString(year) + "年";
		int month = ca.get(Calendar.MONTH) + 1;// 获取月份
		dateView += Integer.toString(month) + "月";
		int day = ca.get(Calendar.DATE);// 获取日
		dateView += Integer.toString(day) + "日";

		dateView += "&nbsp;&nbsp;&nbsp;星期";
		int dayOfWeek = ca.get(Calendar.DAY_OF_WEEK);
		switch (dayOfWeek) {
		case 1:
			dateView += "日";
			break;
		case 2:
			dateView += "一";
			break;
		case 3:
			dateView += "二";
			break;
		case 4:
			dateView += "三";
			break;
		case 5:
			dateView += "四";
			break;
		case 6:
			dateView += "五";
			break;
		case 7:
			dateView += "六";
			break;
		}

		// added by Cube @131129
		MagazineNews magazineNews = null;
		String magazineNum = null;
		try{
			magazineNews = indexServiceImpl.getIndexMagazineNews();
			magazineNum = magazineNews.getMagazineNum();
		}catch (Exception e){
			e.printStackTrace();
			Logger.getLogger(this.getClass()).error("/common/header error:", e);
		}
		model.addAttribute("dateView", dateView);
		model.addAttribute("latestMag", magazineNum);
		/*
		 * model.addAttribute("latestMag", magazineNews.getMagazineNum()
		 * .replace(".", "-"));
		 */
		return "/common/header";
	}

	@RequestMapping(params = "action=getVisiterCounter")
	public String getVisiterCounter(HttpServletRequest request) {
		return "/common/visiterCounter";
	}

	@RequestMapping(params = "action=getUpToDateInterview")
	public String getUpToDateInterview(HttpServletRequest request, Model model)
			throws Exception {

		List<Interview> interviewList = interviewServiceImpl.getInterviewList(
				1, 1);
		if (interviewList != null && interviewList.size() > 0) {
			Interview iv = interviewList.get(0);

			model.addAttribute("interviewID", iv.getInterviewId());
			model.addAttribute("interviewTitle", iv.getInterviewTitle());
			model.addAttribute("guestPhotosUrl",
					contentServer + iv.getGuestPhotosUrl());
			model.addAttribute("interviewPhotosUrl",
					contentServer + iv.getInterviewPhotosUrl());
			model.addAttribute("guestName", iv.getGuestName());
			model.addAttribute("guestDesc", iv.getGuestDesc());
			model.addAttribute("interviewSummary", iv.getInterviewSummary());
			model.addAttribute("interviewDate", iv.getInterviewDate());
		}
		return "/common/upToDateInterview";
	}

	/*
	 * 最新政务信息
	 */
	@RequestMapping(params = "action=getUpToDateAnnouncementList")
	public String getUpToDateAnnouncementList(HttpServletRequest request,
			Model model) {
		// 政务信息 resource_id
		String programType = "01010901";
		List<TextNews> announcementList = indexServiceImpl
				.getUpToDateAnnouncementList(programType);

		System.out.println(announcementList.size());
		model.addAttribute("announcementList", announcementList);
		model.addAttribute("programType", programType);
		return "/common/upToDateAnnouncementList";
	}

	/**
	 * 最近来信
	 * 
	 * @param model
	 * @return
	 * @throws ParseException
	 */

	public String recentlyMail(Model model) throws ParseException {

		List<MailList> mailLists = parserMailList();
		model.addAttribute("mailLists", mailLists);

		return "/common/recentlyMail";
	}

	/**
	 * 全国交通新闻
	 * 
	 * @param model
	 * @return
	 */

	public String nationNewsList(Model model) {
		List<NationwideNews> nationNewsList = parserNationwideNews();
		model.addAttribute("nationNewsList", nationNewsList);
		return "common/nationNewsList";
	}

	/**
	 * 从html解析领导信箱数据
	 * 
	 * @return
	 * @throws ParseException
	 */
	@ResponseBody
	@RequestMapping(params = "action=recentlyMail")
	public List<MailList> parserMailList() throws ParseException {
		// System.out.println("parserMailList");
		String html = recentlyMailHtml;
		String url = recentlyMailUrl;
		List<MailList> mailLists = new ArrayList<MailList>();
		try {
			// 调用timeout(0)，持续的请求url，缺省为2000
			Document doc = Jsoup.connect(html).timeout(0).get();
			// 获取body下标签为table的第二个（从0开始计数）元素
			Elements elements = doc.body().getElementsByTag("table").get(1)
					.select("tr");

			// modified by Cube @131120
			for (int i = 1; i < 6; i++) {
				Element element = elements.get(i);
				MailList ml = new MailList();
				String href = url
						+ element.select("td").select("a").attr("href");
				// System.out.println(href);
				// //获取table的第一个td的文本信息
				String text = element.select("td").get(0).text();
				// //获取table的第三个td的文本信息
				String date = element.select("td").get(2).text();
				if (date != null && date.length() > 4) {
					String[] dates = date.split(" ");
					date = dates[0];
				}
				ml.setHref(href);
				ml.setText(text);
				ml.setDate(date);
				mailLists.add(ml);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return mailLists;
	}

	/**
	 * 从html解析全国交通新闻
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(params = "action=nationNewsList")
	public List<NationwideNews> parserNationwideNews() {

		String html = nationwideNewsHtml;
		;
		String url = nationWideNewsUrl;
		// System.out.println(url);
		List<NationwideNews> nationNewsList = new ArrayList<NationwideNews>();
		// 调用timeout(0)，持续的请求url，缺省为2000
		Document doc;
		try {
			doc = Jsoup.connect(html).timeout(300000).post();
			Elements lis = doc.getElementsByTag("div").select("li");
			for (int i = 0; i < 6; i++) {
				NationwideNews news = new NationwideNews();
				Element element = lis.get(i);
				String href = element.select("a").attr("href");
				String text = element.select("a").text();
				news.setText(text);
				if (href.contains("http")) {
					news.setHref(href);
				} else {
					// System.out.println(href);
					String newHref = url + href.substring(1);
					news.setHref(newHref);
				}
				nationNewsList.add(news);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return nationNewsList;
	}

	// added by Cube @131127
	@RequestMapping(params = "action=getRoadCondition")
	public void getRoadCondition(HttpServletResponse response)
			throws IOException {
		// System.out.println("getRoadCondition");

		URL url = new URL(roadCondition);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.connect();
		InputStream inputStream = connection.getInputStream();
		ServletOutputStream fos = response.getOutputStream();
		int len = 0;
		byte[] buf = new byte[1024];
		while ((len = inputStream.read(buf)) != -1) {
			try {
				fos.write(buf, 0, len);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		inputStream.close();
		fos.close();

		/*
		 * String roadCondition = getRoadConditionString();
		 * System.out.println(roadCondition);
		 * response.getWriter().append(roadCondition);
		 */
	}
	
	// added by Cube @140731
		@RequestMapping(params = "action=getRoadConditionGSGD")
		public void getRoadConditionGSGD(HttpServletResponse response)
				throws IOException {
			// System.out.println("getRoadCondition");

			URL url = new URL(roadConditionGSGD);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.connect();
			InputStream inputStream = connection.getInputStream();
			ServletOutputStream fos = response.getOutputStream();
			int len = 0;
			byte[] buf = new byte[1024];
			while ((len = inputStream.read(buf)) != -1) {
				try {
					fos.write(buf, 0, len);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			inputStream.close();
			fos.close();

		}

	@Cacheable(cacheName = "portalCache")
	private String getRoadConditionString() throws IOException {
		// System.out.println("getRoadCondition");
		URL url = new URL(roadCondition);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.connect();
		InputStream inputStream = connection.getInputStream();
		StringBuffer out = new StringBuffer();
		byte[] b = new byte[4096];
		for (int n; (n = inputStream.read(b)) != -1;) {
			out.append(new String(b, 0, n));
		}
		return out.toString();
	}
	
	@RequestMapping(params="action=postSiteMap")
	public String PostSiteMap(Model model,HttpServletResponse response) throws Exception{
		
		List<SiteMap> siteMapList = siteMapServiceImpl.getSiteMapList(new SiteMap());
		SiteMap site = new SiteMap();
		recursionDeptTree(siteMapList, site);
		model.addAttribute("siteMapList", site.getData());
		return "common/siteMap";
		
//		response.setCharacterEncoding("UTF-8");
//		try {
//			response.getWriter().write(
//					JsonUtil.list2json(site.getData()));
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}
	
	/**
	 * 2016-12-30
	 * @param 新年贺词
	 * @return
	 */
	@RequestMapping(params = "action=jumpNewYear")
	public String jumpNewYear(HttpServletRequest request) {
	
		return "common/happyNewYear";
	}
	
	/**
	 * 手动更新静态页面
	 * @param response
	 */
	@RequestMapping(params = "action=updateStaticPage")
	public void UpdateStaticPage (HttpServletResponse response) {
		
		String CurrentClassFilePath = this.getClass().getResource("").getPath();
		int lastpath = CurrentClassFilePath.lastIndexOf("classes/");
		String subCurrent = CurrentClassFilePath.substring(0, lastpath);
		final String web_rootPath = subCurrent.replace("WEB-INF/", "index_static.htm");
		boolean isGenerator = HtmlGenerator.createHtmlPage("http://127.0.0.1:8080/index.html", web_rootPath);
//		boolean isGenerator = HtmlGenerator.createHtmlPage("http://10.224.9.99:8088/index.html", web_rootPath);
		
		
		response.setCharacterEncoding("UTF-8");
		try {
			if(isGenerator) {
				System.out.println("更新静态页面成功！");
				response.getWriter().write("更新静态页面成功！");
			}else { 
				System.out.println("更新静态页面失败！");
				response.getWriter().write("更新静态页面失败！");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void recursionDeptTree(List<SiteMap> siteMapList, SiteMap site){
		List<SiteMap> childList = site.getData();
		int id = StringUtils.isBlank(site.getId()) ? -1 : Integer.parseInt(site.getId());
		for (SiteMap s : siteMapList) {
			int pId = StringUtils.isBlank(s.getPid()) ? -1 : Integer.parseInt(s.getPid());
			if(id == pId){
				childList.add(s);
				site.setData(childList);
				recursionDeptTree(siteMapList, s);
			} 
		}
	}
	
	public void setSiteMap(List<SiteMap> siteMapList, List<SiteMap> resultList) throws Exception{
		
		for (SiteMap siteMap : siteMapList) {
			SiteMap siteMapBean=new SiteMap();
			List<SiteMap> siteMapL = new  ArrayList<SiteMap>();
			if(siteMap.getIsClick().equals("0")){
				siteMapBean = siteMap;
				for(SiteMap siteMap2 : siteMapList){
					if(siteMap2.getPid().equals(siteMap.getId())){
						siteMapL.add(siteMap2);
					}
				}
				siteMapBean.setData(siteMapL);
				resultList.add(siteMapBean);
			}
			
		}
	}
}
