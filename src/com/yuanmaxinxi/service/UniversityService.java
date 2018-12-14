package com.yuanmaxinxi.service;

import java.util.List;

import com.yuanmaxinxi.dao.university.UniversityDao;
import com.yuanmaxinxi.dto.BaseQueryPageDTO;
import com.yuanmaxinxi.entity.dictionary.Dictionary;
import com.yuanmaxinxi.entity.university.University;
import com.yuanmaxinxi.util.StringUtil;


//主要进行非空验证
public class UniversityService {
	private UniversityDao universityDAO = new UniversityDao();
	public void insert(University obj) {
		if(StringUtil.isNotNullAndEmpty(obj.getName())) {
			throw new RuntimeException("院校名称不能空");
		}
		if(StringUtil.isNotNullAndEmpty(obj.getAddress())) {
			throw new RuntimeException("院校所在地不能空");
		}
		if(obj.getQuality()==0||obj.getQuality()==null) {
			throw new RuntimeException("院校所在地不能空");
		}
		if(obj.getType()==0||obj.getType()==null) {
			throw new RuntimeException("院校所在地不能空");
		}
		if(StringUtil.isNotNullAndEmpty(obj.getRemark())) {
			throw new RuntimeException("院校所在地不能空");
		}
		if(obj.getRanking()==0) {
			throw new RuntimeException("院校所在地不能空");
		}
		if(StringUtil.isNotNullAndEmpty(obj.getAddress())) {
			throw new RuntimeException("院校所在地不能空");
		}
		if(StringUtil.isNotNullAndEmpty(obj.getAddress())) {
			throw new RuntimeException("院校所在地不能空");
		}
	}

	public void update(University obj) {
	}

	public void delete(Long id) {
	}

	public University selectOneById(Long id) {
		return null;
	}

	//查询所有的院校信息
	public List<University> selectAll() {
		//不存在信息的异常提示，只提示服务器，客户端不做提示
		List<University> selectAll = universityDAO.selectAll();
//		if (selectAll==null) {
//			throw new RuntimeException("院校信息不存在.");
//		}
		return selectAll;
	}
	//查询所有省份表
	public  List<University> selectAllByProvince() {
		List<University> selectAllByProvince = universityDAO.selectAllByProvince();
//		if (selectAllByProvince==null) {
//			throw new RuntimeException("省份信息不存在.");
//		}
		return selectAllByProvince;
	}
	
	public List<University> queryPage(BaseQueryPageDTO dto) {
		return null;
	}

	public List<Dictionary> selectAllByQuality() {
		List<Dictionary> selectAllByQuality = universityDAO.selectAllByQuality();
//		if (selectAllByQuality==null) {
//			throw new RuntimeException("院校水平信息不存在.");
//		}
		return selectAllByQuality;
	}

	public List<Dictionary> selectAllByType() {
		List<Dictionary> selectAllByType = universityDAO.selectAllByType();
//		if (selectAllByType==null) {
//			throw new RuntimeException("院校类型信息不存在.");
//		}
		return selectAllByType;
	}

	public List<Dictionary> selectAllByRecord() {
		List<Dictionary> selecetAllByRecord = universityDAO.selecetAllByRecord();
//		if(selecetAllByRecord==null) {
//			throw new RuntimeException("院校学历信息不存在");
//		}
		return selecetAllByRecord;
	}
}
