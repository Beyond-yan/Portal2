package com.foxconn.service.govdocument;

import java.util.List;
import com.foxconn.pojo.govdocument.municipalGovDocument;


public interface municipalGovDocumentService {
	public List<municipalGovDocument> getMunicipalGovDocument(municipalGovDocument govDocument)throws Exception;
	public void updateMunicipalGovDocument(municipalGovDocument govDocument)throws Exception;
	public void insertGovDocument(municipalGovDocument govDocument)throws Exception;
	public int getMunicipalGovDocumentCount(municipalGovDocument govDocument)throws Exception;
	public municipalGovDocument getGovDocumentForID(municipalGovDocument govDocument)throws Exception;
	public void updateReadNumber(municipalGovDocument govDocument)throws Exception;
}
