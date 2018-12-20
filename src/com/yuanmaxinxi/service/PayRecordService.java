package com.yuanmaxinxi.service;

import java.util.List;

import com.yuanmaxinxi.dao.payrecord.PayrecordDao;
import com.yuanmaxinxi.dao.province.ProvinceDao;
import com.yuanmaxinxi.dto.BaseQueryPageDTO;
import com.yuanmaxinxi.entity.admin.Admin;
import com.yuanmaxinxi.entity.payrecord.PayRecord;
import com.yuanmaxinxi.entity.province.Province;
import com.yuanmaxinxi.util.StringUtil;

public class PayRecordService {
	PayrecordDao payrecorddao=new PayrecordDao();
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
}
