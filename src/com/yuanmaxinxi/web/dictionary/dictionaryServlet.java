package com.yuanmaxinxi.web.dictionary;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yuanmaxinxi.dao.dictionaryType.DictionaryTypeDAO;
import com.yuanmaxinxi.dto.ResultDTO;
import com.yuanmaxinxi.entity.dictionary.Dictionary;
import com.yuanmaxinxi.entity.dictionaryType.DictionaryType;
import com.yuanmaxinxi.service.DictionaryService;
import com.yuanmaxinxi.util.StringUtil;
import com.yuanmaxinxi.web.BaseServlet;
@WebServlet("/dictionary")
public class dictionaryServlet extends BaseServlet{
	private static final long serialVersionUID = 1L;
	private DictionaryService ds;
	public void init() throws ServletException {
		ds = DictionaryService.getDictionaryService();
	}
	
	
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String cmd = req.getParameter("cmd");
		System.err.println(cmd);
		if ("showAdd".equals(cmd)) {
			List<DictionaryType> dt = DictionaryTypeDAO.getDictionaryTypeDao().selectAll();
			req.setAttribute("dtlist", dt);
			req.getRequestDispatcher("/WEB-INF/dictionary/add.jsp").forward(req, resp);
		}else if("add".equals(cmd)) {
			//封装
			Dictionary dictionary = new Dictionary();
			dictionary.setName(req.getParameter("name"));
			dictionary.setTypeId(Integer.parseInt(req.getParameter("type")));
			ResultDTO dto;
			try {
				ds.insert(dictionary);
				dto = ResultDTO.newInstance(true, "添加成功!");
			} catch (Exception e) {
				e.printStackTrace();
				dto = ResultDTO.newInstance(false, e.getMessage());
			}
			putJson(dto, resp);
		}else if("showEdit".equals(cmd)) {
			String idStr = req.getParameter("id");
			System.err.print(req.getParameter("type"));
			if (StringUtil.isNotNullAndEmpty(idStr)) {
				req.setAttribute("obj", ds.selectOneById(Long.parseLong(idStr)));
				List<DictionaryType> dt = DictionaryTypeDAO.getDictionaryTypeDao().selectAll();
				req.setAttribute("dtlist", dt);
				req.getRequestDispatcher("/WEB-INF/dictionary/edit.jsp").forward(req, resp);
			}else {
				resp.sendRedirect("/dictionary");
			}
			
			
		}else if("edit".equals(cmd)) {
			Dictionary dictionary = new Dictionary();
			dictionary.setId(Integer.parseInt(req.getParameter("id")));
			dictionary.setName(req.getParameter("name"));
			dictionary.setTypeId(Integer.parseInt(req.getParameter("type")));
			ResultDTO dto;
			try {
				ds.update(dictionary);
				dto = ResultDTO.newInstance(true, "修改成功!");
			} catch (Exception e) {
				e.printStackTrace();
				dto = ResultDTO.newInstance(false, e.getMessage());
			}
			putJson(dto, resp);
			
		}else if("delete".equals(cmd)) {
			String idStr = req.getParameter("id");
		}else {
			//获取所有数据并跳转到列表页面
			List<Dictionary> list = ds.selectAll();
			req.setAttribute("list", list);
			req.getRequestDispatcher("/WEB-INF/dictionary/list.jsp").forward(req, resp);
		}
	}
}