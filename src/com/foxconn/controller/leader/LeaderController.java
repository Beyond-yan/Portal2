package com.foxconn.controller.leader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.foxconn.pojo.leader.Leader;
import com.foxconn.service.leader.LeaderService;

@Controller
@RequestMapping("leader.do")
public class LeaderController {
	
	@Value("${leaderAct.page.size}")
	private int pageSize;
	
	@Autowired
	@Resource(name = "leaderServiceImpl")
	private LeaderService leaderServiceImpl;

	@RequestMapping(params = "action=getLeaderList")
	public String getLeaderList(HttpServletRequest request, Model model)
			{
		System.out.println("-------------------------------------------------");
		String projectPath = request.getSession().getServletContext()
				.getRealPath("/");
		String prourl = projectPath + "WEB-INF\\classes\\portal.properties"; // 取路径
		Properties portal = new Properties();
		FileInputStream fis = null;	
		try {
		fis = new FileInputStream(prourl);
		portal.load(fis);
		// 取出文件属性
		String prourl1 = portal.getProperty("portal.content.server") == null ? "" : portal.getProperty("portal.content.server"); 		

			Leader leader = new Leader();
			leader.setStatus(7);
			List<Leader> LeaderInfoList = leaderServiceImpl.getLeaderInfo(leader);
			model.addAttribute("prourl", prourl1);
			model.addAttribute("leaderList", LeaderInfoList);

			String leaderId = request.getParameter("leaderId");
			if (leaderId.isEmpty()) {
//				if (null == leaderId || "" == leaderId) {
				if (LeaderInfoList != null && LeaderInfoList.size() > 0)
					leaderId = LeaderInfoList.get(0).getLeaderId();
			}
			leader.setLeaderId(leaderId);
			Leader LeaderResume = leaderServiceImpl.getLeaderResume(leader);
			//modified by Cube @131219
			//List<Leader> LeaderList = leaderServiceImpl.getLeaderActivate(leader);
			String strCurpage = request.getParameter("curpage");
			int curpage;
//			if (null == strCurpage || "".equals(strCurpage.trim())){
//				curpage = 1;
//			}else{
//				try{
//					curpage = Integer.parseInt(strCurpage);
//				}catch(Exception e){
//					curpage = 1;
//				}
//				}
			
			try{
				curpage = Integer.parseInt(strCurpage);
			}catch(Exception e){
				curpage = 1;
			}
			List<Leader> LeaderList = leaderServiceImpl.getLeaderActivate(leaderId,curpage,pageSize);
			int cnt = leaderServiceImpl.getActivateCount(leaderId);
			
			String leaderPost = LeaderResume.getLeaderPost();
			if (leaderPost == null) {
				leaderPost = "";
			} else {
				leaderPost = leaderPost.replace(",", "<br/>");
			}
			String newleaderPost = leaderPost;

			String leaderPhotosUrl = prourl1 + LeaderResume.getLeaderPhotosUrl();
			model.addAttribute("NewsList", LeaderList);
			model.addAttribute("leaderId", LeaderResume.getLeaderId());
			model.addAttribute("leaderName", LeaderResume.getLeaderName());
			model.addAttribute("leaderPost", newleaderPost);
			model.addAttribute("leaderPhotosUrl", leaderPhotosUrl);
			model.addAttribute("leaderResume", LeaderResume.getLeaderResume());
			
			model.addAttribute("count", cnt);
			model.addAttribute("pagesize", pageSize);
			model.addAttribute("curpage", curpage);
			fis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}finally
		{
			try {
				fis.close();
			} catch (IOException e) {
			}
		}
		return "leader/leaderlist";
	}
}