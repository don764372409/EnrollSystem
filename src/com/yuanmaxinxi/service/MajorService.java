
package com.yuanmaxinxi.service;

import java.util.List;

import com.yuanmaxinxi.dao.major.MajorDAO;
import com.yuanmaxinxi.dto.BaseQueryPageDTO;
import com.yuanmaxinxi.entity.admin.Admin;
import com.yuanmaxinxi.entity.major.Major;

public class MajorService {
	private MajorDAO majorDAO = new MajorDAO();
	public void insert(Admin obj) {
	}

	public void update(Admin obj) {
	}

	public void delete(Long id) {
	}

	public Admin selectOneById(Long id) {
		return null;
	}

	public List<Major> selectAll() {
		return majorDAO.selectAll();
	}

	public List<Admin> queryPage(BaseQueryPageDTO dto) {
		return null;
	}
}

