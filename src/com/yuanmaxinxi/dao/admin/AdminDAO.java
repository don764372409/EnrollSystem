package com.yuanmaxinxi.dao.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.yuanmaxinxi.dao.BaseDAO;
import com.yuanmaxinxi.dto.BaseQueryPageDTO;
import com.yuanmaxinxi.entity.admin.Admin;
import com.yuanmaxinxi.util.DBUtil;
import com.yuanmaxinxi.util.StringUtil;

public class AdminDAO implements BaseDAO<Admin>{
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public int insert(Admin obj)throws SQLException {
		String sql = "insert into t_admin(name,username,password,phone,time)value(?,?,?,?,?)";
		PreparedStatement state = DBUtil.getConn().prepareStatement(sql );
		state.setObject(1, obj.getName());
		state.setObject(2, obj.getUsername());
		state.setObject(3, obj.getPassword());
		state.setObject(4, obj.getPhone());
		state.setObject(5, new Date());
		return state.executeUpdate();
	}

	public int update(Admin obj) throws SQLException {
		PreparedStatement state = DBUtil.getConn().prepareStatement("update t_admin set name = ?,phone=?,ip=?,time=? where id = ?");
		state.setObject(1, obj.getName());
		state.setObject(2, obj.getPhone());
		state.setObject(3, obj.getIp());
		state.setObject(4, obj.getTime());
		state.setObject(5, obj.getId());
		return state.executeUpdate();
	}
	public int edit(Admin obj) throws SQLException {
		PreparedStatement state = DBUtil.getConn().prepareStatement("update t_admin set name = ?,phone=? where id = ?");
		state.setObject(1, obj.getName());
		state.setObject(2, obj.getPhone());
		state.setObject(3, obj.getId());
		return state.executeUpdate();
	}

	@Override
	public int delete(Long id) {
		// TODO Auto-generated method stub
		PreparedStatement state;
		try {
			state = DBUtil.getConn().prepareStatement("delete from t_admin where id="+id);
			return state.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	    
	}

	@Override
	public Admin selectOneById(Long id) {
		Admin admin;
		Connection conn = DBUtil.getConn();
		try {
			PreparedStatement pre = conn.prepareStatement("select * from t_admin where id="+id);
			ResultSet result = pre.executeQuery();
			while(result.next()) {
				admin = new Admin();
				admin.setId(id);
				admin.setName(result.getString("name"));
				admin.setUsername(result.getString("username"));
				admin.setHeadImg(result.getString("headImg"));
				admin.setPhone(result.getString("phone"));
				return admin;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Admin> selectAll() {
		List<Admin> list = new ArrayList<>();
		try {
			Connection conn = DBUtil.getConn();
			PreparedStatement state = conn.prepareStatement("select * from t_admin");
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
				admin.setIp(resultSet.getString("ip"));
				admin.setLastIp(resultSet.getString("lastIp"));
				String dateStr = resultSet.getString("time");
				if (StringUtil.isNotNullAndEmpty(dateStr)) {
					admin.setTime(sdf.parse(dateStr));
				}
				String loginTime = resultSet.getString("loginTime");
				if (StringUtil.isNotNullAndEmpty(loginTime)) {
					admin.setLoginTime(sdf.parse(loginTime));
				}
				String lastTime = resultSet.getString("lastTime");
				if (StringUtil.isNotNullAndEmpty(lastTime)) {
					admin.setLastTime(sdf.parse(lastTime));
				}
				list.add(admin);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public void queryPage(BaseQueryPageDTO<Admin> dto) {
	}
	/**
	 * 根据账号
	 * @param username
	 * @return
	 */
	public Admin selectOneByUsername(String username) {
		try {
			PreparedStatement state = DBUtil.getConn().prepareStatement("select * from t_admin where username = ?");
			state.setObject(1, username);
			ResultSet resultSet = state.executeQuery();
			if(resultSet.next()) {
				Admin admin = new Admin();
				admin.setId(resultSet.getLong("id"));
				admin.setUsername(resultSet.getString("username"));
				admin.setName(resultSet.getString("name"));
				admin.setPassword(resultSet.getString("password"));
				admin.setHeadImg(resultSet.getString("headImg"));
				admin.setPhone(resultSet.getString("phone"));
				admin.setStatus(resultSet.getInt("status"));
				admin.setIp(resultSet.getString("ip"));
				admin.setLastIp(resultSet.getString("lastIp"));
				String dateStr = resultSet.getString("time");
				if (StringUtil.isNotNullAndEmpty(dateStr)) {
					admin.setTime(sdf.parse(dateStr));
				}
				String loginTime = resultSet.getString("loginTime");
				if (StringUtil.isNotNullAndEmpty(loginTime)) {
					admin.setLoginTime(sdf.parse(loginTime));
				}
				String lastTime = resultSet.getString("lastTime");
				if (StringUtil.isNotNullAndEmpty(lastTime)) {
					admin.setLastTime(sdf.parse(lastTime));
				}
				return admin;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
