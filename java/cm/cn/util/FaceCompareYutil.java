package cm.cn.util;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.youtu.Youtu;

public class FaceCompareYutil {
//	public static final String APP_ID = "10100648";
	public static final String APP_ID = "10113173";
	public static final String SECRET_ID = "AKIDc7b5d8QmA2oOn3RCze7OSxJTQ8viM5JY";
	public static final String SECRET_KEY = "C9sQkpfGcnjV3NSJvJTO3RdZFfpjNlvq";
	public static final String USER_ID = "123456";
	public static Map<String, Integer> faceCompare(String imageA,String imageB) throws KeyManagementException, NoSuchAlgorithmException, IOException, JSONException{
		Map<String, Integer> map = new HashMap<String, Integer>();
		Youtu faceYoutu = new Youtu(APP_ID, SECRET_ID, SECRET_KEY,Youtu.API_YOUTU_END_POINT,USER_ID);
		JSONObject object = faceYoutu.FaceCompareUrl(imageA, imageB);
		if (object.has("fail_flag")) {
//			System.out.println("µÚ"+object.getInt("fail_flag")+"²»·ûºÏÌõ¼þ");
			map.put("fail",object.getInt("fail_flag"));
		}
		else{
//			System.out.println(object.getInt("similarity"));
			map.put("similarity", object.getInt("similarity"));
		}
		return map;
	}
}
