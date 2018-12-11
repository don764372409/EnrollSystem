package com.yuanmaxinxi.web.occupation;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yuanmaxinxi.dto.ResultDTO;
import com.yuanmaxinxi.entity.occupation.Occupation;
import com.yuanmaxinxi.service.OccupationService;
import com.yuanmaxinxi.web.BaseServlet;
@WebServlet("/occupation")
public class OccupationServlet extends BaseServlet{

	private static final long serialVersionUID = 1L;
	private OccupationService ocpService;
	@Override
	public void init() throws ServletException {
		ocpService = new OccupationService();
		super.init();
	}
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String cmd = req.getParameter("cmd");
		if ("showAdd".equals(cmd)) {
			req.getRequestDispatcher("/WEB-INF/occupation/add.jsp").forward(req, resp);
		}else if("add".equals(cmd)) {
			String name = getInitParameter("name");
			Long pId = Long.parseLong(getInitParameter("pId"));
			String remark = getInitParameter("remark");
			String workContent = getInitParameter("workContent");
			Occupation occ = new Occupation();
			occ.setName(name);
			occ.setpId(pId);
			occ.setRemark(remark);
			occ.setWorkContent(workContent);
			ResultDTO dto;
			try {
				ocpService.insert(occ);
				dto = ResultDTO.newInstance(true, "添加成功!");
			} catch (Exception e) {
				dto = ResultDTO.newInstance(false, e.getMessage());
			}
			putJson(dto, resp);
		}else if("showEdit".equals(cmd)) {
			
		}else if("edit".equals(cmd)) {
			
		}else if("delete".equals(cmd)) {
			
		}else {
			//获取所有数据并跳转到列表页面
			List<Occupation> list = ocpService.selectAll();
			req.setAttribute("list", list);
			req.getRequestDispatcher("/WEB-INF/occupation/list.jsp").forward(req, resp);
		super.service(req, resp);
	}
}
}