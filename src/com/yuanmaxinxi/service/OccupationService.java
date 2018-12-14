package com.yuanmaxinxi.service;

import java.util.List;

import com.yuanmaxinxi.dao.occupation.OccupationDAO;
import com.yuanmaxinxi.dto.BaseQueryPageDTO;
import com.yuanmaxinxi.entity.admin.Admin;
//github.com/don764372409/EnrollSystem.git
import com.yuanmaxinxi.entity.occupation.Occupation;

public class OccupationService {
	private OccupationDAO ocpDAO = new OccupationDAO();
	public void insert(Occupation obj) {
		ocpDAO.insert(obj);
	}

	public void update(Occupation obj) {
		ocpDAO.update(obj);
	}

	public void delete(Long id) {
		ocpDAO.delete(id);
	}

	public Occupation selectOneById(Long id) {
		return ocpDAO.selectOneById(id);
	}

	public List<Occupation> selectAll() {
		return ocpDAO.selectAll();
	}

	public List<Occupation> queryPage(BaseQueryPageDTO dto) {
		return null;
	}
}