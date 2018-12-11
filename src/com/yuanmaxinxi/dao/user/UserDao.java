package com.yuanmaxinxi.dao.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yuanmaxinxi.dao.BaseDAO;
import com.yuanmaxinxi.dto.BaseQueryPageDTO;
import com.yuanmaxinxi.entity.payrecord.PayRecord;
import com.yuanmaxinxi.entity.user.User;
import com.yuanmaxinxi.util.DBUtil;

public class UserDao implements BaseDAO<User>{
	Connection conn = DBUtil.getConn();
	PreparedStatement sql;
	@Override
	public int insert(User obj) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(User obj) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Long id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public User selectOneById(Long id) {
		// TODO Auto-generated method stub
		User user = new User();
		String sql="select from t_province where id="+id;
		try {
			PreparedStatement pre = conn.prepareStatement(sql);
			ResultSet result = pre.executeQuery();
			while(result.next()) {
				user.setId(result.getInt("id"));
				user.setUsername(result.getString("username"));
				user.setPassword(result.getString("password"));
				user.setVip(result.getInt("vip"));	
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();	
		}
		return user;
	}

	@Override
	public List<User> selectAll() {
		// TODO Auto-generated method stub
		ArrayList<User> list = new ArrayList<>();
		User user = new User();
		String sql="select * from t_user";
		try {
			PreparedStatement pre = conn.prepareStatement(sql);
			ResultSet result = pre.executeQuery();
			while(result.next()) {
				user.setId(result.getInt("id"));
				user.setUsername(result.getString("username"));
				user.setPassword(result.getString("password"));
				user.setVip(result.getInt("vip"));
				list.add(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();	
		}
		return list;
	}

	@Override
	public List<User> queryPage(BaseQueryPageDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
