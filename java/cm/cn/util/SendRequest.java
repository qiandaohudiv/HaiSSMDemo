package cm.cn.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import org.json.JSONException;
import org.json.JSONObject;

import com.youtu.Youtu;
import com.youtu.sign.Base64Util;
import com.youtu.sign.YoutuSign;

public class SendRequest {
	// 30 days
	private static int EXPIRED_SECONDS = 2592000;
//	public static final String APP_ID = "10100648";
	public static final String m_appid = "10113173";
//	public static final String SECRET_ID = "AKIDCCwSijHzEEySowKSr0sTosgDUUN0aVO1";
	public static final String m_secret_id = "AKIDc7b5d8QmA2oOn3RCze7OSxJTQ8viM5JY";
//	public static final String SECRET_KEY = "VllW1GEvMXR4RXNwINmwpZ5LNAi16wXu";
	public static final String m_secret_key = "T2b9g1PoEe4z03AHOVchnu17DJ5tnhbU";
	public static final String m_user_id = "1846548223";
	public static JSONObject SendHttpRequest(JSONObject postData, String mothod)
			throws IOException, JSONException, KeyManagementException, NoSuchAlgorithmException {

				StringBuffer mySign = new StringBuffer("");
				YoutuSign.appSign(m_appid, m_secret_id, m_secret_key,
					System.currentTimeMillis() / 1000 + EXPIRED_SECONDS,
					m_user_id, mySign);

				System.setProperty("sun.net.client.defaultConnectTimeout", "30000");
				System.setProperty("sun.net.client.defaultReadTimeout", "30000");
				URL url = new URL(Youtu.API_YOUTU_END_POINT + mothod);

				HttpURLConnection connection = (HttpURLConnection) url.openConnection();

				connection.setRequestMethod("POST");
				connection.setRequestProperty("accept", "*/*");
				connection.setRequestProperty("user-agent", "youtu-java-sdk");
				connection.setRequestProperty("Authorization", mySign.toString());

				connection.setDoOutput(true);
				connection.setDoInput(true);
				connection.setUseCaches(false);
				connection.setInstanceFollowRedirects(true);
				connection.setRequestProperty("Content-Type", "text/json");
				connection.connect();

				DataOutputStream out = new DataOutputStream(
					connection.getOutputStream());

				postData.put("app_id", m_appid);
				out.write(postData.toString().getBytes("utf-8"));
				out.flush();
				out.close();
				BufferedReader reader = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));
				String lines;
				StringBuffer resposeBuffer = new StringBuffer("");
				while ((lines = reader.readLine()) != null) {
					lines = new String(lines.getBytes(), "utf-8");
					resposeBuffer.append(lines);
				}
				// System.out.println(resposeBuffer+"\n");
				reader.close();
				connection.disconnect();

				JSONObject respose = new JSONObject(resposeBuffer.toString());

				return respose;

			}
	public static void GetBase64FromFile(String filePath, StringBuffer base64)
			throws IOException {
				File imageFile = new File(filePath);
				if (imageFile.exists()) {
					InputStream in = new FileInputStream(imageFile);
					byte data[] = new byte[(int) imageFile.length()];
					in.read(data);
					in.close();
					base64.append(Base64Util.encode(data));

				} else {
					throw new FileNotFoundException(filePath + " not exist");
				}

	}
	public static JSONObject compareByBase64(String base64a,String base64b) throws JSONException, KeyManagementException, NoSuchAlgorithmException, IOException{
		JSONObject data = new JSONObject();
//		GetBase64FromFile(image_path_a, image_data);
		data.put("imageA", base64a);
//		image_data.setLength(0);
//		GetBase64FromFile(image_path_b, image_data);
		data.put("imageB", base64b);
		JSONObject respose = SendHttpRequest(data, "api/facecompare");
		return respose;
	}
}
