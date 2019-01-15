package com.yuanmaxinxi.dto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yuanmaxinxi.util.DBUtil;

public class BaseQueryPageDTO<T> {
	private int currentPage=1;
	private int pageSize=10;
	List<Object> params=new ArrayList<>();//装高级sql片段
	List<T> rows=new ArrayList<>();//装高级查询分页得到的结果
	private String tableName;
	protected List<String> sqls=new ArrayList<>();
	private boolean flag=true;
	private String whereSql;
	Connection conn = DBUtil.getConn();
	PreparedStatement sql;
	ResultSet executeQuery; 
	protected BaseQueryPageDTO(String tableName){
		this.tableName=tableName;
	}
	
	public void coverSqls() {
		
	}
	private String getWhereSql() throws SQLException {
		coverSqls();
		String whereSql="";
		for(int i=0;i<sqls.size();i++) {
			if(i==(sqls.size()-1)) {
				whereSql=whereSql+sqls.get(i)+";";
			}else {
				whereSql=whereSql+sqls.get(i)+"and";
			}
		}
		if(sqls.size()>0) {
			whereSql="select * from "+tableName+" where "+whereSql;
		}else {
			whereSql="select * from "+tableName;
			
		}
		System.out.println(whereSql);
		return whereSql;
	}
	public String getSql() {
		try {
			return getWhereSql();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
	public String getCountSql() {
		return tableName;
		
	}
	public List<Object> getParams(){
		return params;
		
	}
}
