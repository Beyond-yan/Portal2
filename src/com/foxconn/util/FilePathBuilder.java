package com.foxconn.util;

import javax.servlet.http.HttpServletRequest;

import net.fckeditor.requestcycle.UserPathBuilder;
/**
 * 配置fckeditor文件上傳的物理路徑
 * @author F3225959
 * @date:2011/07/01
 */
public class FilePathBuilder implements UserPathBuilder{
 
	/**
	 * 配置物理路徑
	 * @author F3225959
     * @date:2011/07/01
	 */
	public String getUserFilesAbsolutePath(HttpServletRequest request) {
		//return PathPool.getDownloadFilePath();
		return "D:/FoxTool/Files/File";
	}
 
	/**
	 * 配置虛擬路徑
	 * @author F3225959
     * @date:2011/07/01
	 */
	public String getUserFilesPath(HttpServletRequest tequest) {
		//return PathPool.getDownloadFilePath();
		return "http://" + tequest.getLocalAddr() + ":" + tequest.getLocalPort() + "/BFLH/File";
	}
}