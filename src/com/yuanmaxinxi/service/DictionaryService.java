package com.yuanmaxinxi.service;

import java.util.List;

import com.yuanmaxinxi.dao.dictionary.DictionaryDAO;
import com.yuanmaxinxi.entity.dictionary.Dictionary;


public class DictionaryService {
	private static DictionaryService ds;
	private DictionaryDAO dd;
	private DictionaryService() {
		dd = DictionaryDAO.getDictionaryDao();
	}
	public static DictionaryService getDictionaryService() {
		return ds == null ? ds = new DictionaryService() : ds;
	}
	
	public void insert(Dictionary obj) {
	}

	public void update(Dictionary obj) {
	}

	public void delete(Long id) {
	}

	public Dictionary selectOneById(Long id) {
		return null;
	}

	public List<Dictionary> selectAll() {
		return dd.selectAll();
	}
}
