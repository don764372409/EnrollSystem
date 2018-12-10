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
import com.yuanmaxinxi.dto.university.UniversityDTO;
import com.yuanmaxinxi.util.DBUtil;

public class UniversityDao implements BaseDAO<UniversityDao>{
	static {
		Properties properties = new Properties();
		try {
			properties.load(new FileInputStream("C:\\Users\\YDM-STU001\\Desktop\\EnrollSystem\\resource\\dbb.properties"));
			DBUtil.setProperties(properties);
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	public static Connection conn=DBUtil.getConn();
	public int insert(UniversityDao obj) {
		
		return 0;
	}

	@Override
	public int update(UniversityDao obj) {
		return 0;
	}

	@Override
	public int delete(Long id) {
		return 0;
	}

	@Override
	public UniversityDao selectOneById(Long id) {
		return null;
	}

	@Override
	public List<UniversityDao> selectAll() {
		try {
			List<UniversityDao> list = new ArrayList<>();
			String sql="select * from enroll";
			PreparedStatement state = conn.prepareStatement(sql);
			ResultSet result = state.executeQuery();
//			while(result.next()) {
//				UniversityDTO uni = new UniversityDTO();
//				uni.setId(result.getLong(columnLabel));
//				result.getLong("id");
//			}
			return null;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<UniversityDao> queryPage(BaseQueryPageDTO dto) {
		return null;
	}


}
