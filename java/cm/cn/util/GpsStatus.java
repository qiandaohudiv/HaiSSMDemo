package cm.cn.util;

public class GpsStatus {
   public static String statusdeal(Integer status){
		String s = Integer.toBinaryString(status);
	    if(s.length()>=2){ 
	         s=s.substring(s.length()-2, s.length());
	    }
	   if(s.equals("0")||s.equals("00")){
		   s = "车辆停止,未定位";
	   }else if(s.equals("1")||s.equals("01")){
		   s = "车辆启动或行驶,未定位";
	   }else if(s.equals("10")){
		   s = "车辆停止,定位开";
	   }else if(s.equals("11")){
		   s = "车辆启动或行驶,定位开";
	   }else{
		   s = "读取状态出现错误";
	   }
	   return s;
   }
}
