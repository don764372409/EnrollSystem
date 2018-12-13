package com.yuanmaxinxi.service;

import java.util.List;

import com.yuanmaxinxi.dao.province.ProvinceDao;
import com.yuanmaxinxi.dto.BaseQueryPageDTO;
import com.yuanmaxinxi.entity.admin.Admin;
import com.yuanmaxinxi.entity.province.Province;
public class ProvinceService {
	ProvinceDao provincedao=new ProvinceDao();
	public void insert(Province obj) {
	}

	public void update(Province obj) {
	}

	public void delete(Long id) {
	}

	public Admin selectOneById(Long id) {
		return null;
	}

	public List<Province> selectAll() {
		return provincedao.selectAll();
	}

	public List<Admin> queryPage(BaseQueryPageDTO dto) {
		return null;
	}
}
