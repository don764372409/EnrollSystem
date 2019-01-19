package com.yuanmaxinxi.dao.batch;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yuanmaxinxi.dao.BaseDAO;
import com.yuanmaxinxi.dao.sqldao.SqlDAO;
import com.yuanmaxinxi.dto.BaseQueryPageDTO;
import com.yuanmaxinxi.entity.batch.Batch;
import com.yuanmaxinxi.entity.university.University;

public class BatchDAO extends SqlDAO implements BaseDAO<Batch> {
	private static BatchDAO dtd;
	private BatchDAO() {}
	public static BatchDAO getBatchDao() {
		return dtd == null ? dtd = new BatchDAO() : dtd;
	}
	
	private List<Batch> query(String perparedSql, Object[] param) {
		List<Batch> list = new ArrayList<Batch>();
		try {
			rs = this.execResult(perparedSql, param);
			while (rs.next()) {
				Batch dt = new Batch();
				dt.setId(rs.getLong("id"));
				dt.setName(rs.getString("name"));
				list.add(dt);
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
	public int insert(Batch obj) {
		return exceuteUpdate("INSERT INTO t_batch(name) VALUES(?)", new Object[] {obj.getName()});
	}

	@Override
	public int update(Batch obj) {
		return exceuteUpdate("UPDATE t_batch SET name=? WHERE id=?", new Object[] {
				obj.getName(), obj.getId()
		});
	}

	@Override
	public int delete(Long id) {
		return exceuteUpdate("DELETE FROM t_batch WHERE id=?",new Object[] {id});
	}

	@Override
	public Batch selectOneById(Long id) {
		List<Batch> query = query("SELECT * FROM t_batch WHERE id=?", new Object[] {id});
		return query.isEmpty()?null:query.get(0);
	}
	
	public Batch selectOneByName(String name) {
		List<Batch> query = query("SELECT * FROM t_batch WHERE name=?", new Object[] {name});
		return query.isEmpty()?null:query.get(0);
	}

	@Override
	public List<Batch> selectAll() {
		return query("SELECT * FROM t_batch", null); 
	}
	@Override
	public void queryPage(BaseQueryPageDTO<Batch> dto) {
		// TODO Auto-generated method stub
		
	}


}