package com.foxconn.dao.govdocument;

import java.util.List;

import javax.annotation.Resource;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;
import com.foxconn.pojo.govdocument.municipalGovDocument;


@Repository("municipalGovDocumentDao")
public class municipalGovDocumentDao {

	@Resource(name = "sqlMapClientTemplate")
	private SqlMapClientTemplate sqlMapClientTemplate;
	
	@SuppressWarnings("unchecked")
	public List<municipalGovDocument> getMunicipalGovDocument(municipalGovDocument govDocument) {
		return this.sqlMapClientTemplate.queryForList(
				"MunicipalGovDocument.getMunicipalGovDocument",govDocument);
	}
	
	public void updateMunicipalGovDocument(municipalGovDocument govDocument){
		this.sqlMapClientTemplate.update("MunicipalGovDocument.updateMunicipalGovDocument", govDocument);
	}
	
	public void insertGovDocument(municipalGovDocument govDocument){
		this.sqlMapClientTemplate.insert("MunicipalGovDocument.insertGovDocument", govDocument);
	}
	public int getMunicipalGovDocumentCount(municipalGovDocument govDocument){
		return (Integer)this.sqlMapClientTemplate.queryForObject("MunicipalGovDocument.getMunicipalGovDocumentCount", govDocument);
	}
	public municipalGovDocument getGovDocumentForID(municipalGovDocument govDocument){
		return (municipalGovDocument) this.sqlMapClientTemplate.queryForObject("MunicipalGovDocument.getGovDocumentForID", govDocument);
	}
	public void updateReadNumber(municipalGovDocument govDocument){
		this.sqlMapClientTemplate.update("MunicipalGovDocument.updateReadNumber", govDocument);
	}
}
