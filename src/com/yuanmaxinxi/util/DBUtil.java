package com.yuanmaxinxi.util;
import java.sql.Connection;
import java.sql.DriverManager;
public class DBUtil {
	private static Connection conn;
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn=DriverManager.getConnection("jdbc:mysql://localhost/enroll?useUnicode=true&characterEncoding=UTF-8","root","admin");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static Connection getConn() {
		return conn;
	}
}