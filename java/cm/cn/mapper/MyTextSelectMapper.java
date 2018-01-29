package cm.cn.mapper;

import java.util.List;

import cm.cn.po.HlCheckText;
import cm.cn.po.HlDriverlogText;

public interface MyTextSelectMapper {
	//根据  ID 数组获取 HlDriverlogText
	public List<HlDriverlogText> selectHlDriverlogTextByArray(int[] array);
	//根据 ID 数组获取HlCheckText
	public List<HlCheckText> selectHlCheckTextByArray(int[] array);
}
