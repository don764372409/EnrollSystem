package com.yuanmaxinxi.dao.university;


import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.yuanmaxinxi.dao.BaseDAO;
import com.yuanmaxinxi.entity.enroll.Enroll;
import com.yuanmaxinxi.entity.major.Major;
import com.yuanmaxinxi.entity.province.Province;
import com.yuanmaxinxi.entity.university.University;
import com.yuanmaxinxi.entity.university.jianzhang.Jianzhang;


public interface UniversityDao extends BaseDAO<University>{
	University selectOneByName(String name);

	public int updateRanking(University uni);
	public List<String> selectUniversityProperty();
	
	/**
	 * 根据学校ID查询学校的录取专业
	 * @param id
	 * @param activBatch 
	 * @return
	 */
	public List<Major> selectMajorsById(Map<String,String> map);
	/**
	 * 根据学校ID获取录取数据 指定专业最新的五个年份
	 * @param String id,String activBatch,String mId
	 * @return
	 */
	public List<Enroll> selectYearByMajorAndBidAndId(Map<String,String> map);
	/**
	 * 
	 * @param map 
	 * @param uId
	 * @param id
	 * @return
	 */
	public University selectOneShoucang(Map<String, String> map);
	/**
	 * 添加收藏
	 * @param uId
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public int addShoucang(Map<String,String> map)throws SQLException;
	/**
	 * 
	 * @param uId
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public int unShoucang(Map<String,String> map)throws SQLException;

	public List<University> selectShoucangUnis(String uId);

	
	public List<University> selectUnisByIds(@Param("ids")String[] ids);
	public List<University> selectUnisList();

	List<Jianzhang> selectAllJianZhangById(Long uId);

	List<University> selectAllName();
	List<String> selectPropertys();
	
	int deleteById(int id);

	List<Province> selectProvince();

	List<Major> selectMajor(Map<String,String> map);

	List<Enroll> selectEnrollByTwoUni(Map<String, String> map);
	int update(University obj);
	
}
