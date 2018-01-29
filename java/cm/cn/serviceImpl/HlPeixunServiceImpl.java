package cm.cn.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cm.cn.datasource.CustomerContextHolder;
import cm.cn.mapper.HlPeixunMapper;
import cm.cn.mapper.MYPeixunMapper;
import cm.cn.po.HlPeixun;
import cm.cn.po.HlPeixunExample;
import cm.cn.service.HlPeixunService;
@Service
public class HlPeixunServiceImpl implements HlPeixunService {
	
	@Autowired
	private HlPeixunMapper hlPeixunMapper;
	@Autowired
	private MYPeixunMapper myPeixunMapper;
	@Override
	public List<HlPeixun> selectAllPeixun() {
		CustomerContextHolder.setCustomerType(CustomerContextHolder.DATA_SOURCE_GENERAL);
		return hlPeixunMapper.selectByExample(null);
	}

	@Override
	public int addPeixun(HlPeixun hlPeixun) {
		CustomerContextHolder.setCustomerType(CustomerContextHolder.DATA_SOURCE_GENERAL);
		return hlPeixunMapper.insertSelective(hlPeixun);
	}

	@Override
	public int delPeixun(int[] array) {
		CustomerContextHolder.setCustomerType(CustomerContextHolder.DATA_SOURCE_GENERAL);
		return myPeixunMapper.deleteByIDarray(array);
	}

	@Override
	public List<HlPeixun> selectPeixunByName(String name) {
		HlPeixunExample example = new HlPeixunExample();
		HlPeixunExample.Criteria criteria = example.createCriteria();
		criteria.andNameEqualTo(name);
		return hlPeixunMapper.selectByExample(example);
	}

}
