package com.yuanmaxinxi.dao.university;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.yuanmaxinxi.dao.BaseDAO;
import com.yuanmaxinxi.dto.BaseQueryPageDTO;
import com.yuanmaxinxi.entity.dictionary.Dictionary;
import com.yuanmaxinxi.entity.university.University;
import com.yuanmaxinxi.util.DBUtil;

public class UniversityDao implements BaseDAO<University>{
	private Connection conn= DBUtil.getConn();
	//插入院校信息
	@Override
	public int insert(University obj) {
		try {
			String sql="insert into t_university(pId,name,property,type,remark,record,dept,nature,f211,f985,guanwang) values (?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement state = conn.prepareStatement(sql);
			state.setObject(1, obj.getpId());
			state.setObject(2, obj.getName());
			state.setObject(3, obj.getProperty());
			state.setObject(4, obj.getType());
			state.setObject(5, obj.getRemark());
			state.setObject(6, obj.getRecord());
			state.setObject(7, obj.getDept());
			state.setObject(8, obj.getNature());
			state.setObject(9, obj.getF211());
			state.setObject(10, obj.getF985());
			state.setObject(11, obj.getGuanwang());
			int row = state.executeUpdate();
			return row;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
//	////?????
//	public int insert(Map obj) {
//		try {
////			String sql="select * from t_university";
////			PreparedStatement state = conn.prepareStatement(sql);
////			ResultSet result = state.executeQuery();
////			ResultSetMetaData data = result.getMetaData();
////			for(int i = 1; i <= data.getColumnCount(); i++) {
////				//获得列名
////				String columnName = data.getColumnName(i);
////				
////			}
//			String sql="insert into t_university(pId,name,address,quality,type,remark,ranking,teachers,record,subject) values (?,?,?,?,?,?,?,?,?,?)";
//			PreparedStatement state = conn.prepareStatement(sql);
//			state.setObject(1, obj.getpId());
//			state.setObject(2, obj.getName());
//			state.setObject(3, obj.getAddress());
//			state.setObject(4, obj.getQuality());
//			state.setObject(5, obj.getType());
//			state.setObject(6, obj.getRemark());
//			state.setObject(7, obj.getRanking());
//			state.setObject(8, obj.getTeachers());
//			state.setObject(9, obj.getRecord());
//			state.setObject(10, obj.getSubject());
//			int row = state.executeUpdate();
//			return row;
//		}catch (Exception e) {
//			e.printStackTrace();
//		}
//		return 0;
//	}
	
	//更新院校信息
	@Override
	public int update(University obj) {
		try {
			String sql="update t_university set pId = ?,name=?,property=?,type=?,remark=?,record=?,dept=?,nature=?,f211=?,f958=?,guanwang=? where id=?";
			PreparedStatement state = conn.prepareStatement(sql);
			//???是否更新院校id
			state.setObject(1, obj.getpId());
			state.setObject(2, obj.getName());
			state.setObject(3, obj.getProperty());
			state.setObject(4, obj.getType());
			state.setObject(5, obj.getRemark());
			state.setObject(6, obj.getRecord());
			state.setObject(7, obj.getDept());
			state.setObject(8, obj.getNature());
			state.setObject(9, obj.getF211());
			state.setObject(10, obj.getF985());
			state.setObject(11, obj.getGuanwang());
			state.setObject(12, obj.getId());
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
			PreparedStatement state = conn.prepareStatement(sql);
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
			PreparedStatement state = conn.prepareStatement(sql);
			state.setObject(1, id);
			ResultSet result = state.executeQuery();
			if(result.next()) {
//				uni.setId(result.getLong("id"));
//				uni.setpId(result.getLong("pId"));
//				uni.setName(result.getString("name"));
//				uni.setAddress(result.getString("address"));
//				uni.setQuality(result.getLong("quality"));
//				uni.setType(result.getLong("type"));
//				uni.setRemark(result.getString("remark"));
//				uni.setRanking(result.getInt("ranking"));
//				uni.setTeachers(result.getString("teachers"));
//				uni.setRecord(result.getLong("record"));
//				uni.setSubject(result.getString("subject"));
			}
			return uni;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public University selectOneByName(String Str) {
		try {
			String sql="select * from t_university where name = ?";
			PreparedStatement state = conn.prepareStatement(sql);
			state.setObject(1, Str);
			ResultSet result = state.executeQuery();
			if(result.next()) {
				University uni = new University();
				uni.setId(result.getLong("id"));
				return uni;
			}
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
			PreparedStatement state = conn.prepareStatement(sql);
			ResultSet result = state.executeQuery();
			//添加获取数据库的信息
			while(result.next()) {
				University uni = new University();
				uni.setId(result.getLong("id"));
				uni.setpId(result.getLong("pId"));
				uni.setName(result.getString("name"));
//				uni.setAddress(result.getString("address"));
//				uni.setQuality(result.getLong("quality"));
				uni.setType(result.getString("type"));
				uni.setRemark(result.getString("remark"));
//				uni.setRanking(result.getInt("ranking"));
//				uni.setTeachers(result.getString("teachers"));
				uni.setRecord(result.getString("record"));
//				uni.setSubject(result.getString("subject"));
				uni.setGuanwang(result.getString("guanwang"));
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
			PreparedStatement state = conn.prepareStatement(sql);
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
	public List<University> queryPage(BaseQueryPageDTO dto,int pageNum,int pageSize) {
		try {
			List<University> list = new ArrayList<>();
			String sql="select * from (select * from t_university where ranking is not null) AS T_TABLE order by ranking asc limit ?,?";
			PreparedStatement state = conn.prepareStatement(sql);
			state.setObject(1, ((pageNum-1)*10));
			System.out.println((pageNum-1)*10);
			state.setObject(2, pageSize);
			ResultSet result = state.executeQuery();
			//添加获取数据库的信息
			while(result.next()) {
<<<<<<< HEAD
				University uni = new University();//数据：名字，nature，
				uni.setName(result.getString("name"));//名字
				uni.setImgSrc(result.getString("imgsrc"));//校徽
				uni.setProperty(result.getString("property"));
				uni.setDept(result.getString("dept"));//隶属教育部
				uni.setType(result.getString("nature"));//性质，公办民办
				uni.setRanking(result.getInt("ranking"));//排名
				uni.setF211(result.getInt("f211"));//是否211
				uni.setF985(result.getInt("f985"));//是否985
				uni.setRecord(result.getString("record"));//专科，本科
				uni.setGuanwang(result.getString("guanwang"));//官网
=======
				University uni = new University();
				uni.setId(result.getLong("id"));
				uni.setpId(result.getLong("pId"));
				uni.setName(result.getString("name"));
				uni.setAddress(result.getString("address"));
//				uni.setQuality(result.getLong("quality"));
				uni.setType(result.getString("type"));
				uni.setRemark(result.getString("remark"));
				uni.setRanking(result.getInt("ranking"));
				uni.setTeachers(result.getString("teachers"));
				uni.setRecord(result.getString("record"));
				uni.setSubject(result.getString("subject"));
				uni.setGuanwang(result.getString("guanwang"));
>>>>>>> branch 'master' of https://github.com/don764372409/EnrollSystem.git
				list.add(uni);
			}
			return list;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	//通过院校水平查询所有
	public List<Dictionary> selectAllByQuality() {
		try {
			List<Dictionary> qualitylist = new ArrayList<>();
			String sql="select d.id , d.name from t_dictionarytype t , t_dictionary d where t.id = d.typeId and t.id=? ";
			PreparedStatement state = conn.prepareStatement(sql);
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
	
	//通过院校类型查询所有
	public List<Dictionary> selectAllByType() {
		try {
			List<Dictionary> typelist = new ArrayList<>();
			String sql="select d.id, d.name from t_dictionarytype t, t_dictionary d where t.id = d.typeId and t.id = ?";
			PreparedStatement state = conn.prepareStatement(sql);
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
	
	//通过院校学历查询所有信息
	public List<Dictionary> selecetAllByRecord() {
		ArrayList<Dictionary> recordlist = new ArrayList<>();
		try {
			String sql = "select d.id , d.name from t_dictionarytype t,t_dictionary d where t.id = d.typeId and t.id = ?";
			PreparedStatement state = conn.prepareStatement(sql);
			state.setObject(1, "2");
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

<<<<<<< HEAD
	@Override
	public List<University> queryPage(BaseQueryPageDTO dto) {
		// TODO Auto-generated method stub
=======
	public int updateRanking(University uni) {
		String sql = "update t_university set ranking = ? where name = ?";
		try {
			PreparedStatement state = conn.prepareStatement(sql);
			state.setObject(1, uni.getRanking());
			state.setObject(2, uni.getName());
			int update = state.executeUpdate();
			return update;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public List<University> queryPageRangking(BaseQueryPageDTO dto) {
		try {
			List<University> list = new ArrayList<>();
			String sql="select id,name,ranking from t_university order by ranking is null,ranking asc";
			PreparedStatement state = conn.prepareStatement(sql);
			ResultSet result = state.executeQuery();
			//添加获取数据库的信息
			while(result.next()) {
				
				University uni = new University();
				uni.setId(result.getLong("id"));
//				uni.setpId(result.getLong("pId"));
				uni.setName(result.getString("name"));
//				uni.setAddress(result.getString("address"));
//				uni.setQuality(result.getLong("quality"));
//				uni.setType(result.getString("type"));
//				uni.setRemark(result.getString("remark"));
				uni.setRanking(result.getInt("ranking"));
//				uni.setTeachers(result.getString("teachers"));
//				uni.setRecord(result.getString("record"));
//				uni.setSubject(result.getString("subject"));
//				uni.setGuanwang(result.getString("guanwang"));
				
				list.add(uni);
			}
			return list;
		}catch (Exception e) {
			e.printStackTrace();
		}
>>>>>>> branch 'master' of https://github.com/don764372409/EnrollSystem.git
		return null;
	}
}
