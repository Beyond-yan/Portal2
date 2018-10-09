/**
 * OA公开公文上外网  控制器
 * 2017-04-01 GuoY
 */
package com.foxconn.controller.trafficNews;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.foxconn.pojo.opencatalog.PublicArchive;
import com.foxconn.service.impl.opencatalog.PublicArchiveServiceImpl;

@Controller
@RequestMapping("publicArchive.do")
public class PublicArchiveController {

	@Autowired
	@Resource(name = "publicArchiveServiceImpl")
	private PublicArchiveServiceImpl publicArchiveServiceImpl;

	/**
	 * 通过搜索引擎获取公开公文的列表数据
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(params = "action=publicArchiveList")
	public String getPublicArchiveList(HttpServletRequest request, Model model) {

		Integer pageSize = Integer.valueOf(request.getParameter("pageSize") == null ? "" : request.getParameter("pageSize"));
		Integer startIndex = Integer.valueOf(request.getParameter("startIndex") == null ? "" : request.getParameter("startIndex"));
		List<PublicArchive> publicArchives = publicArchiveServiceImpl.getPublicArchives(startIndex, pageSize);

		model.addAttribute("publicArchives", publicArchives);
		return "opencatalog/publicArchiveList";
	}

	/**
	 * 公开公文详情页面
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(params = "action=publicArchiveDetail")
	public String publicArchiveDetail(HttpServletRequest request, Model model) {
		
		String schema = request.getParameter("schema") == null ? "" : request.getParameter("schema");
		String archivesid = request.getParameter("archivesid") == null ? "" : request.getParameter("archivesid");
		String result = publicArchiveServiceImpl.getPublicArchivesDetail(archivesid, schema);
		result = result.replace("http://10.224.5.177:8080/oa/attachFiles/oa",
				"http://www.cqjt.gov.cn");
		model.addAttribute("result", result);
		model.addAttribute("schema", schema);
		model.addAttribute("archivesid", archivesid);
		return "opencatalog/publicArchiveDetail";
	}
	
	/**
	 * 公开公文附件下载
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(params = "face=downLoad")
	public void downLoad(HttpServletResponse response, HttpServletRequest request) {
		String fileName = request.getParameter("fileName") == null ? "" : request.getParameter("fileName");
		String filePath = request.getParameter("filePath") == null ? "" : request.getParameter("filePath");
		filePath = filePath.replace("http://www.cqjt.gov.cn", 
				"http://10.224.5.177:8080/oa/attachFiles/oa");
		try {
			fileName = URLDecoder.decode(fileName, "utf-8");
		} catch (UnsupportedEncodingException e2) {
			e2.printStackTrace();
		}
		System.out.println("fileName=" + fileName + ",filePath=" + filePath);
		String finalFilePath = filePath;
		InputStream insr = null;
		URL myURL;
		try {
			myURL = new URL(finalFilePath);
			HttpURLConnection httpsConn = (HttpURLConnection) myURL.openConnection();
			insr = httpsConn.getInputStream();
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String finalFileName = "";
		try {
			finalFileName = new String(fileName.getBytes("gb2312"), "iso8859-1");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		System.out.println("finalFileName=" + finalFileName + ",finalFilePath=" + finalFilePath);
		response.setContentType("application/x-download");
		response.addHeader("content-type", "application/x-msdownload");
		response.addHeader("Content-Disposition", "attachment;filename=" + finalFileName);
		java.io.OutputStream outp = null;
		try {
			outp = response.getOutputStream();
			byte[] b = new byte[1024];
			int i = 0;
			while ((i = insr.read(b)) > 0) {
				outp.write(b, 0, i);
			}
			outp.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (insr != null) {
				try {
					insr.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				insr = null;
			}
			if (outp != null) {
				try {
					outp.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				outp = null;
			}
		}
	}
}
