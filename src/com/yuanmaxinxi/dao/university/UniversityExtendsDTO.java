package com.yuanmaxinxi.dao.university;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class UniversityExtendsDTO {
	private String tableName;//表名
	protected UniversityExtendsDTO(String tableName){
		this.tableName = tableName;
	}
	public void getInsertSql(Map<String,String> map) {
		//循环拼，关键字
		String Sql1 = "";//inStr(name,?) and  pId = ?
		String Sql2	="";
		Set<String> keySet = map.keySet();
		Iterator<String> iterator = keySet.iterator();
		while(iterator.hasNext()) {
			String key=iterator.next();
			Sql1 += key+",";
			String value = map.get(key);
			Sql2 += value+",";
		}
//		for (int i = 0; i < map.size(); i++) {
//			if (i==map.size()-1) {
//				whereSql += sqls.get(i);
//			}else {
//				whereSql += sqls.get(i)+" and ";
//			}
//		}
//		//拼接最终SQL
//		//拼接where关键字
//		if (sqls.size()>0) {
//			whereSql = " where "+whereSql;
//		}
//		this.whereSql = whereSql;
	}
}
