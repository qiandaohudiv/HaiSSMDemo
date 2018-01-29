package cm.cn.service;

import java.util.List;

import cm.cn.po.HlCompany;

public interface HlCompanyService {
	//显示所有公司
	public List<HlCompany> selAllCompays();
	//增加公司
	public int insertCompany(HlCompany company);
	//批量删除公司
	public int delCompanyBatch(int[] arrays);
	//更改公司信息
	public int updateCompany(HlCompany company);
	//根据公司名称查到公司
	public List<HlCompany> selectCompanyByName(String name);
	//通过 ID 查询公司信息
	public HlCompany selectCompanyById(int id);
}
