package com.yuanmaxinxi.web.admin;

import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yuanmaxinxi.dto.ResultDTO;
import com.yuanmaxinxi.entity.admin.Admin;
import com.yuanmaxinxi.service.AdminService;
import com.yuanmaxinxi.web.BaseServlet;
@RequestMapping("/admin")
@Controller
public class AdminServlet{
	@Autowired
	private AdminService adminService;
	ResultDTO dto;
	@RequestMapping("list")
	public String adminList(Model model) {
		List<Admin> list = adminService.selectAll();
		model.addAttribute("list",list);
		return "admin/list";
		
	}
	@RequestMapping("/add")
	@ResponseBody
	public ResultDTO add(Admin admin) {
		try {
			adminService.add(admin);
			dto=ResultDTO.newInstance(true, "添加成功!");
		} catch (Exception e) {
			dto=ResultDTO.newInstance(false, e.getMessage());
		}
		return dto;
		
	}
	@RequestMapping("/showAdd")
	public String showAdd() {
		return "admin/add";
	}
	@RequestMapping("/showEdit")
	public String showEdit(Long id,Model model) {
		Admin obj = adminService.selectOneById(id);
		model.addAttribute("obj",obj);
		return "admin/updata";
	}
	@RequestMapping("/edit")
	@ResponseBody
	public ResultDTO edit(Admin admin) {
		
		System.out.println(admin.getName()+admin.getUsername()+admin.getPhone());
		try {
			adminService.update(admin);
			dto=ResultDTO.newInstance(true, "修改成功");
		} catch (Exception e) {
			dto=ResultDTO.newInstance(false, "修改失败，稍后再试");
		}
		return dto;
	}
	
	@RequestMapping("delete")
	@ResponseBody
	public ResultDTO delete(Long id) {
		try {
			adminService.delete(id);
			dto=ResultDTO.newInstance(true, "删除成功");
		} catch (Exception e) {
			dto=ResultDTO.newInstance(true, "删除成功");
		}
			return dto;
	}
//	private static final long serialVersionUID = 1L;
//	private AdminService adminService;
//	public void init() throws ServletException {
//		adminService = new AdminService();
//	}
//	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		req.setCharacterEncoding("utf-8");
//		String cmd = req.getParameter("cmd");
//		if ("showAdd".equals(cmd)) {
//			req.getRequestDispatcher("/WEB-INF/admin/add.jsp").forward(req, resp);
//		}else if("add".equals(cmd)) {
//			String name = req.getParameter("name");
//			String username = req.getParameter("username");
//			String phone = req.getParameter("phone");
//			String headImg = req.getParameter("headImg");
//			//封装
//			Admin admin = new Admin();
//			admin.setName(name);
//			admin.setUsername(username);
//			admin.setPhone(phone);
//			admin.setPassword("88888888");
//			admin.setHeadImg(headImg);
//			//调方法添加
//			ResultDTO dto;
//			try {
//				adminService.insert(admin);
//				dto = ResultDTO.newInstance(true, "添加成功!");
//			} catch (Exception e) {
//				dto = ResultDTO.newInstance(false, e.getMessage());
//			}
//			putJson(dto, resp);
//		}else if("showEdit".equals(cmd)) {
//			String idStr = req.getParameter("id");
//			Admin admin=adminService.selectOneById(Long.parseLong(idStr));
//			req.setAttribute("obj", admin);
//			req.getRequestDispatcher("/WEB-INF/admin/updata.jsp").forward(req, resp);
//		}else if("edit".equals(cmd)) {
//			String name = req.getParameter("name");
//			String phone = req.getParameter("phone");
//			Long id=Long.parseLong(req.getParameter("id"));
//			Admin admin = new Admin();
//			admin.setId(id);
//			admin.setName(name);
//			admin.setPhone(phone);
//			ResultDTO dto;
//			try {
//				adminService.edit(admin);
//				dto=ResultDTO.newInstance(true, "修改成功!");
//				
//			} catch (Exception e) {
//				dto=ResultDTO.newInstance(false, e.getMessage());
//			}
//			putJson(dto, resp);
//			
//		}else if("delete".equals(cmd)) {
//			String idStr = req.getParameter("id");
//			ResultDTO dto;
//			try {
//				adminService.delete(Long.parseLong(idStr));
//				dto=ResultDTO.newInstance(true, "修改成功!");
//			} catch (Exception e) {
//				dto=ResultDTO.newInstance(false, e.getMessage());
//			}
//			putJson(dto, resp);
//		}else {
//			//获取所有数据并跳转到列表页面
//			List<Admin> list = adminService.selectAll();
//			req.setAttribute("list", list);
//			req.getRequestDispatcher("/WEB-INF/admin/list.jsp").forward(req, resp);
//		}
//	}
}