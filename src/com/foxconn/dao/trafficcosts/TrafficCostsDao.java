package com.foxconn.dao.trafficcosts;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;
import com.foxconn.pojo.trafficcosts.TrafficCosts;

@Repository("trafficCostsDao")
public class TrafficCostsDao {

	@Autowired
	@Resource(name="sqlMapClientTemplate")
	private SqlMapClientTemplate sqlMapClientTemplate;
	
	@SuppressWarnings("unchecked")
	public List<TrafficCosts> getTrafficCostsList(TrafficCosts trafficCosts){
		return sqlMapClientTemplate.queryForList("TrafficCosts.getTrafficCostsList",trafficCosts);
		
	}
	
	public void editTrafficCosts(TrafficCosts trafficCosts){
		sqlMapClientTemplate.update("TrafficCosts.editTrafficCosts", trafficCosts);
	}
	
	public void addTrafficCosts(TrafficCosts trafficCosts){
		sqlMapClientTemplate.insert("TrafficCosts.addTrafficCosts", trafficCosts);
	}
}
