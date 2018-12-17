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
	
	public void insert(Enroll obj) throws Exception{
		ed.insert(obj);
	}

	public void update(Enroll obj) throws Exception{
		ed.update(obj);
	}

	public void delete(Long id) throws Exception{
		ed.delete(id);
	}

	public Enroll selectOneById(Long id) {
		return ed.selectOneById(id);
	}

	public List<Enroll> selectAll() {
		return ed.selectAll();
	}
}