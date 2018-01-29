package cm.cn.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cm.cn.po.HlPeixun;
import cm.cn.po.Page;
import cm.cn.service.HlPeixunService;

@Controller 
public class HlPeixunController {
	@Autowired
	private HlPeixunService hlPeixunService;
	@RequestMapping("/selectPeixu")
	@ResponseBody
	public Page<HlPeixun> selectByGoodsTypeName(int current,int pageNum){
		List<HlPeixun> list = hlPeixunService.selectAllPeixun();
		Page<HlPeixun> page = null;
		if (list.size()>0) {
			page = new Page<>(current, pageNum, list);
			return page;
		}
		else {
			return null;
		}
	}
	@RequestMapping("/selectPeixuByName")
	@ResponseBody
	public Page<HlPeixun> selectPeixuByName(int current,int pageNum,String name){
		List<HlPeixun> list = hlPeixunService.selectPeixunByName(name);
		Page<HlPeixun> page = null;
		if (list.size()>0) {
			page = new Page<>(current, pageNum, list);
			return page;
		}
		else {
			return null;
		}
	}
	@RequestMapping(value="/insertPeixu",method=RequestMethod.POST)
	@ResponseBody
	public int insertPeixu(HlPeixun hlPeixun){
		return hlPeixunService.addPeixun(hlPeixun);
	}
	@RequestMapping(value="/delPeixu")
	@ResponseBody
	public int delPeixu(int[] array){
		return hlPeixunService.delPeixun(array);
	}
}
