package com.yuanmaxinxi.dao;

import java.sql.SQLException;
import java.util.List;
import com.yuanmaxinxi.dto.BaseQueryPageDTO;
import com.yuanmaxinxi.entity.university.University;

public interface BaseDAO<T> {
	int insert(T obj) throws SQLException;
	int update(T obj) throws SQLException;
	int delete(Long id) throws SQLException;
	T selectOneById(Long id);
	List<T> selectAll();
	List<T> queryPage(BaseQueryPageDTO dto);
	/**
	 * @param dto
	 * @param str1
	 * @param str2
	 * @return
	 */
	List<University> queryPage(String wheresql, int pageNum, int pageSize);
	List<University> queryPage(int pageNum, int pageSize);
}
