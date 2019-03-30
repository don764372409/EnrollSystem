package com.yuanmaxinxi.web.dictionaryType;

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

import com.yuanmaxinxi.dto.ResultDTO;
import com.yuanmaxinxi.entity.dictionaryType.DictionaryType;
import com.yuanmaxinxi.service.DictionaryTypeService;
import com.yuanmaxinxi.util.StringUtil;
import com.yuanmaxinxi.web.BaseServlet;
@Controller
@RequestMapping("/dictionaryType")
public class DictionaryTypeServlet extends BaseServlet{
	@Autowired
	private DictionaryTypeService dictionaryTypeService;
	@RequestMapping("/list")
	public void selectAll(Model model) {
		List<DictionaryType> list = dictionaryTypeService.selectAll();
		model.addAttribute("list", list);
	}
//	private static final long serialVersionUID = 1L;
//	private DictionaryTypeService dts;
//	public void init() throws ServletException {
//		dts = DictionaryTypeService.getDictionaryService();
//	}
//	
//	
//	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		String cmd = req.getParameter("cmd");
//		System.err.println(cmd);
//		if ("showAdd".equals(cmd)) {
//			req.getRequestDispatcher("/WEB-INF/dictionaryType/add.jsp").forward(req, resp);
//		}else if("add".equals(cmd)) {
//			//封装
//			DictionaryType dictionaryType = new DictionaryType();
//			dictionaryType.setName(req.getParameter("name"));
//			ResultDTO dto;
//			try {
//				dts.insert(dictionaryType);
//				dto = ResultDTO.newInstance(true, "添加成功!");
//			} catch (Exception e) {
//				e.printStackTrace();
//				dto = ResultDTO.newInstance(false, e.getMessage());
//			}
//			putJson(dto, resp);
//		}else if("showEdit".equals(cmd)) {
//			String idStr = req.getParameter("id");
//			if (StringUtil.isNotNullAndEmpty(idStr)) {
//				req.setAttribute("obj", dts.selectOneById(Long.parseLong(idStr)));
//				req.getRequestDispatcher("/WEB-INF/dictionaryType/edit.jsp").forward(req, resp);
//			}else {
//				resp.sendRedirect("/dictionaryType");
//			}
//		}else if("edit".equals(cmd)) {
//			DictionaryType dt = new DictionaryType();
//			dt.setId(Integer.parseInt(req.getParameter("id")));
//			dt.setName(req.getParameter("name"));
//			
//			ResultDTO dto;
//			try {
//				dts.update(dt);
//				dto = ResultDTO.newInstance(true, "修改成功!");
//			} catch (Exception e) {
//				e.printStackTrace();
//				dto = ResultDTO.newInstance(false, e.getMessage());
//			}
//			putJson(dto, resp);
//		}else if("delete".equals(cmd)) {
//			ResultDTO dto;
//			try {
//				dts.delete(Long.parseLong(req.getParameter("id")));
//				dto=ResultDTO.newInstance(true, "删除成功");
//			}catch (Exception e) {
//				dto=ResultDTO.newInstance(false, e.getMessage());
//			}
//			putJson(dto, resp);
//		}else {
//			//获取所有数据并跳转到列表页面
//			List<DictionaryType> list = dts.selectAll();
//			req.setAttribute("list", list);
//			req.getRequestDispatcher("/WEB-INF/dictionaryType/list.jsp").forward(req, resp);
//		}
//	}
}