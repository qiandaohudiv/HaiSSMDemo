package cm.cn.test;

public class BitTest {
  public static void main(String[] args) {
	  Integer status = 1;
	  String s = Integer.toBinaryString(status);
	  System.out.println(s);
	  if(s.length()>=2){ 
	  System.out.println(s.substring(s.length()-2, s.length()));
	  }else{
		  System.out.println(s);
	  }
}
}
