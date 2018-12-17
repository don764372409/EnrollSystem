package com.yuanmaxinxi.dao.university;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.yuanmaxinxi.dao.BaseDAO;
import com.yuanmaxinxi.dto.BaseQueryPageDTO;
import com.yuanmaxinxi.entity.dictionary.Dictionary;
import com.yuanmaxinxi.entity.university.University;
import com.yuanmaxinxi.util.DBUtil;

public class UniversityDao implements BaseDAO<University>{
	//插入院校信息
	@Override
	public int insert(University obj) {
		try {
			String sql="insert into t_university(id,pId,name,address,quality,type,remake,ranking,teachers,record,subject) values (?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement state = DBUtil.getConn().prepareStatement(sql);
			state.setObject(1, obj.getId());
			state.setObject(2, obj.getpId());
			state.setObject(3, obj.getName());
			state.setObject(3, obj.getAddress());
			state.setObject(4, obj.getQuality());
			state.setObject(5, obj.getType());
			state.setObject(6, obj.getRemark());
			state.setObject(7, obj.getRanking());
			state.setObject(8, obj.getTeachers());
			state.setObject(9, obj.getRecord());
			state.setObject(10, obj.getSubject());
			int row = state.executeUpdate();
			return row;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	//更新院校信息
	@Override
	public int update(University obj) {
		try {
			String sql="update t_university set id=?,pId = ?,name=?,address=?,quality=?,type=?,remake=?,ranking=?,teachers=?,record=?,subject=? where id= ?";
			PreparedStatement state = DBUtil.getConn().prepareStatement(sql);
			//???是否更新院校id
			state.setObject(1, obj.getId());
			state.setObject(2, obj.getpId());
			state.setObject(3, obj.getName());
			state.setObject(3, obj.getAddress());
			state.setObject(4, obj.getQuality());
			state.setObject(5, obj.getType());
			state.setObject(6, obj.getRemark());
			state.setObject(7, obj.getRanking());
			state.setObject(8, obj.getTeachers());
			state.setObject(9, obj.getRecord());
			state.setObject(10, obj.getSubject());
			state.setObject(11, obj.getId());
			int row = state.executeUpdate();
			return row;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int delete(Long id) {
		try {
			String sql="delete from t_university where id=?";
			PreparedStatement state = DBUtil.getConn().prepareStatement(sql);
			state.setObject(1, id);
			int row = state.executeUpdate();
			return row;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	//通过id查询院校单条信息
	@Override
	public University selectOneById(Long id) {
		try {
			University uni = new University();
			String sql="select * from t_university where id = ?";
			PreparedStatement state = DBUtil.getConn().prepareStatement(sql);
			state.setObject(1, id);
			ResultSet result = state.executeQuery();
			if(result.next()) {
				uni.setId(result.getLong("id"));
				uni.setpId(result.getLong("pId"));
				uni.setName(result.getString("name"));
				uni.setAddress(result.getString("address"));
				uni.setQuality(result.getLong("quality"));
				uni.setType(result.getLong("type"));
				uni.setRemark(result.getString("remark"));
				uni.setRanking(result.getInt("ranking"));
				uni.setTeachers(result.getString("teachers"));
				uni.setRecord(result.getLong("result"));
				uni.setSubject(result.getString("subject"));
			}
			return uni;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	//查询全部院校信息
	@Override
	public  List<University> selectAll() {
		try {
			List<University> list = new ArrayList<>();
			String sql="select * from t_university";
			PreparedStatement state = DBUtil.getConn().prepareStatement(sql);
			ResultSet result = state.executeQuery();
			//添加获取数据库的信息
			while(result.next()) {
				University uni = new University();
				uni.setId(result.getLong("id"));
				uni.setpId(result.getLong("pId"));
				uni.setName(result.getString("name"));
				uni.setAddress(result.getString("address"));
				uni.setQuality(result.getLong("quality"));
				uni.setType(result.getLong("type"));
				uni.setRemark(result.getString("remark"));
				uni.setRanking(result.getInt("ranking"));
				uni.setTeachers(result.getString("teachers"));
				uni.setRecord(result.getLong("record"));
				uni.setSubject(result.getString("subject"));
				list.add(uni);
			}
			return list;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	//查询所有省份
	public  List<University> selectAllByProvince() {
		try {
			List<University> provincelist = new ArrayList<>();
			String sql="select * from t_province";
			PreparedStatement state = DBUtil.getConn().prepareStatement(sql);
			ResultSet result = state.executeQuery();
			//添加获取数据库的信息
			while(result.next()) {
				University uni = new University();
				uni.setId(result.getLong("id"));
				uni.setName(result.getString("name"));
				provincelist.add(uni);
			}
			return provincelist;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public List<University> queryPage(BaseQueryPageDTO dto) {
		return null;
	}

	//查询所有的院校水平
	public List<Dictionary> selectAllByQuality() {
		try {
			List<Dictionary> qualitylist = new ArrayList<>();
			String sql="select d.id , d.name from t_dictionarytype t , t_dictionary d where t.id = d.typeId and t.id=? ";
			PreparedStatement state = DBUtil.getConn().prepareStatement(sql);
			//？？？假设1记录的院校水平
			state.setObject(1, "1");
			ResultSet result = state.executeQuery();
			//添加获取数据库的信息
			while(result.next()) {
				Dictionary uni = new Dictionary();
				uni.setId((int)result.getLong("id"));
				uni.setName(result.getString("name"));
				qualitylist.add(uni);
			}
			return qualitylist;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//查询院校类型
	public List<Dictionary> selectAllByType() {
		try {
			List<Dictionary> typelist = new ArrayList<>();
			String sql="select d.id, d.name from t_dictionarytype t, t_dictionary d where t.id = d.typeId and t.id = ?";
			PreparedStatement state = DBUtil.getConn().prepareStatement(sql);
			//假设2记录的院校类型
			state.setObject(1, "2");
			ResultSet result = state.executeQuery();
			//添加获取数据库的信息
			while(result.next()) {
				Dictionary uni = new Dictionary();
				uni.setId((int)result.getLong("id"));
				uni.setName(result.getString("name"));
				typelist.add(uni);
			}
			return typelist;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Dictionary> selecetAllByRecord() {
		ArrayList<Dictionary> recordlist = new ArrayList<>();
		try {
			String sql = "select d.id , d.name from t_dictionarytype t,t_dictionary d where t.id = d.typeId and t.id = ?";
			PreparedStatement state = DBUtil.getConn().prepareStatement(sql);
			state.setObject(1, "3");
			ResultSet result = state.executeQuery();
			while(result.next()) {
				Dictionary uni = new Dictionary();
				uni.setId((int)result.getLong("id"));
				uni.setName(result.getString("name"));
				recordlist.add(uni);
			}
			return recordlist;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
