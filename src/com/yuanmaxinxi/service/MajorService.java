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
	public List<Major2> selectMajor2(){
		ArrayList<Major2> list = majorDAO.selectFirstMajor();
		return list;
		
	}
	public List<Major2> selectMajor2Lis(String name,String no){
		ArrayList<Major2> list = majorDAO.selectMajor2List(name, no);
		return list;
	}
}