package cm.cn.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cm.cn.datasource.CustomerContextHolder;
import cm.cn.mapper.HlGoodstypeMapper;
import cm.cn.mapper.MyGoodsInfoMapper;
import cm.cn.mapper.MyGoodsTypeMapper;
import cm.cn.po.HlGoodstype;
import cm.cn.po.HlGoodstypeExample;
import cm.cn.service.HlGoodsTypeService;
@Service
public class HlGoodsTypeServiceImpl implements HlGoodsTypeService {
	@Autowired
	private HlGoodstypeMapper hlGoodsTypeMapper;
	@Autowired
	private MyGoodsTypeMapper myGoodsTypeMapper;
	@Autowired
	private MyGoodsInfoMapper myGoodsInfoMapper;
	@Override
	public List<HlGoodstype> selectAllGoodsType() {
		CustomerContextHolder.setCustomerType(CustomerContextHolder.DATA_SOURCE_GENERAL);
		HlGoodstypeExample example = new HlGoodstypeExample();
		return hlGoodsTypeMapper.selectByExample(example);
	}

	@Override
	public int insertGoodsType(HlGoodstype hlGoodstype) {
		CustomerContextHolder.setCustomerType(CustomerContextHolder.DATA_SOURCE_GENERAL);
		// TODO Auto-generated method stub
		return hlGoodsTypeMapper.insertSelective(hlGoodstype);
	}

	@Override
	public int updateGoodsType(HlGoodstype hlGoodstype) {
		CustomerContextHolder.setCustomerType(CustomerContextHolder.DATA_SOURCE_GENERAL);
		// TODO Auto-generated method stub
		return hlGoodsTypeMapper.updateByPrimaryKeySelective(hlGoodstype);
	}

	@Override
	public int delGoodsTypeBatch(int[] arrays) {
		CustomerContextHolder.setCustomerType(CustomerContextHolder.DATA_SOURCE_GENERAL);
		// TODO Auto-generated method stub
		myGoodsInfoMapper.delGoodsInfoBatchByGoodsTypeId(arrays);
		return myGoodsTypeMapper.delGoodsTypeBatch(arrays);
	}

	@Override
	public List<HlGoodstype> selectByName(String name) {
		CustomerContextHolder.setCustomerType(CustomerContextHolder.DATA_SOURCE_GENERAL);
		HlGoodstypeExample example = new HlGoodstypeExample();
		HlGoodstypeExample.Criteria criteria = example.createCriteria();
		criteria.andNameEqualTo(name);
		return hlGoodsTypeMapper.selectByExample(example);
	}

}
