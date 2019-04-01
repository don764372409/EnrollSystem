package com.yuanmaxinxi.dao.dictionaryType;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yuanmaxinxi.dao.BaseDAO;
import com.yuanmaxinxi.dao.sqldao.SqlDAO;
import com.yuanmaxinxi.dto.BaseQueryPageDTO;
import com.yuanmaxinxi.entity.dictionaryType.DictionaryType;

public interface DictionaryTypeDAO extends BaseDAO<DictionaryType> {
	
	public List<DictionaryType> selectAll();
	public int insert(DictionaryType obj);
	public int update(DictionaryType obj);
	public DictionaryType selectOneById(int id);
	public int delete(int id);
//	private static DictionaryTypeDAO dtd;
//	private DictionaryTypeDAO() {}
//	public static DictionaryTypeDAO getDictionaryTypeDao() {
//		return dtd == null ? dtd = new DictionaryTypeDAO() : dtd;
//	}
//	
//	private List<DictionaryType> query(String perparedSql, Object[] param) {
//		List<DictionaryType> list = new ArrayList<DictionaryType>();
//		try {
//			rs = this.execResult(perparedSql, param);
//			while (rs.next()) {
//				DictionaryType dt = new DictionaryType();
//				dt.setId(rs.getInt("id"));
//				dt.setName(rs.getString("name"));
//				list.add(dt);
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				if (rs != null) {
//					rs.close();
//				}
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//		return list;
//	}
//	@Override
//	public int insert(DictionaryType obj) {
//		return exceuteUpdate("INSERT INTO t_dictionarytype(name) VALUES(?)", new Object[] {obj.getName()});
//	}
//
//	@Override
//	public int update(DictionaryType obj) {
//		return exceuteUpdate("UPDATE t_dictionarytype SET name=? WHERE id=?", new Object[] {
//				obj.getName(), obj.getId()
//		});
//	}
//
//	@Override
//	public int delete(Long id) {
//		return exceuteUpdate("DELETE FROM t_dictionarytype WHERE id=?",new Object[] {id});
//	}
//
//	@Override
//	public DictionaryType selectOneById(Long id) {
//		List<DictionaryType> query = query("SELECT * FROM t_dictionarytype WHERE id=?", new Object[] {id});
//		return query.isEmpty()?null:query.get(0);
//	}
//	
//	public DictionaryType selectOneByName(String name) {
//		List<DictionaryType> query = query("SELECT * FROM t_dictionarytype WHERE name=?", new Object[] {name});
//		return query.isEmpty()?null:query.get(0);
//	}
//
//	@Override
//	public List<DictionaryType> selectAll() {
//		return query("SELECT * FROM t_dictionarytype", null); 
//	}
//	@Override
//	public void queryPage(BaseQueryPageDTO<DictionaryType> dto) {
//	}
//		

}
