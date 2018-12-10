package com.yuanmaxinxi.dao.admin;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.yuanmaxinxi.dao.BaseDAO;
import com.yuanmaxinxi.dto.BaseQueryPageDTO;
import com.yuanmaxinxi.entity.admin.Admin;
import com.yuanmaxinxi.util.DBUtil;

public class AdminDAO implements BaseDAO<Admin>{

	@Override
	public int insert(Admin obj) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Admin obj) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Long id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Admin selectOneById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Admin> selectAll() {
		List<Admin> list = new ArrayList<>();
		try {
			PreparedStatement state = DBUtil.getConn().prepareStatement("select * from t_admin");
			ResultSet resultSet = state.executeQuery();
			while(resultSet.next()) {
				Admin admin = new Admin();
				admin.setId(resultSet.getLong("id"));
				admin.setUsername(resultSet.getString("username"));
				admin.setName(resultSet.getString("name"));
				admin.setPassword(resultSet.getString("password"));
				admin.setHeadImg(resultSet.getString("headImg"));
				admin.setPhone(resultSet.getString("phone"));
				admin.setStatus(resultSet.getInt("status"));
				list.add(admin);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<Admin> queryPage(BaseQueryPageDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
