package cm.cn.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cm.cn.datasource.CustomerContextHolder;
import cm.cn.mapper.HlDriveruserMapper;
import cm.cn.mapper.MyDriveruserMapper;
import cm.cn.po.HlDriveruser;
import cm.cn.po.HlDriveruserExample;
import cm.cn.po.HlPeople;
import cm.cn.service.HlDriveruserService;
@Service
public class HlDriveruserServiceImpl implements HlDriveruserService {
	@Autowired
	private HlDriveruserMapper hlDriveruserMapper;
	@Autowired
	private MyDriveruserMapper myDriveruserMapper;
	@Override
	public int insertHlDriveruser(HlDriveruser hlDriveruser) {
		CustomerContextHolder.setCustomerType(CustomerContextHolder.DATA_SOURCE_GENERAL);
		return hlDriveruserMapper.insertSelective(hlDriveruser);
	}

	@Override
	public int updateHlDriveruser(HlDriveruser hlDriveruser) {
		CustomerContextHolder.setCustomerType(CustomerContextHolder.DATA_SOURCE_GENERAL);
		return hlDriveruserMapper.updateByPrimaryKeySelective(hlDriveruser);
	}

	@Override
	public int delHlDriveruser(int[] arrays) {
		CustomerContextHolder.setCustomerType(CustomerContextHolder.DATA_SOURCE_GENERAL);
		return myDriveruserMapper.delDriveruserBatch(arrays);
	}

	@Override
	public List<HlDriveruser> selByPhone(String phone) {
		CustomerContextHolder.setCustomerType(CustomerContextHolder.DATA_SOURCE_GENERAL);
		HlDriveruserExample example = new HlDriveruserExample();
		HlDriveruserExample.Criteria criteria = example.createCriteria();
		criteria.andPhoneEqualTo(phone);
		return hlDriveruserMapper.selectByExample(example);
	}

	@Override
	public HlPeople selectDriverUserMsgByPhone(String phone) {
		CustomerContextHolder.setCustomerType(CustomerContextHolder.DATA_SOURCE_GENERAL);
		// TODO Auto-generated method stub
		return myDriveruserMapper.selectDriverUserMsgByPhone(phone);
	}

	@Override
	public String selectCompanyLinkManPhone(String phone) {
		CustomerContextHolder.setCustomerType(CustomerContextHolder.DATA_SOURCE_GENERAL);
		// TODO Auto-generated method stub
		return myDriveruserMapper.selectCompanyLinkManPhone(phone);
	}

}
