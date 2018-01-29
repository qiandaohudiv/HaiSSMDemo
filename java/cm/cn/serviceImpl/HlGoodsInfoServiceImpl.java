package cm.cn.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cm.cn.datasource.CustomerContextHolder;
import cm.cn.mapper.HlGoodsinfoMapper;
import cm.cn.mapper.MyGoodsInfoMapper;
import cm.cn.po.HlGoodsinfo;
import cm.cn.po.HlGoodsinfoExample;
import cm.cn.service.HlGoodsInfoService;
@Service
public class HlGoodsInfoServiceImpl implements HlGoodsInfoService {
	@Autowired
	private HlGoodsinfoMapper hlGoodsinfoMapper;
	@Autowired
	private MyGoodsInfoMapper myGoodsInfoMapper;
	@Override
	public List<HlGoodsinfo> selectAllGoodsInfo() {
		CustomerContextHolder.setCustomerType(CustomerContextHolder.DATA_SOURCE_GENERAL);
		HlGoodsinfoExample example = new HlGoodsinfoExample();
		return hlGoodsinfoMapper.selectByExample(example);
	}

	@Override
	public int insertHlGoodsinfo(HlGoodsinfo goodsinfo) {
		CustomerContextHolder.setCustomerType(CustomerContextHolder.DATA_SOURCE_GENERAL);
		return hlGoodsinfoMapper.insertSelective(goodsinfo);
	}

	@Override
	public int delHlGoodsinfo(int[] arrays) {
		CustomerContextHolder.setCustomerType(CustomerContextHolder.DATA_SOURCE_GENERAL);
		return myGoodsInfoMapper.delGoodsInfoBatch(arrays);
	}

	@Override
	public int updateHlGoodsinfo(HlGoodsinfo goodsinfo) {
		CustomerContextHolder.setCustomerType(CustomerContextHolder.DATA_SOURCE_GENERAL);
		return hlGoodsinfoMapper.updateByPrimaryKeySelective(goodsinfo);
	}

	@Override
	public List<HlGoodsinfo> selectGoodsInfoByName(String name) {
		CustomerContextHolder.setCustomerType(CustomerContextHolder.DATA_SOURCE_GENERAL);
		HlGoodsinfoExample example = new HlGoodsinfoExample();
		HlGoodsinfoExample.Criteria criteria = example.createCriteria();
		criteria.andNameEqualTo(name);
		return hlGoodsinfoMapper.selectByExample(example);
	}

}
