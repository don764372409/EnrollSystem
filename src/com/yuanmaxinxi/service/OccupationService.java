package com.yuanmaxinxi.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yuanmaxinxi.dao.occupation.OccupationDAO;
//github.com/don764372409/EnrollSystem.git
import com.yuanmaxinxi.entity.occupation.Occupation;
@Service
public class OccupationService {
	
	@Autowired
	OccupationDAO occupationDAO;
	
	
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
	public List<Occupation> selectAll() {
		return occupationDAO.selectAll();
	}
//
//	public List<Occupation> queryPage(BaseQueryPageDTO dto) {
//		return null;
//	}
}