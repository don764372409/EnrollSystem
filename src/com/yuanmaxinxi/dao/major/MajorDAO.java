package com.yuanmaxinxi.dao.major;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
		
		//获取sql语句
		String sql = "insert into t_major(id,name,pId,type,remark,explain,ranking)values"
				+ "('1','叫做','1','1','啊啊','啊啊','2')";
		
		return 0;
	}

	@Override
	public int update(Major obj) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Long id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Major selectOneById(Long id) {
		// TODO Auto-generated method stub
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
