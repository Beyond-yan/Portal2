package com.foxconn.service.impl.opencatalog;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.stereotype.Service;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.foxconn.pojo.opencatalog.PublicArchive;
import com.foxconn.pojo.trafficNews.KeyWordNews;
import com.foxconn.service.searchEngine.SearchEngineService;
import com.foxconn.util.WebServiceCall;

@Service("publicArchiveServiceImpl")
public class PublicArchiveServiceImpl implements SearchEngineService {

	private static final String HOST_DETAIL = "http://10.224.5.177:8080/oa";
	private static final String HOST_LIST = "http://10.224.9.100:8080/cq_search";
//	private static final String HOST_LIST = "http://10.224.5.183:8080/cq_search";
//	private static final String HOST_LIST = "http://127.0.0.1:8080/cq_search";

	// 获取公开公文列表
	@Override
	public List<PublicArchive> getPublicArchives(int startIndex, int pageSize) {

		String url = HOST_LIST + "/JW_PUBLIC_ARCHIVES/select?q=is_public:3%20AND%20schema_code:oa&sort=createtime+desc&start=" + startIndex
				+ "&rows=" + pageSize + "&wt=json&indent=true";
		String result = null;
		try {
			result = call(url);
		} catch (IOException e) {
			e.printStackTrace();
		}

		JSONObject all = JSONObject.fromObject(result);
		JSONObject res = all.getJSONObject("response");
		int total = res.getInt("numFound");
		JSONArray docs = res.getJSONArray("docs");
		List<PublicArchive> PublicArchive = tranFromKeyNewsJSONObject(docs);
		if (PublicArchive.size() > 0) {
			PublicArchive.get(0).setCount(total);
		}
		return PublicArchive;
	}

	// 获取公开公文详情
	public String getPublicArchivesDetail(String archivesId, String schema) {

		String result = "";
		try {
			WebServiceCall call = new WebServiceCall();
			call.setOperation("SearchArchivesDetail");
			call.setUrl(HOST_DETAIL + "/services/ArchivesContactsService?wsdl");
			call.setArgs(new String[] { "portal", "e10adc3949ba59abbe56e057f20f883e", archivesId, schema});
			result = call.getStringResult();
			System.out.println("返回结果：" + result);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("获取公开公文详情失败！" + e);
			result = "{\"Status\":\"1\",\"Error_Msg\":\"调用OA服务失败！\"}";
		}
		return result;
	}

	private static List<PublicArchive> tranFromKeyNewsJSONObject(JSONArray array) {

		List<PublicArchive> publicArchives = new ArrayList<PublicArchive>();
		for (int i = 0; i < array.size(); i++) {
			PublicArchive publicArchive = new PublicArchive();
			try {
				JSONObject json = array.getJSONObject(i);
				publicArchive.setArchivesid(getValueByName(json, "archivesid"));
				publicArchive.setIssuer(getValueByName(json, "issuer"));
				publicArchive.setCreatetime(getValueByName(json, "createtime"));
				publicArchive.setSchema_code(getValueByName(json, "schema_code"));
				publicArchive.setSubject(getValueByName(json, "subject"));
				publicArchives.add(publicArchive);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return publicArchives;
	}

	@Override
	public String call(String url) throws IOException {

		HttpURLConnection httpurlconnection = null;
		URL my_url = new URL(url);
		httpurlconnection = (HttpURLConnection) my_url.openConnection();
		httpurlconnection.setDoOutput(true);
		httpurlconnection.setRequestMethod("GET");
		httpurlconnection.setRequestProperty("Content-type", "text/xml; charset=UTF-8");
		BufferedReader br = new BufferedReader(new InputStreamReader(httpurlconnection.getInputStream(), "UTF-8"));
		StringBuilder stringBulider = new StringBuilder();
		String linerep = null;
		while ((linerep = br.readLine()) != null) {
			stringBulider.append(linerep);
		}
		br.close();
		br = null;
		return stringBulider.toString();
	}

	public static String getValueByName(JSONObject json, String pName) {
		String result = "";
		try {
			if (json != null) {
				result = json.getString(pName);
			}
		} catch (Exception e) {
			result = "";
		}
		return result;
	}

	@Override
	public List<KeyWordNews> getNewsSearchInfo(String keyStr, int curpage, int pageSize, String programType, String orderby, String startTime, String endTime, String wordPosition) {
		// TODO Auto-generated method stub
		return null;
	}
}
