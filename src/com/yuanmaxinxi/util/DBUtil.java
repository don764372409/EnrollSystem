package com.yuanmaxinxi.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.alibaba.druid.sql.visitor.functions.Insert;
import com.yuanmaxinxi.entity.occupation.Occupation;
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
				conn = DriverManager.getConnection("jdbc:mysql://47.112.20.227:3306/enroll?useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull", "root", "admin");
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
//			insert(answer);
		}
	}
	public static void insert(Occupation occ){
		try {
			String sql = "insert into t_occupation(name,pId,remark) values(?,?,?)";
			PreparedStatement state = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			state.setObject(1,occ.getName());
			state.setObject(2, occ.getpId());
			state.setObject(3, occ.getRemark());
			state.executeUpdate();
			ResultSet generatedKeys = state.getGeneratedKeys();
			while (generatedKeys.next()) {
				long generateKey = generatedKeys.getLong(1);
				occ.setId(generateKey);
			}
		} catch (Exception e) {
			String message = e.getMessage();
			if (message.contains("Data truncation")) {
				
			}else {
				throw new RuntimeException();
			}
		}
	}
}