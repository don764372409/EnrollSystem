<<<<<<< HEAD
package com.yuanmaxinxi.dao.major;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yuanmaxinxi.dao.BaseDAO;
import com.yuanmaxinxi.dto.BaseQueryPageDTO;
import com.yuanmaxinxi.entity.major.Major;
import com.yuanmaxinxi.util.DBUtil;

public class MajorDAO implements BaseDAO<Major>{
	
	@Override
	public int insert(Major obj) {
		try {
		//获取sql语句
		String sql = "insert into t_major(name,pId,type,remark,majorExplain,ranking)values (?,?,?,?,?,?)";
			PreparedStatement state = DBUtil.getConn().prepareStatement(sql);
			state.setObject(1, obj.getName());
			state.setObject(2, obj.getpId() );
			state.setObject(3, obj.getType());
			state.setObject(4, obj.getRemark());
			state.setObject(5, obj.getMajorExplain());
			state.setObject(6, obj.getRanking());
			System.err.println(sql);
			return state.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return -1;
	}

	@Override
	public int update(Major obj) {
		try {
		String sql = "update t_major set name = ?,pId=?,type=?,remark=?,majorExplain=?,ranking=? where id = ?";
		PreparedStatement state = DBUtil.getConn().prepareStatement(sql);
		state.setObject(1, obj.getId());
		state.setObject(2, obj.getName());
		state.setObject(3, obj.getpId());
		state.setObject(4, obj.getType());
		state.setObject(5, obj.getRemark());
		state.setObject(6, obj.getMajorExplain());
		state.setObject(7, obj.getRanking());
			return state.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public int delete(Long id) {
		try {
			String sql = "delete from t_majir where id =?";
				PreparedStatement state = DBUtil.getConn().prepareStatement(sql);
				state.setObject(1, id);
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
			String sql = "select * from t_major where id =? ";
			PreparedStatement state = DBUtil.getConn().prepareStatement(sql);
			ResultSet result = state.executeQuery();
			if(result.next()) {
				Major major = new Major();
				major.setId(result.getLong("id"));
				major.setName(result.getString("name"));
				major.setpId(result.getLong("pId"));
				major.setType(result.getLong("name"));
				major.setRemark(result.getString("remark"));
				major.setMajorExplain(result.getString("majorExplain"));
				major.setRanking(result.getInt("ranking"));
				list.add(major);
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
				major.setType(result.getLong("name"));
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

}
=======
package com.yuanmaxinxi.dao.major;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.yuanmaxinxi.dao.BaseDAO;
import com.yuanmaxinxi.dto.BaseQueryPageDTO;
import com.yuanmaxinxi.entity.major.Major;
import com.yuanmaxinxi.util.DBUtil;

public class MajorDAO implements BaseDAO<Major>{
	
	@Override
	public int insert(Major obj) {
		try {
		//获取sql语句
		String sql = "insert into t_major(id,name,pId,type,remark,explain,ranking)values"
				+ "(?,?,?,?,?,?,?)";
			PreparedStatement state = DBUtil.getConn().prepareStatement(sql);
			int result = state.executeUpdate();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return -1;
	}

	@Override
	public int update(Major obj) {
		try {
		String sql = "update t_major set name = "+obj.getName()+",pId = "+obj.getpId()+",type "
				+ "= "+obj.getType()+",remark = "+obj.getRemark()+",explain = "+obj.getExplain()+",ranking "
						+ "= "+obj.getRanking()+" where id = "+obj.getId();
			PreparedStatement state = DBUtil.getConn().prepareStatement(sql);
			int result = state.executeUpdate();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public int delete(Long id) {
		try {
			String sql = "delete from t_majir where id ="+id;
				PreparedStatement state = DBUtil.getConn().prepareStatement(sql);
				int result = state.executeUpdate();
				return result;
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return -1;
	}

	@Override
	public Major selectOneById(Long id) {
		List<Major> list = new ArrayList<>();
		try {
			String sql = "select * from Major where id = "+id;
			PreparedStatement state = DBUtil.getConn().prepareStatement(sql);
			ResultSet result = state.executeQuery();
			if(result.next()) {
				Major major = new Major();
				major.setId(result.getLong("id"));
				major.setName(result.getString("name"));
				major.setpId(result.getLong("pId"));
				major.setType(result.getLong("name"));
				major.setRemark(result.getString("remark"));
				major.setExplain(result.getString("explain"));
				major.setRanking(result.getString("ranking"));
				list.add(major);
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
			String sql = "select * from Major";
			PreparedStatement state = DBUtil.getConn().prepareStatement(sql);
			ResultSet result = state.executeQuery();
			while(result.next()) {
				Major major = new Major();
				major.setId(result.getLong("id"));
				major.setName(result.getString("name"));
				major.setpId(result.getLong("pId"));
				major.setType(result.getLong("name"));
				major.setRemark(result.getString("remark"));
				major.setExplain(result.getString("explain"));
				major.setRanking(result.getString("ranking"));
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

}
>>>>>>> branch 'master' of https://github.com/don764372409/EnrollSystem.git
