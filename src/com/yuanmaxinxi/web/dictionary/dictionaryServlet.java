package com.yuanmaxinxi.web.dictionary;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yuanmaxinxi.dto.ResultDTO;
import com.yuanmaxinxi.entity.dictionary.Dictionary;
import com.yuanmaxinxi.service.DictionaryService;
import com.yuanmaxinxi.web.BaseServlet;
@WebServlet("/dictionary")
public class dictionaryServlet extends BaseServlet{
	private static final long serialVersionUID = 1L;
	private DictionaryService dictionaryService;
	public void init() throws ServletException {
		dictionaryService = DictionaryService.getDictionaryService();
	}
	
	
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String cmd = req.getParameter("cmd");
		if ("showAdd".equals(cmd)) {
			req.getRequestDispatcher("/WEB-INF/dictionary/add.jsp").forward(req, resp);
		}else if("add".equals(cmd)) {
			//封装
			Dictionary dictionary = new Dictionary();
			dictionary.setName(req.getParameter("name"));
			dictionary.setTypeId(Integer.parseInt(req.getParameter("type")));
			ResultDTO dto;
			try {
			System.out.println(111);
				dictionaryService.insert(dictionary);
				dto = ResultDTO.newInstance(true, "添加成功!");
			System.out.println(222);
			} catch (Exception e) {
				e.printStackTrace();
				dto = ResultDTO.newInstance(false, e.getMessage());
			}
			putJson(dto, resp);
		}else if("showEdit".equals(cmd)) {
			
		}else if("edit".equals(cmd)) {
			String idStr = req.getParameter("id");
			
		}else if("delete".equals(cmd)) {
			String idStr = req.getParameter("id");
		}else {
			//获取所有数据并跳转到列表页面
			List<Dictionary> list = dictionaryService.selectAll();
			req.setAttribute("list", list);
			req.getRequestDispatcher("/WEB-INF/dictionary/list.jsp").forward(req, resp);
		}
	}
}