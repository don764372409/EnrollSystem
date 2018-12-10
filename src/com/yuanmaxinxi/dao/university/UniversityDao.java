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
<<<<<<< HEAD
	static {
		Properties properties = new Properties();
		try {
			properties.load(new FileInputStream("C:\\Users\\YDM-STU001\\Desktop\\EnrollSystem\\resource\\dbb.properties"));
//			DBUtil.setProperties(properties);
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
=======
>>>>>>> branch 'master' of https://github.com/don764372409/EnrollSystem.git
	@Override
	public int insert(University obj) {
		try {
			String sql="insert into enroll(pId,address,quality,type,remake,ranking,teachers,record,subject) values (?,?,?,?,?,?,?,?,?)";
			PreparedStatement state = DBUtil.getConn().prepareStatement(sql);
			int row = state.executeUpdate();
			return row;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	@Override
	public int update(University obj) {
		try {
			String sql="update enroll set id=?,pId = ?,address=?,quality=?,type=?,remake=?,ranking=?,teachers=?,record=?,subject=?";
			PreparedStatement state = DBUtil.getConn().prepareStatement(sql);
			state.setObject(1, obj.getId());
			state.setObject(2, obj.getpId());
			state.setObject(3, obj.getAddress());
			state.setObject(4, obj.getQuality());
			state.setObject(5, obj.getType());
			state.setObject(6, obj.getRemark());
			state.setObject(7, obj.getRanking());
			state.setObject(8, obj.getTeachers());
			state.setObject(9, obj.getRecord());
			state.setObject(10, obj.getSubject());
			int row = state.executeUpdate();
			return row;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int delete(Long id) {
		try {
			String sql="delete from enroll where id=?";
			PreparedStatement state = DBUtil.getConn().prepareStatement(sql);
			state.setObject(1, id);
			int row = state.executeUpdate();
			return row;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public University selectOneById(Long id) {
		try {
			University uni = new University();
			String sql="select * from enroll where id = ?";
			PreparedStatement state = DBUtil.getConn().prepareStatement(sql);
			state.setObject(1, id);
			ResultSet result = state.executeQuery();
			if(result.next()) {
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
			}
			return uni;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	//查询所有的方法
	@Override
	public  List<University> selectAll() {
		try {
			List<University> list = new ArrayList<>();
			String sql="select * from enroll";
			PreparedStatement state = DBUtil.getConn().prepareStatement(sql);
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
