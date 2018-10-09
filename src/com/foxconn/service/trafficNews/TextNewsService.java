package com.foxconn.service.trafficNews;

import java.util.List;

import com.foxconn.pojo.trafficNews.NewsCommendInfo;
import com.foxconn.pojo.trafficNews.TextNews;
import com.foxconn.pojo.trafficNews.UserReadRecord;

public interface TextNewsService {

	List<TextNews> getCQTrafficNewsList(TextNews textNews,int pageSize,int curPage);

	int getCQTrafficNewsListCount(TextNews textNews);
	
    TextNews getTextNewsDetail(TextNews textNews);
    
    void insertReadRecord(UserReadRecord userReadRecord);
    
    void updateEffective(TextNews textNews);    
    void updateEmotional(TextNews textNews);
    void updateHappy(TextNews textNews);
    void updateNonsense(TextNews textNews);
    void updateBoring(TextNews textNews);
    void updateFear(TextNews textNews);
    void updateSad(TextNews textNews);
    void updateAngry(TextNews textNews);
    
    String selectProgramTypeName(String resourceID);
    
    void insertNewsCommendInfo(NewsCommendInfo newsCommendInfo);
    int getNewsCommendInfoCount(NewsCommendInfo newsCommendInfo);
    

	List<TextNews> getHotNewsList(TextNews textNews,String type, int size);

}
