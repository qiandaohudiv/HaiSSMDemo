package cm.cn.service;

import java.util.List;

import cm.cn.po.HlPeople;

public interface HlPeopleService {
	//添加人员信息
	public int insertPeople(HlPeople hlPeople);
	//显示所有人员
	public List<HlPeople> selAllPeople();
	//批量删除人员
	public int delPeopleBatch(int[] arrays);
	//更改人员信息
	public int updatePeople(HlPeople hlPeople);
	//根据人员名字查询信息
	public List<HlPeople> selectPeopleByName(String name);
	//通过公司 Id 查询到对应的员工
	public List<HlPeople> selectByCompanyId(int company_id);
}
