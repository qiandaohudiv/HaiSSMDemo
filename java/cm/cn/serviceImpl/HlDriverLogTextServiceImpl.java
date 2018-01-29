package cm.cn.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cm.cn.datasource.CustomerContextHolder;
import cm.cn.mapper.HlDriverlogTextMapper;
import cm.cn.mapper.MyTextSelectMapper;
import cm.cn.po.HlDriverlogText;
import cm.cn.service.HlDriverLogTextService;
@Service
public class HlDriverLogTextServiceImpl implements HlDriverLogTextService {
	@Autowired
	private HlDriverlogTextMapper hlDriverlogTextMapper;
	@Autowired
	private MyTextSelectMapper myTextSelectMapper;
	@Override
	public List<HlDriverlogText> selectAllDriverlogText() {
		CustomerContextHolder.setCustomerType(CustomerContextHolder.DATA_SOURCE_GENERAL);
		return hlDriverlogTextMapper.selectByExample(null);
	}

	@Override
	public List<HlDriverlogText> selectDriverLogsById(int[] array) {
		CustomerContextHolder.setCustomerType(CustomerContextHolder.DATA_SOURCE_GENERAL);
		return myTextSelectMapper.selectHlDriverlogTextByArray(array);
	}

}
