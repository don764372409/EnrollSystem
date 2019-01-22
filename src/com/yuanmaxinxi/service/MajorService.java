package com.yuanmaxinxi.service;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.yuanmaxinxi.dao.major.MajorDAO;
import com.yuanmaxinxi.dto.BaseQueryPageDTO;
import com.yuanmaxinxi.entity.major.Major;
import com.yuanmaxinxi.entity.major.Major2;
import com.yuanmaxinxi.util.DBUtil;

public class MajorService {
	private MajorDAO majorDAO;
	private SqlSession session;
	private void init() {
		session = DBUtil.openSession();
		session.getMapper(MajorDAO.class);
	}
	public MajorService() {
		init();
	}
	public void insert(Major obj) {
		try {
			majorDAO.insert(obj);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void update(Major obj) {
		try {
			majorDAO.update(obj);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void delete(Long id) {
		try {
			majorDAO.delete(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Major selectOneById(Long id) {
		return majorDAO.selectOneById(id);
	}

	public List<Major> selectAll() {
		return majorDAO.selectAll();
	}

	public List<Major> queryPage(BaseQueryPageDTO dto) {
		return null;
	}
	/**
	 * 获取具有层次关系的专业
	 * @return
	 */
	public List<Major2> selectAllByLayer(int type) {
		List<Major2> list = majorDAO.selectFirstMajor(type);
		for (Major2 mj : list) {
			List<Major2> children = majorDAO.selectChildrenByPNo(mj.getNo());
			mj.setChildren(children);
			for (Major2 mj2 : children) {
				List<Major2> children2 = majorDAO.selectChildrenByPNo(mj2.getNo());
				mj2.setChildren(children2);
			}
		}
		return list;
	}
	public List<Major2> selectJianjie(long id){
		List<Major2> list = majorDAO.selectJianjieById(id);
		return list;
		
	}

}