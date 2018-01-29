package cm.cn.mapper;

import cm.cn.po.HlPeople;

public interface MyDriveruserMapper {
	//删除人员信息当有驾驶员信息时删除 driverUser里面信息
	public int delDriveruserBatch(int[] arrays);
	//根据电话号码查询驾驶员信息
	public HlPeople selectDriverUserMsgByPhone(String phone);
	//根据电话号码查询对应公司联系人的报警电话
	public String selectCompanyLinkManPhone(String phone);
}
