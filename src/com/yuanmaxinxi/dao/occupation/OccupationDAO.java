package com.yuanmaxinxi.dao.occupation;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.yuanmaxinxi.dao.BaseDAO;
import com.yuanmaxinxi.dto.BaseQueryPageDTO;
import com.yuanmaxinxi.entity.occupation.Occupation;
import com.yuanmaxinxi.util.DBUtil;
@Repository
@Mapper
public interface OccupationDAO extends BaseDAO<Occupation>{

	
	public int insert(Occupation obj);

	
	public int update(Occupation obj);
	
	public int delete(Long id);

	
	public Occupation selectOneById(Long id);

	
	public List<Occupation> selectAll();
	public List<Occupation> selectFirst();
	public List<Occupation> selectBypId(Long pId);
	
	public List<Occupation> selectByMajor(Long mId);

	
	public void queryPage(BaseQueryPageDTO<Occupation> dto);

}
