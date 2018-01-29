package cm.cn.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cm.cn.po.HlCompanyuser;
import cm.cn.service.HlCompanyUserService;

@Controller
public class HlCompanyUserController {
	@Autowired
	private HlCompanyUserService hlCompanyUserService;

	// 添加公司的时候同时为其创建账户
	@RequestMapping("/insertCompanyUserAndPass")
	@ResponseBody
	public Map<Integer, String> insertCompanyUserAndPass(HlCompanyuser hlCompanyuser) {
		Map<Integer, String> map = new HashMap<Integer, String>();
		// 先查询该用户是否存在，存在的话不能添加，不存在的话可以直接添加
		List<HlCompanyuser> list = hlCompanyUserService.selectByUserName(hlCompanyuser.getName());
		if (list.size() > 0) {
			map.put(1, "该用户名已经存在");
		} else {
			hlCompanyUserService.addCompanyUserAndPass(hlCompanyuser);
			map.put(2, "添加成功");
		}
		return map;
	}

	@RequestMapping("/companyUserLogin")
	@ResponseBody
	public Map<Integer, Object> companyUserLogin(String name, String pass, HttpSession session) {
		Map<Integer, Object> map = new HashMap<>();
		List<HlCompanyuser> list = hlCompanyUserService.selectByUserNameAndPassword(name, pass);
		if (list.size() == 1) {
			session.setAttribute("companyUser", list.get(0));
			map.put(1, "登录成功");
			map.put(2, list.get(0));
		} else {
			map.put(3, "用户名或密码错误");
		}
		return map;
	}

	@RequestMapping(value = "/logout")
	@ResponseBody
	public String logout(HttpSession session) {
		// session失效
		System.out.println("logout");
		session.removeAttribute("companyUser");
		// 重定向到商品查询页面
//		return "redirect:/index/first.action";
		return "redirect:/";
	}

}
