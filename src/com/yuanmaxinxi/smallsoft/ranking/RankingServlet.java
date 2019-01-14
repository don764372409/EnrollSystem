package com.yuanmaxinxi.smallsoft.ranking;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yuanmaxinxi.dto.universitydto.UniversityQueryPageDTO;
import com.yuanmaxinxi.entity.university.University;
import com.yuanmaxinxi.service.UniversityService;
import com.yuanmaxinxi.web.BaseServlet;

@WebServlet("/soft/ranking")
public class RankingServlet extends BaseServlet{
	private UniversityService universityService;
	private static final long serialVersionUID = 1L;
	
	@Override
	public void init() throws ServletException {
		universityService = new UniversityService();
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UniversityQueryPageDTO dto = new UniversityQueryPageDTO();
		List<University> list = universityService.queryPageRangking(dto);
		putJson(list, resp);
	}
	
}
