package cm.cn.service;

import java.util.List;

import cm.cn.po.HlCarinfo;

public interface HlCarInfoService {
	//查询所有的车信息
	public List<HlCarinfo> selAllCar();
	//批量删除车的信息
	public int delCarBatch(int[] arrays);
	//增加车的信息
	public int insertCar(HlCarinfo carinfo);
	//更改车的信息
	public int updateCar(HlCarinfo carinfo);
	//根据车牌号查询车的信息
	public List<HlCarinfo> selectByCarLicense(String carLicense);
	//根据公司名查询车的信息
	public List<HlCarinfo> selectByCompanyName(String company_name);
}
