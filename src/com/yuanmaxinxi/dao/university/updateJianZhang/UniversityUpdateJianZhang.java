package com.yuanmaxinxi.dao.university.updateJianZhang;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.yuanmaxinxi.entity.university.jianzhang.Jianzhang;
import com.yuanmaxinxi.util.DBUtil;
import com.yuanmaxinxi.util.crawlUniversityJianzhang.CrawlUniversityJianzhang;

public class UniversityUpdateJianZhang {
	private static UniversityUpdateJianZhang instance = new UniversityUpdateJianZhang();
	private UniversityUpdateJianZhang() {
	}
	public static UniversityUpdateJianZhang getInstance() {
		return instance;
	}
	public static int updateJiezhang(List<Jianzhang> lists) {
		Connection conn = DBUtil.getConn();
		int row = 0;
		for (Jianzhang jiezhang : lists) {
			try {
				String sql = "INSERT INTO t_jianzhang(uID,jianzhang,jianzhangtext) VALUES ((select id from t_university where name = ?),?,?);  ";
				PreparedStatement state = conn.prepareStatement(sql);
				state.setObject(1, jiezhang.getName());
				state.setObject(2, jiezhang.getZhangshengName());
				state.setObject(3, jiezhang.getText());
				row += state.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return row;
	}
}
