package com.yuanmaxinxi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yuanmaxinxi.dao.dictionary.DictionaryDAO;
import com.yuanmaxinxi.entity.admin.Admin;
import com.yuanmaxinxi.entity.dictionary.Dictionary;
import com.yuanmaxinxi.util.MD5Util;
import com.yuanmaxinxi.util.StringUtil;

@Service
public class TestInfoService1 {
	@Autowired
	private DictionaryDAO dictionaryDAO;
	public List<Dictionary> selectAll(){
		return dictionaryDAO.selectAll();
	}
	
	public Dictionary selectOneById(int id) {
		return dictionaryDAO.selectOneById(id);	
	}
	public void edit(Dictionary dictionary) {
		if(StringUtil.isNullOrEmpty(dictionary.getName())) {
			throw new RuntimeException("名字不能为空");
		}
		if(StringUtil.isNullOrEmpty(Long.toString(dictionary.getTypeId()))){
			throw new RuntimeException("typeId不能为空");
		}
		try {
			int rs = dictionaryDAO.edit(dictionary);
			if(rs!=1) {
				System.out.println("111111111");
				throw new RuntimeException("添加失败");
				
			}
		} catch (Exception e) {
			System.out.println("222222");
			throw new RuntimeException("添加失败");
		}
	}
//	private static DictionaryService ds;
//	private DictionaryDAO dd;
//	private DictionaryService() {
//		dd = DictionaryDAO.getDictionaryDao();
//	}
//	public static DictionaryService getDictionaryService() {
//		return ds == null ? ds = new DictionaryService() : ds;
//	}
//	
//	public void insert(Dictionary obj) throws Exception {
//		if (StringUtil.isNullOrEmpty(obj.getName())) {
//			throw new RuntimeException("名称不能为空.");
//		}
//		//通过账号去数据库找,是否存在
//		if (dd.selectOneByName(obj.getName())!=null) {
//			throw new RuntimeException("名称存在,不能重复,请修改后再次添加.");
//		}
//		//添加
//		try {
//			if (dd.insert(obj)!=1) {
//				throw new RuntimeException("");
//			}
//		} catch (Exception e) {
//			throw new RuntimeException("添加失败,请稍后重试.");
//		}
//	}
//
//	public void update(Dictionary obj) throws Exception{
//		dd.update(obj);
//	}
//
//	public void delete(Long id) throws Exception{
//		dd.delete(id);
//	}
//
//	public Dictionary selectOneById(Long id) {
//		return dd.selectOneById(id);
//	}
//
//	public List<Dictionary> selectAll() {
//		return dd.selectAll();
//	}

	public void deleteById(int id) {
		// TODO Auto-generated method stub
		try {
			int rs = dictionaryDAO.delete(id);
			if(rs!=1) {
				System.out.println("6666");
				throw new RuntimeException("删除失败");
			}
		} catch (Exception e) {
			System.out.println("777");
			throw new RuntimeException("删除失败");
		}
	}
}
