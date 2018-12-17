package com.yuanmaxinxi.service;

import java.util.List;

import com.yuanmaxinxi.dao.dictionary.DictionaryDAO;
import com.yuanmaxinxi.dao.dictionaryType.DictionaryTypeDAO;
import com.yuanmaxinxi.entity.admin.Admin;
import com.yuanmaxinxi.entity.dictionary.Dictionary;
import com.yuanmaxinxi.entity.dictionaryType.DictionaryType;
import com.yuanmaxinxi.util.MD5Util;
import com.yuanmaxinxi.util.StringUtil;


public class DictionaryTypeService {
	private static DictionaryTypeService dts;
	private DictionaryTypeDAO dtd;
	private DictionaryTypeService() {
		dtd = DictionaryTypeDAO.getDictionaryTypeDao();
	}
	public static DictionaryTypeService getDictionaryService() {
		return dts == null ? dts = new DictionaryTypeService() : dts;
	}
	
	public void insert(DictionaryType obj) {
		if (StringUtil.isNullOrEmpty(obj.getName())) {
			throw new RuntimeException("名称不能为空.");
		}
		//通过账号去数据库找,是否存在
		if (dtd.selectOneByName(obj.getName())!=null) {
			throw new RuntimeException("名称存在,不能重复,请修改后再次添加.");
		}
		//添加
		try {
			if (dtd.insert(obj)!=1) {
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

	public List<DictionaryType> selectAll() {
		return dtd.selectAll();
	}
}
