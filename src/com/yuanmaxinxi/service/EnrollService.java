package com.yuanmaxinxi.service;

import java.util.List;

import com.yuanmaxinxi.dao.enroll.EnrollDAO;
import com.yuanmaxinxi.entity.enroll.Enroll;

public class EnrollService {
	private static EnrollService es;
	private EnrollDAO ed;
	private EnrollService() {
		ed = EnrollDAO.getDictionaryDao();
	}
	public static EnrollService getDictionaryService() {
		return es == null ? es = new EnrollService() : es;
	}
	
	public void insert(Enroll obj) {
	}

	public void update(Enroll obj) {
	}

	public void delete(Long id) {
	}

	public Enroll selectOneById(Long id) {
		return null;
	}

	public List<Enroll> selectAll() {
		return ed.selectAll();
	}
}