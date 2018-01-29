package cm.cn.test;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import cm.cn.util.GetCheckCode;
import cm.cn.util.SendRequest;

public class HaiTest {
	@Test
	public void testMsg(){
		String phone = "15927062311" ;
		String code = "asdqwe";
		boolean flag = GetCheckCode.getCode(phone, code);
		System.out.println(flag);
	}
	@Test
	public void tesatMsg() throws IOException, KeyManagementException, NoSuchAlgorithmException, JSONException{
		StringBuffer data = new StringBuffer("");
		StringBuffer data1 = new StringBuffer("");
		SendRequest.GetBase64FromFile("D:\\202127.jpg", data);
		SendRequest.GetBase64FromFile("D:\\202127.jpg", data1);
		JSONObject p = SendRequest.compareByBase64(data.toString(), data1.toString());
		System.out.println(p);
		SendRequest.GetBase64FromFile("D:\\0170927175339.jpg", data);
		SendRequest.GetBase64FromFile("D:\\0170927175339.jpg", data1);
		System.out.println(SendRequest.compareByBase64(data.toString(), data1.toString()));
//		StringBuffer data = new StringBuffer("");
//		StringBuffer data1 = new StringBuffer("");
//		SendRequest.GetBase64FromFile("D:\\0170929201700.jpg", data);
//		SendRequest.GetBase64FromFile("D:\\0170929201700.jpg", data1);
//		JSONObject p = SendRequest.compareByBase64(data.toString(), data1.toString());
//		System.out.println(p);
	}
	
}
