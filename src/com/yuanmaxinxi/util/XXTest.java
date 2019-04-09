package com.yuanmaxinxi.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.yuanmaxinxi.dao.enroll.EnrollDAO;
import com.yuanmaxinxi.dao.major.MajorDAO;
import com.yuanmaxinxi.entity.enroll.Enroll;
import com.yuanmaxinxi.entity.major.Major;

public class XXTest {
	public static void main(String[] args) throws IOException {
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis/mybatis-config.xml"));
		SqlSession session = factory.openSession();
		List<String> names = new ArrayList<>();
		names.add("哲学类");
		names.add("经济学类");
		names.add("金融学类");
		names.add("经济与贸易类");
		names.add("法学类");
		names.add("政治学类");
		names.add("药学类");
		try {
			MajorDAO majorDAO = session.getMapper(MajorDAO.class);
			EnrollDAO enrollDAO = session.getMapper(EnrollDAO.class);
			//1845 711
			//查询所有专业
			List<Major> majors = majorDAO.selectAll();
			for (Major major : majors) {
				List<Enroll> list = enrollDAO.selectAllByMajorName(major.getName());
				if (names.contains(major.getName())) {
					continue;
				}
				for (Enroll enroll : list) {
					if (enroll.getmId()<=1845) {
						continue;
					}
					Major newMajor;
					if (enroll.getbId()==12||enroll.getbId()==13) {
						//select id from t_major where name = '电梯工程技术' and id>=711 and id<=1845 and layer = 3
						//查专科
						newMajor = majorDAO.selectOneByNameXXX(major.getName());
					}else {
						//select id from t_major where name = '电梯工程技术' and id<711 and layer = 3
						//查本科
						newMajor = majorDAO.selectOneByNameYYY(major.getName());
					}
					//如果上面都没查到
					/**
					 * select min(mId) as id from t_enroll where mId in(
							select id from t_major where name = '电梯工程技术'
						)
					 */
					if (newMajor==null) {
						newMajor = majorDAO.selectOneByNameZZZ(major.getName());
					}
					if (newMajor==null) {
						System.err.println(major.getName());
						System.err.println(enroll.getbId());
					}
					System.err.println(newMajor);
					//开始修改
					enroll.setmId(newMajor.getId());
					enrollDAO.update(enroll);
					
					/**
					 *select id from t_major where id in(
						select id from t_major where name = '电梯工程技术'
					) and id > 5099 and id >1845
					 */
					Major major2 = new Major();
					major2.setName(major.getName());
					major2.setId(newMajor.getId());
					List<Major> newList = majorDAO.selectAllCCC(major2);
					//开始删
					for (Major major3 : newList) {
						majorDAO.delete(major3.getId());
					}
					session.commit();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
