package com.foxconn.service.index;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foxconn.dao.index.IndexDao;
import com.foxconn.pojo.Communication.Topic;
import com.foxconn.pojo.index.PhotoInfo;
import com.foxconn.pojo.questionaire.SURVEY_QUESTIONAIRE;
import com.foxconn.pojo.trafficNews.MagazineNews;
import com.foxconn.pojo.trafficNews.TextNews;
import com.foxconn.pojo.trafficNews.VideoNews;
import com.foxconn.util.SpiderTimer;
import com.googlecode.ehcache.annotations.Cacheable;

/**
 * @author F3228761
 * @date : Jun 21, 2013 3:25:41 PM
 */
@Service("indexServiceImpl")
public class IndexServiceImpl implements IndexService {

	@Autowired
	@Resource(name = "indexDao")
	private IndexDao indexDao;

	@Override
	public List<PhotoInfo> getPhotos() {
		return indexDao.getPhotos();
	}

	@Override
	public List<TextNews> getIndexTextNews() {
		return indexDao.getIndexTextNews();
	}
	
	@Override
	public List<TextNews> getIndexNews() {
		return indexDao.getIndexNews();
	}
	


	/* added by Cube @130905 */
//	@Override
//	@Cacheable(cacheName = "portalCache")
	public StringBuilder setTextNewsLayout() {
		int rows1 = 2;
		// int rows2 = 3;
		// int chars = 50;
		StringBuilder sb = new StringBuilder();
		List<TextNews> list = getIndexTextNews();
		TextNews headLine = getHeadSecondLine(list, 2);
		TextNews secondLine = getHeadSecondLine(list, 3);
		sb.append(setHeadSecondLineLayout(headLine));
		// sb.append(setGenernalNewsLayout(list, rows1, chars));
		sb.append(setGenernalNewsLayout(list, rows1));
		sb.append(setHeadSecondLineLayout(secondLine));
		// sb.append(setGenernalNewsLayout(list, rows2, chars));
		sb.append(setGenernalNewsLayout(list, rows1));
		return sb;
	}
	
	
	public StringBuilder setTextNewsLayout2() {
		int rows1 = 2;
		StringBuilder sb = new StringBuilder();
		List<TextNews> list = getIndexNews();
		sb = setNewsLayout(list);
		return sb;
	}
	
	
	/* added by Cube @130905 */
	private TextNews getHeadSecondLine(List<TextNews> list, int type) {
		TextNews textNews = new TextNews();
		for (int i = 0; i < list.size(); i++) {
			textNews = list.get(i);
			if (textNews.getSectionPosition() == type) {
				list.remove(i);
				break;
			}
		}
		return textNews;
	}
	
	

	/* added by Cube @130905 */
	private StringBuilder setHeadSecondLineLayout(TextNews headLine) {
		StringBuilder sb = new StringBuilder();
		sb.append("<dt style=\"margin-bottom: 10px;\"><a target=\"_blank\" title=\"" + headLine.getNewsTitle()
				+ "\" href=\"articles/010101/" + headLine.getNewsID()
				+ ".html\">" + headLine.getNewsTitle() + "</a></dt>");
		return sb;
	}

	/* added by Cube @131126 */
	private StringBuilder setGenernalNewsLayout(List<TextNews> textNewsList,
			int showRow) {
		int row = 0;
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < textNewsList.size();) {
			TextNews textNews = textNewsList.get(i);
			if (textNews.getSectionPosition() == 4) {
				sb.append("<dd><a target=\"_blank\" title=\""
						+ textNews.getNewsTitle()
						+ "\"  href=\"articles/010101/" + textNews.getNewsID()
						+ ".html\">" + textNews.getNewsTitle() + "</a></dd>");
				textNewsList.remove(i);
				row += 1;
				if (row == showRow) {
					break;
				}
			}
		}
		return sb;
	}
	
	
	
	public StringBuilder setNewsLayout(List<TextNews> textNewsList) {
		StringBuilder sb = new StringBuilder();
		TextNews textNews;
		for (int i = 0; i < textNewsList.size();i++) {
			textNews = textNewsList.get(i);
			
			//if(i == 0  || i%3 ==0){
			//	sb.append("<dt style=\"margin-bottom: 10px;\"><a target=\"_blank\" title=\"" + textNews.getNewsTitle()
			//			+ "\" href=\"openCatalog/new/" + textNews.getNewsID()
			//			+ ".html\">" + textNews.getNewsTitle() + "</a></dt>");
			//} else {
				sb.append("<dd><a target=\"_blank\" title=\""
						+ textNews.getNewsTitle()
						+ "\"  href=\"openCatalog/new/" + textNews.getNewsID()
						+ ".html\">" + textNews.getNewsTitle() + "</a></dd>");
			//}
		}
		return sb;
	}


	/*抓新闻*/
	public  List fetchNews() {
		
		List list = new ArrayList<String>();
		File file = new File("D:/SiteURL.txt");
		try {
			
			if(!file.exists() ){
				SpiderTimer sp = new SpiderTimer();
				sp.execute();
			}
			
			FileReader reader = new FileReader(file);
			BufferedReader buf = new BufferedReader(reader);
			String str = null;
			while(null != (str = buf.readLine())){
					list.add(str);
			}
					 
		
			
		} catch (Exception e1) {
			e1.printStackTrace();
		}
            return list;
	}
        
	/* 对数据做 html显示的处理 */
	public StringBuilder setFetchNewsLayout(List<String> newList) {
		int row = 0;
		StringBuilder sb = new StringBuilder();
		String title = new String();
		String url = new String();
		 
		for (int i = 0; i < 6; i++) {
			 String s= newList.get(i);
			 String[] ss= newList.get(i).split("amp");
			title = newList.get(i).split("amp")[0];
			url = newList.get(i).split("amp")[1];
//			if(i == 0  || i%3 ==0){
//				sb.append("<dt style=\"margin-bottom: 10px;\"><a target=\"_blank\" title=\"" 
//						+ title
//						+ "\"  href=\"" + url
//						+ "\">" + title
//						+ "</a></dt>");
//			}else{
			
				sb.append("<dd ><a target=\"_blank\" title=\""
						+ title
						+ "\"  href=\"" + url
						+ "\">" + title.trim().substring(0 , title.length()-3)
						+"</a></dd>");
			//}
		}
		return sb;
	}


	/* added by Cube @130905 */
	/*
	 * private StringBuilder setGenernalNewsLayout(List<TextNews> textNewsList,
	 * int showRow, int lineWidth) { int len = 0; int row = 0; int gap = 3;
	 * StringBuilder sb = new StringBuilder(); for (int i = 0; i <
	 * textNewsList.size();) { TextNews textNews = textNewsList.get(i); if
	 * (textNews.getSectionPosition() == 4) { if (0 == len) {
	 * sb.append("<dd><a target=\"_blank\" title=\"" + textNews.getNewsTitle() +
	 * "\"  href=\"articles/010101/" + textNews.getNewsID() + ".html\">" +
	 * textNews.getNewsTitle() + "</a>"); len = textNews.getNewsTitle().length()
	 * + gap; textNewsList.remove(i); } else if (len +
	 * textNews.getNewsTitle().length() <= lineWidth) {
	 * sb.append("&nbsp;&nbsp;&nbsp;<a target=\"_blank\" title=\"" +
	 * textNews.getNewsTitle() + "\"  href=\"articles/010101/" +
	 * textNews.getNewsID() + ".html\">" + textNews.getNewsTitle() + "</a>");
	 * len += textNews.getNewsTitle().length() + gap; textNewsList.remove(i); }
	 * else if (len + textNews.getNewsTitle().length() > lineWidth) {
	 * sb.append("</dd>"); row += 1; if (row == showRow) { break; }
	 * sb.append("<dd><a target=\"_blank\" title=\"" + textNews.getNewsTitle() +
	 * "\"  href=\"articles/010101/" + textNews.getNewsID() + ".html\">" +
	 * textNews.getNewsTitle() + "</a>"); len = textNews.getNewsTitle().length()
	 * + gap; textNewsList.remove(i); } } } return sb; }
	 */

	@Override
	public MagazineNews getIndexMagazineNews() {
		return indexDao.getIndexMagazineNews();

	}

	@Override
	public VideoNews getIndexVideoNews() {
		return indexDao.getIndexVideoNews();
	}

	@Override
	public List<TextNews> getUpToDateAnnouncementList(String programType) {
		return indexDao.getUpToDateAnnouncement(programType);
	}

	@Override
	public Topic getIndexTopic() {
		return indexDao.getIndexTopic();
	}

	@Override
	public SURVEY_QUESTIONAIRE getIndexQuestionaire() {
		return indexDao.getIndexQuestionaire();
	}


	
}
