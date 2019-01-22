package com.yuanmaxinxi.dao.province;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yuanmaxinxi.dao.BaseDAO;
import com.yuanmaxinxi.dto.BaseQueryPageDTO;
import com.yuanmaxinxi.entity.admin.Admin;
import com.yuanmaxinxi.entity.province.Province;
import com.yuanmaxinxi.util.DBUtil;


public interface ProvinceDao extends BaseDAO<Province>{
	
	public int insert(Province obj);


	
	public  int update(Province obj);

	
	public int delete(Long id);

	public Province selectOneById(Long id);
	
	public Province selectOneById(String name);

	public List<Province> selectAll();

}
