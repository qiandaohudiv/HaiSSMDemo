package cm.cn.service;

import java.util.List;

import cm.cn.po.HlGoodstype;

public interface HlGoodsTypeService {
	//分页显示所有货物类别
	public List<HlGoodstype> selectAllGoodsType();
	//插入货物类别
	public int insertGoodsType(HlGoodstype hlGoodstype);
	//更改货物类被
	public int updateGoodsType(HlGoodstype hlGoodstype);
	//批量删除货物类别
	public int delGoodsTypeBatch(int[] arrays);
	//根据货物名称查询
	public List<HlGoodstype> selectByName(String name);
}
