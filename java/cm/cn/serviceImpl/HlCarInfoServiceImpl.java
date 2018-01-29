package cm.cn.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cm.cn.datasource.CustomerContextHolder;
import cm.cn.mapper.HlCarinfoMapper;
import cm.cn.mapper.MyCarInfoMapper;
import cm.cn.po.HlCarinfo;
import cm.cn.po.HlCarinfoExample;
import cm.cn.service.HlCarInfoService;
@Service
public class HlCarInfoServiceImpl implements HlCarInfoService {
	@Autowired
	private MyCarInfoMapper myCarInfoMapper;
	@Autowired
	private HlCarinfoMapper hlCarinfoMapper;
	@Override
	public List<HlCarinfo> selAllCar() {
		CustomerContextHolder.setCustomerType(CustomerContextHolder.DATA_SOURCE_GENERAL);
//		HlCarinfoExample example = new HlCarinfoExample();
		return hlCarinfoMapper.selectByExample(null);
	}

	@Override
	public int delCarBatch(int[] arrays) {
		CustomerContextHolder.setCustomerType(CustomerContextHolder.DATA_SOURCE_GENERAL);
		// TODO Auto-generated method stub
		return myCarInfoMapper.delCarInfoBatch(arrays);
	}

	@Override
	public int insertCar(HlCarinfo carinfo) {
		CustomerContextHolder.setCustomerType(CustomerContextHolder.DATA_SOURCE_GENERAL);
		// TODO Auto-generated method stub
		return hlCarinfoMapper.insertSelective(carinfo);
	}

	@Override
	public int updateCar(HlCarinfo carinfo) {
		CustomerContextHolder.setCustomerType(CustomerContextHolder.DATA_SOURCE_GENERAL);
		// TODO Auto-generated method stub
		return hlCarinfoMapper.updateByPrimaryKeySelective(carinfo);
	}

	@Override
	public List<HlCarinfo> selectByCarLicense(String carLicense) {
		CustomerContextHolder.setCustomerType(CustomerContextHolder.DATA_SOURCE_GENERAL);
		HlCarinfoExample example = new HlCarinfoExample();
		HlCarinfoExample.Criteria criteria = example.createCriteria();
		criteria.andCarNumEqualTo(carLicense);
		return hlCarinfoMapper.selectByExample(example);
	}

	@Override
	public List<HlCarinfo> selectByCompanyName(String company_name) {
		CustomerContextHolder.setCustomerType(CustomerContextHolder.DATA_SOURCE_GENERAL);
		HlCarinfoExample example = new HlCarinfoExample();
		HlCarinfoExample.Criteria criteria = example.createCriteria();
		criteria.andCompanyNameEqualTo(company_name);
		return hlCarinfoMapper.selectByExample(example);
	}

}
