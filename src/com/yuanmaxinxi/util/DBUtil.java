<<<<<<< HEAD

=======
>>>>>>> branch 'master' of https://github.com/don764372409/EnrollSystem.git
package com.yuanmaxinxi.util;

<<<<<<< HEAD
import java.sql.Connection;
=======
import java.sql.Connection;
>>>>>>> branch 'master' of https://github.com/don764372409/EnrollSystem.git
import java.sql.DriverManager;
public class DBUtil {
	private static Connection conn;
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn=DriverManager.getConnection("jdbc:mysql://39.108.187.254:3306/enroll?useUnicode=true&characterEncoding=UTF-8","root","admin");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static Connection getConn() {
		return conn;
	}
}
<<<<<<< HEAD

=======
>>>>>>> branch 'master' of https://github.com/don764372409/EnrollSystem.git
