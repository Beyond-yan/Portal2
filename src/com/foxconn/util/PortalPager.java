package com.foxconn.util;

import java.io.IOException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class PortalPager extends TagSupport {
	/**
	 * @author Cube created @130629
	 */
	private static final long serialVersionUID = 1L;
	private String url; // 链接地址
	private int curpage;// 当前页
	private int pagesize; // 页大小
	private int totalLines; // 总记录条数
	private int displayPages = 10;// 显示页码个数

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUrl() {
		return url;
	}

	public int getCurpage() {
		return curpage;
	}

	public void setCurpage(int curpage) {
		this.curpage = curpage;
	}

	public int getPagesize() {
		return pagesize;
	}

	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}

	public int getTotalLines() {
		return totalLines;
	}

	public void setTotalLines(int totalLines) {
		this.totalLines = totalLines;
	}

	/**
	 * 计算总页数
	 * 
	 * @return
	 */
	private int totalPages() {
		return totalLines % pagesize == 0 ? totalLines / pagesize : totalLines
				/ pagesize + 1;
	}

	@Override
	public int doStartTag() throws JspException {
		JspWriter out = this.pageContext.getOut();
		StringBuilder s = new StringBuilder();
		String pref;
		/*
		 * if (url.indexOf("?") > -1) { // 如果url中已经包含了其他的参数,就把offset参数接在后面 pref
		 * = "&"; } else { // 如果url中没有别的参数 pref = "?"; }
		 */
		pref = "_";

		s.append("<div class=\"page\">");
		if (curpage > 1)
			s.append("<a href=\"" + url + pref + (curpage - 1)
					+ ".html\">&lt;上一页</a>");
		if (displayPages >= totalPages()) {
			for (int i = 1; i <= totalPages(); ++i) {
				if (curpage == i)
					s.append(" <a href=\"javascript:;\" class=\"cur\">" + i
							+ "</a>");
				else
					s.append(" <a href=\"" + url + pref + i + ".html\">" + i
							+ "</a>");
			}
		} else {
			for (int i = (curpage - displayPages / 2) > 1 ? (curpage - displayPages / 2)
					: 1; i <= ((curpage + displayPages / 2 - 1) < totalPages() ? (curpage
					+ displayPages / 2 - 1)
					: totalPages()); ++i) {
				if (curpage == i)
					s.append(" <a href=\"javascript:;\" class=\"cur\">" + i
							+ "</a>");
				else
					s.append(" <a href=\"" + url + pref + i + ".html\">" + i
							+ "</a>");
			}
		}
		if (curpage < totalPages())
			s.append(" <a href=\"" + url + pref + (curpage + 1)
					+ ".html\">&gt;下一页</a>");
		s.append("</div>");
		try {
			out.println(s.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return EVAL_BODY_INCLUDE;
	}
}