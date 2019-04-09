package com.yuanmaxinxi.dao.province;

import java.util.List;

import com.yuanmaxinxi.dao.BaseDAO;
import com.yuanmaxinxi.entity.province.Province;


public interface ProvinceDao extends BaseDAO<Province>{
	
	public int insert(Province obj);
	public  int update(Province obj);
	public int delete(Long id);
	public Province selectOneById(Long id);
	public List<Province> selectAll();
	public Province selectOneByName(String name);

}
