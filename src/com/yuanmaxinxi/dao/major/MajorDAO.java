package com.yuanmaxinxi.dao.major;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yuanmaxinxi.dao.BaseDAO;
import com.yuanmaxinxi.dto.BaseQueryPageDTO;
import com.yuanmaxinxi.entity.major.Major;
import com.yuanmaxinxi.entity.university.University;
import com.yuanmaxinxi.util.DBUtil;

public class MajorDAO implements BaseDAO<Major>{
	
	@Override
	public int insert(Major obj) {
		try {
		//获取sql语句
		String sql = "insert into t_major(name,pId,type,remark,majorExplain,ranking)values"
				+ "(?,?,?,?,?,?)";
			PreparedStatement state = DBUtil.getConn().prepareStatement(sql);
			state.setObject(1, obj.getName());
			state.setObject(2, obj.getId());
			state.setObject(3, obj.getType());
			state.setObject(4, obj.getRemark());
			state.setObject(5, obj.getMajorExplain());
			state.setObject(6, obj.getRanking());
//			System.err.println(obj.getRemark());
			return state.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return -1;
	}

	@Override
	public int update(Major obj) {
		try {
		String sql = "update t_major set name = ?,pId=?,type=?,remark=?,majorExplain=?,ranking=? where id =?";
			PreparedStatement state = DBUtil.getConn().prepareStatement(sql);
			state.setObject(1, obj.getName());
			state.setObject(2, obj.getpId());
			state.setObject(3, obj.getType());
			state.setObject(4, obj.getRemark());
			state.setObject(5, obj.getMajorExplain());
			state.setObject(6, obj.getRanking());
			state.setObject(7, obj.getId());
			System.err.println(sql);
			System.err.println(state);
			return state.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public int delete(Long id) {
		try {
			String sql = "delete from t_major where id =?";
				PreparedStatement state = DBUtil.getConn().prepareStatement(sql);
				state.setObject(1,id);
				return state.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return -1;
	}

	@Override
	public Major selectOneById(Long id) {
		List<Major> list = new ArrayList<>();
		try {
			String sql = "select * from t_major where id = ?";
			PreparedStatement state = DBUtil.getConn().prepareStatement(sql);
			state.setObject(1,id);
			ResultSet result = state.executeQuery();
			if(result.next()) {
				Major major = new Major();
				major.setId(result.getLong("id"));
				major.setName(result.getString("name"));
				major.setpId(result.getLong("pId"));
				major.setType(result.getLong("type"));
				major.setRemark(result.getString("remark"));
				major.setMajorExplain(result.getString("majorExplain"));
				major.setRanking(result.getInt("ranking"));
//				System.err.println(major);
				return major;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Major> selectAll() {
		List<Major> list = new ArrayList<>();
		try {
			String sql = "select * from t_major";
			PreparedStatement state = DBUtil.getConn().prepareStatement(sql);
			ResultSet result = state.executeQuery();
			while(result.next()) {
				Major major = new Major();
				major.setId(result.getLong("id"));
				major.setName(result.getString("name"));
				major.setpId(result.getLong("pId"));
				major.setType(result.getLong("type"));
				major.setRemark(result.getString("remark"));
				major.setMajorExplain(result.getString("majorExplain"));
				major.setRanking(result.getInt("ranking"));
				list.add(major);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<Major> queryPage(BaseQueryPageDTO dto) {
		
		return null;
	}

	@Override
	public List<University> queryPage(String wheresql, int pageNum, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<University> queryPage(int pageNum, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

}