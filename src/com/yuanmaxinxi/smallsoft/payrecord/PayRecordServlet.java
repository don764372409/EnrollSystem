package com.yuanmaxinxi.smallsoft.payrecord;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yuanmaxinxi.dto.PayRecordDTO;
import com.yuanmaxinxi.entity.payrecord.PayRecord;
import com.yuanmaxinxi.service.PayRecordService;
import com.yuanmaxinxi.web.BaseServlet;
@WebServlet("/soft/payrecord")
public class PayRecordServlet extends BaseServlet{
	private PayRecordService payrecordservice;
	private static final long serialVersionUID = 1L;
	@Override
	public void init() throws ServletException {
		payrecordservice = new PayRecordService();
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PayRecordDTO dto = new PayRecordDTO();
		List<PayRecord> list = payrecordservice.queryPageName(dto);
		putJson(list, resp);
	}
}
