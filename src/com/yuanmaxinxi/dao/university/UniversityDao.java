package com.yuanmaxinxi.dao.university;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.yuanmaxinxi.dao.BaseDAO;
import com.yuanmaxinxi.dto.BaseQueryPageDTO;
import com.yuanmaxinxi.entity.university.University;
import com.yuanmaxinxi.util.DBUtil;

public class UniversityDao implements BaseDAO<University>{
	static {
		Properties properties = new Properties();
		try {
			properties.load(new FileInputStream("C:\\Users\\YDM-STU001\\Desktop\\EnrollSystem\\resource\\dbb.properties"));
//			DBUtil.setProperties(properties);
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	@Override
	public int insert(University obj) {
		// TODO Auto-generated method stub
		return 0;
	}

	public static Connection conn=DBUtil.getConn();
	public int insert(UniversityDao obj) {
		
		return 0;
	}

	@Override
	public int update(University obj) {
		return 0;
	}

	@Override
	public int delete(Long id) {
		return 0;
	}

	@Override
	public University selectOneById(Long id) {
		return null;
	}
	//查询所有的方法
	@Override
	public List<University> selectAll() {
		try {
			List<University> list = new ArrayList<>();
			String sql="select * from enroll";
			PreparedStatement state = conn.prepareStatement(sql);
			ResultSet result = state.executeQuery();
			while(result.next()) {
				University uni = new University();
				uni.setId(result.getLong("id"));
				uni.setpId(result.getLong("pId"));
				uni.setAddress(result.getString("address"));
				uni.setQuality(result.getLong("quality"));
				uni.setType(result.getLong("type"));
				uni.setRemark(result.getString("remark"));
				uni.setRanking(result.getInt("ranking"));
				uni.setTeachers(result.getString("teachers"));
				uni.setRecord(result.getLong("result"));
				uni.setSubject(result.getString("subject"));
				list.add(uni);
			}
			return list;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<University> queryPage(BaseQueryPageDTO dto) {
		return null;
	}

	

}
