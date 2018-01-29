package cm.cn.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cm.cn.po.HlGoodsinfo;
import cm.cn.po.Page;
import cm.cn.service.HlGoodsInfoService;

@Controller
public class HlGoodsInfoController {
	@Autowired
	private HlGoodsInfoService hlGoodsInfoService;
	@RequestMapping("/selectGoodsInfoByName")
	@ResponseBody
	public Page<HlGoodsinfo> selectGoodsInfoByName(int current,int pageNum,String name){
		//String str = null ;
		//try {
		//	str = new String(name.getBytes("ISO-8859-1"),"UTF-8");
		//} catch (UnsupportedEncodingException e) {
		//	e.printStackTrace();
		//}
		List<HlGoodsinfo> list = hlGoodsInfoService.selectGoodsInfoByName(name);
		Page<HlGoodsinfo> page = null;
		if (list.size()>0) {
			page = new Page<>(current, pageNum, list);
			return page;
		}
		else {
			return null;
		}
	}
	@RequestMapping("/selectGoodsInfoList")
	@ResponseBody
	public List<HlGoodsinfo> selectGoodsInfoList(){
		return hlGoodsInfoService.selectAllGoodsInfo();
	}
	@RequestMapping("allGoodsInfo")
	@ResponseBody
	public Page<HlGoodsinfo> allGoodsInfo(int current,int pageNum){
		List<HlGoodsinfo> list =hlGoodsInfoService.selectAllGoodsInfo();
		Page<HlGoodsinfo> page = null;
		if (list.size()>0) {
			page = new Page<>(current, pageNum, list);
			return page;
		}
		else {
			return null;
		}
	}
	@RequestMapping(value="insertGoodsInfo",method=RequestMethod.POST)
	@ResponseBody
	public int insertGoodsInfo(@RequestBody HlGoodsinfo hlGoodsinfo){
		return hlGoodsInfoService.insertHlGoodsinfo(hlGoodsinfo);
	}
	@RequestMapping(value="updateGoodsInfo",method=RequestMethod.POST)
	@ResponseBody
	public int updateGoodsInfo(@RequestBody HlGoodsinfo hlGoodsinfo){
		return hlGoodsInfoService.updateHlGoodsinfo(hlGoodsinfo);
	}
	@RequestMapping(value="delGoodsInfoBatch")
	@ResponseBody
	public int delGoodsInfoBatch(int[] arrays){
		return hlGoodsInfoService.delHlGoodsinfo(arrays);
	}
}
