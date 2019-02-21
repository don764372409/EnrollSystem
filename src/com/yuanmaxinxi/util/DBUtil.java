package com.yuanmaxinxi.util;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
public class DBUtil {
	private static SqlSessionFactory factory;
	private static SqlSession session;
	static {
		while(factory==null) {
			try {
				factory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml"));
				Class.forName("com.mysql.jdbc.Driver");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	public static SqlSession openSession() {
		if (session==null) {
			session = factory.openSession();
		}
		return session;
	}
}