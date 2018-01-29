package cm.cn.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cm.cn.po.HlSafecard;
import cm.cn.po.Page;
import cm.cn.service.HlSafeCardService;

@Controller
public class HlSafeCardController {
	@Autowired
	private HlSafeCardService hlSafeCardService;
	@RequestMapping("/selectBySafeCardName")
	@ResponseBody
	public Page<HlSafecard> selectBySafeCardName(int current,int pageNum,String safeCardName){
		
		List<HlSafecard> list = hlSafeCardService.selectBySafeCardName(safeCardName);
		Page<HlSafecard> page = null;
		if (list.size()>0) {
			page = new Page<>(current, pageNum, list);
			return page;
		}
		else {
			return null;
		}
	}
	@RequestMapping("/allSafeCards")
	@ResponseBody
	public Page<HlSafecard> allSafeCards(int current,int pageNum){
		List<HlSafecard> list = hlSafeCardService.selectAllSafeCard();
		Page<HlSafecard> page = null;
		if (list.size()>0) {
			page = new Page<>(current, pageNum, list);
			return page;
		}
		else {
			return null;
		}
	}
	@RequestMapping("/getSafeCardsList")
	@ResponseBody
	public List<HlSafecard> getSafeCardsList(){
		return hlSafeCardService.selectAllSafeCard();
	}
	@RequestMapping("/upfiles")
	@ResponseBody
	public List<String> upsafecardfiles(@RequestParam MultipartFile[] file){
		List<String> list = new ArrayList<>();
		 //获取文件名  
		String safeCardFileName = file[0].getOriginalFilename();
	    String introductionFileName = file[1].getOriginalFilename(); 
	    //文件扩展名
	    String newsafeCardFileName = "safeCard"+UUID.randomUUID()+safeCardFileName.substring(safeCardFileName.lastIndexOf("."));
	    String newintroductionFileName = "introduction"+UUID.randomUUID()+introductionFileName.substring(introductionFileName.lastIndexOf("."));  
	    // 存储视屏的物理路径"D:\\"
 	 	String fileSavepath = "D:\\SafeCard\\";
// 	 	String pic_path = "D:\\";
	 	// 新视屏
		File introductionFile = new File(fileSavepath + newintroductionFileName);
		File safeCardFile = new File(fileSavepath+newsafeCardFileName);
		
		list.add(safeCardFile.getAbsolutePath());
		list.add(introductionFile.getAbsolutePath());
		list.add(newsafeCardFileName);
		list.add(newintroductionFileName);
		try {
			file[0].transferTo(safeCardFile);
			file[1].transferTo(introductionFile);
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}
		return list;
	}
	@RequestMapping(value= "/insertSafeCard",method=RequestMethod.POST)
	@ResponseBody
	public int insertSafeCard(@RequestBody HlSafecard record){
		record.setCreateTime(String.valueOf(System.currentTimeMillis()));
		return hlSafeCardService.insertSafeCard(record);
	}
	@RequestMapping(value= "/updateSafeCard",method=RequestMethod.POST)
	@ResponseBody
	public int updateSafeCard(@RequestBody HlSafecard record){
		return hlSafeCardService.updateSafeCardById(record);
	}
	@RequestMapping(value= "/delSafeCardBatch")
	@ResponseBody
	public int delSafeCardBatch(int[] arrays){
		return hlSafeCardService.delSafeCardBatch(arrays);
	}
}
