package com.yuanmaxinxi.dao.admin;

import java.util.List;

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
	public List<Admin> select();
	public Admin selectOneById(Long id);
	public int insert(Admin admin);
	public int update(Admin admin);
}
