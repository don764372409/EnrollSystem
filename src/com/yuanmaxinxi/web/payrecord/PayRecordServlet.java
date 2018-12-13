package com.yuanmaxinxi.web.payrecord;

import java.io.IOException;
import java.util.List;

import javax.jws.WebService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yuanmaxinxi.entity.payrecord.PayRecord;
import com.yuanmaxinxi.service.PayRecordService;
import com.yuanmaxinxi.web.BaseServlet;
@WebServlet("/payrecord")
public class PayRecordServlet extends BaseServlet{
	PayRecordService payrecordservice;
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		payrecordservice=new PayRecordService();
	}
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String cmd = req.getParameter("cmd");
		if("showEdit".equals("cmd")) {
		
		}else{
		
		List<PayRecord>  list= payrecordservice.selectAll();
		req.setAttribute("list", list);
		req.getRequestDispatcher("/WEB-INF/payrecord/payrecord.jsp").forward(req, resp);
		}	
	}
}
