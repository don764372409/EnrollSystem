package com.yuanmaxinxi.service;

import java.util.List;

import com.yuanmaxinxi.dao.admin.AdminDAO;
import com.yuanmaxinxi.dto.BaseQueryPageDTO;
import com.yuanmaxinxi.dto.ResultDTO;
import com.yuanmaxinxi.entity.admin.Admin;
import com.yuanmaxinxi.util.StringUtil;

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
	/**
	 * 登录方法
	 * @param username
	 * @param password
	 * @return
	 */
	public Admin login(String username, String password) {
		if (StringUtil.isNullOrEmpty(username)) {
			throw new RuntimeException("账号不能为空");
		}
		if (StringUtil.isNullOrEmpty(password)) {
			throw new RuntimeException("密码不能为空");
		}
		return null;
	}
}
