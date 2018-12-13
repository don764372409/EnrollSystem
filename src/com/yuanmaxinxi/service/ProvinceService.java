package com.yuanmaxinxi.service;

import java.util.List;

import com.yuanmaxinxi.dao.province.ProvinceDao;
import com.yuanmaxinxi.dto.BaseQueryPageDTO;
import com.yuanmaxinxi.entity.admin.Admin;
import com.yuanmaxinxi.entity.province.Province;
<<<<<<< HEAD
=======
import com.yuanmaxinxi.util.StringUtil;
>>>>>>> branch 'master' of https://github.com/don764372409/EnrollSystem.git

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
