package com.foxconn.service.watertransportIllegal;

import java.util.List;
import java.util.Map;

import com.foxconn.pojo.watertransportIllegal.WaterTransportIllegal;

public interface WaterTransportIllegalService {

	public List<WaterTransportIllegal> SelectWaterTransportIllegal(Map<String, String> map );
	
	public void InsertWaterTransportIllegal(WaterTransportIllegal illegal );
	
	public void UpdateWaterTransportIllegal(WaterTransportIllegal illegal );
	
	public void DeleteWaterTransportIllegal(WaterTransportIllegal illegal );
	
}
