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
	@RequestMapping("/listLayer")
	@ResponseBody
	public List<Occupation> listLayer(){
		return occupationService.selectAllByLayer();
	}
}