package com.yuanmaxinxi.dao.major;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.yuanmaxinxi.dao.BaseDAO;
import com.yuanmaxinxi.dto.LikeMajor;
import com.yuanmaxinxi.entity.major.Major;
import com.yuanmaxinxi.entity.university.University;
@Repository
@Mapper
public interface MajorDAO extends BaseDAO<Major>{
	/**
	 * 爬虫爬数据的时候  需要先验证是否存在
	 * @param obj
	 * @return
	 */
	public int save(Major obj);
	public List<Major> selectFirstMajor(int type);
	/**
	 * 根据父级专业代码获取儿子们
	 * @param no
	 * @return
	 */
	public List<Major> selectChildrenByPNo(String no);
	public List<Major> selectJianjieById(long id);
	/**
	 * 得到专业  详细信息页面的 url
	 * @return
	 */
	public List<Major> selectAllMajor();
	public void updateInfo(Major mj);
	public Major selectOneByOn(String on);
	
	public LikeMajor selectLikeMajor(LikeMajor lm);
	public int insertLikeMajor(LikeMajor lm);
	public int insert(Major major);
	public void insertMajorAndUniversity(Map<String, Object> map);
	String selectxxx(Map<String, Object> map);
	public void insertXXX(String content);
	public List<Major> selectLikeMajorsById(Long id);
	public List<University> selectUnis(Long id);

	
	
	public Major selectOneBenByName(String name);
	public Major selectOneByNameXXX(String name);
	public Major selectOneByNameYYY(String name);
	public Major selectOneByNameZZZ(String name);
	public List<Major> selectAllCCC(Major major2);
	public List<Major> selectAllName();
}