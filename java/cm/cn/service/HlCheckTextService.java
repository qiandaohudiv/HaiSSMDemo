package cm.cn.service;

import java.util.List;

import cm.cn.po.HlCheckText;

public interface HlCheckTextService {
	//查询所有的车辆检查文本
	public List<HlCheckText> selectAll();
	//根据对应 ID数组 查询所有车辆检查文本
	public List<HlCheckText> selectHlCheckTextByIdArrays(int[] array);
}
