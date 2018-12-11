package com.yuanmaxinxi.dao.occupation;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yuanmaxinxi.dao.BaseDAO;
import com.yuanmaxinxi.dto.BaseQueryPageDTO;
import com.yuanmaxinxi.entity.major.Major;
import com.yuanmaxinxi.entity.occupation.Occupation;
import com.yuanmaxinxi.util.DBUtil;

public class OccupationDAO implements BaseDAO<Occupation>{

	@Override
	public int insert(Occupation obj) {
		try {
			//获取sql语句
			String sql = "insert into t_occupation(id,name,pId,remark,workContent)values"
					+ "('1','叫做','1','啊啊','啊啊')";
				PreparedStatement state = DBUtil.getConn().prepareStatement(sql);
				int result = state.executeUpdate();
				return result;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return -1;
	}

	@Override
	public int update(Occupation obj) {
		try {
			String sql = "update t_occupation set name = "+obj.getName()+",pId = "+obj.getpId()+",remark = "+obj.getRemark()+", workContent = "+obj.getWorkContent()+"where id = "+obj.getId();
				PreparedStatement state = DBUtil.getConn().prepareStatement(sql);
				int result = state.executeUpdate();
				return result;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return 0;
	}

	@Override
	public int delete(Long id) {
		try {
			String sql = "delete from t_occupation where id ="+id;
				PreparedStatement state = DBUtil.getConn().prepareStatement(sql);
				int result = state.executeUpdate();
				return result;
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return -1;
	}

	@Override
	public Occupation selectOneById(Long id) {
		List<Occupation> list = new ArrayList<>();
		try {
			String sql = "select * from t_occupation where id = "+id;
			PreparedStatement state = DBUtil.getConn().prepareStatement(sql);
			ResultSet result = state.executeQuery();
			if(result.next()) {
				Occupation oc = new Occupation();
				oc.setId(result.getLong("id"));
				oc.setName(result.getString("name"));
				oc.setpId(result.getLong("pId"));
				oc.setWorkContent(result.getString("workContent"));
				oc.setRemark(result.getString("remark"));
				list.add(oc);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Occupation> selectAll() {
		List<Occupation> list = new ArrayList<>();
		try {
			String sql = "select * from Occupation";
			PreparedStatement state = DBUtil.getConn().prepareStatement(sql);
			ResultSet result = state.executeQuery();
			while(result.next()) {
				Occupation op = new Occupation();
				op.setId(result.getLong("id"));
				op.setName(result.getString("name"));
				op.setpId(result.getLong("pId"));
				op.setRemark(result.getString("remark"));
				op.setWorkContent(result.getString("workContent"));
				list.add(op);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<Occupation> queryPage(BaseQueryPageDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

}
