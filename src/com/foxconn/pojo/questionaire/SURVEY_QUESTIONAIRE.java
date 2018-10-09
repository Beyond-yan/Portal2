package com.foxconn.pojo.questionaire;

import com.foxconn.pojo.util.Paging;

public class SURVEY_QUESTIONAIRE extends Paging  {

	private String QTE_ID;
	private String QTE_TITLE;
	private String QTE_DES;
	private String QTE_EXPIRES;
	private int SHOW_ON_INDEX;
	private int IS_DEL;
	private String CREATOR;
	private String CREATE_DATE;
	private String EDITOR;
	private String EDIT_DATE;

	public String getQTE_ID() {
		return QTE_ID;
	}

	public void setQTE_ID(String qTE_ID) {
		QTE_ID = qTE_ID;
	}

	public String getQTE_TITLE() {
		return QTE_TITLE;
	}

	public void setQTE_TITLE(String qTE_TITLE) {
		QTE_TITLE = qTE_TITLE;
	}

	public String getQTE_DES() {
		return QTE_DES;
	}

	public void setQTE_DES(String qTE_DES) {
		QTE_DES = qTE_DES;
	}

	public String getQTE_EXPIRES() {
		return QTE_EXPIRES;
	}

	public void setQTE_EXPIRES(String qTE_EXPIRES) {
		QTE_EXPIRES = qTE_EXPIRES;
	}

	public int getSHOW_ON_INDEX() {
		return SHOW_ON_INDEX;
	}

	public void setSHOW_ON_INDEX(int sHOW_ON_INDEX) {
		SHOW_ON_INDEX = sHOW_ON_INDEX;
	}

	public int getIS_DEL() {
		return IS_DEL;
	}

	public void setIS_DEL(int iS_DEL) {
		IS_DEL = iS_DEL;
	}

	public String getCREATOR() {
		return CREATOR;
	}

	public void setCREATOR(String cREATOR) {
		CREATOR = cREATOR;
	}

	public String getCREATE_DATE() {
		return CREATE_DATE;
	}

	public void setCREATE_DATE(String cREATE_DATE) {
		CREATE_DATE = cREATE_DATE;
	}

	public String getEDITOR() {
		return EDITOR;
	}

	public void setEDITOR(String eDITOR) {
		EDITOR = eDITOR;
	}

	public String getEDIT_DATE() {
		return EDIT_DATE;
	}

	public void setEDIT_DATE(String eDIT_DATE) {
		EDIT_DATE = eDIT_DATE;
	}

}
