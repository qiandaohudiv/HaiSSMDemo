package cm.cn.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cm.cn.datasource.CustomerContextHolder;
import cm.cn.mapper.HlPeixunAndStateMapper;
import cm.cn.mapper.HlPeixunstateMapper;
import cm.cn.po.HlPeixunAndState;
import cm.cn.po.HlPeixunstate;
import cm.cn.po.HlPeixunstateExample;
import cm.cn.service.HlPeixunStateService;
@Service
public class HlPeixunStateServiceImpl implements HlPeixunStateService {
	@Autowired
	private HlPeixunstateMapper hlPeixunstateMapper;
	@Autowired
	private HlPeixunAndStateMapper hlPeixunAndStateMapper;
	@Override
	public int insertPeixunState(HlPeixunstate hlPeixunstate) {
		CustomerContextHolder.setCustomerType(CustomerContextHolder.DATA_SOURCE_GENERAL);
		return hlPeixunstateMapper.insertSelective(hlPeixunstate);
	}

	@Override
	public List<HlPeixunstate> selectAllPeixunState() {
		CustomerContextHolder.setCustomerType(CustomerContextHolder.DATA_SOURCE_GENERAL);
		return hlPeixunstateMapper.selectByExample(null);
	}

	@Override
	public List<HlPeixunstate> selectPeixunStateByCompanyId(int company_id) {
		CustomerContextHolder.setCustomerType(CustomerContextHolder.DATA_SOURCE_GENERAL);
		// TODO Auto-generated method stub
		HlPeixunstateExample example = new HlPeixunstateExample();
		HlPeixunstateExample.Criteria criteria = example.createCriteria();
		criteria.andCompayIdEqualTo(company_id);
		return hlPeixunstateMapper.selectByExample(example);
	}

	@Override
	public List<HlPeixunAndState> selectPeixuAndState(String driverpeopleName) {
		return hlPeixunAndStateMapper.selectPeixuAndState(driverpeopleName);
	}

}
