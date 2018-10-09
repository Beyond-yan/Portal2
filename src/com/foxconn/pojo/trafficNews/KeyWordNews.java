package com.foxconn.pojo.trafficNews;

public class KeyWordNews {

	private String id;
	private String title;
	private String entry_date;
	private String content;
	private String source_des;
	private String program_type;
	private String type;
	private int count;
	private String url;
	
	
	
	public String getProgram_type() {
		return program_type;
	}
	public void setProgram_type(String program_type) {
		this.program_type = program_type;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getSource_des() {
		return source_des;
	}
	public void setSource_des(String source_des) {
		this.source_des = source_des;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getEntry_date() {
		return entry_date;
	}
	public void setEntry_date(String string) {
		this.entry_date = string;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
}
