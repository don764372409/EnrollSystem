package com.yuanmaxinxi.smallsoft.major;

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
@RequestMapping("/soft/major")
@Controller
@CrossOrigin
public class MajorController{
	@Autowired
	private MajorService majorService;
	/**
	 * 获取具有层次关系的所有专业
	 * @return
	 */
	@RequestMapping("/listLayer")
	@ResponseBody
	public List<Major> listLayer(){
		return majorService.selectLayer(null,3);
	}
	/**
	 * 根据职业查专业
	 * @param oId 职业Id
	 * @return
	 */
	@RequestMapping("/listByoId")
	@ResponseBody
	public List<Major> listByoId(Long oId){
		return majorService.selectByoId(oId);
	}
	/*
	@RequestMapping("/firstmajor2")
	@ResponseBody
	public List<Major> firstmajor2(int type){
		return majorService.selectAllByLayer(type);
	}
	*/
	@ResponseBody
	@RequestMapping("/selectOneById")
	public Major selectOneById(Long id){
		return majorService.selectOneById(id);
	}
	@ResponseBody
	@RequestMapping("/query")
	public MajorQueryPageDTO query(MajorQueryPageDTO dto){
		majorService.queryPage(dto);
		return dto;
	}
	@ResponseBody
	@RequestMapping("/like")
	public List<Major> like(Long id){
		return majorService.selectLikeMajorsById(id);
	}
	@RequestMapping("/uni")
	@ResponseBody
	public List<University> uni(Long id){
		return  majorService.selectUnis(id);
	}

	
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/json;charset=UTF-8");
		resp.setCharacterEncoding("UTF-8");
		String cmd = req.getParameter("cmd");
		if ("list".equals(cmd)) {
			//专业信息
			List<Major> majors = majorService.selectAllByLayer(0);
		}
	}
}
