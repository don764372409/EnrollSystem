package com.yuanmaxinxi.dao.sqldao;

import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.yuanmaxinxi.util.DBUtil;

public class SqlDAO {
	private PreparedStatement pstmt;
	protected ResultSet rs;

	// 为pstmt设置参数
	protected void setPstmt(Object[] param) throws SQLException {
		int i = 0, paramLen = param.length;
		while (++i <= paramLen) {
			pstmt.setObject(i, param[i-1]);
		}
	}

	/**
	 * 增,删,改操作
	 * @param perparedSql 预编译的sql
	 * @param param       参数的字符串数组语句
	 * @return 影响行数
	 */
	protected int exceuteUpdate(String perparedSql, Object[] param) {
		try {
			pstmt = DBUtil.getConn().prepareStatement(perparedSql);
			if (param != null) {
				setPstmt(param);
			}
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * 查询操作
	 * @param perparedSql 预编译的sql
	 * @param param       参数的字符串数组语句
	 * @return 查询结果集
	 */
	protected ResultSet execResult(String perparedSql, Object[] param) {
		try {
			pstmt = DBUtil.getConn().prepareStatement(perparedSql);
			if (param != null) {
				setPstmt(param);
			}
			return pstmt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 分页查询
	 * @param perparedSql 预编译的sql
	 * @param param       参数的字符串数组语句
	 * @param page        查询的页码
	 * @param count       每页的条数
	 * @return 查询结果集
	 */
	protected ResultSet execResult(String perparedSql, Object[] param, int page, int count) {
		return execResult(perparedSql+"limit "+page+" "+count, param);
	}
}
