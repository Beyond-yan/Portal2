package com.foxconn.controller.leaderMail;

import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.foxconn.service.trafficNews.TextNewsService;

@Controller
@RequestMapping("leaderMail.do")
public class LeaderMailController {

	@Autowired
	@Resource(name = "textNewsServiceImpl")
	private TextNewsService textNewsServiceImpl;

	@RequestMapping(params = "action=getLeaderMailList")
	public String getLeaderMailList(Model model) {

		return "leadermail/leadermaillist";
	}
}