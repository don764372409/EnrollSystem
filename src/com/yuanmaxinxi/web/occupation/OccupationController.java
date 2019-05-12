package com.yuanmaxinxi.web.occupation;

import java.io.IOException;
import java.util.List;

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
import com.yuanmaxinxi.entity.major.Major;
import com.yuanmaxinxi.entity.occupation.Occupation;
import com.yuanmaxinxi.service.OccupationService;
import com.yuanmaxinxi.util.StringUtil;
import com.yuanmaxinxi.web.BaseServlet;

@Controller
@RequestMapping("/occupation")
public class OccupationController{
	@Autowired
	private OccupationService occupationService;
	@RequestMapping("/list")
	public String occupationList(Model model){
		List<Occupation> list = occupationService.selectAll();
		model.addAttribute("list",list);
		return "occupation/list";
		
	}
	@RequestMapping("/showAdd")
	public String showAdd() {
		return "/occupation/add";
	}
	@RequestMapping("/add")
	@ResponseBody
	public ResultDTO add(Occupation occupation) {
		ResultDTO dto;
		try {
			occupationService.insert(occupation);
			dto=ResultDTO.newInstance(true, "添加成功");
		} catch (Exception e) {
			dto=ResultDTO.newInstance(false, "添加失败");
		}
		
		return dto;
	}
	@RequestMapping("/showEdit")
		
	public String showEdit(Long id,Model model) {
		Occupation occupation=occupationService.selectOneById(id);
		model.addAttribute("occupation", occupation);
		return "/occupation/update";
		
	}
	
	@RequestMapping("/edit")
	@ResponseBody
	public ResultDTO update(Occupation obj) {
		ResultDTO dto;
		try {
			occupationService.update(obj);
			dto=ResultDTO.newInstance(true, "添加成功6");
		} catch (Exception e) {
			dto=ResultDTO.newInstance(false, e.getMessage());
		}
		return dto;
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public ResultDTO delete(Long id) {
		ResultDTO dto;
		try {
			occupationService.delete(id);
			dto=ResultDTO.newInstance(true, "删除成功6");
		} catch (Exception e) {
			dto=ResultDTO.newInstance(true, e.getMessage());
		}
		return dto;
	}
	
	
//
//	private static final long serialVersionUID = 1L;
//	private OccupationService ocpService;
//	@Override
//	public void init() throws ServletException {
//		ocpService = new OccupationService();
//	}
//	@Override
//	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		String cmd = req.getParameter("cmd");
//		if ("showAdd".equals(cmd)) {
//			req.getRequestDispatcher("/WEB-INF/occupation/add.jsp").forward(req, resp);
//		}else if("add".equals(cmd)) {
//			String name = req.getParameter("name");
//			Long pId = Long.parseLong(req.getParameter("pId"));
//			String remark = req.getParameter("remark");
//			String workContent = req.getParameter("workContent");
//			Occupation occupation = new Occupation();
//			occupation.setName(name);
//			occupation.setpId(pId);
//			occupation.setRemark(remark);
//			occupation.setWorkContent(workContent);
//			
//			ResultDTO dto;
//			try {
//				ocpService.insert(occupation);
//				dto = ResultDTO.newInstance(true, "添加成功!");
//			} catch (Exception e) {
//				e.printStackTrace();
//				dto = ResultDTO.newInstance(false, e.getMessage());
//			}
//			putJson(dto, resp);
//		}else if("showEdit".equals(cmd)) {
//			String id = req.getParameter("id");
//			if (StringUtil.isNotNullAndEmpty(id)) {
//				Occupation occupation = ocpService.selectOneById(Long.parseLong(id));
//				 req.setAttribute("occupation", occupation);
//				req.getRequestDispatcher("/WEB-INF/occupation/update.jsp").forward(req, resp);
//			}else {
//				resp.sendRedirect("/occupation");
//			}
//		}else if("edit".equals(cmd)) {
//			Long id = Long.parseLong(req.getParameter("id"));
//			String name = req.getParameter("name");
//			Long pId = Long.parseLong(req.getParameter("pId"));
//			String remark = req.getParameter("remark");
//			String workContent = req.getParameter("workContent");
//			Occupation occupation = new Occupation();
//			occupation.setId(id);
//			occupation.setName(name);
//			occupation.setpId(pId);
//			occupation.setRemark(remark);
//			occupation.setName(workContent);
//			ResultDTO dto;
//			try {
//				ocpService.update(occupation);
//				dto = ResultDTO.newInstance(true, "修改成功!");
//			} catch (Exception e) {
//				e.printStackTrace();
//				dto = ResultDTO.newInstance(false, e.getMessage());
//			}
//			putJson(dto, resp);
//		}else if("delete".equals(cmd)) {
//			Long id = Long.parseLong(req.getParameter("id"));
//			ResultDTO dto;
//			try {
//				ocpService.delete(id);
//				dto = ResultDTO.newInstance(true, "删除成功!");
//			} catch (Exception e) {
//				e.printStackTrace();
//				dto = ResultDTO.newInstance(false, e.getMessage());
//			}
//			putJson(dto, resp);
//		}else {
//			//获取所有数据并跳转到列表页面
//			List<Occupation> list = ocpService.selectAll();
//			req.setAttribute("list", list);
//			req.getRequestDispatcher("/WEB-INF/occupation/list.jsp").forward(req, resp);
//	}
//}
}