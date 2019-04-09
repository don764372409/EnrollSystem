package com.yuanmaxinxi.dto;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseQueryPageDTO<T> {
	private int currentPage=1;//当前页码
	private int pageSize=10;//每页显示条数
	private int endPage;//最大页码  尾页
	private int count;//总记录数
	private List<Object> params=new ArrayList<>();//装SQL片段中对应的参数值的
	private List<String> sqls=new ArrayList<>();//装高级sql片段
	private List<T> rows=new ArrayList<>();//装高级查询分页得到的结果集
	private String tableName;//表名   子类构造器传过来的
	private boolean flag=true;//开关  用来保证拼接SQL的方式只执行一次
	private String whereSQl;//最终拼接好的where字句
	private String orderBySql;//排序的sql
	protected BaseQueryPageDTO(String tableName){
		this.tableName=tableName;
	}
	public int getEndPage() {
		endPage = getCount()%pageSize==0?getCount()/pageSize:getCount()/pageSize+1;
		return endPage;
	}

	public String getOrderBySql() {
		return orderBySql;
	}
	public void setOrderBySql(String orderBySql) {
		this.orderBySql = orderBySql;
	}
	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getCurrentPage() {
		//最大值判断 必须先判断
		if (currentPage>getEndPage()) {
			currentPage = getEndPage();
		}
		//最小值判断
		if (currentPage<1) {
			currentPage =1;
		}
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	/**
	 * 根据子类高级查询参数装Sql片段的方法
	 * 1.这个方法是子类用来装sql的,当前类负责调用
	 * 2.这个方法只能执行一次
	 */
	protected abstract void coverSqls();
	//拼接where字句  这个方法只能执行一次
	private void getWhereSql(){
		coverSqls();
		String whereSql="";
		for(int i=0;i<sqls.size();i++) {
			if(i==(sqls.size()-1)) {
				whereSql=whereSql+sqls.get(i);
			}else {
				whereSql=whereSql+sqls.get(i)+" and ";
			}
		}
		if(sqls.size()>0) {
			whereSql=" where "+whereSql;
		}
		this.whereSQl = whereSql;
	}
	
	public String getSql() {
		if (flag) {
			getWhereSql();
			flag = false;
		}
		return "select * from "+tableName+whereSQl+orderBySql+" limit ?,?";
	}
	public String getCountSql() {
		if (flag) {
			getWhereSql();
			flag = false;
		}
		return "select count(id) as count from "+tableName+whereSQl;
	}
	public List<Object> getParams(){
		return params;
	}
	public List<String> getSqls() {
		return sqls;
	}
	public int getPageSize() {
		return pageSize;
	}
	
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public List<T> getRows() {
		return rows;
	}
	public void setRows(List<T> rows) {
		this.rows = rows;
	}
}
