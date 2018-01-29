package cm.cn.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cm.cn.po.HlDriveruser;
import cm.cn.po.HlPeople;
import cm.cn.po.Page;
import cm.cn.service.HlDriveruserService;
import cm.cn.service.HlPeopleService;

@Controller
public class HlPeopleController {
	@Autowired
	private HlPeopleService hlPeopleService;
	@Autowired
	private HlDriveruserService hlDriveruserService;
	@RequestMapping("/selectPeopleById")
	@ResponseBody
	public Page<HlPeople> selectPeopleById(int current,int pageNum,int id){
		List<HlPeople> list = hlPeopleService.selectByCompanyId(id);
		Page<HlPeople> page = null;
		if (list.size()>0) {
			page = new Page<>(current, pageNum, list);
			return page;
		}
		else {
			return null;
		}
	}
	@RequestMapping("/selectPeopleByName")
	@ResponseBody
	public Page<HlPeople> selectPeopleByName(int current,int pageNum,String name){
		//String str = null ;
		//try {
		//	str = new String(name.getBytes("ISO-8859-1"),"UTF-8");
		//} catch (UnsupportedEncodingException e) {
			//e.printStackTrace();
		//}
		List<HlPeople> list = hlPeopleService.selectPeopleByName(name);
		Page<HlPeople> page = null;
		if (list.size()>0) {
			page = new Page<>(current, pageNum, list);
			return page;
		}
		else {
			return null;
		}
	}
	@RequestMapping("/upPicture")
	@ResponseBody
	public List<String> upPicture(MultipartFile file){
		List<String> list = new ArrayList<>();
		String originFileName = file.getOriginalFilename();
		String newFileName = "photo"+UUID.randomUUID()+originFileName.substring(originFileName.lastIndexOf("."));
		String parentsPath = "D:\\hailiangpic\\";
		File photo = new File(parentsPath+newFileName);
		try {
			file.transferTo(photo);
			list.add(newFileName);
			list.add(parentsPath+newFileName);
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}
		return list;
	}
	@RequestMapping("/allPeople")
	@ResponseBody
	public Page<HlPeople> allPeople(int current,int pageNum){
		List<HlPeople> list =hlPeopleService.selAllPeople();
		Page<HlPeople> page = null;
		if (list.size()>0) {
			page = new Page<>(current, pageNum,  list);
			return page;
		}
		else {
			return null;
		}
	}
	@RequestMapping(value="/insertPeople",method=RequestMethod.POST)
	@ResponseBody
	public int insertPeople(HlPeople hlPeople){
		HlDriveruser hlDriveruser = new HlDriveruser();
		int num = hlPeopleService.insertPeople(hlPeople);
		if (num>0&&hlPeople.getPeopleType().equals("1")) {
			hlDriveruser.setHlPeopleId(hlPeople.getId());
			System.out.println(hlPeople.getId());
			hlDriveruser.setPhone(hlPeople.getPhone());
			hlDriveruser.setName(hlPeople.getName());
			hlDriveruserService.insertHlDriveruser(hlDriveruser);
		}
		return num;
	}
	@RequestMapping(value="/updatePeople",method=RequestMethod.POST)
	@ResponseBody
	public int updatePeople(HlPeople hlPeople){
		return hlPeopleService.updatePeople(hlPeople);
	}
	@RequestMapping(value="/delPeopleBatch")
	@ResponseBody
	public int delPeopleBatch(int[] arrays){
		hlDriveruserService.delHlDriveruser(arrays);
		return hlPeopleService.delPeopleBatch(arrays);
	}
}
