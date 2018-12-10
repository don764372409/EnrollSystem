package com.yuanmaxinxi.service;

import java.util.List;

import com.yuanmaxinxi.dao.admin.AdminDAO;
import com.yuanmaxinxi.dto.BaseQueryPageDTO;
import com.yuanmaxinxi.entity.admin.Admin;

public class AdminService {
	private AdminDAO adminDAO = new AdminDAO();
	public void insert(Admin obj) {
	}

	public void update(Admin obj) {
	}

	public void delete(Long id) {
	}

	public Admin selectOneById(Long id) {
		return null;
	}

	public List<Admin> selectAll() {
		return adminDAO.selectAll();
	}

	public List<Admin> queryPage(BaseQueryPageDTO dto) {
		return null;
	}
}
