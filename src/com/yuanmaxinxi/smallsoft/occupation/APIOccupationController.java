package com.yuanmaxinxi.smallsoft.occupation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yuanmaxinxi.entity.occupation.Occupation;
import com.yuanmaxinxi.service.OccupationService;

@RequestMapping("/soft/occupation")
@Controller
@CrossOrigin
public class APIOccupationController{
	@Autowired
	private OccupationService occupationService;
	/**
	 * 获取具有层次关系的所有职业
	 * @return
	 */
	@RequestMapping("/listLayer")
	@ResponseBody
	public List<Occupation> listLayer(){
		return occupationService.selectAllByLayer();
	}
	@RequestMapping("/list")
	@ResponseBody
	public List<Occupation> list(String name){
		return occupationService.selectByName(name);
	}
	
	/**
	 * 根据专业查职业
	 * @param mId 专业Id
	 * @return
	 */
	@RequestMapping("/listBymId")
	@ResponseBody
	public List<Occupation> listBymId(Long mId){
		return occupationService.selectBymId(mId);
	}
}