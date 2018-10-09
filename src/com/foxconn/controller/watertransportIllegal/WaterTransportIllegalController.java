package com.foxconn.controller.watertransportIllegal;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.foxconn.pojo.watertransportIllegal.WaterTransportIllegal;
import com.foxconn.service.watertransportIllegal.WaterTransportIllegalService;

/**
 * 公路水运工程建设质量安全违法违规行为信息公开
 * @author WANGSHAN
 *
 */
@Controller
@RequestMapping("/waterTransport.do")
public class WaterTransportIllegalController {

	@Autowired
	@Resource(name = "waterTransportIllegalServiceImpl")
	private WaterTransportIllegalService waterTransportIllegalServiceImpl;
	
	/**
	 * 列表页面
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "action=waterTransportIllegalManager")
	public String waterTransportIllegalManager(HttpServletRequest request) {
		
		HashMap<String, String> map = new HashMap<String, String>();
		
		List<WaterTransportIllegal> llegals = waterTransportIllegalServiceImpl.SelectWaterTransportIllegal(map);
		
		request.setAttribute("llegals", llegals);
		return "watertransportIllegal/waterTransportIllegalManager";
	}
	
	/**
	 * 详情数据
	 * @param model
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(params = "action=waterTransportIllegalDetail")
	public String waterTransportIllegalDetail(Model model, HttpServletRequest request) {
		
		String id = request.getParameter("id");
		String REPORT_FORM_TYPE = request.getParameter("REPORT_FORM_TYPE");
		Map<String, String> map = new HashMap<String, String>();
		map.put("ID", id);
		
		List<WaterTransportIllegal> list = 
				waterTransportIllegalServiceImpl.SelectWaterTransportIllegal(map);
		request.setAttribute("waterTransportIllegal", list.get(0));
		
		if(null != REPORT_FORM_TYPE && "2".equals(REPORT_FORM_TYPE)) {
			return "watertransportIllegal/waterTransportIllegalDetailForRY";
		}
		return "watertransportIllegal/waterTransportIllegalDetailForQY";
	}
}
