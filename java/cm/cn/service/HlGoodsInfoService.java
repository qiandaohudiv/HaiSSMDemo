package cm.cn.service;

import java.util.List;

import cm.cn.po.HlGoodsinfo;

public interface HlGoodsInfoService {
	//查询所有货物信息
	public List<HlGoodsinfo> selectAllGoodsInfo();
	//添加一条货物信息
	public int insertHlGoodsinfo(HlGoodsinfo goodsinfo);
	//批量删除货物信息
	public int delHlGoodsinfo(int[] arrays);
	//更改货物信息
	public int updateHlGoodsinfo(HlGoodsinfo goodsinfo);
	//根据货物名称查询货物信息
	public List<HlGoodsinfo> selectGoodsInfoByName(String name);
}
