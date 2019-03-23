package com.yuanmaxinxi.web.university;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yuanmaxinxi.dto.ResultDTO;
import com.yuanmaxinxi.entity.dictionary.Dictionary;
import com.yuanmaxinxi.entity.province.Province;
import com.yuanmaxinxi.entity.university.University;
import com.yuanmaxinxi.service.ProvinceService;
import com.yuanmaxinxi.service.UniversityService;
import com.yuanmaxinxi.util.CrawUniversityAllUtil;
import com.yuanmaxinxi.util.CrawlUniversityDataUtil;
import com.yuanmaxinxi.util.StringUtil;
import com.yuanmaxinxi.web.BaseServlet;
//@WebServlet("/university")
@Controller
@RequestMapping("/university")
public class University_Servlet{
	@Autowired
	private UniversityService universityService;

	@RequestMapping("/list")
	public String selectUniList(Model model) {
		List<University> list = universityService.selectUniList();
		model.addAttribute("list",list);
		return "university/university";
	}
//	private static final long serialVersionUID = 1L;
//	private ProvinceService provinceService;
//	@Override
//	public void init() throws ServletException {
//		universityService = new UniversityService();
//		provinceService = new ProvinceService();
//	}
//	@Override
//	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		String cmd = req.getParameter("cmd");
//		if("showAdd".equals(cmd)) {
//			try {
//			//
//			List<Dictionary> selectAllByQuality = universityService.selectAllByQuality();
//			List<Dictionary> selectAllByType = universityService.selectAllByType();
//			List<Dictionary> selectAllByRecord = universityService.selectAllByRecord();
//			req.setAttribute("records", selectAllByRecord);
//			req.setAttribute("qualities", selectAllByQuality);
//			req.setAttribute("types", selectAllByType);
//			List<Province> pros = provinceService.selectAll();
//			req.setAttribute("pros", pros);
//			req.getRequestDispatcher("/WEB-INF/university/add.jsp").forward(req, resp);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}else if("add".equals(cmd)) {
//			String name = req.getParameter("name");
//			String pId = req.getParameter("pId");
//			String address = req.getParameter("address");
//			String quality = req.getParameter("quality");
//			String type = req.getParameter("type");
//			//简介
//			String remark = req.getParameter("remark");
//			String ranking = req.getParameter("ranking");
//			//师资团队
//			String teacher = req.getParameter("teacher");
//			String record = req.getParameter("record");
//			//学科建设
//			String subject = req.getParameter("subject");
//			University university = new University();
//			university.setName(name);
//			//类型转换错误？？？
//			university.setpId(Long.parseLong(pId));
//			university.setAddress(address);
//			//类型转换错误？？
//			university.setSubject(subject);
//			ResultDTO dto;
//			//传输信息至客户
//			try {
//				universityService.insert(university);
//				dto=ResultDTO.newInstance(true, "添加成功");
//				putJson(dto, resp);
//			}catch (Exception e) {
//				dto=ResultDTO.newInstance(false, e.getMessage());
//				putJson(dto,resp);
//			}
//		}else if("showEdit".equals(cmd)) {
//			String idStr = req.getParameter("id");
//			if (StringUtil.isNotNullAndEmpty(idStr)) {
//				University obj = universityService.selectOneById(Long.parseLong(idStr));
//				req.setAttribute("obj", obj);
//				List<Dictionary> selectAllByQuality = universityService.selectAllByQuality();
//				List<Dictionary> selectAllByType = universityService.selectAllByType();
//				List<Dictionary> selectAllByRecord = universityService.selectAllByRecord();
//				req.setAttribute("records", selectAllByRecord);
//				req.setAttribute("qualities", selectAllByQuality);
//				req.setAttribute("types", selectAllByType);
//				
//				List<Province> pros = provinceService.selectAll();
//				req.setAttribute("pros", pros);
//				req.getRequestDispatcher("/WEB-INF/university/edit.jsp").forward(req, resp);
//			}else {
//				resp.sendRedirect("/university");
//			}
//		}else if("edit".equals(cmd)) {
//			String id = req.getParameter("id");
//			String name = req.getParameter("name");
//			String pId = req.getParameter("pId");
//			String address = req.getParameter("address");
//			String quality = req.getParameter("quality");
//			String type = req.getParameter("type");
//			//简介
//			String remark = req.getParameter("remark");
//			String ranking = req.getParameter("ranking");
//			//师资团队
//			String teacher = req.getParameter("teacher");
//			String record = req.getParameter("record");
//			//学科建设
//			String subject = req.getParameter("subject");
//			University university = new University();
//			ResultDTO dto;
//			try {
//				university.setId(Long.parseLong(id));
//				university.setName(name);
//				//类型转换错误？？？
//				university.setpId(Long.parseLong(pId));
//				university.setAddress(address);
//				//传输信息至客户
//				universityService.update(university);
//				dto=ResultDTO.newInstance(true, "添加成功");
//				putJson(dto, resp);
//				resp.sendRedirect("/university");
//			}catch (Exception e) {
//				dto=ResultDTO.newInstance(false, e.getMessage());
//				putJson(dto,resp);
//			}
//		}else if("delete".equals(cmd)) {
//			ResultDTO dto;
//			try {
//				String id = req.getParameter("id");
//				universityService.delete(Long.parseLong(id));
//				dto=ResultDTO.newInstance(true, "删除成功");
//				putJson(dto, resp);
//			}catch (Exception e) {
//				dto=ResultDTO.newInstance(false, e.getMessage());
//				putJson(dto,resp);
//			}
//		}else if("craw".equals(cmd)) {
//			universityService.craw();
//			ResultDTO dto=ResultDTO.newInstance(true, "开始爬取");
//			putJson(dto,resp);
//		}else if("crawMsg".equals(cmd)) {
//			//获取爬虫的消息
//			ResultDTO dto;
//			LinkedBlockingQueue<String> msgs = CrawUniversityAllUtil.msgs;
//			int size = CrawUniversityAllUtil.schools.size();
//			//集合长度等于2843  说明爬虫执行完毕
//			if(CrawUniversityAllUtil.flag) {
//				dto=ResultDTO.newInstance(true,"持久化完毕");
//			}else if (size==2843&&CrawUniversityAllUtil.isDAO) {
//				dto=ResultDTO.newInstance(false,"爬虫爬取完毕,开始将爬取到的数据与数据库同步");
//				universityService.updateCrawSchool();
//			}else {
//				dto=ResultDTO.newInstance(false, msgs.poll()+"size:"+size);
//			}
//			putJson(dto, resp);
//		}else if("downReload".equals(cmd)){
//			//重新爬取数据，也就是更新数据库
//			ResultDTO dto = null;
//			try {
//				universityService.downReload();
//				dto=ResultDTO.newInstance(true, "重新爬取成功");
//			} catch (Exception e) {
//				e.printStackTrace();
//				dto = ResultDTO.newInstance(false, e.getMessage());
//			}
//			putJson(dto, resp);
//		}else {
//			//获取所有数据并跳转页面
//			try {
//				List<University> list = universityService.selectAll();
//				req.setAttribute("list", list);
//				req.getRequestDispatcher("/WEB-INF/university/list.jsp").forward(req, resp);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//	}
}
