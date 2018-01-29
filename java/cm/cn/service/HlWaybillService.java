package cm.cn.service;

import java.util.List;

import cm.cn.po.HlWaybill;

public interface HlWaybillService {
	//查找所有订单
	public List<HlWaybill> selAllWaybill();
	//根据公司查找订单
	public List<HlWaybill> selWaybillByCompanyId(int company_id);
	//根据托运方查找订单
	public List<HlWaybill> selWaybillByShipper(String Shipper);
	//添加订单
	public int insertWaybill(HlWaybill hlWaybill);
	//批量删除订单
	public int delWaybillBatch(int[] arrays);
	//更改订单
	public int updateWaybill(HlWaybill hlWaybill);
	//根据驾驶员姓名查询他对应的订单
	public List<HlWaybill> selectWayBillByDriverName(String name);
	//根据 ID 得到运单详细信息
	public HlWaybill selectById(int id);
}
