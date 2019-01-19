package com.yuanmaxinxi.dao.major;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yuanmaxinxi.dao.BaseDAO;
import com.yuanmaxinxi.dto.BaseQueryPageDTO;
import com.yuanmaxinxi.entity.major.Major;
import com.yuanmaxinxi.entity.major.Major2;
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
	public int insert(Major2 obj) {
		try {
			String sql = "select id from t_major2 where no = ?";
			PreparedStatement state = DBUtil.getConn().prepareStatement(sql);
			state.setObject(1, obj.getNo());
			ResultSet set = state.executeQuery();
			if (set.next()) {
				Long id = set.getLong("id");
				if (id!=null||id>0) {
					return 0;
				}
			}
			//获取sql语句
			sql = "insert into t_major2(name,no,type,jianjie,layer,content,url,pNo)values"
					+ "(?,?,?,?,?,?,?,?)";
			state = DBUtil.getConn().prepareStatement(sql);
			state.setObject(1, obj.getName());
			state.setObject(2, obj.getNo());
			state.setObject(3, obj.getType());
			state.setObject(4, obj.getJianjie());
			state.setObject(5, obj.getLayer());
			state.setObject(6, obj.getContent());
			state.setObject(7, obj.getUrl());
			state.setObject(8, obj.getpNo());
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
	public void queryPage(BaseQueryPageDTO<Major> dto) {
	}
	
	public List<Major2> selectFirstMajor(int type) {
		List<Major2> list = new ArrayList<>();
		String sql="select * from t_major2 where layer=1 and type="+type;
		try {
			PreparedStatement state = DBUtil.getConn().prepareStatement(sql);
			ResultSet result = state.executeQuery();
			while(result.next()) {
				Major2 major2 = new Major2();
				major2.setName(result.getString("name"));
				major2.setNo(result.getString("no"));
				major2.setId(result.getLong("id"));
				list.add(major2);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	/**
	 * 根据父级专业代码获取儿子们
	 * @param no
	 * @return
	 */
	public List<Major2> selectChildrenByPNo(String no) {
		ArrayList<Major2> list = new ArrayList<>();
		String sql="select * from t_major2 where pNo="+no;
		PreparedStatement state;
		try {
			state = DBUtil.getConn().prepareStatement(sql);
			ResultSet result = state.executeQuery();
			while(result.next()) {
				Major2 major2 = new Major2();
				major2.setName(result.getString("name"));
				major2.setNo(result.getString("no"));
				major2.setId(result.getLong("id"));
				list.add(major2);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	public List<Major2> selectJianjieById(long id){
		ArrayList<Major2> list = new ArrayList<>();
		String sql="select * from t_major2 where id="+id;
		PreparedStatement state;
		 try {
			state = DBUtil.getConn().prepareStatement(sql);
			ResultSet result = state.executeQuery();
			while(result.next()) {
				Major2 major2 = new Major2();
				
				major2.setJianjie(result.getString("jianjie"));
				list.add(major2);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
		
	}

}