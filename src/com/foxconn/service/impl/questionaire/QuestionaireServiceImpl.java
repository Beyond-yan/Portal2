package com.foxconn.service.impl.questionaire;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foxconn.dao.questionaire.QuestionaireDao;
import com.foxconn.pojo.questionaire.SURVEY_ANSWER;
import com.foxconn.pojo.questionaire.SURVEY_OPTION;
import com.foxconn.pojo.questionaire.SURVEY_QUESTION;
import com.foxconn.pojo.questionaire.SURVEY_QUESTIONAIRE;
import com.foxconn.pojo.questionaire.SURVEY_RESPONDANT;
import com.foxconn.service.questionaire.QuestionaireService;

@Service("questionaireServiceImpl")
public class QuestionaireServiceImpl implements QuestionaireService {

	@Autowired
	@Resource(name = "questionaireDao")
	private QuestionaireDao questionaireDao;

	@Override
	public List<SURVEY_QUESTIONAIRE> selQuestionaire(SURVEY_QUESTIONAIRE obj) {
		return questionaireDao.selQuestionaire(obj);
	}

	@Override
	public SURVEY_QUESTIONAIRE selQuestionaireByID(SURVEY_QUESTIONAIRE obj) {
		List<SURVEY_QUESTIONAIRE>  list = questionaireDao.selQuestionaireByID(obj);
		if(list!=null&&list.size()>0){
			return list.get(0);
		}else{
			return new SURVEY_QUESTIONAIRE();
		}
//		return questionaireDao.selQuestionaireByID(obj).get(0);
	}

	@Override
	public List<SURVEY_QUESTION> selQuestion(SURVEY_QUESTION obj) {
		return questionaireDao.selQuestion(obj);
	}

	@Override
	public List<SURVEY_OPTION> selOption(SURVEY_OPTION obj) {
		return questionaireDao.selOption(obj);
	}

	@Override
	public int getQuestionaireCount(SURVEY_QUESTIONAIRE obj) {
		return questionaireDao.getQuestionaireCount(obj);
	}

	@Override
	public List<SURVEY_ANSWER> selStatisticResultList(String qteID) {
		return questionaireDao.selStatisticResultList(qteID);
	}

	@Override
	public SURVEY_QUESTION selQuestionByID(SURVEY_QUESTION obj) {
		List<SURVEY_QUESTION>  list = questionaireDao.selQuestionByID(obj);
		if(list!=null&&list.size()>0){
			return list.get(0);
		}else{
			return new SURVEY_QUESTION();
		}
		
//		return questionaireDao.selQuestionByID(obj).get(0);
	}

	@Override
	public List<SURVEY_OPTION> selOptionByQTNID(SURVEY_OPTION obj) {
		return questionaireDao.selOptionByQTNID(obj);
	}

	@Override
	public List<SURVEY_OPTION> selAnswerList(SURVEY_OPTION obj) {
		return questionaireDao.selAnswerList(obj);
	}

	@Override
	public int selRespondantCount(String qteID) {
		return questionaireDao.selRespondantCount(qteID);
	}

	@Override
	public void insRespondant(SURVEY_RESPONDANT obj) {
		questionaireDao.insRespondant(obj);
	}

	@Override
	public void insAnswer(SURVEY_ANSWER obj) {
		questionaireDao.insAnswer(obj);
	}

	@Override
	public void saveAnswers(String ip, String QTEID,
			Map<String, String[]> mapPara) {

		String rstID = UUID.randomUUID().toString().replace("-", "");
		SURVEY_RESPONDANT rst = new SURVEY_RESPONDANT();
		rst.setRST_ID(rstID);
		rst.setQTE_ID(QTEID);
		rst.setRST_IP(ip);
		this.insRespondant(rst);

		SURVEY_ANSWER asr = new SURVEY_ANSWER();
		Set<String> setPara = mapPara.keySet();
		Iterator<String> it = setPara.iterator();
		while (it.hasNext()) {
			String key = it.next();
			String[] paras = (String[]) mapPara.get(key);
			for (String s : paras) {
				asr.setRST_ID(rstID);
				asr.setQTN_ID(key);
				asr.setOPN_ID(s);
				this.insAnswer(asr);
			}
		}
	}

	/**
	 * by H2603045
	 */
	@Override
	public List<SURVEY_QUESTIONAIRE> getQuestionaireList(int curpage,
			int pagesize) {
		int rStart = (curpage - 1) * pagesize + 1;
		int rEnd = curpage * pagesize;
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("rStart", rStart);
		map.put("rEnd", rEnd);
		return questionaireDao.getQuestionaireList(map);
	}

	@Override
	public int getQuestionaireCountNoDel() {
		return questionaireDao.getQuestionaireCountNoDel();
	}
}
