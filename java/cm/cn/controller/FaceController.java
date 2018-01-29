package cm.cn.controller;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cm.cn.util.SendRequest;

@Controller
public class FaceController {
	@RequestMapping(value="/faceCompare",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Integer> faceCompare(String imageA,String imageB){
		Map<String, Integer> map = new HashMap<String, Integer>();
		try {
			JSONObject object = SendRequest.compareByBase64(imageA, imageB);
			if (object.has("fail_flag")) {
//				System.out.println("第"+object.getInt("fail_flag")+"不符合条件");
				map.put("fail",object.getInt("fail_flag"));
			}
			else{
//				System.out.println(object.getInt("similarity"));
				map.put("similarity", object.getInt("similarity"));
			}
		} catch (KeyManagementException | NoSuchAlgorithmException | IOException | JSONException e) {
			e.printStackTrace();
		}
		return map;
	}
	@RequestMapping(value="/faceCompares",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Integer> faceCompares(String filepathA,String imageB){
		Map<String, Integer> map = new HashMap<String, Integer>();
		StringBuffer base64a = new StringBuffer("");
		try {
			SendRequest.GetBase64FromFile(filepathA, base64a);
			JSONObject object = SendRequest.compareByBase64(base64a.toString(), imageB);
			if (object.has("fail_flag")) {
//				System.out.println("第"+object.getInt("fail_flag")+"不符合条件");
				map.put("fail",object.getInt("fail_flag"));
			}
			else{
//				System.out.println(object.getInt("similarity"));
				map.put("similarity", object.getInt("similarity"));
			}
		} catch (KeyManagementException | NoSuchAlgorithmException | IOException | JSONException e) {
			e.printStackTrace();
		}
		return map;
	}
}
