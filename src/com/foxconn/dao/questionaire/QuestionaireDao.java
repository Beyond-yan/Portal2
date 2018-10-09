package com.foxconn.dao.questionaire;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

import com.foxconn.pojo.questionaire.SURVEY_OPTION;
import com.foxconn.pojo.questionaire.SURVEY_QUESTION;
import com.foxconn.pojo.questionaire.SURVEY_QUESTIONAIRE;
import com.foxconn.pojo.questionaire.SURVEY_ANSWER;
import com.foxconn.pojo.questionaire.SURVEY_RESPONDANT;
import com.googlecode.ehcache.annotations.Cacheable;

@Repository
public class QuestionaireDao {

	@Resource(name = "sqlMapClientTemplate")
	private SqlMapClientTemplate sqlMapClientTemplate;

	@SuppressWarnings("unchecked")
	@Cacheable(cacheName = "portalCache")
	public List<SURVEY_QUESTIONAIRE> selQuestionaire(SURVEY_QUESTIONAIRE obj) {
		List<SURVEY_QUESTIONAIRE> list = new ArrayList<SURVEY_QUESTIONAIRE>();
		list = this.sqlMapClientTemplate.queryForList(
				"QuestionaireSQL.selQuestionaire", obj);
		return list;
	}

	public int getQuestionaireCount(SURVEY_QUESTIONAIRE obj) {
		return (Integer) this.sqlMapClientTemplate.queryForObject(
				"QuestionaireSQL.getQuestionaireCount", obj);
	}

	@SuppressWarnings("unchecked")
	public List<SURVEY_QUESTIONAIRE> selQuestionaireByID(SURVEY_QUESTIONAIRE obj) {
		List<SURVEY_QUESTIONAIRE> list = new ArrayList<SURVEY_QUESTIONAIRE>();
		list = this.sqlMapClientTemplate.queryForList(
				"QuestionaireSQL.selQuestionaireByID", obj);
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<SURVEY_QUESTION> selQuestion(SURVEY_QUESTION obj) {
		List<SURVEY_QUESTION> list = new ArrayList<SURVEY_QUESTION>();
		list = this.sqlMapClientTemplate.queryForList(
				"QuestionaireSQL.selQuestion", obj);
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<SURVEY_QUESTION> selQuestionByID(SURVEY_QUESTION obj) {
		List<SURVEY_QUESTION> list = new ArrayList<SURVEY_QUESTION>();
		list = this.sqlMapClientTemplate.queryForList(
				"QuestionaireSQL.selQuestionByID", obj);
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<SURVEY_OPTION> selOption(SURVEY_OPTION obj) {
		List<SURVEY_OPTION> list = new ArrayList<SURVEY_OPTION>();
		list = this.sqlMapClientTemplate.queryForList(
				"QuestionaireSQL.selOption", obj);
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<SURVEY_OPTION> selOptionByQTNID(SURVEY_OPTION obj) {
		List<SURVEY_OPTION> list = new ArrayList<SURVEY_OPTION>();
		list = this.sqlMapClientTemplate.queryForList(
				"QuestionaireSQL.selOptionByQTNID", obj);
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<SURVEY_OPTION> selAnswerList(SURVEY_OPTION obj) {
		List<SURVEY_OPTION> list = new ArrayList<SURVEY_OPTION>();
		list = this.sqlMapClientTemplate.queryForList(
				"QuestionaireSQL.selAnswerList", obj);
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<SURVEY_ANSWER> selStatisticResultList(String qteID) {
		List<SURVEY_ANSWER> list = this.sqlMapClientTemplate.queryForList(
				"QuestionaireSQL.selStatisticResultList", qteID);
		return list;
	}

	public int selRespondantCount(String qteID) {
		return (Integer) this.sqlMapClientTemplate.queryForObject(
				"QuestionaireSQL.selRespondantCount", qteID);
	}

	public void insRespondant(SURVEY_RESPONDANT obj) {
		this.sqlMapClientTemplate.insert("QuestionaireSQL.insRespondant", obj);
	}

	public void insAnswer(SURVEY_ANSWER obj) {
		this.sqlMapClientTemplate.insert("QuestionaireSQL.insAnswer", obj);
	}
	
	/**
	 * by H2603045
	 */
	@SuppressWarnings("unchecked")
	public List<SURVEY_QUESTIONAIRE> getQuestionaireList(Map<String, Integer> map){
		List<SURVEY_QUESTIONAIRE> list = this.sqlMapClientTemplate.queryForList(
				"QuestionaireSQL.getQuestionaireList", map);
		return list;
	}
	
	@Cacheable(cacheName = "portalCache")
	public int getQuestionaireCountNoDel() {
		return (Integer) this.sqlMapClientTemplate
				.queryForObject("QuestionaireSQL.getQuestionaireCountNoDel");
	}
	
}
