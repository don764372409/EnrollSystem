package com.yuanmaxinxi.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.yuanmaxinxi.dao.payrecord.PayrecordDao;
import com.yuanmaxinxi.dao.province.ProvinceDao;
import com.yuanmaxinxi.dto.BaseQueryPageDTO;
import com.yuanmaxinxi.dto.PayRecordDTO;
import com.yuanmaxinxi.entity.admin.Admin;
import com.yuanmaxinxi.entity.payrecord.PayRecord;
import com.yuanmaxinxi.entity.province.Province;
import com.yuanmaxinxi.entity.university.University;
import com.yuanmaxinxi.util.DBUtil;
import com.yuanmaxinxi.util.StringUtil;

public class PayRecordService {
	private PayrecordDao payrecorddao;
	private void init() {
		SqlSession session = DBUtil.openSession();
		payrecorddao = session.getMapper(PayrecordDao.class);
	}
	public PayRecordService() {
		init();
	}
	public void insert(PayRecord obj) {		
	}

	public void update(PayRecord obj) {
	}

	public int delete(Long id) {
		return payrecorddao.delete(id);
	}

	public Admin selectOneById(Long id) {
		return null;
	}

	public List<PayRecord> selectAll() {
		return payrecorddao.selectAll();
	}

	public List<Admin> queryPage(BaseQueryPageDTO dto) {
		return null;
	}

//	public List<PayRecord> queryPageName(PayRecordDTO dto) {
//		return payrecorddao.queryPageName(dto);
//	}
}
