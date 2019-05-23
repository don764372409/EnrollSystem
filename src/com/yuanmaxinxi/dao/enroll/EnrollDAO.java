package com.yuanmaxinxi.dao.enroll;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.yuanmaxinxi.dao.BaseDAO;
import com.yuanmaxinxi.dto.enroll.EnrollQueryPageDTO;
import com.yuanmaxinxi.entity.enroll.Enroll;
import com.yuanmaxinxi.entity.major.Major;
import com.yuanmaxinxi.entity.province.Province;
import com.yuanmaxinxi.entity.provincescore.Provincescore;
import com.yuanmaxinxi.entity.university.University;
@Repository
@Mapper
public interface EnrollDAO extends BaseDAO<Enroll>{
	
//	private List<Enroll> query(String perparedSql, Object[] param) {
//		List<Enroll> list = new ArrayList<Enroll>();
//		
//		try {
//			rs = this.execResult(perparedSql, param);
//			while (rs.next()) {
//				Enroll e = new Enroll();
//				e.setId(rs.getLong("id"));
//				Long uId=rs.getLong("uId");
//				e.setuId(uId);
//				e.setUniversity(new UniversityDao().selectOneById(uId));
//				Long pId=rs.getLong("pId");
//				e.setpId(pId);
//				e.setProvince(new ProvinceDao().selectOneById(pId));
//				Long mId=rs.getLong("mId");
//				e.setmId(mId);
//				e.setMajor(new MajorDAO().selectOneById(mId));
//				Long bId=rs.getLong("bId");
//				e.setbId(bId);
//				e.setBatch(BatchDAO.getBatchDao().selectOneById(bId));
//				e.setNumber(rs.getInt("number"));
//				e.setMaxNumber(rs.getInt("maxNumber"));
//				e.setMinNumber(rs.getInt("minNumber"));
//				e.setMaxRanking(rs.getInt("maxRanking"));
//				e.setMinRanking(rs.getInt("minRanking"));
//				e.setAvgRanking(rs.getInt("avgRanking"));
//				e.setTime(rs.getInt("time"));
//				e.setTuition(rs.getBigDecimal("tuition"));
//				e.setStudyYear(rs.getBigDecimal("studyYear"));
//				list.add(e);
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
//	public int insert(Enroll obj) {
//		return exceuteUpdate("INSERT INTO t_enroll(uId,pId,mId,bId,number,maxNumber,"
//				+ "minNumber,avgNumber,maxRanking,minRanking,avgRanking,time,tuition,studyYear)"
//				+ " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)", new Object[] {
//						obj.getuId(),obj.getpId(),obj.getmId(),obj.getbId(),obj.getNumber(),obj.getMaxNumber(),
//						obj.getMinNumber(),obj.getAvgNumber(),obj.getMaxRanking(),obj.getMinRanking(),
//						obj.getAvgRanking(),obj.getTime(),obj.getTuition(),obj.getStudyYear()
//		});
//	}

//	@Override
//	public int update(Enroll obj) {
//		return exceuteUpdate("UPDATE t_enroll SET uId=?,pId=?,mId=?,bId=?,number=?,maxNumber=?,"
//				+ "minNumber=?,avgNumber=?,maxRanking=?,minRanking=?,avgRanking=?,time=?,tuition=?,"
//				+ "studyYear=? WHERE id=?", new Object[] {
//						obj.getuId(),obj.getpId(),obj.getmId(),obj.getbId(),obj.getNumber(),obj.getMaxNumber(),
//						obj.getMinNumber(),obj.getAvgNumber(),obj.getMaxRanking(),obj.getMinRanking(),
//						obj.getAvgRanking(),obj.getTime(),obj.getTuition(),obj.getStudyYear(),obj.getId()
//						
//				});
//	}
//
//	@Override
//	public int delete(Long id) {
//		return 0;
//	}
//
//	@Override
//	public Enroll selectOneById(Long id) {
//		List<Enroll> query = query("SELECT * FROM t_enroll WHERE id=?", new Object[]{id});
//		return query==null?null:query.get(0);
//	}
//
	public List<Enroll> selectAll();
//	@Override
//	public void queryPage(BaseQueryPageDTO<Enroll> dto) {
//	}

	public List<Province> selectEnrollProvince();

	public List<Enroll> serch(Map<String, Object> map);

	public List<Enroll> enrollBigUni(Map<String, Object> map);
	public Provincescore queryProvinceScore(Provincescore ps);
	public  List<Enroll> queryEnrollByRankANDMajor(EnrollQueryPageDTO page);
	public List<Enroll> queryEnrollByRankUni();
	public void xxx(String content);

	public List<Enroll> selectAllByMajorName(String majorName);
	/**
	 * 根据招生地区和学校id查询录取数据里面的专业
	 * @param map
	 * @return
	 */
	public List<Major> queryMajorByuId(Map<String,Long> map);
	public List<University> queryUniversity(String name);

	public List<Province> selectEnrollProvinceByUniId(Long uniId);
}
