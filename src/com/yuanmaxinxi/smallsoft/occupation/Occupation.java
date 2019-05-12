package com.yuanmaxinxi.smallsoft.occupation;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yuanmaxinxi.dto.MajorQueryPageDTO;
import com.yuanmaxinxi.entity.enroll.Enroll;
import com.yuanmaxinxi.entity.major.Major;
import com.yuanmaxinxi.entity.university.University;
import com.yuanmaxinxi.service.MajorService;
import com.yuanmaxinxi.service.OccupationService;
@RequestMapping("/soft/occupation")
@Controller
@CrossOrigin
public class Occupation{
	@Autowired
	private OccupationService occupationService;
	
	@ResponseBody
	@RequestMapping("/selectAll")
	public List<com.yuanmaxinxi.entity.occupation.Occupation> selectAll(){
		return occupationService.sFselectAll();
	}
	
	@RequestMapping("selectFatherOneById")
	@ResponseBody
	public com.yuanmaxinxi.entity.occupation.Occupation selectFatherOneById(Long id) {
		System.out.println(id);
		return occupationService.selectFatherOneByPid(id);
	}
	
}
