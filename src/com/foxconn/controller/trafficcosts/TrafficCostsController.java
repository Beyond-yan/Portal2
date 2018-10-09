package com.foxconn.controller.trafficcosts;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.foxconn.pojo.trafficcosts.TrafficCosts;
import com.foxconn.service.impl.trafficcosts.TrafficCostsServiceImpl;

@Controller
@RequestMapping("/trafficCosts.do")
public class TrafficCostsController {

	@Autowired
	@Resource(name = "trafficCostsServiceImpl")
	private TrafficCostsServiceImpl trafficCostsServiceImpl;
	
	@RequestMapping(params="action=trafficCostsJump")
	public String getTrafficCostsList(HttpServletRequest request, HttpServletResponse response){
		
		List<TrafficCosts> trafficCostsList = new ArrayList<TrafficCosts>();
		TrafficCosts trafficCosts = new TrafficCosts();
		try{
			trafficCostsList = trafficCostsServiceImpl.getTrafficCostsList(trafficCosts);
		}catch(Exception e){
			e.printStackTrace();
		}
		System.out.println("length---->"+trafficCostsList.size());
		request.setAttribute("trafficCostsList", trafficCostsList);
		return "opencatalog/trafficFees";
	}
	
	@RequestMapping(params="action=trafficCostsDetail")
	public String trafficCostsDetail(HttpServletRequest request, HttpServletResponse response){
		
		String ID = request.getParameter("ID");
		
		List<TrafficCosts> trafficCostsList = new ArrayList<TrafficCosts>();
		TrafficCosts trafficCosts = new TrafficCosts();
		try{
			trafficCosts.setID(ID);
			trafficCostsList = trafficCostsServiceImpl.getTrafficCostsList(trafficCosts);
		}catch(Exception e){
			e.printStackTrace();
		}
		System.out.println("length---->"+trafficCostsList.size());
		request.setAttribute("trafficCosts", trafficCostsList.get(0));
		return "opencatalog/trafficFeesDetail";
	}
}
