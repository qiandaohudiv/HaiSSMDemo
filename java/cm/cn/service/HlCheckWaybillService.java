package cm.cn.service;

import java.util.List;

import cm.cn.po.HlCheckWaybill;

public interface HlCheckWaybillService {
	//插入订单状态
	public int insertHlCheckWaybill(HlCheckWaybill hlCheckWaybill);
	//更改订单状态
	public int updateHlCheckWaybill(HlCheckWaybill hlCheckWaybill);
	//根据订单 waybillID 查询关于订单状态的数据
	public List<HlCheckWaybill> selectBywaybillId(int waybillId);
}
