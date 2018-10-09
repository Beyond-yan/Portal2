package com.foxconn.controller.majorproject;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.foxconn.pojo.opencatalog.Catalog;
import com.foxconn.service.opencatalog.CatalogService;
import com.foxconn.service.trafficNews.TextNewsService;

@Controller
@RequestMapping("majorproject.do")
public class MajorProjectController {

	@Value("${portal.page.size}")
	private int pageSize;

	@Value("${opinion.page.size}")
	private int opinionPageSize;

	@Autowired
	@Resource(name = "catalogServiceImpl")
	private CatalogService catalogServiceImpl;
	@Resource(name = "textNewsServiceImpl")
	private TextNewsService textNewsServiceImpl;

	@RequestMapping(params = "action=init")
	public String init(HttpServletRequest request, Model model) {
		return "majorproject/list";
	}
	@RequestMapping(params = "action=queryList")
	public String queryList(HttpServletRequest request, Model model) {
		List<Catalog> cataList = catalogServiceImpl.getMajorProjectCatalog();
		if(cataList != null && cataList.size() > 0){
			for (Catalog catalog : cataList) {
//				Catalog catalog = new Catalog();
//				catalog.setId(Id);
				List<Catalog> catalogList = catalogServiceImpl.getPageCatalogList(catalog);
				catalog.setCatalog(catalogList);
			}
		}
		model.addAttribute("cataList", cataList);
		return "majorproject/majorList";
	}

	
}