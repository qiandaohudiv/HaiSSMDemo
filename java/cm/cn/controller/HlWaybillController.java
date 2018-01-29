package cm.cn.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cm.cn.po.HlCheckWaybill;
import cm.cn.po.HlWaybill;
import cm.cn.po.Page;
import cm.cn.service.HlCheckWaybillService;
import cm.cn.service.HlWaybillService;

@Controller
public class HlWaybillController {
	@Autowired
	private HlWaybillService hlWaybillService;
	@Autowired
	private HlCheckWaybillService hlCheckWaybillService;
	@RequestMapping("/allWaybill")
	@ResponseBody
	public Page<HlWaybill> allWaybill(int current,int pageNum){
		List<HlWaybill> list =hlWaybillService.selAllWaybill();
		Page<HlWaybill> page = null;
		if (list.size()>0) {
			page = new Page<>(current, pageNum,  list);
			return page;
		}
		else {
			return null;
		}
	}
	@RequestMapping("/selWaybillByCompanyId")
	@ResponseBody
	public Page<HlWaybill> selWaybillByCompanyId(int current,int pageNum,int company_id){
		List<HlWaybill> list = hlWaybillService.selWaybillByCompanyId(company_id);
		Page<HlWaybill> page = null;
		if (list.size()>0) {
			page = new Page<>(current, pageNum,  list);
			return page;
		}
		else {
			return null;
		}
	}
	@RequestMapping("/selWaybillByShipper")
	@ResponseBody
	public Page<HlWaybill> selWaybillByShipper(int current,int pageNum,String Shipper){
		List<HlWaybill> list = hlWaybillService.selWaybillByShipper(Shipper);
		Page<HlWaybill> page = null;
		if (list.size()>0) {
			page = new Page<>(current, pageNum,  list);
			return page;
		}
		else {
			return null;
		}
	}
	@RequestMapping("/getWaybillList")
	@ResponseBody
	public List<HlWaybill> getSafeCardsList(){
		return hlWaybillService.selAllWaybill();
	}
	@RequestMapping(value="/insertWaybill",method=RequestMethod.POST)
	@ResponseBody
	public int insertWaybill(HlWaybill hlWaybill){
		int i = hlWaybillService.insertWaybill(hlWaybill);
		HlCheckWaybill hlCheckWaybill = new HlCheckWaybill();
		hlCheckWaybill.setWaybillId(hlWaybill.getId());
		hlCheckWaybill.setWaybillState("订单创建");
		hlCheckWaybillService.insertHlCheckWaybill(hlCheckWaybill);
		return i;
	}
	@RequestMapping(value="/updateWaybill",method=RequestMethod.POST)
	@ResponseBody
	public int updateWaybill(HlWaybill hlWaybill){
		return hlWaybillService.updateWaybill(hlWaybill);
	}
	@RequestMapping(value="/delWaybillBatch")
	@ResponseBody
	public int delWaybillBatch(int[] arrays){
		return hlWaybillService.delWaybillBatch(arrays);
	}
	@RequestMapping(value="/selectWayBillByDriverName")
	@ResponseBody
	public List<HlWaybill> selectWayBillByDriverName(String name){
		//String str = null;
		//try {
		//	str = new String(name.getBytes("ISO-8859-1"),"UTF-8");
		//} catch (UnsupportedEncodingException e) {
		//	e.printStackTrace();
		//}
		return hlWaybillService.selectWayBillByDriverName(name);
	}
	@RequestMapping(value="/getWaybillBatchById")
	@ResponseBody
	public HlWaybill getWaybillBatchById(int id){
		return hlWaybillService.selectById(id);
	}
}
