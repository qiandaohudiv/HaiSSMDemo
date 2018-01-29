package cm.cn.service;


import cm.cn.po.Gpsinfo;
import cm.cn.po.Gpsinfo20171128;

public interface HlGpsService {
	public Gpsinfo20171128 selectGpsByPlateNo(String plateNo);
	public Gpsinfo selectGpsByPlate(String plateNo);
}
