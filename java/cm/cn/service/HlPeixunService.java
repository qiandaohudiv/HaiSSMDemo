package cm.cn.service;

import java.util.List;

import cm.cn.po.HlPeixun;

public interface HlPeixunService {
	//查询所有的培训
	public List<HlPeixun> selectAllPeixun();
	//根据培训主题查询
	public List<HlPeixun> selectPeixunByName(String name);
	//添加培训
	public int addPeixun(HlPeixun hlPeixun);
	//删除培训
	public int delPeixun(int[] array);
}