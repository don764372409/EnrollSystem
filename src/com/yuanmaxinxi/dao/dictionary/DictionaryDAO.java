package com.yuanmaxinxi.dao.dictionary;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yuanmaxinxi.dao.BaseDAO;
import com.yuanmaxinxi.dao.sqldao.SqlDAO;
import com.yuanmaxinxi.dto.BaseQueryPageDTO;
import com.yuanmaxinxi.entity.dictionary.Dictionary;
import com.yuanmaxinxi.entity.enroll.Enroll;

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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Dictionary obj) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Long id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Dictionary selectOneById(Long id) {
		return query("SELECT * FROM t_dicitonary WHERE id=?", new Object[] {id}).get(0); 
	}

	@Override
	public List<Dictionary> selectAll() {
		return query("SELECT * FROM t_dicitonary", null); 
	}

	@Override
	public List<Dictionary> queryPage(BaseQueryPageDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

}
