package com.yuanmaxinxi.dao.occupation;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Occupation obj) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Long id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Occupation selectOneById(Long id) {
		// TODO Auto-generated method stub
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
