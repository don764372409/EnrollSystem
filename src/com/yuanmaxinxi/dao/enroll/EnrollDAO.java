package com.yuanmaxinxi.dao.enroll;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yuanmaxinxi.dao.BaseDAO;
import com.yuanmaxinxi.dao.major.MajorDAO;
import com.yuanmaxinxi.dao.sqldao.SqlDAO;
import com.yuanmaxinxi.dao.university.UniversityDao;
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
		UniversityDao ud = new UniversityDao();
		MajorDAO md = new MajorDAO();
		try {
			rs = this.execResult(perparedSql, param);
			while (rs.next()) {
				Enroll e = new Enroll();
				e.setId(rs.getLong("id"));
				Long uId=rs.getLong("uId");
				e.setuId(uId);
				e.setUniversity(ud.selectOneById(uId));
				Long mId=rs.getLong("mId");
				e.setmId(mId);
				e.setMajor(md.selectOneById(mId));
				e.setBatch(rs.getString("batch"));
				e.setNumber(rs.getInt("number"));
				e.setMaxNumber(rs.getInt("maxNumber"));
				e.setMinNumber(rs.getInt("minNumber"));
				e.setMaxRanking(rs.getInt("maxRanking"));
				e.setMinRanking(rs.getInt("minRanking"));
				e.setAvgRanking(rs.getInt("avgRanking"));
				e.setTime(null);
				e.setTuition(rs.getBigDecimal("tuition"));
				e.setStudyYear(rs.getBigDecimal("studyYear"));
				list.add(e);
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
		return exceuteUpdate("INSERT INTO t_enroll(uId,mId,batch,number,maxNumber,"
				+ "minNumber,avgNumber,maxRanking,minRanking,avgRanking,time,tuition,studyYear)"
				+ " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)", new Object[] {
						obj.getuId(),obj.getmId(),obj.getBatch(),obj.getNumber(),obj.getMaxNumber(),
						obj.getMinNumber(),obj.getAvgNumber(),obj.getMaxRanking(),obj.getMinRanking(),
						obj.getAvgRanking(),obj.getTime(),obj.getTuition(),obj.getStudyYear()
		});
	}

	@Override
	public int update(Enroll obj) {
		return exceuteUpdate("UPDATE t_enroll SET uId=?,mId=?,batch=?number=?,maxNumber=?,"
				+ "minNumber=?,avgNumber=?,maxRanking=?,minRanking=?,avgRanking=?,time=?,tuition=?,"
				+ "studyYear=? WHERE id=?", new Object[] {
						obj.getuId(),obj.getmId(),obj.getBatch(),obj.getNumber(),obj.getMaxNumber(),
						obj.getMinNumber(),obj.getAvgNumber(),obj.getMaxRanking(),obj.getMinRanking(),
						obj.getAvgRanking(),obj.getTime(),obj.getTuition(),obj.getStudyYear(),obj.getId()
						
				});
	}

	@Override
	public int delete(Long id) {
		return 0;
	}

	@Override
	public Enroll selectOneById(Long id) {
		List<Enroll> query = query("SELECT * FROM t_enroll WHERE id=?", new Object[]{id});
		return query==null?null:query.get(0);
	}

	@Override
	public List<Enroll> selectAll() {
		return this.query("SELECT * FROM t_enroll", null);
	}

	@Override
	public void queryPage(BaseQueryPageDTO<Enroll> dto) {
	}
}
