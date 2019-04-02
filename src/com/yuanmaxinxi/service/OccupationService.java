package com.yuanmaxinxi.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yuanmaxinxi.dao.occupation.OccupationDAO;
//github.com/don764372409/EnrollSystem.git
import com.yuanmaxinxi.entity.occupation.Occupation;
import com.yuanmaxinxi.util.StringUtil;
@Service
public class OccupationService {
	
	@Autowired
	private OccupationDAO occupationDAO;
	
	
//	private void init() {
//		SqlSession session = DBUtil.openSession();
//		ocpDAO = session.getMapper(OccupationDAO.class);
//	}
//	public OccupationService() {
//		init();
//	}
	@Transactional
	public void insert(Occupation obj) {
		if(StringUtil.isNullOrEmpty(Long.toString(obj.getpId()))) {
			throw new RuntimeException("id不能为空");
		}
		if(StringUtil.isNullOrEmpty(obj.getName())) {
			throw new RuntimeException("名称不能为空");
		}
		if(StringUtil.isNullOrEmpty(obj.getRemark())) {
			throw new RuntimeException("内容不能为空");
		}
		if(StringUtil.isNullOrEmpty(obj.getWorkContent())) {
			throw new RuntimeException("工作内容不能为空");
		}
		try {
			System.out.println("666");
			int rs = occupationDAO.insert(obj);
			if(rs!=1) {
				throw new RuntimeException("添加失败");
			}
		} catch (Exception e) {
			throw new RuntimeException("添加失败");
		}
		
	}
	
	@Transactional
	public void update(Occupation obj) {

		if(StringUtil.isNullOrEmpty(Long.toString(obj.getpId()))) {
			throw new RuntimeException("id不能为空");
		}
		if(StringUtil.isNullOrEmpty(obj.getName())) {
			throw new RuntimeException("名称不能为空");
		}
		if(StringUtil.isNullOrEmpty(obj.getRemark())) {
			throw new RuntimeException("内容不能为空");
		}
		if(StringUtil.isNullOrEmpty(obj.getWorkContent())) {
			throw new RuntimeException("工作内容不能为空");
		}
		try {
			int rs = occupationDAO.update(obj);
			if(rs!=1) {
				throw new RuntimeException("更新失败");
			}
		} catch (Exception e) {
			throw new RuntimeException("更新失败");
		}
	}
	@Transactional
	public void delete(Long id) {
		try {
			int rs = occupationDAO.delete(id);
			if(rs!=1) {
				throw new RuntimeException("删除失败");
			}
		} catch (Exception e) {
			throw new RuntimeException("删除失败");
		}
		
	}

	public Occupation selectOneById(Long id) {
		return occupationDAO.selectOneById(id);
	}
//
	public List<Occupation> selectAll() {
		return occupationDAO.selectAll();
	}
//
//	public List<Occupation> queryPage(BaseQueryPageDTO dto) {
//		return null;
//	}
}