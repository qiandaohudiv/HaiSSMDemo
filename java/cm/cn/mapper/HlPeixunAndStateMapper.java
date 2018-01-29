package cm.cn.mapper;

import java.util.List;

import cm.cn.po.HlPeixunAndState;

public interface HlPeixunAndStateMapper {
  public List<HlPeixunAndState> selectPeixuAndState(String driverpeopleName);
}
