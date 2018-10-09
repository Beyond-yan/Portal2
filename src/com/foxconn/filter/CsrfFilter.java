package com.foxconn.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

public class CsrfFilter extends OncePerRequestFilter {
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filter)
			throws ServletException, IOException {
		// 从 HTTP 头中取得 Referer 值
		 String referer=request.getHeader("Referer");
		 // 判断 Referer 是否以 bank.example 开头
		 System.out.println("--------->referer:"+referer);
		 if((referer!=null) &&(referer.trim().contains("/questionaires/")) && !referer.contains("/www.cqjt.gov.cn/")){
//				 request.getRequestDispatcher("www.cqjt.gov.cn").forward(request,response);
//				 System.out.println(referer.substring(0,referer.lastIndexOf("/")));
//				 response.sendRedirect(referer.substring(0,referer.lastIndexOf("/")));
				 System.out.println("---->"+request.getContextPath());
				 response.sendRedirect("www.baidu.com");
		 } else {
			 System.out.println("--------------->wwww");
			 filter.doFilter(request, response);
		 }
	}

}
