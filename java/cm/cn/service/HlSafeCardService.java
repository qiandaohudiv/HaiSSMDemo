package cm.cn.service;

import java.util.List;

import cm.cn.po.HlSafecard;

public interface HlSafeCardService {
	//查询所有安全卡
	public List<HlSafecard> selectAllSafeCard();
	//更改安全卡信息
	public int updateSafeCardById(HlSafecard hlSafecard);
	//插入安全卡信息
	public int insertSafeCard(HlSafecard hlSafecard);
	//批量删除安全卡信息
	public int delSafeCardBatch(int[] array);
	//根据 安全卡名称查询
	public List<HlSafecard> selectBySafeCardName(String safeCardName);
}
