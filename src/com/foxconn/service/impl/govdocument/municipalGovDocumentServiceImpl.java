package com.foxconn.service.impl.govdocument;

import java.util.List;

import javax.annotation.Resource;
import com.foxconn.dao.govdocument.municipalGovDocumentDao;
import com.foxconn.pojo.govdocument.municipalGovDocument;
import com.foxconn.service.govdocument.municipalGovDocumentService;

import org.springframework.stereotype.Service;

@Service("municipalGovDocumentServiceImpl")
public class municipalGovDocumentServiceImpl implements municipalGovDocumentService {

	@Resource(name = "municipalGovDocumentDao")
	private municipalGovDocumentDao municipalGovDocumentDao;
	
	@Override
	public List<municipalGovDocument> getMunicipalGovDocument(municipalGovDocument govDocument) throws Exception {
		return municipalGovDocumentDao.getMunicipalGovDocument(govDocument);
	}

	@Override
	public void updateMunicipalGovDocument(municipalGovDocument govDocument) throws Exception{
		municipalGovDocumentDao.updateMunicipalGovDocument(govDocument);
	}

	@Override
	public void insertGovDocument(municipalGovDocument govDocument)throws Exception {
		municipalGovDocumentDao.insertGovDocument(govDocument);
	}

	@Override
	public int getMunicipalGovDocumentCount(municipalGovDocument govDocument) throws Exception {
		return municipalGovDocumentDao.getMunicipalGovDocumentCount(govDocument);
	}

	@Override
	public municipalGovDocument getGovDocumentForID(municipalGovDocument govDocument) throws Exception {
		return municipalGovDocumentDao.getGovDocumentForID(govDocument);
	}

	@Override
	public void updateReadNumber(municipalGovDocument govDocument) throws Exception {
		municipalGovDocumentDao.updateReadNumber(govDocument);
	}
	
}
