package com.yuanmaxinxi.dao.university.updateTeacher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import com.yuanmaxinxi.entity.university.teacherSrc.TeacherSrc;
import com.yuanmaxinxi.util.DBUtil;
import com.yuanmaxinxi.util.CrawlUniversityTeachers.CrawlUniversityTeachers;

public class UniversityUpdateTeacherDAO {
//	private static  UniversityUpdateTeacherDAO instance =new UniversityUpdateTeacherDAO();
//	private UniversityUpdateTeacherDAO() {
//	}
//	public static UniversityUpdateTeacherDAO getInstance() {
//		return instance;
//	}
//	private Connection conn = DBUtil.getConn();
//	public int updateTeacher() {
//		int row = 0;
//		try {
//			String sql = "update t_university set teachers = ? where name = ?";
//			PreparedStatement state = conn.prepareStatement(sql);
//			List<TeacherSrc> lists = CrawlUniversityTeachers.lists;
//			for (TeacherSrc teacherSrc : lists) {
//				state.setObject(1, teacherSrc.getTeacherText());
//				state.setObject(2, teacherSrc.getName());
//				row = state.executeUpdate();
//				System.out.println(row+=1);
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return row;
//	}
}
