package com.yuanmaxinxi.service;

import java.util.List;

import com.yuanmaxinxi.dao.dictionary.DictionaryDAO;
import com.yuanmaxinxi.entity.admin.Admin;
import com.yuanmaxinxi.entity.dictionary.Dictionary;
import com.yuanmaxinxi.util.MD5Util;
import com.yuanmaxinxi.util.StringUtil;


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
		if (StringUtil.isNullOrEmpty(obj.getName())) {
			throw new RuntimeException("名称不能为空.");
		}
		//通过账号去数据库找,是否存在
		if (dd.selectOneByName(obj.getName())!=null) {
			throw new RuntimeException("名称存在,不能重复,请修改后再次添加.");
		}
		//添加
		try {
			if (dd.insert(obj)!=1) {
				throw new RuntimeException("");
			}
		} catch (Exception e) {
			throw new RuntimeException("添加失败,请稍后重试.");
		}
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
