package com.yuanmaxinxi.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.alibaba.druid.sql.visitor.functions.Insert;
import com.yuanmaxinxi.entity.test.Answer;
public class DBUtil {
	private static SqlSessionFactory factory;
	private static SqlSession session;
	private static Connection conn;
	static {
//		while(factory==null) {
			try {
//				factory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis/mybatis-config.xml"));
				Class.forName("com.mysql.jdbc.Driver");
				conn = DriverManager.getConnection("jdbc:mysql://122.114.0.52:3306/enroll?useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull", "root", "100-99=yi.0");
			} catch (Exception e) {
				e.printStackTrace();
			}
//		}
	}
//	public static SqlSession openSession() {
//		if (session==null) {
//			session = factory.openSession();
//		}
//		return session;
//	}
	public static void main(String[] args) {
		long id = 101;
		int k = 1;
		for (int i = 201; i <=380; i++) {
			Answer answer = new Answer();
			if (i%2==1) {
				answer.setId((long)i);
				answer.setContent("是的");
				answer.setOption("A");
				answer.setTopicId(id);
				switch (k) {
				case 1:
					answer.setType("R");
					break;
				case 2:
					answer.setType("I");
					break;
				case 3:
					answer.setType("A");
					break;
				case 4:
					answer.setType("S");
					break;
				case 5:
					answer.setType("E");
					break;
				case 6:
					answer.setType("C");
					break;
				default:
					break;
				}
			}else {
				answer.setId((long)i);
				answer.setContent("不是的");
				answer.setOption("B");
				answer.setTopicId(id);
				answer.setType("no");
				k++;
				if (k==7) {
					k=1;
				}
				id++;
			}
			insert(answer);
		}
	}
	private static int insert(Answer obj) {
		try {
			String sql = "insert into t_answer values(?,?,?,?,?)";
			PreparedStatement state = conn.prepareStatement(sql);
			state.setObject(1, obj.getId());
			state.setObject(2, obj.getContent());
			state.setObject(3, obj.getOption());
			state.setObject(4, obj.getTopicId());
			state.setObject(5, obj.getType());
			return state.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
}