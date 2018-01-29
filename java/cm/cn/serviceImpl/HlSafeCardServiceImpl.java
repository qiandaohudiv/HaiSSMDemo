package cm.cn.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cm.cn.datasource.CustomerContextHolder;
import cm.cn.mapper.HlSafecardMapper;
import cm.cn.mapper.MyGoodsInfoMapper;
import cm.cn.mapper.MySafeCardMapper;
import cm.cn.po.HlSafecard;
import cm.cn.po.HlSafecardExample;
import cm.cn.service.HlSafeCardService;
@Service
public class HlSafeCardServiceImpl implements HlSafeCardService {
	@Autowired
	private HlSafecardMapper hlSafecardMapper;
	@Autowired
	private MySafeCardMapper mySafeCardMapper;
	@Autowired
	private MyGoodsInfoMapper myGoodsInfoMapper;
	@Override
	public List<HlSafecard> selectAllSafeCard() {
		CustomerContextHolder.setCustomerType(CustomerContextHolder.DATA_SOURCE_GENERAL);
		HlSafecardExample example = new HlSafecardExample();
		return hlSafecardMapper.selectByExample(example);
	}

	@Override
	public int updateSafeCardById(HlSafecard hlSafecard) {
		CustomerContextHolder.setCustomerType(CustomerContextHolder.DATA_SOURCE_GENERAL);
		// TODO Auto-generated method stub
		return hlSafecardMapper.updateByPrimaryKeySelective(hlSafecard);
	}

	@Override
	public int insertSafeCard(HlSafecard hlSafecard) {
		CustomerContextHolder.setCustomerType(CustomerContextHolder.DATA_SOURCE_GENERAL);
		// TODO Auto-generated method stub
		return hlSafecardMapper.insertSelective(hlSafecard);
	}

	@Override
	public int delSafeCardBatch(int[] array) {
		CustomerContextHolder.setCustomerType(CustomerContextHolder.DATA_SOURCE_GENERAL);
		// TODO Auto-generated method stub
		myGoodsInfoMapper.delGoodsInfoBatchBySafeCardID(array);
		return mySafeCardMapper.delSafeCardBatch(array);
	}

	@Override
	public List<HlSafecard> selectBySafeCardName(String safeCardName) {
		CustomerContextHolder.setCustomerType(CustomerContextHolder.DATA_SOURCE_GENERAL);
		HlSafecardExample example = new HlSafecardExample();
		HlSafecardExample.Criteria criteria = example.createCriteria();
		criteria.andSafeCardNameEqualTo(safeCardName);
		return hlSafecardMapper.selectByExample(example);
	}

}
