package com.foxconn.pojo.questionaire;

import com.foxconn.pojo.util.Paging;

public class SURVEY_RESPONDANT  extends Paging {
	private String RST_ID;
	private String QTE_ID;
	private String RST_IP;
	private int IS_ADOPTED;
	private int IS_DEL;
	private String CREATOR;
	private String CREATE_DATE;
	private String EDITOR;
	private String EDIT_DATE;

	public String getRST_ID() {
		return RST_ID;
	}

	public void setRST_ID(String rST_ID) {
		RST_ID = rST_ID;
	}

	public String getQTE_ID() {
		return QTE_ID;
	}

	public void setQTE_ID(String qTE_ID) {
		QTE_ID = qTE_ID;
	}

	public String getRST_IP() {
		return RST_IP;
	}

	public void setRST_IP(String rST_IP) {
		RST_IP = rST_IP;
	}

	public int getIS_ADOPTED() {
		return IS_ADOPTED;
	}

	public void setIS_ADOPTED(int iS_ADOPTED) {
		IS_ADOPTED = iS_ADOPTED;
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
