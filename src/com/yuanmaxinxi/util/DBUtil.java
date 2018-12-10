package com.yuanmaxinxi.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;


public class DBUtil {
		private static Connection conn;

		public static Connection getConn() {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				conn=DriverManager.getConnection("jdbc:mysql://39.108.187.254:3306/enroll","root","admin");
			}catch(Exception e) {
				e.printStackTrace();
			}
			return conn;
		}
}
