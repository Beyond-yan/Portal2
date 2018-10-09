package com.foxconn.pojo.questionaire;

public class SURVEY_QUESTION {

	private String QTN_ID;
	private String QTE_ID;
	private String QTN_TYPE;
	private String QTN_DES;
	private int IS_REQUISITE;
	private int IS_DEL;
	private String CREATOR;
	private String CREATE_DATE;
	private String EDITOR;
	private String EDIT_DATE;

	public String getQTN_ID() {
		return QTN_ID;
	}

	public void setQTN_ID(String qTN_ID) {
		QTN_ID = qTN_ID;
	}

	public String getQTE_ID() {
		return QTE_ID;
	}

	public void setQTE_ID(String qTE_ID) {
		QTE_ID = qTE_ID;
	}

	public String getQTN_TYPE() {
		return QTN_TYPE;
	}

	public void setQTN_TYPE(String qTN_TYPE) {
		QTN_TYPE = qTN_TYPE;
	}

	public String getQTN_DES() {
		return QTN_DES;
	}

	public void setQTN_DES(String qTN_DES) {
		QTN_DES = qTN_DES;
	}

	public int getIS_REQUISITE() {
		return IS_REQUISITE;
	}

	public void setIS_REQUISITE(int iS_REQUISITE) {
		IS_REQUISITE = iS_REQUISITE;
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
