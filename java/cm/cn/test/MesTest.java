package cm.cn.test;

import cm.cn.util.GetCheckCode;

public class MesTest {

	public static void main(String[] args) {
		String phone = "17601510248";
		String check_code="987654";
		GetCheckCode.getCode(phone, check_code);

	}

}
