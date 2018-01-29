package cm.cn.test;


import javax.annotation.Resource;

import cm.cn.service.HlGoodsTypeService;



public class Test {
	@Resource
	private HlGoodsTypeService hlGoodsTypeService;
	
	@org.junit.Test
	public void allUser(){
		//List list = hlGoodsTypeService.selectAllGoodsType();
		//Iterator it = list.iterator();
		//while(it.hasNext()){
		//    System.out.println(it.next());
		//}

}

}
