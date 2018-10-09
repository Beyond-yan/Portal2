package com.foxconn.pojo.trafficNews;

import com.foxconn.util.BaseTreeGrid;

public class NewsComment extends BaseTreeGrid{
	private String COMMENT_ID;
	private String NEWS_ID;
	private String COMMENT_CONTENT;
	private String COMMENT_IPADDRS;
	private String COMMENT_TIME;
	private String COMMENT_CATEGORY; // 新增评论分类 ：新闻（1）、在线访谈（2）
	private int AUDIT_RESULT; // 0未审核 1 通过 2不通过
	private int IS_DEL;
	private String PARENT_ID;



	public String getCOMMENT_CATEGORY() {
		return COMMENT_CATEGORY;
	}
	public void setCOMMENT_CATEGORY(String cOMMENT_CATEGORY) {
		COMMENT_CATEGORY = cOMMENT_CATEGORY;
	}
	public String getCOMMENT_ID()
	{
		return COMMENT_ID;
	}
	public void setCOMMENT_ID(String cOMMENT_ID)
	{
		COMMENT_ID = cOMMENT_ID;
	}
	public String getNEWS_ID()
	{
		return NEWS_ID;
	}
	public void setNEWS_ID(String nEWS_ID)
	{
		NEWS_ID = nEWS_ID;
	}
	public String getCOMMENT_CONTENT()
	{
		return COMMENT_CONTENT;
	}
	public void setCOMMENT_CONTENT(String cOMMENT_CONTENT)
	{
		COMMENT_CONTENT = cOMMENT_CONTENT;
	}
	public String getCOMMENT_IPADDRS()
	{
		return COMMENT_IPADDRS;
	}
	public void setCOMMENT_IPADDRS(String cOMMENT_IPADDRS)
	{
		COMMENT_IPADDRS = cOMMENT_IPADDRS;
	}
	public String getCOMMENT_TIME()
	{
		return COMMENT_TIME;
	}
	public void setCOMMENT_TIME(String cOMMENT_TIME)
	{
		COMMENT_TIME = cOMMENT_TIME;
	}
	public int getAUDIT_RESULT()
	{
		return AUDIT_RESULT;
	}
	public void setAUDIT_RESULT(int aUDIT_RESULT)
	{
		AUDIT_RESULT = aUDIT_RESULT;
	}
	public int getIS_DEL()
	{
		return IS_DEL;
	}
	public void setIS_DEL(int iS_DEL)
	{
		IS_DEL = iS_DEL;
	}

	public String getPARENT_ID() {
		return PARENT_ID;
	}

	public void setPARENT_ID(String PARENT_ID) {
		this.PARENT_ID = PARENT_ID;
	}
}
