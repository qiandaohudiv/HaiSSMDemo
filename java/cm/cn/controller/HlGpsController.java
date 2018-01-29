package cm.cn.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cm.cn.po.Gpsinfo;
import cm.cn.po.Gpsinfo20171128;
import cm.cn.service.HlGpsService;
@Controller
public class HlGpsController {
	@Autowired
	private HlGpsService hlGpsService;
	@RequestMapping("/selectGpsByPlateNO")
	@ResponseBody
	public Gpsinfo20171128 selectGpsByPlateNo(String plateNo){
	
		Gpsinfo20171128 list = hlGpsService.selectGpsByPlateNo(plateNo);
		//Page<Gpsinfo20171128> page = null;
		if (list!=null) {
			return list;
			//page = new Page<>(current, pageNum, list);
			//return page;
		}
		else {
			return null;
		}
	}
	@RequestMapping("/selectGpsByPlateNO2")
	@ResponseBody
	public Gpsinfo selectGpsByPlateNo2(String plateNo){
	
		Gpsinfo list = hlGpsService.selectGpsByPlate(plateNo);
		//Page<Gpsinfo20171128> page = null;
		if (list!=null) {
			return list;
			//page = new Page<>(current, pageNum, list);
			//return page;
		}
		else {
			return null;
		}
	}
}
