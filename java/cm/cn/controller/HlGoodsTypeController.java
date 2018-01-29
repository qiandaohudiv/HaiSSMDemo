package cm.cn.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cm.cn.po.HlGoodstype;
import cm.cn.po.Page;
import cm.cn.service.HlGoodsTypeService;

@Controller
public class HlGoodsTypeController {
	@Autowired
	private HlGoodsTypeService hlGoodsTypeService;
	@RequestMapping("/selectGoodsTypeByName")
	@ResponseBody
	public Page<HlGoodstype> selectByGoodsTypeName(int current,int pageNum,String name){
		//String str = null ;
		//try {
		//	str = new String(name.getBytes("ISO-8859-1"),"UTF-8");
		//} catch (UnsupportedEncodingException e) {
		//	e.printStackTrace();
		//}
		List<HlGoodstype> list = hlGoodsTypeService.selectByName(name);
		Page<HlGoodstype> page = null;
		if (list.size()>0) {
			page = new Page<>(current, pageNum, list);
			return page;
		}
		else {
			return null;
		}
	}
	@RequestMapping("allGoodsType")
	@ResponseBody
	public Page<HlGoodstype> allGoodsType(int current,int pageNum){
		List<HlGoodstype> list = hlGoodsTypeService.selectAllGoodsType();
		Page<HlGoodstype> page = null;
		if (list.size()>0) {
			page = new Page<>(current, pageNum, list);
			return page;
		}
		else {
			return null;
		}
	}
	@RequestMapping("/getGoodsTypeList")
	@ResponseBody
	public List<HlGoodstype> getSafeCardsList(){
		return hlGoodsTypeService.selectAllGoodsType();
	}
	@RequestMapping(value="insertGoodsType",method=RequestMethod.POST)
	@ResponseBody
	public int insertGoodsType(@RequestBody HlGoodstype hlGoodstype){
		System.out.println("++++++++++"+hlGoodstype.getId());
		hlGoodstype.setCratetime(String.valueOf(System.currentTimeMillis()));
		return hlGoodsTypeService.insertGoodsType(hlGoodstype);
	}
	@RequestMapping(value="updateGoodsType",method=RequestMethod.POST)
	@ResponseBody
	public int updateGoodsType(@RequestBody HlGoodstype hlGoodstype){
		return hlGoodsTypeService.updateGoodsType(hlGoodstype);
	}
	@RequestMapping(value="delGoodsTypeBatch")
	@ResponseBody
	public int delGoodsTypeBatch(int[] arrays){
		return hlGoodsTypeService.delGoodsTypeBatch(arrays);
	}
}
