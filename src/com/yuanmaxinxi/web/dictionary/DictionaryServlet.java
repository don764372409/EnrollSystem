package com.yuanmaxinxi.web.dictionary;

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
import com.yuanmaxinxi.entity.dictionary.Dictionary;
import com.yuanmaxinxi.service.DictionaryService;
import com.yuanmaxinxi.service.DictionaryTypeService;
import com.yuanmaxinxi.util.StringUtil;
import com.yuanmaxinxi.web.BaseServlet;
@RequestMapping("/dictionary")
@Controller
public class DictionaryServlet{
	@Autowired
	private DictionaryService dictionaryService;
	@RequestMapping("/list")
	public String dictionaryList(Model model) {
		List<Dictionary> list = dictionaryService.selectAll();
		model.addAttribute("list",list);
		return "dictionary/list";
		
	}
//	private static final long serialVersionUID = 1L;
//	private DictionaryService ds;
//	private DictionaryTypeService dts;
//	public void init() throws ServletException {
//		ds = DictionaryService.getDictionaryService();
//		dts = DictionaryTypeService.getDictionaryService();
//	}
//	
//	
//	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		String cmd = req.getParameter("cmd");
//		System.err.println(cmd);
//		if ("showAdd".equals(cmd)) {
//			req.setAttribute("dtlist", dts.selectAll());
//			req.getRequestDispatcher("/WEB-INF/dictionary/add.jsp").forward(req, resp);
//		}else if("add".equals(cmd)) {
//			//封装
//			Dictionary dictionary = new Dictionary();
//			dictionary.setName(req.getParameter("name"));
//			dictionary.setTypeId(Integer.parseInt(req.getParameter("type")));
//			ResultDTO dto;
//			try {
//				ds.insert(dictionary);
//				dto = ResultDTO.newInstance(true, "添加成功!");
//			} catch (Exception e) {
//				e.printStackTrace();
//				dto = ResultDTO.newInstance(false, e.getMessage());
//			}
//			putJson(dto, resp);
//		}else if("showEdit".equals(cmd)) {
//			String idStr = req.getParameter("id");
//			if (StringUtil.isNotNullAndEmpty(idStr)) {
//				req.setAttribute("obj", ds.selectOneById(Long.parseLong(idStr)));
//				req.setAttribute("dtlist", dts.selectAll());
//				req.getRequestDispatcher("/WEB-INF/dictionary/edit.jsp").forward(req, resp);
//			}else {
//				resp.sendRedirect("/dictionary");
//			}
//			
//			
//		}else if("edit".equals(cmd)) {
//			ResultDTO dto;
//			try {
//				Dictionary d = new Dictionary();
//				d.setId(Integer.parseInt(req.getParameter("id")));
//				d.setName(req.getParameter("name"));
//				d.setTypeId(Integer.parseInt(req.getParameter("type")));
//				ds.update(d);
//				dto = ResultDTO.newInstance(true, "修改成功!");
//			} catch (Exception e) {
//				dto = ResultDTO.newInstance(false, e.getMessage());
//			}
//			putJson(dto, resp);
//			
//		}else if("delete".equals(cmd)) {
//			ResultDTO dto;
//			try {
//				ds.delete(Long.parseLong(req.getParameter("id")));
//				dto=ResultDTO.newInstance(true, "删除成功");
//			}catch (Exception e) {
//				dto=ResultDTO.newInstance(false, e.getMessage());
//			}
//			putJson(dto, resp);
//		}else {
//			//获取所有数据并跳转到列表页面
//			List<Dictionary> list = ds.selectAll();
//			req.setAttribute("list", list);
//			req.getRequestDispatcher("/WEB-INF/dictionary/list.jsp").forward(req, resp);
//		}
//	}
}