package com.yuanmaxinxi.web.major;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yuanmaxinxi.service.MajorService;

@RequestMapping("/major")
@Controller
public class Web_MajorController{
	@Autowired
	private MajorService majorService;

//	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		String cmd = req.getParameter("cmd");
//		if ("showAdd".equals(cmd)) {
//			req.getRequestDispatcher("/WEB-INF/major/add.jsp").forward(req, resp);
//		}
//		else if ("add".equals(cmd)) {
//			String name = req.getParameter("name");
//			 Long pId = Long.parseLong(req.getParameter("pId"));
//			 Long type =Long.parseLong(req.getParameter("type"));
//			String remark = req.getParameter("remark");
//			String explain = req.getParameter("majorExplain");
//			String ranking =req.getParameter("ranking");
			// 封装
//			Major major = new Major();
//			major.setName(name);
//			 major.setpId(pId);
//			 major.setType(type);
//			major.setRemark(remark);
//			major.setMajorExplain(explain);
//			major.setRanking(Integer.parseInt(ranking));
			// req.getRequestDispatcher("/major").forward(req, resp);
			// 调用方法
//			ResultDTO dto;
//			try {
//				majorService.insert(major);
//				dto = ResultDTO.newInstance(true, "添加成功!");
//			} catch (Exception e) {
//				e.printStackTrace();
//				dto = ResultDTO.newInstance(false, e.getMessage());
//			}
//			putJson(dto, resp);
			// resp.sendRedirect("major");

//		} else if ("showEdit".equals(cmd)) {
//			String id = req.getParameter("id");
//			if (StringUtil.isNotNullAndEmpty(id)) {
//				Major major = majorService.selectOneById(Long.parseLong(id));
//				 req.setAttribute("major", major);
//				 System.err.println("servlet"+major);
//				req.getRequestDispatcher("/WEB-INF/major/update.jsp").forward(req, resp);
//			}else {
//				resp.sendRedirect("/major");
//			}
//		} else if ("edit".equals(cmd)) {
//			Long id = Long.parseLong(req.getParameter("id"));
//			String name = req.getParameter("name");
//			 Long pId = Long.parseLong(req.getParameter("pId"));
//			 Long type =Long.parseLong(req.getParameter("type"));
//			String remark = req.getParameter("remark");
//			String explain = req.getParameter("majorExplain");
//			String ranking =req.getParameter("ranking");
//			// 封装
//			Major major = new Major();
//			major.setId(id);
//			major.setName(name);
//			major.setpId(pId);
//			major.setType(type);
//			major.setRemark(remark);
//			major.setMajorExplain(explain);
//			major.setRanking(Integer.parseInt(ranking));
//			ResultDTO dto;
//			try {
////				majorService.update(major);
//				dto = ResultDTO.newInstance(true, "修改成功!");
//			} catch (Exception e) {
//				e.printStackTrace();
//				dto = ResultDTO.newInstance(false, e.getMessage());
//			}
////			putJson(dto, resp);
////			resp.sendRedirect("/major");
//		} else if ("delete".equals(cmd)) {
//			String id = req.getParameter("id");
//			
//			ResultDTO dto;
//			try {
//				majorService.delete(Long.parseLong(id));
//				dto = ResultDTO.newInstance(true, "删除成功!");
//			} catch (Exception e) {
//				e.printStackTrace();
//				dto = ResultDTO.newInstance(false, e.getMessage());
//			}	
////			putJson(dto, resp);
////			resp.sendRedirect("major");
//		}else {
//			// 获取所有数据并跳转到列表页面
////			List<Major> list = majorService.selectAll();
////			req.setAttribute("list", list);
//			req.getRequestDispatcher("/WEB-INF/major/list.jsp").forward(req, resp);
//			
//		}
//
//	}
}