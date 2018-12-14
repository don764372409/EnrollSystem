package com.yuanmaxinxi.service;

import java.util.List;

import com.yuanmaxinxi.dao.major.MajorDAO;
import com.yuanmaxinxi.dto.BaseQueryPageDTO;
import com.yuanmaxinxi.entity.major.Major;

public class MajorService {
	private MajorDAO majorDAO = new MajorDAO();
	public void insert(Major obj) {
		majorDAO.insert(obj);
	}

	public void update(Major obj) {
	}

	public void delete(Long id) {
		majorDAO.delete(id);
	}

	public Major selectOneById(Long id) {
		return null;
	}

	public List<Major> selectAll() {
		return majorDAO.selectAll();
	}

	public List<Major> queryPage(BaseQueryPageDTO dto) {
		return null;
	}
}