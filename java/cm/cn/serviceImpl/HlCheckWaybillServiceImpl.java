package cm.cn.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cm.cn.datasource.CustomerContextHolder;
import cm.cn.mapper.HlCheckWaybillMapper;
import cm.cn.po.HlCheckWaybill;
import cm.cn.po.HlCheckWaybillExample;
import cm.cn.service.HlCheckWaybillService;
@Service
public class HlCheckWaybillServiceImpl implements HlCheckWaybillService {
	@Autowired
	private HlCheckWaybillMapper hlCheckWaybillMapper;
	@Override
	public int insertHlCheckWaybill(HlCheckWaybill hlCheckWaybill) {
		CustomerContextHolder.setCustomerType(CustomerContextHolder.DATA_SOURCE_GENERAL);
		// TODO Auto-generated method stub
		return hlCheckWaybillMapper.insertSelective(hlCheckWaybill);
	}

	@Override
	public int updateHlCheckWaybill(HlCheckWaybill hlCheckWaybill) {
		CustomerContextHolder.setCustomerType(CustomerContextHolder.DATA_SOURCE_GENERAL);
		// TODO Auto-generated method stub
		return hlCheckWaybillMapper.updateByPrimaryKeySelective(hlCheckWaybill);
	}

	@Override
	public List<HlCheckWaybill> selectBywaybillId(int waybillId) {
		CustomerContextHolder.setCustomerType(CustomerContextHolder.DATA_SOURCE_GENERAL);
		HlCheckWaybillExample example = new HlCheckWaybillExample();
		HlCheckWaybillExample.Criteria criteria = example.createCriteria();
		criteria.andWaybillIdEqualTo(waybillId);
		return hlCheckWaybillMapper.selectByExample(example);
	}

}
