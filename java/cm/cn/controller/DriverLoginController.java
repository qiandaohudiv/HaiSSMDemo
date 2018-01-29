package cm.cn.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cm.cn.po.HlDriveruser;
import cm.cn.service.HlDriveruserService;
import cm.cn.util.GetCheckCode;

@Controller
public class DriverLoginController {
	@Autowired
	private HlDriveruserService hlDriveruserService;
	@RequestMapping(value="/beforeLogin")
	@ResponseBody
	public Map<Integer, String> beforeLogin(String phone){
		Map<Integer, String> map = new HashMap<>();
		String check_code = String.valueOf((int)((Math.random()*9+1)*100000));
		List<HlDriveruser> list = hlDriveruserService.selByPhone(phone);
		if (list.size()>0) {
			HlDriveruser driveruser = list.get(0);
			if (GetCheckCode.getCode(phone, check_code)) {
				driveruser.setCode(check_code);
				driveruser.setChecktime(String.valueOf(System.currentTimeMillis()));
				hlDriveruserService.updateHlDriveruser(driveruser);
				map.put(0, "验证码发送正确");
			}
			else{
				map.put(1, "验证码发送错误");
			}
		}
		else
			map.put(2, "手机号码错误");
		return map;
	}
	
	@RequestMapping(value="/doLogin",method=RequestMethod.GET)
	@ResponseBody
	public Map<Integer, String> dologin(String phone,String chenk_code){
		Map<Integer, String> map = new HashMap<>();
		List<HlDriveruser> list = hlDriveruserService.selByPhone(phone);
		if(list.size()>0){
			HlDriveruser driveruser = list.get(0);
			long nowTime = System.currentTimeMillis();
			long cha = (nowTime-Long.parseLong(driveruser.getChecktime()))/1000;
			if(driveruser.getCode().equals(chenk_code)){
				if(cha<=300){
					map.put(0, "成功");
				}
				else {
					map.put(1, "验证码超时");
				}
			}
			else
				map.put(2, "验证码错误");
		}else {
			map.put(3, "号码不存在");
		}
		return map;
	}
}
