package com.foxconn.pojo.govdocument;

public class municipalGovDocument {
	
	private String DOCUMENT_ID;
	private String DOCUMENT_TITLE;
	private String DOCUMENT_SUBTITLE;
	private String DOCUMENT_CONTENT;
	private String DOCUMENT_ABSTRACT;
	private String DOCUMENT_SOURCE;
	private String PUBLISH_MECHANISM;
	private String CREATE_TIME;
	private String PUBLISH_TIME;
	private String KEYWORD;
	private Integer DOCUMENT_STATUS;
	private Integer IS_DEL;
	
	private String START_DATE;
	private String END_DATE;
	
	private String pageIndex;
	private String pageSize;
	private Integer READ_NUMBER;
	
	public Integer getREAD_NUMBER() {
		return READ_NUMBER;
	}
	public void setREAD_NUMBER(Integer rEAD_NUMBER) {
		READ_NUMBER = rEAD_NUMBER;
	}
	public String getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(String pageIndex) {
		this.pageIndex = pageIndex;
	}
	public String getPageSize() {
		return pageSize;
	}
	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getDOCUMENT_STATUS() {
		return DOCUMENT_STATUS;
	}
	public void setDOCUMENT_STATUS(Integer dOCUMENT_STATUS) {
		DOCUMENT_STATUS = dOCUMENT_STATUS;
	}
	public String getDOCUMENT_SUBTITLE() {
		return DOCUMENT_SUBTITLE;
	}
	public void setDOCUMENT_SUBTITLE(String dOCUMENT_SUBTITLE) {
		DOCUMENT_SUBTITLE = dOCUMENT_SUBTITLE;
	}
	public String getDOCUMENT_ABSTRACT() {
		return DOCUMENT_ABSTRACT;
	}
	public void setDOCUMENT_ABSTRACT(String dOCUMENT_ABSTRACT) {
		DOCUMENT_ABSTRACT = dOCUMENT_ABSTRACT;
	}
	public String getPUBLISH_TIME() {
		return PUBLISH_TIME;
	}
	public void setPUBLISH_TIME(String pUBLISH_TIME) {
		PUBLISH_TIME = pUBLISH_TIME;
	}
	public String getKEYWORD() {
		return KEYWORD;
	}
	public void setKEYWORD(String kEYWORD) {
		KEYWORD = kEYWORD;
	}
	public String getSTART_DATE() {
		return START_DATE;
	}
	public void setSTART_DATE(String sTART_DATE) {
		START_DATE = sTART_DATE;
	}
	public String getEND_DATE() {
		return END_DATE;
	}
	public void setEND_DATE(String eND_DATE) {
		END_DATE = eND_DATE;
	}
	public String getDOCUMENT_ID() {
		return DOCUMENT_ID;
	}
	public void setDOCUMENT_ID(String dOCUMENT_ID) {
		DOCUMENT_ID = dOCUMENT_ID;
	}
	public String getDOCUMENT_TITLE() {
		return DOCUMENT_TITLE;
	}
	public void setDOCUMENT_TITLE(String dOCUMENT_TITLE) {
		DOCUMENT_TITLE = dOCUMENT_TITLE;
	}
	public String getDOCUMENT_CONTENT() {
		return DOCUMENT_CONTENT;
	}
	public void setDOCUMENT_CONTENT(String dOCUMENT_CONTENT) {
		DOCUMENT_CONTENT = dOCUMENT_CONTENT;
	}
	public String getDOCUMENT_SOURCE() {
		return DOCUMENT_SOURCE;
	}
	public void setDOCUMENT_SOURCE(String dOCUMENT_SOURCE) {
		DOCUMENT_SOURCE = dOCUMENT_SOURCE;
	}
	public String getPUBLISH_MECHANISM() {
		return PUBLISH_MECHANISM;
	}
	public void setPUBLISH_MECHANISM(String pUBLISH_MECHANISM) {
		PUBLISH_MECHANISM = pUBLISH_MECHANISM;
	}
	public String getCREATE_TIME() {
		return CREATE_TIME;
	}
	public void setCREATE_TIME(String cREATE_TIME) {
		CREATE_TIME = cREATE_TIME;
	}
	public Integer getIS_DEL() {
		return IS_DEL;
	}
	public void setIS_DEL(Integer iS_DEL) {
		IS_DEL = iS_DEL;
	}
}