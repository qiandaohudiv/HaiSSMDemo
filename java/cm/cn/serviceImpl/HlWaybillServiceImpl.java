package cm.cn.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cm.cn.datasource.CustomerContextHolder;
import cm.cn.mapper.HlWaybillMapper;
import cm.cn.mapper.MyWaybillMapper;
import cm.cn.po.HlWaybill;
import cm.cn.po.HlWaybillExample;
import cm.cn.service.HlWaybillService;
@Service
public class HlWaybillServiceImpl implements HlWaybillService {
	@Autowired
	private MyWaybillMapper myWaybillMapper;
	@Autowired
	private HlWaybillMapper hlWaybillMapper;
	@Override
	public List<HlWaybill> selAllWaybill() {
		CustomerContextHolder.setCustomerType(CustomerContextHolder.DATA_SOURCE_GENERAL);
		HlWaybillExample example = new HlWaybillExample();
		// TODO Auto-generated method stub
		return hlWaybillMapper.selectByExample(example);
	}
	@Override
	public List<HlWaybill> selWaybillByCompanyId(int company_id) {
		CustomerContextHolder.setCustomerType(CustomerContextHolder.DATA_SOURCE_GENERAL);
		// TODO Auto-generated method stub
		HlWaybillExample example = new HlWaybillExample();
		HlWaybillExample.Criteria criteria = example.createCriteria();
		criteria.andCompayIdEqualTo(company_id);
		return hlWaybillMapper.selectByExample(example);
	}
	@Override
	public int insertWaybill(HlWaybill hlWaybill) {
		CustomerContextHolder.setCustomerType(CustomerContextHolder.DATA_SOURCE_GENERAL);
		// TODO Auto-generated method stub
		return hlWaybillMapper.insertSelective(hlWaybill);
	}

	@Override
	public int delWaybillBatch(int[] arrays) {
		CustomerContextHolder.setCustomerType(CustomerContextHolder.DATA_SOURCE_GENERAL);
		// TODO Auto-generated method stub
		return myWaybillMapper.delWaybillBatch(arrays);
	}

	@Override
	public int updateWaybill(HlWaybill hlWaybill) {
		CustomerContextHolder.setCustomerType(CustomerContextHolder.DATA_SOURCE_GENERAL);
		// TODO Auto-generated method stub
		return hlWaybillMapper.updateByPrimaryKeySelective(hlWaybill);
	}

	@Override
	public List<HlWaybill> selectWayBillByDriverName(String name) {
		CustomerContextHolder.setCustomerType(CustomerContextHolder.DATA_SOURCE_GENERAL);
		// TODO Auto-generated method stub
		return myWaybillMapper.selectWayBillByDriverName(name);
	}

	@Override
	public HlWaybill selectById(int id) {
		CustomerContextHolder.setCustomerType(CustomerContextHolder.DATA_SOURCE_GENERAL);
		// TODO Auto-generated method stub
		return hlWaybillMapper.selectByPrimaryKey(id);
	}
	@Override
	public List<HlWaybill> selWaybillByShipper(String Shipper) {
		// TODO Auto-generated method stub
		HlWaybillExample example = new HlWaybillExample();
		HlWaybillExample.Criteria criteria = example.createCriteria();
		criteria.andShipperEqualTo(Shipper);
		return hlWaybillMapper.selectByExample(example);
	}


}
