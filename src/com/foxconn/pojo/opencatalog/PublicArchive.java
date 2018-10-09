package com.foxconn.pojo.opencatalog;

import java.util.Date;

import com.foxconn.util.DateUtil;

public class PublicArchive {
	
	private String archivesid;
	private String issuer;
	private String subject;
	private String createtime;
	private String schema_code;
	private int count;
	
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getArchivesid() {
		return archivesid;
	}
	public void setArchivesid(String archivesid) {
		this.archivesid = archivesid;
	}
	public String getIssuer() {
		return issuer;
	}
	public void setIssuer(String issuer) {
		this.issuer = issuer;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getCreatetime() {
		
		Date date = DateUtil.getDate(createtime, "yyyy-MM-dd");
		return DateUtil.getDateString(date);
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public String getSchema_code() {
		return schema_code;
	}
	public void setSchema_code(String schema_code) {
		this.schema_code = schema_code;
	}
}
