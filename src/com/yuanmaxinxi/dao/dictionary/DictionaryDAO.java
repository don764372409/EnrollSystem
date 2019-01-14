package com.yuanmaxinxi.dao.dictionary;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yuanmaxinxi.dao.BaseDAO;
import com.yuanmaxinxi.dao.dictionaryType.DictionaryTypeDAO;
import com.yuanmaxinxi.dao.sqldao.SqlDAO;
import com.yuanmaxinxi.dto.BaseQueryPageDTO;
import com.yuanmaxinxi.entity.dictionary.Dictionary;
import com.yuanmaxinxi.entity.dictionaryType.DictionaryType;
import com.yuanmaxinxi.entity.enroll.Enroll;
import com.yuanmaxinxi.entity.university.University;

public class DictionaryDAO extends SqlDAO implements BaseDAO<Dictionary> {
	private static DictionaryDAO dd;
	private DictionaryDAO() {}
	public static DictionaryDAO getDictionaryDao() {
		return dd == null ? dd = new DictionaryDAO() : dd;
	}
	
	private List<Dictionary> query(String perparedSql, Object[] param) {
		List<Dictionary> list = new ArrayList<Dictionary>();
		try {
			rs = this.execResult(perparedSql, param);
			while (rs.next()) {
				Dictionary d = new Dictionary();
				d.setId(rs.getLong("id"));
				Long typeId = rs.getLong("typeId");
				d.setTypeId(typeId);
				d.setDt(DictionaryTypeDAO.getDictionaryTypeDao().selectOneById(typeId));
				d.setName(rs.getString("name"));
				list.add(d);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	@Override
	public int insert(Dictionary obj) {
		return exceuteUpdate("INSERT INTO t_dictionary(typeId,name) VALUES(?,?)", new Object[] {
				obj.getTypeId(), obj.getName()
		});
	}

	@Override
	public int update(Dictionary obj) {
		return exceuteUpdate("UPDATE t_dictionary SET typeId=?,name=? WHERE id=?", new Object[] {
				obj.getTypeId(), obj.getName(), obj.getId()
		});
	}

	@Override
	public int delete(Long id) {
		return exceuteUpdate("DELETE FROM t_dictionary WHERE id=?",new Object[] {id});
	}

	@Override
	public Dictionary selectOneById(Long id) {
		List<Dictionary> query = query("SELECT * FROM t_dictionary WHERE id=?", new Object[] {id});
		return query.isEmpty()?null:query.get(0);
	}
	
	public Dictionary selectOneByName(String name) {
		List<Dictionary> query = query("SELECT * FROM t_dictionary WHERE name=?", new Object[] {name});
		return query.isEmpty()?null:query.get(0);
	}

	@Override
	public List<Dictionary> selectAll() {
		return query("SELECT * FROM t_dictionary", null); 
	}

	@Override
	public List<Dictionary> queryPage(BaseQueryPageDTO dto) {
		return null;
	}
	@Override
	public List<University> queryPage(BaseQueryPageDTO dto, int pageNum, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

}
