package com.foxconn.util;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.util.HtmlUtils;

public class CharFilter implements Filter {

	private XssUtil xssUtil = null;  
	private FilterConfig filterConfig = null;  
	
	@Override
	public void destroy() {
		this.filterConfig = null; 
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		String encoding = this.filterConfig.getInitParameter("encoding");  
		
		HttpServletRequest request = (HttpServletRequest) req;  
		HttpServletResponse resp = (HttpServletResponse) response;  
		request.setCharacterEncoding(encoding);  
		resp.setContentType("text/html;charset="+encoding);  

		//System.out.println("-----getMethod-----" + request.getMethod()); 
		
		String[] queryStringArray = { "" };  
		if (request.getMethod().equalsIgnoreCase("get")) {
			String queryString = request.getQueryString();  
		   if (StringUtils.isNotBlank(queryString)) {  
		       queryStringArray = queryString.split("&");  
		   }  
		   for (int i = 0; i < queryStringArray.length; i++) {  
		       queryStringArray[i] = queryStringArray[i].replaceAll("(.*)=(.*)", "$1");  
		   }  
		}
		
		Map<String, String[]> paramMap = req.getParameterMap();
		Set<String> keySet = paramMap.keySet();
		for (String key : keySet) {
			
			boolean isFromGet = false;
			if (request.getMethod().equalsIgnoreCase("get")) {
				for (String paramFromGet : queryStringArray) {
					if (key.equals(paramFromGet)) {
						isFromGet = true;
					}
				}
			}

			String[] paramArray = paramMap.get(key);
			for (int i = 0; i < paramArray.length; i++) {
//				if (isFromGet) {
					//解决GET参数中文乱码问题
					paramArray[i] = new String(paramArray[i].getBytes("iso-8859-1"), encoding); 
//				}
				//将输入参数中的html标签进行转换，避免xss漏洞
				paramArray[i] = HtmlUtils.htmlEscape(xssUtil.clear(paramArray[i]));
			}
		}
		
		 String referer=request.getHeader("Referer");
		 // 判断 Referer 是否以 bank.example 开头
		 StringBuffer requestPath = request.getRequestURL();
//		 System.out.println("requestPath-------->"+requestPath+","+request.getPathInfo()+","+request.getQueryString());
//		 if(requestPath.toString().contains("/Questionaire.do") && (referer == null || !(referer!=null && referer.contains("/www.cqjt.gov.cn/")))){
//				 resp.sendRedirect("error");
//				 return;
//		}
//		 if(requestPath.toString().contains("/Questionaire.do") && request.getQueryString() != null && request.getQueryString().contains("action=questionList")){
//			//验证tokenServletActionContext
//			 HttpSession s = request.getSession();
//			 // 从 session 中得到 csrftoken 属性
//			 String sToken = (String)s.getAttribute("csrftoken");
//			 System.out.println("sToken-------->"+sToken);
//			 if(sToken == null){
//			    // 产生新的 token 放入 session 中
//			    sToken = generateToken();
//			    s.setAttribute("csrftoken",sToken);
////			    chain.doFilter(request, response);
//			 } else{
//			    // 从 HTTP 头中取得 csrftoken
//			    String xhrToken = request.getHeader("csrftoken");
//			    // 从请求参数中取得 csrftoken
//			    String pToken = request.getParameter("csrftoken");
//			    System.out.println("--------->xhrToken:"+xhrToken + "------->pToken:" +pToken +"------>sToken:"+sToken);
//			    if(sToken != null && xhrToken != null && sToken.equals(xhrToken)){
////			        chain.doFilter(request, response);
//			    }else if(sToken != null && pToken != null && sToken.equals(pToken)){
////			        chain.doFilter(request, response);
//			    }else{
//			    	request.getRequestDispatcher("error").forward(request,response);
////			    	resp.sendRedirect("error");
//					return;
//			    }
//			 } 
//		 }
		
		resp.setHeader("X-Frame-Options","SAMEORIGIN");
		//设置httponly
		Cookie[] cookies = request.getCookies();  
		  
        if (cookies != null) {  
        	Cookie cookie = cookies[0];  
            if (cookie != null) {  
                /*cookie.setMaxAge(3600); 
                cookie.setSecure(true); 
                resp.addCookie(cookie);*/  
                //Servlet 2.5不支持在Cookie上直接设置HttpOnly属性  
                String value = cookie.getValue() == null ? "" : cookie.getValue();  
                StringBuilder builder = new StringBuilder();  
                builder.append("JSESSIONID=" + value + "; ");  
                builder.append("Secure; ");  
                builder.append("HttpOnly; ");  
                Calendar cal = Calendar.getInstance();  
                cal.add(Calendar.HOUR, 1);  
                Date date = cal.getTime();  
                Locale locale = Locale.CHINA;  
                SimpleDateFormat sdf =  new SimpleDateFormat("dd-MM-yyyy HH:mm:ss",locale);  
                builder.append("Expires=" + sdf.format(date));  
                resp.setHeader("Set-Cookie", builder.toString());  
            }  
        }  
//        System.out.println("sys-------->"+request.getSession().getAttribute("csrftoken"));
		chain.doFilter(req, resp);
	}

	private  String generateToken(){
		UUID uuid = UUID.randomUUID();  
		String str = uuid.toString();  
		return str;
	}
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.xssUtil = new XssUtil();
		this.filterConfig = filterConfig;
	}
	
	
}
