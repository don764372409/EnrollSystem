package com.yuanmaxinxi.dao.enroll;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yuanmaxinxi.dao.BaseDAO;
import com.yuanmaxinxi.dao.sqldao.SqlDAO;
import com.yuanmaxinxi.dto.BaseQueryPageDTO;
import com.yuanmaxinxi.entity.enroll.Enroll;


public class EnrollDAO extends SqlDAO implements BaseDAO<Enroll>{
	
	private static EnrollDAO ed;
	private EnrollDAO() {}
	public static EnrollDAO getDictionaryDao() {
		return ed == null ? ed = new EnrollDAO() : ed;
	}
	private List<Enroll> query(String perparedSql, Object[] param) {
		List<Enroll> list = new ArrayList<Enroll>();
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
	public int insert(Enroll obj) {
		return 0;
	}

	@Override
	public int update(Enroll obj) {
		return 0;
	}

	@Override
	public int delete(Long id) {
		return 0;
	}

	@Override
	public Enroll selectOneById(Long id) {
		return query("SELECT * FROM t_enroll WHERE id=?", new Object[]{id}).get(0);
	}

	@Override
	public List<Enroll> selectAll() {
		return this.query("SELECT * FROM t_enroll", null);
	}

	@Override
	public List<Enroll> queryPage(BaseQueryPageDTO dto) {
		return null;
	}

}
