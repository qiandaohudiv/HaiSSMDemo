package cm.cn.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cm.cn.po.HlCheckWaybill;
import cm.cn.service.HlCheckWaybillService;

@Controller
public class HlCheckWaybillController {
	@Autowired
	private HlCheckWaybillService hlCheckWaybillService;
	@RequestMapping(value="/updateHlCheckWaybill",method=RequestMethod.POST)
	@ResponseBody
	public int updateHlCheckWaybill(HlCheckWaybill hlCheckWaybill){
		return hlCheckWaybillService.updateHlCheckWaybill(hlCheckWaybill);
	}
	@RequestMapping("/selectHlCheckWaybillBywaybillId")
	@ResponseBody
	public List<HlCheckWaybill> selectHlCheckWaybillBywaybillId(int waybillId){
		return hlCheckWaybillService.selectBywaybillId(waybillId);
	}
}
