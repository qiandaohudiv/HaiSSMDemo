package cm.cn.po;

public class Gpsinfo {
    private String maxVelocity;
    private String OverSpeedVelocityTimes;
    private String status;
	private Gpsinfo20171128 gpsinfo20171128;
	public String getMaxVelocity() {
		return maxVelocity;
	}
	public void setMaxVelocity(String maxVelocity) {
		this.maxVelocity = maxVelocity;
	}
	public String getOverSpeedVelocityTimes() {
		return OverSpeedVelocityTimes;
	}
	public void setOverSpeedVelocityTimes(String overSpeedVelocityTimes) {
		OverSpeedVelocityTimes = overSpeedVelocityTimes;
	}
    public String getStatus() {
	  	return status;
	}
	public void setStatus(String status) {
	  	this.status = status;
	}
	public Gpsinfo20171128 getGpsinfo20171128() {
		return gpsinfo20171128;
	}
	public void setGpsinfo20171128(Gpsinfo20171128 gpsinfo20171128) {
		this.gpsinfo20171128 = gpsinfo20171128;
	}
    
}
