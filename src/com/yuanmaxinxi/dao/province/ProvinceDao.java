package com.yuanmaxinxi.dao.province;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yuanmaxinxi.dao.BaseDAO;
import com.yuanmaxinxi.dto.BaseQueryPageDTO;
import com.yuanmaxinxi.entity.province.Province;
import com.yuanmaxinxi.util.DBUtil;


public class ProvinceDao implements BaseDAO<Province>{
	Connection conn = DBUtil.getConn();
	PreparedStatement sql;
	@Override
	public int insert(Province obj) {
		// TODO Auto-generated method stub
		Long id = obj.getId();
		String name = obj.getName();
		String sql="insert into t_province(id,name) values "+id+""+name;
		try {
			PreparedStatement pre = conn.prepareStatement(sql);
			int result = pre.executeUpdate();
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
		
	}

	@Override
	public int update(Province obj) {
		Long id = obj.getId();
		String name = obj.getName();
		String sql="updata t_province set id="+id+",name="+name+ "where id="+id;
		try {
			PreparedStatement pre = conn.prepareStatement(sql);
			int result = pre.executeUpdate();
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
		
	}

	@Override
	public int delete(Long id) {
		String sql="delect from t_province where id="+id;
		try {
			PreparedStatement pre = conn.prepareStatement(sql);
			int result = pre.executeUpdate();
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
		
	}

	@Override
	public Province selectOneById(Long id) {
		// TODO Auto-generated method stub
		Province province = new Province();
		String sql="select from t_province where id="+id;
		try {
			PreparedStatement pre = conn.prepareStatement(sql);
			ResultSet result = pre.executeQuery();
			while(result.next()) {
				province.setId(result.getLong("id"));
				province.setName(result.getString("name"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();	
		}
		return province;
	}

	@Override
	public List<Province> selectAll() {
		// TODO Auto-generated method stub
		ArrayList<Province> list = new ArrayList<>();
		Province province = new Province();
		String sql="select * from t_province";
		try {
			PreparedStatement pre = conn.prepareStatement(sql);
			ResultSet result = pre.executeQuery();
			while(result.next()) {
				province.setId(result.getLong("id"));
				province.setName(result.getString("name"));
				list.add(province);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();	
		}
		return list;
	}

	@Override
	public List<Province> queryPage(BaseQueryPageDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

}
