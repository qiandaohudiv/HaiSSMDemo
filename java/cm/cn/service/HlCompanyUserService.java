package cm.cn.service;

import java.util.List;

import cm.cn.po.HlCompanyuser;

public interface HlCompanyUserService {
	//为公司添加账号和密码
	public int addCompanyUserAndPass(HlCompanyuser hlCompanyuser);
	//根据用户名和密码登录系统
	public List<HlCompanyuser> selectByUserNameAndPassword(String name,String pass);
	//查询系统中是否有相同的用户名
	public List<HlCompanyuser> selectByUserName(String name);
}
