package com.foxconn.controller.communication;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;




public class Test {
	  public static void main(String[] args) throws Exception{
		  System.out.println(6%3);
//	        URL url = null;
//	        URLConnection urlconn = null;	
//	        BufferedReader br = null;
//	        PrintWriter pw = null;
//	        //fbpd[\\S]{1,}.html
//	        String regex = "[^\\s]*((<\\s*[aA] target=\"_blank\"\\s+href\\s*=([^>]+\\s*)>))";//url匹配规则
//	        Pattern p = Pattern.compile(regex);
//	        try {
//	            url = new URL("http://www.mot.gov.cn/difangxinwen/xxlb_fabu/index.html");//爬取的网址、这里爬取的是一个生物网站
//	            urlconn = url.openConnection();
//	            pw = new PrintWriter(new FileWriter("D:/SiteURL.txt"), true);//将爬取到的链接放到D盘的SiteURL文件中
//	            br = new BufferedReader(new InputStreamReader(
//	                    urlconn.getInputStream()));
//	            String buf = null;
//	            while ((buf = br.readLine()) != null) {
//	                Matcher buf_m = p.matcher(buf);
//	                while (buf_m.find()) {
//	                    pw.println(buf_m.group());
//	                    
//	                    int index = buf_m.group().indexOf("title")+7;
//	                    int endIndex = buf_m.group().indexOf("class");
//	                    System.out.println(buf_m.group().substring(index, endIndex));
//	                    
//	                    int index2 = buf_m.group().indexOf("href")+6;
//	                    int endIndex2 = buf_m.group().indexOf("title");
//	                    System.out.println(buf_m.group().substring(index2, endIndex2));
//	                }
//	            }
//	            System.out.println("爬取成功^_^");
//	        } catch (MalformedURLException e) {
//	            e.printStackTrace();
//	        } catch (IOException e) {
//	            e.printStackTrace();
//	        } finally {
//	            try {
//	                br.close();
//	            } catch (IOException e) {
//	                e.printStackTrace();
//	            }
//	            pw.close();
//	        }
	    }
}
