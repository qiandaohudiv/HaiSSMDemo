package cm.cn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cm.cn.po.HlPeople;
import cm.cn.service.HlDriveruserService;

@Controller
public class DriverUseInfoController {
	@Autowired
	private HlDriveruserService hlDriveruserService;
	@RequestMapping("/selectDriverUserMsgByPhone")
	@ResponseBody
	public HlPeople selectDriverUserMsgByPhone(String phone){
		return hlDriveruserService.selectDriverUserMsgByPhone(phone);
	}
	@RequestMapping("/selectCompanyLinkManPhone")
	@ResponseBody
	public String selectCompanyLinkManPhone(String phone){
		return hlDriveruserService.selectCompanyLinkManPhone(phone);
	}
}
