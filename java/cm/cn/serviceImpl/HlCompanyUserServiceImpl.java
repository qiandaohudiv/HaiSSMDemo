package cm.cn.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cm.cn.datasource.CustomerContextHolder;
import cm.cn.mapper.HlCompanyuserMapper;
import cm.cn.po.HlCompanyuser;
import cm.cn.po.HlCompanyuserExample;
import cm.cn.service.HlCompanyUserService;
@Service
public class HlCompanyUserServiceImpl implements HlCompanyUserService {
	@Autowired
	private HlCompanyuserMapper hlCompanyuserMapper;
	@Override
	public List<HlCompanyuser> selectByUserNameAndPassword(String name, String pass) {
		CustomerContextHolder.setCustomerType(CustomerContextHolder.DATA_SOURCE_GENERAL);
		HlCompanyuserExample example = new HlCompanyuserExample();
		HlCompanyuserExample.Criteria criteria = example.createCriteria();
		criteria.andNameEqualTo(name);
		criteria.andPasswordEqualTo(pass);
		return hlCompanyuserMapper.selectByExample(example);
	}
	@Override
	public List<HlCompanyuser> selectByUserName(String name) {
		CustomerContextHolder.setCustomerType(CustomerContextHolder.DATA_SOURCE_GENERAL);
		HlCompanyuserExample example = new HlCompanyuserExample();
		HlCompanyuserExample.Criteria criteria = example.createCriteria();
		criteria.andNameEqualTo(name);
		return hlCompanyuserMapper.selectByExample(example);
	}
	@Override
	public int addCompanyUserAndPass(HlCompanyuser hlCompanyuser) {
		CustomerContextHolder.setCustomerType(CustomerContextHolder.DATA_SOURCE_GENERAL);
		return hlCompanyuserMapper.insertSelective(hlCompanyuser);
	}

}
