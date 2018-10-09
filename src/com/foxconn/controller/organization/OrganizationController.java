package com.foxconn.controller.organization;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.foxconn.pojo.trafficNews.TextNews;
import com.foxconn.service.impl.organization.OrganizationServiceImpl;
import com.foxconn.service.trafficNews.TextNewsService;

@Controller
@RequestMapping("organization.do")
public class OrganizationController {
	@Autowired
	@Resource(name = "textNewsServiceImpl")
	private TextNewsService textNewsServiceImpl;

	@Autowired
	@Resource(name = "organizationServiceImpl")
	private OrganizationServiceImpl organizationServiceImpl;

	@RequestMapping(params = "action=getOrganizationInfo")
	public String getOrganizationInfo(HttpServletRequest request, Model model) {

		TextNews textNews = new TextNews();
		textNews.setProgramType("01011003");
		List<TextNews> list1 = organizationServiceImpl.getOrgList(textNews);
		textNews.setProgramType("01011004");
		List<TextNews> list2 = organizationServiceImpl.getOrgList(textNews);
		textNews.setProgramType("01011005");
		List<TextNews> list3 = organizationServiceImpl.getOrgList(textNews);

		String newsId = request.getParameter("newsId");
		TextNews textNewsInit = new TextNews();
		if (!newsId.isEmpty()) {
//			if (newsId != "" && newsId != null) {
			textNews.setNewsID(newsId);
			textNewsInit = textNewsServiceImpl.getTextNewsDetail(textNews);
		} else if (list1.size() > 0) {
			textNews = (TextNews) list1.get(0);
			textNewsInit = textNewsServiceImpl.getTextNewsDetail(textNews);
		} else if (list2.size() > 0) {
			textNews = (TextNews) list2.get(0);
			textNewsInit = textNewsServiceImpl.getTextNewsDetail(textNews);
		} else if (list3.size() > 0) {
			textNews = (TextNews) list3.get(0);
			textNewsInit = textNewsServiceImpl.getTextNewsDetail(textNews);
		}
		model.addAttribute("newsTitle", textNewsInit.getNewsTitle());
		model.addAttribute("modifyDate", textNewsInit.getModifyDate());
		model.addAttribute("newsContent", com.foxconn.util.ServerPathConvet.decodeConvertContent(textNewsInit.getNewsContent()));
		model.addAttribute("newsTitle", textNewsInit.getNewsTitle());
		model.addAttribute("keyword", textNewsInit.getNewsKeyWord());

		model.addAttribute("list1", list1);
		model.addAttribute("list2", list2);
		model.addAttribute("list3", list3);

		return "organization/organization_main";
	}
}
