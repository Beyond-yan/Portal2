package com.foxconn.util;

import java.util.List;


public class HighChartsUtils {
	
	public static String drawLineCharts(List<String[]> names) {
		StringBuffer buf = new StringBuffer();
		int i = 0;
		
		for(String[] temp : names) {
			buf.append(temp[0]);
			buf.append(",");
			buf.append(temp[1]);
			i++;
			if(i != names.size()) {
				buf.append("\n");
			}
		}
		return buf.toString();
	}
	
}
