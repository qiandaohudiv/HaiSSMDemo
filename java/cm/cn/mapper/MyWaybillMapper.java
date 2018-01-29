package cm.cn.mapper;

import java.util.List;

import cm.cn.po.HlWaybill;

public interface MyWaybillMapper {
	public int delWaybillBatch(int[] arrays);
	public List<HlWaybill> selectWayBillByDriverName(String name);
}
