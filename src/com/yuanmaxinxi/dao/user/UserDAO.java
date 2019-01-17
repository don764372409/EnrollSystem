package com.yuanmaxinxi.dao.user;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yuanmaxinxi.dao.BaseDAO;
import com.yuanmaxinxi.dto.BaseQueryPageDTO;
import com.yuanmaxinxi.dto.DeptQueryPageDTO;
import com.yuanmaxinxi.entity.university.University;
import com.yuanmaxinxi.entity.user.User;
import com.yuanmaxinxi.util.DBUtil;

public class UserDAO implements BaseDAO<User>{
	public int insert(User obj) {
		try {
			String sql = "insert into t_user(name,url,openid,code)values(?,?,?,?)";
			PreparedStatement state = DBUtil.getConn().prepareStatement(sql);
			state.setObject(1, obj.getName());
			state.setObject(2, obj.getUrl());
			state.setObject(3, obj.getOpenid());
			state.setObject(4, obj.getCode());
			return state.executeUpdate();
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
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
//		List<User> list = new ArrayList<>();
//		String whereSql = deptQuery.getSql();
//		try {
//			sql = conn.prepareStatement(whereSql);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		List<Object> deptList=deptQuery.getParams();
//		System.out.println(deptList.size());
//		for(int i=0;i<deptList.size();i++) {
//			try {
//				sql.setObject(i+1, deptList.get(i));
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//		ResultSet result = sql.executeQuery();
//		while(result.next()) {
//			User user = new User();
//			user.setId(result.getInt("id"));
//			user.setUsername(result.getString("username"));
//			user.setPassword(result.getString("password"));
//			user.setVip(result.getInt("vip"));
//			list.add(user);
//		}
//		
		return null;
		
	}

	@Override
	public List<User> selectAll() {
		// TODO Auto-generated method stub
		ArrayList<User> list = new ArrayList<>();
		User user = new User();
		String sql="select * from t_user";
		try {
			PreparedStatement pre = DBUtil.getConn().prepareStatement(sql);
			ResultSet result = pre.executeQuery();
			while(result.next()) {
				user.setId(result.getLong("id"));
				user.setVip(result.getInt("vip"));
				list.add(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();	
		}
		return list;
	}
	public User selectOneById(Long id) {
		return null;
	}

	public User selectOneByOpenid(String openid) {
		try {
			String sql = "select * from t_user where openid=?";
			PreparedStatement state = DBUtil.getConn().prepareStatement(sql);
			state.setObject(1, openid);
			ResultSet resultSet = state.executeQuery();
			//调用方法处理结果集
			List<User> list = result(resultSet);
			if (list.size()==1) {
				return list.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 封装结果集
	 * @return
	 */
	private List<User> result(ResultSet resultSet) {
		List<User> list = new ArrayList<>();
		try {
			while(resultSet.next()) {
				User user = new User();
				user.setId(resultSet.getLong("id"));
				user.setName(resultSet.getString("name"));
				user.setOpenid(resultSet.getString("openid"));
				user.setUrl(resultSet.getString("url"));
				user.setVip(resultSet.getInt("vip"));
				user.setCode(resultSet.getString("code"));
				user.setNumber(resultSet.getString("number"));
				list.add(user);
			}
		} catch (Exception e) {
			System.err.println("结果集处理错误");
			throw new RuntimeException("查询用户失败.");
		}
		return list;
	}
	/**
	 * 根据自己唯一的邀请码找到用户对象
	 * @param code
	 * @return
	 */
	public User selectOneByCode(String code) {
		try {
			PreparedStatement state = DBUtil.getConn().prepareStatement("select * from t_user where code = ?");
			state.setObject(1, code);
			ResultSet resultSet = state.executeQuery();
			List<User> list = result(resultSet);
			if (list.size()>0) {
				return list.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("通过邀请码找邀请人时出错,请稍后再次尝试.");
		}
		return null;
	}
	/**
	 * 绑定openid对应对象的邀请人
	 * @param openid
	 * @param code
	 * @return
	 */
	public int bingNumber(String openid, String code) {
		try {
			PreparedStatement state = DBUtil.getConn().prepareStatement("update t_user set number = ? where openid = ?");
			state.setObject(1, code);
			state.setObject(2, openid);
			return state.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("绑定失败,请稍后再试");
		}
	}

	@Override
	public List<User> queryPage(BaseQueryPageDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<University> queryPage(BaseQueryPageDTO dto, int pageNum, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}
}
