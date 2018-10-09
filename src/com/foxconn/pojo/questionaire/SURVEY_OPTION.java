package com.foxconn.pojo.questionaire;

public class SURVEY_OPTION {

	private String OPN_ID;
	private String QTN_ID;
	private String OPN_DES;
	private int OPN_ORDER;
	private int IS_DEL;
	private String CREATOR;
	private String CREATE_DATE;
	private String EDITOR;
	private String EDIT_DATE;
	private String QTE_ID;
	private char chr_order;
	
	//add by david on date 2013/10/18
	private String OPN_ANSWER;
	private String OPN_RST_ID;
	

	public char getChr_order() {
		return (char) (65 + OPN_ORDER);
	}

	public String getQTE_ID() {
		return QTE_ID;
	}

	public void setQTE_ID(String qTE_ID) {
		QTE_ID = qTE_ID;
	}

	public String getOPN_ID() {
		return OPN_ID;
	}

	public void setOPN_ID(String oPN_ID) {
		OPN_ID = oPN_ID;
	}

	public String getQTN_ID() {
		return QTN_ID;
	}

	public void setQTN_ID(String qTN_ID) {
		QTN_ID = qTN_ID;
	}

	public String getOPN_DES() {
		return OPN_DES;
	}

	public void setOPN_DES(String oPN_DES) {
		OPN_DES = oPN_DES;
	}

	public int getOPN_ORDER() {
		return OPN_ORDER;
	}

	public void setOPN_ORDER(int oPN_ORDER) {
		OPN_ORDER = oPN_ORDER;
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

	public String getOPN_ANSWER() {
		return OPN_ANSWER;
	}

	public void setOPN_ANSWER(String oPN_ANSWER) {
		OPN_ANSWER = oPN_ANSWER;
	}

	public String getOPN_RST_ID() {
		return OPN_RST_ID;
	}

	public void setOPN_RST_ID(String oPN_RST_ID) {
		OPN_RST_ID = oPN_RST_ID;
	}
	
	

}
