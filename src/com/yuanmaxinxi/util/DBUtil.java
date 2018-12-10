package com.yuanmaxinxi.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;


public class DBUtil {
		private static Connection conn;
		private static Properties pp;
		public static void setProperties(Properties pro) {
			pp=pro;
		}
		public static Connection getConn() {
			try {
				Class.forName(pp.getProperty("className"));
				conn=DriverManager.getConnection(pp.getProperty("url"),pp.getProperty("username"),pp.getProperty("password"));
			}catch(Exception e) {
				e.printStackTrace();
			}
			return conn;
		}
}
