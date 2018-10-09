package com.foxconn.service.searchEngine.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.foxconn.pojo.opencatalog.PublicArchive;
import com.foxconn.pojo.trafficNews.KeyWordNews;
import com.foxconn.service.searchEngine.SearchEngineService;

@Service("searchEngineServiceImpl")
public class SearchEngineServiceImpl implements SearchEngineService {

	public static final String HOST = "http://10.224.9.100:8880/cq_search";
	//	public static final String HOST = "http://192.168.11.189/cq_search";
	public static final String QT = "select";


	public static final String CORE_KEYWORDS = "JW_TRAFFIC_KEYWORDS";


	public static final String HEADER_KEYWORDS = HOST + "/" + CORE_KEYWORDS + "/" + QT;
	public static String EMPTY_RESULT = "[]";

	public static  int maxContentLength=120;//匹配关键词内容最长文本
	/**
	 * 关键字类信息检索查询
	 */
	@Override
	public List<KeyWordNews> getNewsSearchInfo(String keyStr, int startIndex, int pageSize, String programType, String orderby, String startTime, String endTime, String wordPosition) {

		String url = HEADER_KEYWORDS + "?" + getParamForQueryALL(keyStr,programType,startTime,endTime,wordPosition);
		//默认发布时间  否则orderby传1  按相关度排序
		if(orderby.equals("1")){
			url += "&start=" + startIndex + "&rows=" + pageSize + "&wt=json&indent=true";
		}else {
			url += "&sort=entry_date+desc&start=" + startIndex + "&rows=" + pageSize + "&wt=json&indent=true";
		}

		String result = null;
		try {
			System.out.println(url);
			result = call(url);
		} catch (IOException e) {
			e.printStackTrace();
		}

		JSONObject all = JSONObject.fromObject(result);
		// 解析一般下获取response节点
		JSONObject res = all.getJSONObject("response");
		// 结果一般在docs节点下，但是response节点下也有其它有用的节点，比如可以获取结果的总数
		int total = res.getInt("numFound");

		System.out.println("获得的总数是："+total);
		// 获取最终结果
		JSONArray docs = res.getJSONArray("docs");
		List<KeyWordNews> keyNewsList = tranFromKeyNewsJSONObject(docs,keyStr);
		if(keyNewsList.size() > 0) keyNewsList.get(0).setCount(total);
		return keyNewsList;

	}

	/**
	 * 关键字类信息json转换
	 */
	private static List<KeyWordNews> tranFromKeyNewsJSONObject(JSONArray array, String keyStr) {
		List<KeyWordNews> keyNewsInfos = new ArrayList<KeyWordNews>();
		String[] keyList= keyStr.split(" ");

		for (int i = 0; i < array.size(); i++) {
			KeyWordNews keyNewsInfo = new KeyWordNews();
			try {
				JSONObject json = array.getJSONObject(i);
				keyNewsInfo.setId(getValueByName(json, "id"));
				keyNewsInfo.setTitle(getValueByName(json, "title"));
				keyNewsInfo.setEntry_date(getValueByName(json, "entry_date"));
				String text=getValueByName(json, "content");
				int start=0;
				int end=0;
				String realtxt="";
				boolean foundKey=false;
				while(text.indexOf("<p",end)>=0&&end>=0) {
					start = text.indexOf("<p", end);
					end = text.indexOf("</p>", start);
					realtxt = getText(text.substring(start, end));
					for (String key : keyList) {
						if (realtxt.contains(key)) {
							int keyIndex = realtxt.indexOf(key);
							if (keyIndex > maxContentLength) {//关键词在限定长度之后
								if (realtxt.length() - keyIndex < maxContentLength) {//关键词后面的内容小于规定长度
									realtxt = realtxt.substring(0, maxContentLength - (realtxt.length() - keyIndex)) + realtxt.substring(keyIndex) + "...";//
								} else {
									realtxt = realtxt.substring(0, 10) + "..." + realtxt.substring(keyIndex, keyIndex + maxContentLength - 20) + "...";//前面取10个字，然后取关键词后面的maxContentLength-20个字
								}
							}else{
								if(maxContentLength>realtxt.length()){
									realtxt = realtxt.substring(0,realtxt.length());
								}else {
									realtxt = realtxt.substring(0,maxContentLength) + "...";
								}
							}
							foundKey=true;//找到需要的包含关键字的内容
							break;
						}
					}
					if(foundKey){
						break;
					}else{
						realtxt="";
					}
				}
				keyNewsInfo.setContent(realtxt);
				//	keyNewsInfo.setContent(getValueByName(json, "source_des"));
				keyNewsInfo.setType(getValueByName(json, "type"));
				keyNewsInfo.setProgram_type(getValueByName(json, "program_type"));

				keyNewsInfos.add(keyNewsInfo);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return keyNewsInfos;
	}

	@Override
	public String call(String url) throws IOException {

		// 創建一個鏈接遠端服務的客戶端
		HttpURLConnection httpurlconnection = null;
		URL my_url = new URL(url);
		httpurlconnection = (HttpURLConnection) my_url.openConnection();
		httpurlconnection.setDoOutput(true);
		httpurlconnection.setRequestMethod("GET");
		httpurlconnection.setRequestProperty("Content-type",
				"text/xml; charset=UTF-8");
		// 接受結果
		BufferedReader br = new BufferedReader(new InputStreamReader(
				httpurlconnection.getInputStream(), "UTF-8"));
		StringBuilder stringBulider = new StringBuilder();
		String linerep = null;
		while ((linerep = br.readLine()) != null) {
			stringBulider.append(linerep);
		}
		br.close();
		br = null;
		return stringBulider.toString();
	}



	private String getParamForQueryALL( String keyStr, String programType, String startTime, String endTime,String wordPosition) {
		StringBuffer param = new StringBuffer();
		param.append("q=*:*");
		try {
			String keyString = "";
			String[] keyList = keyStr.split(" ");
			for (int i = 0; i < keyList.length; i++) {
				String key = java.net.URLEncoder.encode(keyList[i].toString(),"utf-8");
				//1头部  //2内容  //否则全部
				if(wordPosition.equals("1")){
					keyString += "&fq=title:" + key;
				}else if(wordPosition.equals("2")){
					keyString += "&fq=content:" + key;
				}else {
					keyString += "&fq=title:" + key;

					keyString += "%20OR%20content:" + key;
				}
			}
			if(!programType.equals("-1")){
				String[] pros=programType.split("_");
				for (String pro:pros) {
					keyString += "&fq=program_type:" + pro;
					if(pro=="01011002"){//交委领导
						keyString += "&fq=program_type:" + pro;
						keyString += "%20OR%20type:" + "领导信息";
					}
				}
			}
			if(!(startTime.equals("")||endTime.equals(""))){
				keyString += "&fq=entry_date:[" + startTime+"%20TO%20"+endTime+"]";
			}
			param.append(keyString);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return param.toString();
	}

	// 獲取屬性的值
	public String getValueByName(String jString, String pName) {
		try {
			JSONObject jsonObject = JSONObject.fromObject(jString);
			return jsonObject.getString(pName);
		}
		// 處理異常
		catch (Exception e) {
			return "";
		}
	}

	// 獲取屬性的值
	public static String getValueByName(JSONObject json, String pName) {
		String result = "";
		try {
			if (json != null) {
				result = json.getString(pName);
			}
		}
		// 處理異常
		catch (Exception e) {
			result = "";
		}
		return result;
	}

	public JSONObject parseJSONObject(String jString) {
		JSONObject result = null;
		try {
			result = JSONObject.fromObject(jString);

		}
		// 處理異常
		catch (Exception e) {

		}
		return result;
	}

	@Override
	public List<PublicArchive> getPublicArchives(int startIndex, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	public static String getText(String html){
		if(html==null) {
			return "";
		}
		String newhtml = html.replaceAll("<[.[^>]]*>","");
		newhtml = newhtml.replaceAll("&nbsp;", "");
		System.out.println(newhtml);
		return newhtml;
	}
}
