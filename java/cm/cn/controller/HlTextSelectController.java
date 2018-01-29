package cm.cn.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cm.cn.po.HlCheckText;
import cm.cn.po.HlDriverlogText;
import cm.cn.service.HlCheckTextService;
import cm.cn.service.HlDriverLogTextService;

@Controller
public class HlTextSelectController {
	@Autowired
	private HlCheckTextService hlCheckTextService;
	@Autowired
	private HlDriverLogTextService hlDriverLogTextService;
	@RequestMapping("/allDriverLogText")
	@ResponseBody
	public List<HlDriverlogText> allDriverLogText(){
		return hlDriverLogTextService.selectAllDriverlogText();
	}
	@RequestMapping("/allCheckText")
	@ResponseBody
	public List<HlCheckText> allCheckText(){
		return hlCheckTextService.selectAll();
	}
	@RequestMapping("/selDriverLogTextArray")
	@ResponseBody
	public List<HlDriverlogText> selDriverLogTextArray(int[] array){
		return hlDriverLogTextService.selectDriverLogsById(array);
	}
	@RequestMapping("/selCheckTextArray")
	@ResponseBody
	public List<HlCheckText> allWaybill(int[] array){
		return hlCheckTextService.selectHlCheckTextByIdArrays(array);
	}
}
