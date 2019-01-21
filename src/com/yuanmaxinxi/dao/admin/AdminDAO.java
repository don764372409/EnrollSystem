package com.yuanmaxinxi.dao.admin;

import com.yuanmaxinxi.dao.BaseDAO;
import com.yuanmaxinxi.entity.admin.Admin;

public interface AdminDAO extends BaseDAO<Admin>{
	public int edit(Admin obj);
	/**
	 * 根据账号
	 * @param username
	 * @return
	 */
	public Admin selectOneByUsername(String username);
	
}
