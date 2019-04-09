package com.yuanmaxinxi.dto;

import java.util.ArrayList;
import java.util.List;

public class MyBatisQueryPageDTO<T>{
	private List<T> rows = new ArrayList<>();
	private int currentPage=1;//当前页码
	private int pageSize=10;//每页显示条数
	private int endPage;//最大页码  尾页
	private int count;//总记录数
	private int beginIndex;
	
	public int getBeginIndex() {
		beginIndex = (getCurrentPage()-1)*pageSize;
		return beginIndex;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	public List<T> getRows() {
		return rows;
	}
	public void setRows(List<T> rows) {
		this.rows = rows;
	}
	public int getCurrentPage() {
		endPage = getEndPage();
		//判断最大值
		if (currentPage>endPage) {
			currentPage = endPage;
		}
		if (currentPage<1) {
			currentPage =1;
		}
		//判断最小值
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getEndPage() {
		//计算
		endPage = count%pageSize==0?count/pageSize:count/pageSize+1;
		return endPage;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	
}
