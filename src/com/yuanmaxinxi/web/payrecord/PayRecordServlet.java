package com.yuanmaxinxi.web.payrecord;

import java.io.IOException;
import java.util.List;

import javax.jws.WebService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yuanmaxinxi.dto.ResultDTO;
import com.yuanmaxinxi.entity.payrecord.PayRecord;
import com.yuanmaxinxi.service.PayRecordService;
import com.yuanmaxinxi.web.BaseServlet;
@Controller
@RequestMapping("/payrecord")
public class PayRecordServlet{
	@Autowired
	private PayRecordService payRecordService;
	ResultDTO dto;
	@RequestMapping("/list")
	public String payRecordList(Model model) {
		List<PayRecord> list = payRecordService.recordList();
		model.addAttribute("list", list);
		return "payrecord/list";
	}
	
	@RequestMapping("/showAdd")
	public String showAdd() {
		return "payrecord/add";
	}
	
	@RequestMapping("/add")
	@ResponseBody
	public ResultDTO add(PayRecord payRecord) {
		System.out.println(payRecord.getTime());
		try {
			payRecordService.insert(payRecord);
			dto=ResultDTO.newInstance(true, "添加成功");
		} catch (Exception e) {
			dto=ResultDTO.newInstance(false, e.getMessage());
		}
		
		return dto;
	}
	
	@RequestMapping("/edit")
	public String showEdit(int id,Model model) {
		PayRecord obj = payRecordService.selectOneById(id);
		model.addAttribute("obj", obj);
		return "payrecord/edit";
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public ResultDTO update(PayRecord payRecord) {
		try {
			payRecordService.update(payRecord);
			dto=ResultDTO.newInstance(true, "修改成功");
		} catch (Exception e) {
			dto=ResultDTO.newInstance(false, e.getMessage());
		}
		
		return dto;
	}
	
	
	
//	PayRecordService payrecordservice;
//	@Override
//	public void init() throws ServletException {
//		// TODO Auto-generated method stub
//		payrecordservice=new PayRecordService();
//	}
//	@Override
//	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		String cmd = req.getParameter("cmd");
//		if("showEdit".equals("cmd")) {
//		}
//		if("delete".equals(cmd)) {
//			Long id = Long.parseLong(req.getParameter("id"));
//			int del=payrecordservice.delete(id);
//			ResultDTO dto;
//			if(del==1) {
//				dto=ResultDTO.newInstance(true, "删除成功!");
//			}else {
//				dto=ResultDTO.newInstance(false, "删除失败!");
//			}
//			putJson(dto, resp);
//		}else{
//		
//		List<PayRecord>  list= payrecordservice.selectAll();
//		req.setAttribute("list", list);
//		req.getRequestDispatcher("/WEB-INF/payrecord/payrecord.jsp").forward(req, resp);
//		}	
//	}
}
