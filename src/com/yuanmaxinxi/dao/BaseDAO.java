package com.yuanmaxinxi.dao;

import java.sql.SQLException;
import java.util.List;

import com.yuanmaxinxi.dto.MyBatisQueryPageDTO;

public interface BaseDAO<T> {
	int insert(T obj) throws SQLException;
	int update(T obj) throws SQLException;
	int delete(Long id) throws SQLException;
	T selectOneById(Long id);
	List<T> selectAll();
	List<T> queryPage(MyBatisQueryPageDTO<T> dto);
	int selectCount(MyBatisQueryPageDTO<T> dto);
	default void query(MyBatisQueryPageDTO<T> dto) {
		int count = selectCount(dto);
		dto.setCount(count);
		List<T> list = queryPage(dto);
		dto.setRows(list);
	}
}
