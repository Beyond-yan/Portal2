package com.foxconn.dao.watertransportIllegal;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

import com.foxconn.pojo.watertransportIllegal.WaterTransportIllegal;

/**
 * 
 * @author WANGSHAN
 * @date 2017-09-04
 *
 */
@Repository("waterTransportIllegalDao")
public class WaterTransportIllegalDao {
	
	@Autowired
	@Resource(name="sqlMapClientTemplate")
	private SqlMapClientTemplate sqlMapClientTemplate;
	
	@SuppressWarnings("unchecked")
	public List<WaterTransportIllegal> SelectWaterTransportIllegal(Map<String, String> map ) {
		return sqlMapClientTemplate.queryForList("WaterTransportIllegalSQL.SelectWaterTransportIllegal", map);
	}
	
	public void InsertWaterTransportIllegal(WaterTransportIllegal illegal ) {
		sqlMapClientTemplate.insert("WaterTransportIllegalSQL.InsertWaterTransportIllegal", illegal);
	}
	
	public void UpdateWaterTransportIllegal(WaterTransportIllegal illegal ) {
		sqlMapClientTemplate.update("WaterTransportIllegalSQL.UpdateWaterTransportIllegal", illegal);
	}
	
	public void DeleteWaterTransportIllegal(WaterTransportIllegal illegal ) {
		sqlMapClientTemplate.update("WaterTransportIllegalSQL.DeleteWaterTransportIllegal", illegal);
	}
	
}
