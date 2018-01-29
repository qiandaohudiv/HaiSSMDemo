package cm.cn.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cm.cn.datasource.CustomerContextHolder;
import cm.cn.mapper.HlCheckTextMapper;
import cm.cn.mapper.MyTextSelectMapper;
import cm.cn.po.HlCheckText;
import cm.cn.service.HlCheckTextService;
@Service
public class HlCheckTextServiceImpl implements HlCheckTextService {
	@Autowired
	private HlCheckTextMapper hlCheckTextMapper;
	@Autowired
	private MyTextSelectMapper myTextSelectMapper;
	@Override
	public List<HlCheckText> selectAll() {
		CustomerContextHolder.setCustomerType(CustomerContextHolder.DATA_SOURCE_GENERAL);
		return hlCheckTextMapper.selectByExample(null);
	}

	@Override
	public List<HlCheckText> selectHlCheckTextByIdArrays(int[] array) {
		CustomerContextHolder.setCustomerType(CustomerContextHolder.DATA_SOURCE_GENERAL);
		return myTextSelectMapper.selectHlCheckTextByArray(array);
	}

}
