package cm.cn.service;

import java.util.List;

import cm.cn.po.HlPeixunAndState;
import cm.cn.po.HlPeixunstate;

public interface HlPeixunStateService {
	//插入培训
	public int insertPeixunState(HlPeixunstate hlPeixunstate);
	//查找所有培训
	public List<HlPeixunstate> selectAllPeixunState();
	//根据姓名查询培训
	public List<HlPeixunAndState> selectPeixuAndState(String driverpeopleName);
	//根据公司Id查找培训
	public List<HlPeixunstate> selectPeixunStateByCompanyId(int company_id);
}
