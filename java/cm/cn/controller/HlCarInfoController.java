package cm.cn.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cm.cn.po.HlCarinfo;
import cm.cn.po.Page;
import cm.cn.service.HlCarInfoService;

@Controller
public class HlCarInfoController {
	@Autowired
	private HlCarInfoService HlCarinfoService;
	@RequestMapping("/selectCarByCarLicense")
	@ResponseBody
	public Page<HlCarinfo> selectCarByCarLicense(int current,int pageNum,String carLicense){
	
		List<HlCarinfo> list = HlCarinfoService.selectByCarLicense(carLicense);
		Page<HlCarinfo> page = null;
		if (list.size()>0) {
			page = new Page<>(current, pageNum, list);
			return page;
		}
		else {
			return null;
		}
	}
	@RequestMapping("/allCarInfo")
	@ResponseBody
	public Page<HlCarinfo> allCarInfo(int current,int pageNum){
		List<HlCarinfo> list =HlCarinfoService.selAllCar();
		Page<HlCarinfo> page = null;
		if (list.size()>0) {
			page = new Page<>(current, pageNum,  list);
			return page;
		}
		else {
			return null;
		}
	}
	@RequestMapping("/selectCarInfoByCompanyName")
	@ResponseBody
	public Page<HlCarinfo> selectCarInfoByCompanyName(int current,int pageNum,String company_name){
		//String str = null ;
		//try {
		//	str = new String(company_name.getBytes("ISO-8859-1"),"UTF-8");
		//} catch (UnsupportedEncodingException e) {
		//	e.printStackTrace();
		//}
		List<HlCarinfo> list = HlCarinfoService.selectByCompanyName(company_name);
		Page<HlCarinfo> page = null;
		if (list.size()>0) {
			page = new Page<>(current, pageNum,  list);
			return page;
		}
		else {
			return null;
		}
	}
	@RequestMapping("/getCarInfoList")
	@ResponseBody
	public List<HlCarinfo> getSafeCardsList(){
		return HlCarinfoService.selAllCar();
	}
	@RequestMapping(value="/insertCarInfo",method=RequestMethod.POST)
	@ResponseBody
	public int insertCarInfo(HlCarinfo HlCarinfo){
		return HlCarinfoService.insertCar(HlCarinfo);
	}
	@RequestMapping(value="/updateCarInfo",method=RequestMethod.POST)
	@ResponseBody
	public int updateCarInfo(HlCarinfo HlCarinfo){
		return HlCarinfoService.updateCar(HlCarinfo);
	}
	@RequestMapping(value="/delCarInfoBatch")
	@ResponseBody
	public int delCarInfoBatch(int[] arrays){
		return HlCarinfoService.delCarBatch(arrays);
	}
}
