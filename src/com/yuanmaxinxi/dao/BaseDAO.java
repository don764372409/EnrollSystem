package com.yuanmaxinxi.dao;

import java.sql.SQLException;
import java.util.List;

import com.yuanmaxinxi.dto.MyBatisQueryPageDTO;

public interface BaseDAO<T> {
	int insert(T t) throws SQLException;
	int update(T obj);
	int delete(Long id);
	T selectOneById(Long id);
	List<T> selectAll();
	List<T> queryPage(MyBatisQueryPageDTO<T> dto);
	int selectCount(MyBatisQueryPageDTO<T> dto);
}
