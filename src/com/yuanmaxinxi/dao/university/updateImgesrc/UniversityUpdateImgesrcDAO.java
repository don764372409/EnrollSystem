package com.yuanmaxinxi.dao.university.updateImgesrc;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yuanmaxinxi.entity.university.University;
import com.yuanmaxinxi.entity.university.ImgSrc.UniversityImgSrc;
import com.yuanmaxinxi.util.DBUtil;
import com.yuanmaxinxi.util.CrawlUniversityImgSrc.CrawlUniversityImgSrc;
import com.yuanmaxinxi.util.crawlJianJie.CrawlJianJie;

public class UniversityUpdateImgesrcDAO {
	//数据库连接 单利模式
	private static UniversityUpdateImgesrcDAO instance = new UniversityUpdateImgesrcDAO();
	private UniversityUpdateImgesrcDAO() {}
	public static UniversityUpdateImgesrcDAO getInstance() {
		return instance;
	}
	private Connection conn = DBUtil.getConn();
	public List<UniversityImgSrc> getUniversityName(){
		try {
			List<UniversityImgSrc> list = new ArrayList<>();
			String sql = "select name from t_university";
			PreparedStatement state = conn.prepareStatement(sql);
			ResultSet result = state.executeQuery();
			while(result.next()) {
				UniversityImgSrc universityImgSrc = new UniversityImgSrc();
				universityImgSrc.setName(result.getString("name"));
				list.add(universityImgSrc);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public int setImgSrc() {
		int row = 0;
		try {
			String sql = "update t_university set imgsrc = ? where name = ?";
			PreparedStatement state = conn.prepareStatement(sql);
			List<UniversityImgSrc> lists = CrawlUniversityImgSrc.lists;
			for (UniversityImgSrc universityImgSrc : lists) {
				state.setObject(1, universityImgSrc.getSrc());
				state.setObject(2, universityImgSrc.getName());
				row = state.executeUpdate();
				System.out.println(row+=1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return row;
	}
	public int setJianjie() {
		int row = 0;
		try {
			String sql = "update t_university set remark = ? where name = ?";
			PreparedStatement state = conn.prepareStatement(sql);
			List<University> lists = CrawlJianJie.lists;
			for (University university : lists) {
				state.setObject(1, university.getRemark());
				state.setObject(2, university.getName());
				row = state.executeUpdate();
				System.out.println(row+=1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return row;
	}
}
