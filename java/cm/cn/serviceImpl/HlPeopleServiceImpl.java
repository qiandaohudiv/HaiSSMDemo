package cm.cn.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cm.cn.datasource.CustomerContextHolder;
import cm.cn.mapper.HlPeopleMapper;
import cm.cn.mapper.MyPeopleMapper;
import cm.cn.po.HlPeople;
import cm.cn.po.HlPeopleExample;
import cm.cn.service.HlPeopleService;
@Service
public class HlPeopleServiceImpl implements HlPeopleService {
	@Autowired
	private HlPeopleMapper hlPeopleMapper;
	@Autowired
	private MyPeopleMapper myPeopleMapper;
	@Override
	public int insertPeople(HlPeople hlPeople) {
		CustomerContextHolder.setCustomerType(CustomerContextHolder.DATA_SOURCE_GENERAL);
		// TODO Auto-generated method stub
		return hlPeopleMapper.insertSelective(hlPeople);
	}

	@Override
	public List<HlPeople> selAllPeople() {
		CustomerContextHolder.setCustomerType(CustomerContextHolder.DATA_SOURCE_GENERAL);
		HlPeopleExample example = new HlPeopleExample();
		return hlPeopleMapper.selectByExample(example);
	}

	@Override
	public int delPeopleBatch(int[] arrays) {
		CustomerContextHolder.setCustomerType(CustomerContextHolder.DATA_SOURCE_GENERAL);
		// TODO Auto-generated method stub
		return myPeopleMapper.delPeopleBatch(arrays);
	}

	@Override
	public int updatePeople(HlPeople hlPeople) {
		CustomerContextHolder.setCustomerType(CustomerContextHolder.DATA_SOURCE_GENERAL);
		// TODO Auto-generated method stub
		return hlPeopleMapper.updateByPrimaryKeySelective(hlPeople);
	}

	@Override
	public List<HlPeople> selectPeopleByName(String name) {
		CustomerContextHolder.setCustomerType(CustomerContextHolder.DATA_SOURCE_GENERAL);
		HlPeopleExample example = new HlPeopleExample();
		HlPeopleExample.Criteria criteria = example.createCriteria();
		criteria.andNameEqualTo(name);
		return hlPeopleMapper.selectByExample(example);
	}

	@Override
	public List<HlPeople> selectByCompanyId(int company_id) {
		CustomerContextHolder.setCustomerType(CustomerContextHolder.DATA_SOURCE_GENERAL);
		HlPeopleExample example = new HlPeopleExample();
		HlPeopleExample.Criteria criteria = example.createCriteria();
		criteria.andCompayIdEqualTo(company_id);
		return hlPeopleMapper.selectByExample(example);
	}

}
