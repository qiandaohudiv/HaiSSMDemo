package cm.cn.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cm.cn.po.HlCompany;
import cm.cn.po.Page;
import cm.cn.service.HlCompanyService;

@Controller
public class HlCompanyController {
	@Autowired
	private HlCompanyService hlCompanyService;
	@RequestMapping("/selectCompanyByName")
	@ResponseBody
	public Page<HlCompany> selectCompanyByName(int current,int pageNum,String name){
		//String str = null ;
		//try {
		//	str = new String(name.getBytes("ISO-8859-1"),"UTF-8");
		//} catch (UnsupportedEncodingException e) {
		//	e.printStackTrace();
		//}
		List<HlCompany> list = hlCompanyService.selectCompanyByName(name);
		Page<HlCompany> page = null;
		if (list.size()>0) {
			page = new Page<>(current, pageNum, list);
			return page;
		}
		else {
			return null;
		}
	}
	@RequestMapping("/allCompany")
	@ResponseBody
	public Page<HlCompany> allCompany(int current,int pageNum){
		List<HlCompany> list =hlCompanyService.selAllCompays();
		Page<HlCompany> page = null;
		if (list.size()>0) {
			page = new Page<>(current, pageNum,  list);
			return page;
		}
		else {
			return null;
		}
	}
	@RequestMapping("/getCompanyList")
	@ResponseBody
	public List<HlCompany> getSafeCardsList(){
		return hlCompanyService.selAllCompays();
	}
	@RequestMapping(value="/insertCompany",method=RequestMethod.POST)
	@ResponseBody
	public int insertCompany(HlCompany hlCompany){
		return hlCompanyService.insertCompany(hlCompany);
	}
	@RequestMapping(value="/updateCompany",method=RequestMethod.POST)
	@ResponseBody
	public int updateCompany(HlCompany hlCompany){
		return hlCompanyService.updateCompany(hlCompany);
	}
	@RequestMapping(value="/delCompanyBatch")
	@ResponseBody
	public int delCompanyBatch(int[] arrays){
		return hlCompanyService.delCompanyBatch(arrays);
	}
	@RequestMapping("/selectCompanyByID")
	@ResponseBody
	public HlCompany selectCompanyByID(int id){
		return hlCompanyService.selectCompanyById(id);
	}
}
