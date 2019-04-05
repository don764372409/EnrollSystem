package com.yuanmaxinxi.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

@Service
public class PayRecordService {
	@Autowired
	private PayrecordDao payrecorddao;
	
	public List<PayRecord> recordList(){
		return payrecorddao.selectAll();
	}
	
	@Transactional
	public void insert(PayRecord obj) {
		if(StringUtil.isNullOrEmpty(obj.getId()+"")) {
			throw new RuntimeException("用户id不能为空");
		}
		if(StringUtil.isNullOrEmpty(obj.getMoney()+"")) {
			throw new RuntimeException("金额不能为空");
		}
		if(StringUtil.isNullOrEmpty(obj.getRemark())) {
			throw new RuntimeException("消费内容不能为空");
		}
		try {
			int rs = payrecorddao.insert(obj);
			if(rs!=1) {
				throw new RuntimeException("添加失败1");
			}
		} catch (Exception e) {
			throw new RuntimeException("添加失败2");
		}
	}
	
	public PayRecord selectOneById(int id) {
		return payrecorddao.selectOneById(id);
	}
	
	public void update(PayRecord obj) {
		if(StringUtil.isNullOrEmpty(obj.getId()+"")) {
			throw new RuntimeException("用户id不能为空");
		}
		if(StringUtil.isNullOrEmpty(obj.getMoney()+"")) {
			throw new RuntimeException("金额不能为空");
		}
		if(StringUtil.isNullOrEmpty(obj.getRemark())) {
			throw new RuntimeException("消费内容不能为空");
		}
		try {
			int rs = payrecorddao.update(obj);
			System.out.println(rs+"666666666666666666666");
			if(rs!=1) {
				throw new RuntimeException("添加失败");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new RuntimeException("添加失败");
		}
	}
//	private void init() {
//		SqlSession session = DBUtil.openSession();
//		payrecorddao = session.getMapper(PayrecordDao.class);
//	}
//	public PayRecordService() {
//		init();
//	}

	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		try {
			int rs = payrecorddao.delete(id);
			if(rs!=1) {
				throw new RuntimeException("删除失败");
			}
		} catch (Exception e) {
			throw new RuntimeException("删除失败,稍后重试");
		}
	}

//
//	public void update(PayRecord obj) {
//	}
//
//	public int delete(Long id) {
//		return payrecorddao.delete(id);
//	}
//

//
//	public List<PayRecord> selectAll() {
//		return payrecorddao.selectAll();
//	}
//
//	public List<Admin> queryPage(BaseQueryPageDTO dto) {
//		return null;
//	}
//
////	public List<PayRecord> queryPageName(PayRecordDTO dto) {
////		return payrecorddao.queryPageName(dto);
////	}
}
