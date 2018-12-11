package com.yuanmaxinxi.service;

import java.util.List;

import com.yuanmaxinxi.dao.university.UniversityDao;
import com.yuanmaxinxi.dto.BaseQueryPageDTO;
import com.yuanmaxinxi.entity.university.University;


public class UniversityService {
	private UniversityDao universityDAO = new UniversityDao();
	public void insert(University obj) {
	}

	public void update(University obj) {
	}

	public void delete(Long id) {
	}

	public University selectOneById(Long id) {
		return null;
	}

	public List<University> selectAll() {
		return universityDAO.selectAll();
	}

	public List<University> queryPage(BaseQueryPageDTO dto) {
		return null;
	}
}
