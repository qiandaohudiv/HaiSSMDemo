package cm.cn.service;

import java.util.List;

import cm.cn.po.HlDriverlogText;

public interface HlDriverLogTextService {
	//查询所有的行车日志文本
	public List<HlDriverlogText> selectAllDriverlogText();
	//根据对应的 ID 数组查询行车日志文本
	public List<HlDriverlogText> selectDriverLogsById(int[] array);
}
