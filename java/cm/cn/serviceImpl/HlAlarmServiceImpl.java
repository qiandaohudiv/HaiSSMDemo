package cm.cn.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cm.cn.datasource.CustomerContextHolder;
import cm.cn.mapper.HlAlarmMapper;
import cm.cn.po.HlAlarm;
import cm.cn.po.HlAlarmExample;
import cm.cn.service.HlAlarmService;

@Service
public class HlAlarmServiceImpl implements HlAlarmService {
	@Autowired
	private HlAlarmMapper hlAlarmMapper;
	@Override
	public int insertAlarm(HlAlarm hlAlarm) {
		CustomerContextHolder.setCustomerType(CustomerContextHolder.DATA_SOURCE_GENERAL);
		return hlAlarmMapper.insertSelective(hlAlarm);
	}

	@Override
	public List<HlAlarm> selectAllAlarm() {
		CustomerContextHolder.setCustomerType(CustomerContextHolder.DATA_SOURCE_GENERAL);
		return hlAlarmMapper.selectByExample(null);
	}
	
	@Override
	public List<HlAlarm> selectAlarmByCompanyId(int company_id) {
		CustomerContextHolder.setCustomerType(CustomerContextHolder.DATA_SOURCE_GENERAL);
		HlAlarmExample example = new HlAlarmExample();
		HlAlarmExample.Criteria criteria = example.createCriteria();
		criteria.andCompayIdEqualTo(company_id);
		return hlAlarmMapper.selectByExample(example);
	}

}
