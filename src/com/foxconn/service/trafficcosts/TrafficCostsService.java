package com.foxconn.service.trafficcosts;

import java.util.List;

import com.foxconn.pojo.trafficcosts.TrafficCosts;

public interface TrafficCostsService {

	public List<TrafficCosts> getTrafficCostsList(TrafficCosts trafficCosts);
	public void editTrafficCosts(TrafficCosts trafficCosts);
	public void addTrafficCosts(TrafficCosts trafficCosts);
	
}
