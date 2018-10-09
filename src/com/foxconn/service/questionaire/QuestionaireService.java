package com.foxconn.service.questionaire;

import java.util.List;
import java.util.Map;

import com.foxconn.pojo.questionaire.SURVEY_ANSWER;
import com.foxconn.pojo.questionaire.SURVEY_OPTION;
import com.foxconn.pojo.questionaire.SURVEY_QUESTION;
import com.foxconn.pojo.questionaire.SURVEY_QUESTIONAIRE;
import com.foxconn.pojo.questionaire.SURVEY_RESPONDANT;

public interface QuestionaireService {

	List<SURVEY_QUESTIONAIRE> selQuestionaire(SURVEY_QUESTIONAIRE obj);

	SURVEY_QUESTIONAIRE selQuestionaireByID(SURVEY_QUESTIONAIRE obj);

	int getQuestionaireCount(SURVEY_QUESTIONAIRE obj);

	List<SURVEY_QUESTION> selQuestion(SURVEY_QUESTION obj);

	SURVEY_QUESTION selQuestionByID(SURVEY_QUESTION obj);

	List<SURVEY_OPTION> selOptionByQTNID(SURVEY_OPTION obj);

	List<SURVEY_OPTION> selOption(SURVEY_OPTION obj);

	List<SURVEY_ANSWER> selStatisticResultList(String qteID);

	int selRespondantCount(String qteID);

	List<SURVEY_OPTION> selAnswerList(SURVEY_OPTION obj);

	void insRespondant(SURVEY_RESPONDANT obj);

	void insAnswer(SURVEY_ANSWER obj);

	void saveAnswers(String ip, String QTEID, Map<String, String[]> mapPara);
	
	// by H2603045
	List<SURVEY_QUESTIONAIRE> getQuestionaireList(int curpage, int pagesize);
	int getQuestionaireCountNoDel();
}
