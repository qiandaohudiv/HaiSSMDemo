package cm.cn.mapper;

public interface MyGoodsInfoMapper {
	//根据货物信息主键批量删除
	public int delGoodsInfoBatch(int[] arrays);
	//根据安全卡 ID 批量删除
	public int delGoodsInfoBatchBySafeCardID(int[] arrays);
	//根据货物类型 ID 批量删除
	public int delGoodsInfoBatchByGoodsTypeId(int[] arrays);
}
