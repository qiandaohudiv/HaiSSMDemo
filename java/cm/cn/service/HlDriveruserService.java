package cm.cn.service;

import java.util.List;

import cm.cn.po.HlDriveruser;
import cm.cn.po.HlPeople;

public interface HlDriveruserService {
	//插入驾驶员信息（APP用作登陆）
	public int insertHlDriveruser(HlDriveruser hlDriveruser);
	//修改驾驶员信息（主要是修改状态,也可修改验证码和）
	public int updateHlDriveruser(HlDriveruser hlDriveruser);
	//当删除人员信息同时删除 APP 驾驶员信息
	public int delHlDriveruser(int[] arrays);
	//根据电话号码查询
	public List<HlDriveruser> selByPhone(String phone);
	//根据电话号码查询驾驶员信息
	public HlPeople selectDriverUserMsgByPhone(String phone);
	//根据驾驶员电话查询公司联系人电话
	public String selectCompanyLinkManPhone(String phone);
}
