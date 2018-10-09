package com.foxconn.util;

public class ServerPathConvet {
	
	public static String decodeConvertContent(String content) {
		String replaceParam = com.foxconn.util.LocalPropertyPhase.readData("portal.content.server.replaceParam");
		String contentServer=com.foxconn.util.LocalPropertyPhase.readData("portal.content.server");
		String tempStr = content;
		if (content != null && tempStr.indexOf(replaceParam) > 0) {
			tempStr = tempStr.replace(replaceParam, contentServer);
		}
		return tempStr;
	}

	public static String encodeConvertContent(String content) {
		String replaceParam = com.foxconn.util.LocalPropertyPhase.readData("portal.content.server.replaceParam");
		String contentServer=com.foxconn.util.LocalPropertyPhase.readData("portal.content.server");
		System.out.println(content);
		System.out.println(contentServer);
		String tempStr = content;
		if (content != null && tempStr.indexOf(contentServer) > 0) {
			tempStr = tempStr.replace(contentServer, replaceParam);
		}
		return tempStr;
	}
}
