package com.yuanmaxinxi.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.yuanmaxinxi.dao.admin.AdminDAO;
import com.yuanmaxinxi.dao.occupation.OccupationDAO;
import com.yuanmaxinxi.dto.BaseQueryPageDTO;
import com.yuanmaxinxi.entity.admin.Admin;
//github.com/don764372409/EnrollSystem.git
import com.yuanmaxinxi.entity.occupation.Occupation;
import com.yuanmaxinxi.util.DBUtil;

public class OccupationService {
//	private OccupationDAO ocpDAO;
//	private void init() {
//		SqlSession session = DBUtil.openSession();
//		ocpDAO = session.getMapper(OccupationDAO.class);
//	}
//	public OccupationService() {
//		init();
//	}
//	public void insert(Occupation obj) {
//		ocpDAO.insert(obj);
//	}
//
//	public void update(Occupation obj) {
//		ocpDAO.update(obj);
//	}
//
//	public void delete(Long id) {
//		ocpDAO.delete(id);
//	}
//
//	public Occupation selectOneById(Long id) {
//		return ocpDAO.selectOneById(id);
//	}
//
//	public List<Occupation> selectAll() {
//		return ocpDAO.selectAll();
//	}
//
//	public List<Occupation> queryPage(BaseQueryPageDTO dto) {
//		return null;
//	}
}