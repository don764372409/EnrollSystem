package com.yuanmaxinxi.dao;

import java.util.List;
import com.yuanmaxinxi.dto.BaseQueryPageDTO;

public interface BaseDAO<T> {
	int insert(T obj);
	int update(T obj);
	int delete(Long id);
	T selectOneById(Long id);
	List<T> selectAll();
	List<T> queryPage(BaseQueryPageDTO dto);
}
