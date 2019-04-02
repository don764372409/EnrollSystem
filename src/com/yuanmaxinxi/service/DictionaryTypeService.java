package com.yuanmaxinxi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yuanmaxinxi.dao.dictionaryType.DictionaryTypeDAO;
import com.yuanmaxinxi.entity.dictionaryType.DictionaryType;
import com.yuanmaxinxi.util.StringUtil;
@Service
@Transactional
public class DictionaryTypeService {
//	private static DictionaryTypeService dts;
	@Autowired
	private DictionaryTypeDAO dtd;
//	private DictionaryTypeService() {
//		dtd = DictionaryTypeDAO.getDictionaryTypeDao();
//	}
//	public static DictionaryTypeService getDictionaryService() {
//		return dts == null ? dts = new DictionaryTypeService() : dts;
//	}
//	
//	public void insert(DictionaryType obj) throws Exception {
//		if (StringUtil.isNullOrEmpty(obj.getName())) {
//			throw new RuntimeException("名称不能为空.");
//		}
//		//通过账号去数据库找,是否存在
//		if (dtd.selectOneByName(obj.getName())!=null) {
//			throw new RuntimeException("名称存在,不能重复,请修改后再次添加.");
//		}
//		//添加
//		try {
//			if (dtd.insert(obj)!=1) {
//				throw new RuntimeException("");
//			}
//		} catch (Exception e) {
//			throw new RuntimeException("添加失败,请稍后重试.");
//		}
//	}
//
//	public void update(DictionaryType obj) throws Exception {
//		dtd.update(obj);
//	}
//
//	public void delete(Long id) throws Exception {
//		dtd.delete(id);
//	}
//
	public DictionaryType selectOneById(int id) {
		return dtd.selectOneById(id);
	}

	public List<DictionaryType> selectAll() {
		return dtd.selectAll();
	}
	
	public void delete(int id) {
		try {
			int rs = dtd.delete(id);
			if(rs!=1) {
				throw new RuntimeException("删除失败");
			}
		} catch (Exception e) {
			throw new RuntimeException("删除失败");
		}
		
	}
	
	public void add(DictionaryType obj) {
		if(StringUtil.isNullOrEmpty(obj.getName())) {
			throw new RuntimeException("名字不能为空");
		}
		
		try {
			int rs = dtd.insert(obj);
			if(rs!=1) {
				throw new RuntimeException("添加失败");
			}
		} catch (Exception e) {
			throw new RuntimeException("添加失败");
		}
	}
	
	public void update(DictionaryType obj) {
		if(StringUtil.isNullOrEmpty(obj.getName())) {
			throw new RuntimeException("名字不能为空");
		}
		
		try {
			int rs = dtd.update(obj);
			if(rs!=1) {
				throw new RuntimeException("修改失败");
			}
		} catch (Exception e) {
			throw new RuntimeException("修改失败");
		}
	}
}
