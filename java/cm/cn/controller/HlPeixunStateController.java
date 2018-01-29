package cm.cn.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cm.cn.po.HlPeixunAndState;
import cm.cn.po.HlPeixunstate;
import cm.cn.po.Page;
import cm.cn.service.HlPeixunStateService;

@Controller
public class HlPeixunStateController {
	@Autowired
	private HlPeixunStateService hlPeixunStateService;
	@RequestMapping("/selectPeixustate")
	@ResponseBody
	public Page<HlPeixunstate> selectAllPeixunState(int current,int pageNum){
		List<HlPeixunstate> list = hlPeixunStateService.selectAllPeixunState();
		Page<HlPeixunstate> page = null;
		if (list.size()>0) {
			page = new Page<>(current, pageNum, list);
			return page;
		}
		else {
			return null;
		}
	}
	@RequestMapping("/selectPeixuAndState")
	@ResponseBody
	public Page<HlPeixunAndState> selectPeixunAndState(int current,int pageNum,String driverpeopleName){
		List<HlPeixunAndState> list = hlPeixunStateService.selectPeixuAndState(driverpeopleName);
		Page<HlPeixunAndState> page = null;
		if (list.size()>0) {
			page = new Page<>(current, pageNum, list);
			return page;
		}
		else {
			return null;
		}
	}
	@RequestMapping("/selectPeixunstateByCompanyId")
	@ResponseBody
	public Page<HlPeixunstate> selectPeixunStateByCompanyId(int current,int pageNum,int company_id){
		List<HlPeixunstate> list = hlPeixunStateService.selectPeixunStateByCompanyId(company_id);
		Page<HlPeixunstate> page = null;
		if(list.size()>0){
			page = new Page<>(current,pageNum,list);
			return page;
		}
		else {
			return null;
		}
	}
	@RequestMapping(value="/insertPeixustate",method=RequestMethod.POST)
	@ResponseBody
	public int insertPeixustate(HlPeixunstate hlPeixunstate){
		return hlPeixunStateService.insertPeixunState(hlPeixunstate);
	}
}
