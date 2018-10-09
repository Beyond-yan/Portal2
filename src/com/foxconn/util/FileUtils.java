package com.foxconn.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.net.URLEncoder;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FileUtils {

	public static void deleteFile(File oldPath) {
		if (oldPath.exists()) {
			if (oldPath.isDirectory()) {
				File[] files = oldPath.listFiles();
				for (File file : files) {
					deleteFile(file);
				}
				oldPath.delete();
			} else {
				oldPath.delete();
			}
		}
	}
	
	//rewrited by Cube @130910
	public static void downLoadFile(HttpServletResponse response,
			HttpServletRequest request, String filePath) throws IOException {
		filePath = URLDecoder.decode(filePath, "UTF-8");
		String fileName = filePath.substring(filePath.lastIndexOf("/") + 1);
		if (request.getHeader("User-Agent").toLowerCase().indexOf("firefox") > 0) {
			fileName = new String(fileName.getBytes("UTF-8"), "ISO8859-1");

		} else if (request.getHeader("User-Agent").toUpperCase()
				.indexOf("MSIE") > 0) {

			fileName = URLEncoder.encode(fileName, "UTF-8");
		}
		response.reset();
		response.setContentType("charset=UTF-8");
		URL url = new URL(filePath);
		URLConnection u = url.openConnection();
		InputStream in = u.getInputStream();
		response.setHeader("Content-disposition", "attachment; filename=\""+ fileName + "\"");
		response.setHeader("Connection", "close");
		ServletOutputStream fos = response.getOutputStream();
		int len = 0;
		byte[] buf = new byte[1024];
		while ((len = in.read(buf)) != -1) {
			try {
				fos.write(buf, 0, len);
			} catch (Exception e) {
//				e.printStackTrace();
			}
		}
		in.close();
		fos.close();
	}
}