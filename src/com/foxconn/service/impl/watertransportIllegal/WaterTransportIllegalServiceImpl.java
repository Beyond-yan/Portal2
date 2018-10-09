package com.foxconn.service.impl.watertransportIllegal;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foxconn.dao.watertransportIllegal.WaterTransportIllegalDao;
import com.foxconn.pojo.watertransportIllegal.WaterTransportIllegal;
import com.foxconn.service.watertransportIllegal.WaterTransportIllegalService;


@Service("waterTransportIllegalServiceImpl")
public class WaterTransportIllegalServiceImpl implements WaterTransportIllegalService {

	@Autowired
	@Resource(name = "waterTransportIllegalDao")
	private WaterTransportIllegalDao waterTransportIllegalDao;

	@Override
	public List<WaterTransportIllegal> SelectWaterTransportIllegal(Map<String, String> map) {
		// TODO Auto-generated method stub
		return waterTransportIllegalDao.SelectWaterTransportIllegal(map);
	}

	@Override
	public void InsertWaterTransportIllegal(WaterTransportIllegal illegal) {
		// TODO Auto-generated method stub
		waterTransportIllegalDao.InsertWaterTransportIllegal(illegal);
	}

	@Override
	public void UpdateWaterTransportIllegal(WaterTransportIllegal illegal) {
		// TODO Auto-generated method stub
		waterTransportIllegalDao.UpdateWaterTransportIllegal(illegal);
	}

	@Override
	public void DeleteWaterTransportIllegal(WaterTransportIllegal illegal) {
		// TODO Auto-generated method stub
		waterTransportIllegalDao.DeleteWaterTransportIllegal(illegal);
	}
	
}
