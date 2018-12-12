<<<<<<< HEAD
package com.yuanmaxinxi.service;

import java.util.List;

import com.yuanmaxinxi.dao.province.ProvinceDao;
import com.yuanmaxinxi.dto.BaseQueryPageDTO;
import com.yuanmaxinxi.entity.admin.Admin;
import com.yuanmaxinxi.entity.province.Province;
import com.yuanmaxinxi.util.StringUtil;

public class ProvinceService {
	ProvinceDao provincedao=new ProvinceDao();
	public void insert(Province obj) {
		if(StringUtil.isNullOrEmpty(obj.getId()+"")) {
			throw new RuntimeException("id不能为空");
		}
		if(StringUtil.isNullOrEmpty(obj.getName())) {
			throw new RuntimeException("省份不能为空");
		}
		Province sysProvince=provincedao.selectOneByName(obj.getName());
		if(sysProvince!=null) {
			throw new RuntimeException("省份已存在，不能重复添加");
		}
		try {
			int insert = provincedao.insert(obj);
			if(insert!=1) {
				throw new RuntimeException("");
			}
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException("添加失败，请联系管理员");
		}
		
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
=======
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
>>>>>>> branch 'master' of https://github.com/don764372409/EnrollSystem.git
