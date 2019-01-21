package com.yuanmaxinxi.dao.university;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yuanmaxinxi.dao.BaseDAO;
import com.yuanmaxinxi.dao.province.ProvinceDao;
import com.yuanmaxinxi.dto.BaseQueryPageDTO;
import com.yuanmaxinxi.entity.dictionary.Dictionary;
import com.yuanmaxinxi.entity.major.Major2;
import com.yuanmaxinxi.entity.province.Province;
import com.yuanmaxinxi.entity.university.University;
import com.yuanmaxinxi.entity.university.jianzhang.Jianzhang;
import com.yuanmaxinxi.util.DBUtil;
import com.yuanmaxinxi.util.StringUtil;

public class UniversityDao implements BaseDAO<University>{
	private Connection conn= DBUtil.getConn();
	private ProvinceDao provinceDao = new ProvinceDao();
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
			String sql="select * from t_university where id = ?";
			PreparedStatement state = conn.prepareStatement(sql);
			state.setObject(1, id);
			ResultSet result = state.executeQuery();
			if(result.next()) {
				University uni = new University();//数据：名字，nature，
				uni.setName(result.getString("name"));//名字
				uni.setImgSrc(result.getString("imgsrc"));//校徽
				uni.setProperty(result.getString("property"));//学校类型  理工类  综合类 师范类 农业类
				uni.setDept(result.getString("dept"));//隶属教育部
				uni.setType(result.getString("nature"));//性质，公办民办
				uni.setRanking(result.getInt("ranking"));//排名
				uni.setF211(result.getInt("f211"));//是否211
				uni.setF985(result.getInt("f985"));//是否985
				uni.setRecord(result.getString("record"));//专科，本科
				uni.setGuanwang(result.getString("guanwang"));//官网
				uni.setRemark(result.getString("remark"));//学校简介
				uni.setTeachers(result.getString("teachers"));//学校师资
				return uni;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	//通过id查询院校单条信息t_university
	public List selectOneByuId(long id) {
		try {
			String sql="select * from t_jianzhang where uId = ?";
			PreparedStatement state = conn.prepareStatement(sql);
			state.setObject(1, id);
			ResultSet result = state.executeQuery();
			List<Jianzhang> list = new ArrayList<>();
			while(result.next()) {
				Jianzhang jianzhang = new Jianzhang();
				jianzhang.setZhangshengName(result.getString("jianzhang"));//数据：名字，nature，
				jianzhang.setText(result.getString("jianzhangtext"));//数据：名字，nature，
				list.add(jianzhang);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public List selectOneByName1(String Str) {
		try {
			String sql="select * from t_university where instr(name,?)";
			PreparedStatement state = conn.prepareStatement(sql);
			state.setObject(1, Str);
			ResultSet result = state.executeQuery();
			List<University> list = new ArrayList<>();
			while(result.next()) {
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
				list.add(uni);
			}
			return list;
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
			List<University> list = new ArrayList<>();
			while(result.next()) {
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
				uni.setType(result.getString("type"));
				uni.setRemark(result.getString("remark"));
				uni.setRecord(result.getString("record"));
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
	public void queryPage(BaseQueryPageDTO<University> dto) {
		try {
			//先查询总记录数
			PreparedStatement state = DBUtil.getConn().prepareStatement(dto.getCountSql());
			for (int i = 0; i < dto.getParams().size(); i++) {
				state.setObject(i+1, dto.getParams().get(i));
			}
			ResultSet query = state.executeQuery();
			if (query.next()) {
				dto.setCount(query.getInt("count"));
			}
			
			//再去高级查询加分页
			//设置排序SQL
			dto.setOrderBySql(" order by ranking is null,ranking ");
			state = DBUtil.getConn().prepareStatement(dto.getSql());
			//设置高级查询参数   select * from t_university where instr(name,?) and xx = ? limit ?,?
			for (int i = 0; i < dto.getParams().size(); i++) {
				state.setObject(i+1, dto.getParams().get(i));
			}
			state.setObject(dto.getParams().size()+1, (dto.getCurrentPage()-1)*dto.getPageSize());
			state.setObject(dto.getParams().size()+2, dto.getPageSize());
			ResultSet result = state.executeQuery();
			//装结果集的集合
			List<University> list = new ArrayList<>();
			//添加获取数据库的信息
			while(result.next()) {
				University uni = new University();//数据：名字，nature，
				uni.setId(result.getLong("id"));
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
				
				list.add(uni);
			}
			dto.setRows(list);
		}catch (Exception e) {
			e.printStackTrace();
		}
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
		return null;
	}
	/**
	 * 根据学校ID查询学校的录取专业
	 * @param id
	 * @param activBatch 
	 * @return
	 */
	public List<Major2> selectMajorsById(String id, String activBatch) {
		List<Major2> list = new ArrayList<>();
		try {
			//默认提前批
			String bId1 = "6" ;
			String bId2 = "7" ;
			switch (activBatch) {
			case "0":
				bId1 = "6" ;
				bId2 = "7" ;
				break;
			case "1":
				bId1 = "10" ;
				bId2 = "11" ;
				break;
			case "2":
				bId1 = "8" ;
				bId2 = "9" ;
				break;
			case "3":
				bId1 = "14" ;
				bId2 = "15" ;
				break;
			case "4":
			case "5":
				bId1 = "12" ;
				bId2 = "13" ;
				break;

			default:
				break;
			}
			PreparedStatement state = DBUtil.getConn().prepareStatement("select id ,name from t_major where id in ("
					+ "select mId from t_enroll where uId = ? and bId = ? union "
					+ "select mId from t_enroll where uId = ? and bId = ?"
					+ ")");
			state.setObject(1, id);
			state.setObject(2, bId1);
			state.setObject(3, id);
			state.setObject(4, bId2);
			ResultSet query = state.executeQuery();
			while(query.next()) {
				Major2 mj = new Major2();
				mj.setId(query.getLong("id"));
				mj.setName(query.getString("name"));
				list.add(mj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	/**
	 * 根据学校ID获取录取数据 指定专业最新的五个年份
	 * @param id
	 * @return
	 */
	public List<Map<String,Object>> selectYearByMajorAndBidAndId(String id,String activBatch,String mId) {
		List<Map<String,Object>> list = new ArrayList<>();
		try {
			//默认提前批
			String bId1 = "6" ;
			String bId2 = "7" ;
			switch (activBatch) {
			case "0":
				bId1 = "6" ;
				bId2 = "7" ;
				break;
			case "1":
				bId1 = "10" ;
				bId2 = "11" ;
				break;
			case "2":
				bId1 = "8" ;
				bId2 = "9" ;
				break;
			case "3":
				bId1 = "14" ;
				bId2 = "15" ;
				break;
			case "4":
			case "5":
				bId1 = "12" ;
				bId2 = "13" ;
				break;

			default:
				break;
			}
			PreparedStatement state = DBUtil.getConn().prepareStatement("select DISTINCT * from t_enroll where uId = ? and (bId = ? or bId=?) and mId = ?");
			System.err.println("学校ID："+id+",专业ID："+mId+",批次ID:"+bId1+","+bId2);
			state.setObject(1, id);
			state.setObject(2, bId1);
			state.setObject(3, bId2);
			state.setObject(4, mId);
			ResultSet query = state.executeQuery();
			while(query.next()) {
				Map<String,Object> map = new HashMap<>();
				map.put("year", query.getString("time"));
				System.err.println(map);
				map.put("number", query.getInt("number"));
				map.put("maxNumber", query.getInt("maxNumber"));
				map.put("minNumber", query.getInt("minNumber"));
				map.put("avgNumber", query.getInt("avgNumber"));
				map.put("maxRanking", query.getInt("maxRanking"));
				map.put("minRanking", query.getInt("minRanking"));
				map.put("avgRanking", query.getInt("avgRanking"));
				list.add(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public University selectOneShoucang(String uId, String id) {
		try {
			PreparedStatement state = DBUtil.getConn().prepareStatement("select * from t_university where id = (select uniId from t_shoucang where uId = ? and uniId = ?)");
			state.setObject(1, uId);
			state.setObject(2,id);
			ResultSet result = state.executeQuery();
			return getOne(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 添加收藏
	 * @param uId
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public int addShoucang(String uId, String id)throws SQLException {
		PreparedStatement state = DBUtil.getConn().prepareStatement("insert into t_shoucang(uId,uniId)values(?,?)");
		state.setObject(1, uId);
		state.setObject(2, id);
		return state.executeUpdate();
	}

	public int unShoucang(String uId, String id)throws SQLException {
		PreparedStatement state = DBUtil.getConn().prepareStatement("delete from t_shoucang where uId = ? and uniId = ?");
		state.setObject(1, uId);
		state.setObject(2, id);
		return state.executeUpdate();
	}

	public List<University> selectShoucangUnis(String uId) {
		List<University> list = new ArrayList<>();
		try {
			PreparedStatement state = DBUtil.getConn().prepareStatement("select * from t_university where id in (select uniId from t_shoucang where uId = ?)");
			state.setObject(1, uId);
			ResultSet result = state.executeQuery();
			getAll(result, list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public List<University> getSelectUnis(String[] ids) {
		List<University> list = new ArrayList<>();
		try {
			if (ids.length>0) {
				String[] idArrs = ids[0].split(",");
				String idStrs = "";
				for (int i = 0; i < idArrs.length; i++) {
					if (i==idArrs.length-1) {
						idStrs += "?";
					}else {
						idStrs += "?,";
					}
				}
				PreparedStatement state = DBUtil.getConn().prepareStatement("select * from t_university where id in ("+idStrs+")");
				for (int i = 0; i < idArrs.length; i++) {
					state.setObject(i+1, idArrs[i]);
				}
				ResultSet result = state.executeQuery();
				getAll(result, list);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public List<University> selectUnisByIds(String ids) {
		List<University> list = new ArrayList<>();
		try {
			if (StringUtil.isNotNullAndEmpty(ids)) {
				String[] uIds = ids.split(",");
				String wenhao = "";
				for (int i = 0; i < uIds.length; i++) {
					if (i==uIds.length-1) {
						wenhao += "?";
					}else {
						wenhao += "?,";
					}
				}
				PreparedStatement state = DBUtil.getConn().prepareStatement("select * from t_university where id in ("+wenhao+")");
				for (int i = 0; i < uIds.length; i++) {
					state.setObject(i+1, uIds[i]);
				}
				ResultSet query = state.executeQuery();
				getAll(query, list);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	private void getAll(ResultSet result,List<University> list){
		try {
			Map<Long,Province> cash = new HashMap<>();
			while(result.next()) {
				University uni = new University();//数据：名字，nature，
				uni.setId(result.getLong("id"));
				uni.setName(result.getString("name"));//名字
				uni.setImgSrc(result.getString("imgsrc"));//校徽
				uni.setProperty(result.getString("property"));
				uni.setDept(result.getString("dept"));//隶属教育部
				uni.setNature(result.getString("nature"));//性质，公办民办
				uni.setType(result.getString("type"));
				uni.setRanking(result.getInt("ranking"));//排名
				uni.setF211(result.getInt("f211"));//是否211
				uni.setF985(result.getInt("f985"));//是否985
				uni.setRecord(result.getString("record"));//专科，本科
				Long pId = result.getLong("pId");
				Province pro = cash.get(pId);
				if (pro==null) {
					pro = provinceDao.selectOneById(pId);
					cash.put(pId, pro);
				}
				uni.setPro(pro);
				list.add(uni);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 处理结果集
	 * @param result
	 * @return
	 */
	private University getOne(ResultSet result){
		try {
			if(result.next()) {
				University uni = new University();//数据：名字，nature，
				uni.setId(result.getLong("id"));
				uni.setName(result.getString("name"));//名字
				uni.setImgSrc(result.getString("imgsrc"));//校徽
				uni.setProperty(result.getString("property"));
				uni.setDept(result.getString("dept"));//隶属教育部
				uni.setNature(result.getString("nature"));//性质，公办民办
				uni.setType(result.getString("type"));
				uni.setRanking(result.getInt("ranking"));//排名
				uni.setF211(result.getInt("f211"));//是否211
				uni.setF985(result.getInt("f985"));//是否985
				uni.setRecord(result.getString("record"));//专科，本科
				return uni;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
