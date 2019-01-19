package com.yuanmaxinxi.service;

import java.util.ArrayList;
import java.util.List;

import com.yuanmaxinxi.dao.major.MajorDAO;
import com.yuanmaxinxi.dto.BaseQueryPageDTO;
import com.yuanmaxinxi.entity.major.Major;
import com.yuanmaxinxi.entity.major.Major2;

public class MajorService {
	private MajorDAO majorDAO = new MajorDAO();
	public void insert(Major obj) {
		majorDAO.insert(obj);
//		System.err.println("service"+obj);s
	}

	public void update(Major obj) {
		majorDAO.update(obj);
	}

	public void delete(Long id) {
		majorDAO.delete(id);
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