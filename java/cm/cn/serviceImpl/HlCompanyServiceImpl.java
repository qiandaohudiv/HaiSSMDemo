package cm.cn.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cm.cn.datasource.CustomerContextHolder;
import cm.cn.mapper.HlCompanyMapper;
import cm.cn.mapper.MyCompanyMapper;
import cm.cn.po.HlCompany;
import cm.cn.po.HlCompanyExample;
import cm.cn.service.HlCompanyService;
@Service
public class HlCompanyServiceImpl implements HlCompanyService {
	@Autowired
	private HlCompanyMapper hlCompanyMapper;
	@Autowired
	private MyCompanyMapper myCompanyMapper;
	@Override
	public List<HlCompany> selAllCompays() {
		CustomerContextHolder.setCustomerType(CustomerContextHolder.DATA_SOURCE_GENERAL);
		HlCompanyExample example = new HlCompanyExample();
		return hlCompanyMapper.selectByExample(example);
	}

	@Override
	public int insertCompany(HlCompany company) {
		CustomerContextHolder.setCustomerType(CustomerContextHolder.DATA_SOURCE_GENERAL);
		return hlCompanyMapper.insertSelective(company);
	}

	@Override
	public int delCompanyBatch(int[] arrays) {
		CustomerContextHolder.setCustomerType(CustomerContextHolder.DATA_SOURCE_GENERAL);
		return myCompanyMapper.delCompanyBatch(arrays);
	}

	@Override
	public int updateCompany(HlCompany company) {
		CustomerContextHolder.setCustomerType(CustomerContextHolder.DATA_SOURCE_GENERAL);
		return hlCompanyMapper.updateByPrimaryKeySelective(company);
	}

	@Override
	public List<HlCompany> selectCompanyByName(String name) {
		CustomerContextHolder.setCustomerType(CustomerContextHolder.DATA_SOURCE_GENERAL);
		HlCompanyExample example = new HlCompanyExample();
		HlCompanyExample.Criteria criteria = example.createCriteria();
		criteria.andNameEqualTo(name);
		return hlCompanyMapper.selectByExample(example);
	}

	@Override
	public HlCompany selectCompanyById(int id) {
		CustomerContextHolder.setCustomerType(CustomerContextHolder.DATA_SOURCE_GENERAL);
		return hlCompanyMapper.selectByPrimaryKey(id);
	}

}
