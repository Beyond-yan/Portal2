package com.foxconn.service.impl.trafficcosts;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.foxconn.dao.trafficcosts.TrafficCostsDao;
import com.foxconn.pojo.trafficcosts.TrafficCosts;
import com.foxconn.service.trafficcosts.TrafficCostsService;

@Service("trafficCostsServiceImpl")
public class TrafficCostsServiceImpl implements TrafficCostsService {

	@Resource(name = "trafficCostsDao")
	private TrafficCostsDao trafficCostsDao;
	
	@Override
	public List<TrafficCosts> getTrafficCostsList(TrafficCosts trafficCosts) {
		return trafficCostsDao.getTrafficCostsList(trafficCosts);
	}

	@Override
	public void editTrafficCosts(TrafficCosts trafficCosts) {
		trafficCostsDao.editTrafficCosts(trafficCosts);
	}

	@Override
	public void addTrafficCosts(TrafficCosts trafficCosts) {
		trafficCostsDao.addTrafficCosts(trafficCosts);
	}

}
