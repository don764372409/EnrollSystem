package com.yuanmaxinxi.web.university;

import java.io.IOException;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yuanmaxinxi.dao.university.UniversityDao;
import com.yuanmaxinxi.dto.ResultDTO;
import com.yuanmaxinxi.entity.dictionary.Dictionary;
import com.yuanmaxinxi.entity.university.University;
import com.yuanmaxinxi.service.UniversityService;
import com.yuanmaxinxi.web.BaseServlet;
@WebServlet("/university")

public class University_Servlet extends BaseServlet{
	private UniversityService universityService;
	public static UniversityDao uniDao=new UniversityDao();
	private static final long serialVersionUID = 1L;
	@Override
	public void init() throws ServletException {
		universityService = new UniversityService();
	}
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String cmd = req.getParameter("cmd");
		if("showAdd".equals(cmd)) {
			try {
			List<Dictionary> selectAllByQuality = universityService.selectAllByQuality();
			List<Dictionary> selectAllByType = universityService.selectAllByType();
			List<Dictionary> selectAllByRecord = universityService.selectAllByRecord();
			System.err.println(selectAllByQuality);
			req.setAttribute("records", selectAllByRecord);
			req.setAttribute("qualities", selectAllByQuality);
			req.setAttribute("types", selectAllByType);
			req.getRequestDispatcher("/WEB-INF/university/universityAdd.jsp").forward(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if("add".equals(cmd)) {
			String name = req.getParameter("name");
			String address = req.getParameter("address");
			String quality = req.getParameter("quality");
			String type = req.getParameter("type");
			String remark = req.getParameter("remark");
			String ranking = req.getParameter("ranking");
			String teacher = req.getParameter("teachers");
			String record = req.getParameter("record");
			String subject = req.getParameter("subject");
			University university = new University();
			university.setName(name);
			university.setAddress(address);
			university.setQuality(Long.parseLong(quality));
			university.setType(Long.parseLong(type));
			university.setRemark(remark);
			university.setRanking(Integer.parseInt(ranking));
			university.setTeachers(teacher);
			university.setRecord(Long.parseLong(record));
			university.setSubject(subject);
			ResultDTO dto;
			try {
				universityService.insert(university);
				dto=ResultDTO.newInstance(true, "添加成功");
			}catch (Exception e) {
				ResultDTO.newInstance(false, e.getMessage());
			}
		}else if("showEdit".equals(cmd)) {
			
		}else if("edit".equals(cmd)) {
			
		}else if("edit".equals(cmd)) {
			
		}
		else {
			//获取所有数据并跳转页面
			try {
				List<University> universities = universityService.selectAll();
				req.setAttribute("unis", universities);
				req.getRequestDispatcher("/WEB-INF/university/university.jsp").forward(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
