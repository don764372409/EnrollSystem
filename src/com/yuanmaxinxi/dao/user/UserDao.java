package com.yuanmaxinxi.dao.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yuanmaxinxi.dao.BaseDAO;
import com.yuanmaxinxi.dto.BaseQueryPageDTO;
import com.yuanmaxinxi.dto.DeptQueryPageDTO;
import com.yuanmaxinxi.entity.payrecord.PayRecord;
import com.yuanmaxinxi.entity.university.University;
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
	
	public List<User> selectOneById(DeptQueryPageDTO deptQuery) throws Exception {
		List<User> list = new ArrayList<>();
		String whereSql = deptQuery.getSql();
		try {
			sql = conn.prepareStatement(whereSql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<Object> deptList=deptQuery.getParams();
		System.out.println(deptList.size());
		for(int i=0;i<deptList.size();i++) {
			try {
				sql.setObject(i+1, deptList.get(i));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		ResultSet result = sql.executeQuery();
		while(result.next()) {
			User user = new User();
			user.setId(result.getInt("id"));
			user.setUsername(result.getString("username"));
			user.setPassword(result.getString("password"));
			user.setVip(result.getInt("vip"));
			list.add(user);
		}
		
		return list;
		
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

	@Override
	public User selectOneById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<University> queryPage(BaseQueryPageDTO dto, int pageNum, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
