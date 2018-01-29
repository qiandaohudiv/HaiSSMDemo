package cm.cn.service;

import java.util.List;

import cm.cn.po.HlAlarm;

public interface HlAlarmService {
	//增加报警记录
	public int insertAlarm(HlAlarm hlAlarm);
	//查看所有报警记录
	public List<HlAlarm> selectAllAlarm();
	//根据公司Id查询报警记录
	public List<HlAlarm> selectAlarmByCompanyId(int company_id);
}
