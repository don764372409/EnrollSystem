package com.yuanmaxinxi.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
public class DBUtil {
	private static SqlSessionFactory factory;
	private static SqlSession session;
	private static Connection conn;
	static {
		while(factory==null) {
			try {
				factory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml"));
				Class.forName("com.mysql.jdbc.Driver");
				conn = DriverManager.getConnection("jdbc:mysql://122.114.0.52/enroll", "root", "100-99=yi.0");
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
	public static Connection getConn() {
		return conn;
	}
//	public static void main(String[] args) {
//		String sql = "select table_name from information_schema.tables where table_schema='jeesite' and table_type='base table';";
////		String[] tbs = {"hghj_activity","hghj_advert","hghj_area","hghj_auth_group","hghj_auth_rule","hghj_auth_group_access",
////				"hghj_balances","hghj_cards","hghj_cash","hghj_category_ad","hghj_category_dt","hghj_category_post","hghj_category_px","hghj_category_sc",
////				"hghj_category_tb","hghj_course","hghj_enterprise","hghj_figure","hghj_issue","hghj_member","hghj_news","hghj_notice"
////				,"hghj_order","hghj_project","hghj_recharge","hghj_shuffling","hghj_sms","hghj_system","hghj_tec_cer","hghj_temporary"};
//		try {
//			PreparedStatement state = getConn().prepareStatement(sql);
//			ResultSet query = state.executeQuery();
//			while(query.next()) {
//				String tableName = query.getString("table_name");
//				if (tableName.contains("hghj_")) {
//					System.err.println(tableName);
////					if (Arrays.asList(tbs).contains(tableName)) {
////						continue;
////					}
//					/**
//					 *  create_by		varchar     60
//						create_date  	datetime  
//						update_by  		varchar     60
//						update_date  	datetime
//						remarks  		varchar     500
//						status			int 
//					 */
//					try {
//						sql = "alter table "+tableName+" add create_by varchar(60) null;";
//						
//						state = getConn().prepareStatement(sql);
//						state.executeUpdate();
//					} catch (Exception e) {
//						System.err.println("重复了");
//					}
//					try {
//						sql = "alter table "+tableName+" add create_date datetime null;";
//						state = getConn().prepareStatement(sql);
//						state.executeUpdate();
//					} catch (Exception e) {
//						System.err.println("重复了");
//					}
//					try {
//						sql = "alter table "+tableName+" add update_by varchar(60) null;";
//						
//						state = getConn().prepareStatement(sql);
//						state.executeUpdate();
//					} catch (Exception e) {
//						System.err.println("重复了");
//					}
//					try {
//						sql = "alter table "+tableName+" add update_date datetime null;";
//						
//						
//						state = getConn().prepareStatement(sql);
//						state.executeUpdate();
//					} catch (Exception e) {
//						System.err.println("重复了");
//					}
//					try {
//						sql = "alter table "+tableName+" add remarks varchar(500) null;";
//						
//						state = getConn().prepareStatement(sql);
//						state.executeUpdate();
//					} catch (Exception e) {
//						System.err.println("重复了");
//					}
//					try {
//						sql = "alter table "+tableName+" add status int(2) null default 0;";
//						state = getConn().prepareStatement(sql);
//						state.executeUpdate();
//					} catch (Exception e) {
//						System.err.println("重复了");
//					}
//					
//					
//					
//					
//					
//					
//					
//					
//				}
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}
}