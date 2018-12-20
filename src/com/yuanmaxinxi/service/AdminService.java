package com.yuanmaxinxi.service;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.yuanmaxinxi.dao.admin.AdminDAO;
import com.yuanmaxinxi.dto.BaseQueryPageDTO;
import com.yuanmaxinxi.entity.admin.Admin;
import com.yuanmaxinxi.util.MD5Util;
import com.yuanmaxinxi.util.StringUtil;

public class AdminService {
	private AdminDAO adminDAO = new AdminDAO();
	public void insert(Admin obj) {
		if (StringUtil.isNullOrEmpty(obj.getName())) {
			throw new RuntimeException("姓名不能为空.");
		}
		if (StringUtil.isNullOrEmpty(obj.getUsername())) {
			throw new RuntimeException("账号不能为空.");
		}
		if (StringUtil.isNullOrEmpty(obj.getPhone())) {
			throw new RuntimeException("手机号不能为空.");
		}
		//通过账号去数据库找,是否存在
		Admin sysAdmin = adminDAO.selectOneByUsername(obj.getUsername());
		if (sysAdmin!=null) {
			throw new RuntimeException("账号已存在,不能重复,请修改后再次添加.");
		}
		//添加
		try {
			String password = MD5Util.encode(obj.getPassword());
			obj.setPassword(password);
			int i = adminDAO.insert(obj);
			if (i!=1) {
				throw new RuntimeException("");
			}
		} catch (Exception e) {
			throw new RuntimeException("添加失败,请稍后重试.");
		}
		
	}

	public int update(Admin obj) {
		if(StringUtil.isNullOrEmpty(obj.getName())) {
			throw new RuntimeException("账号不能为空");
		}
		if(StringUtil.isNullOrEmpty(obj.getPhone())) {
			throw new RuntimeException("电话不能为空");
		}
		try {
			return adminDAO.update(obj);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}
	
	public int edit(Admin obj) {
		if(StringUtil.isNullOrEmpty(obj.getName())) {
			throw new RuntimeException("账号不能为空");
		}
		if(StringUtil.isNullOrEmpty(obj.getPhone())) {
			throw new RuntimeException("电话不能为空");
		}
		try {
			return adminDAO.edit(obj);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}

	
	public int delete(Long id) {
		return adminDAO.delete(id);
	}

	public Admin selectOneById(Long id) {
		return adminDAO.selectOneById(id);
	}

	public List<Admin> selectAll() {
		return adminDAO.selectAll();
	}

	public List<Admin> queryPage(BaseQueryPageDTO dto) {
		return null;
	}
	/**
	 * 登录方法
	 * @param admin 里面有数据：账号、密码、当前IP
	 * @return
	 */
	public Admin login(Admin admin) {
		if (StringUtil.isNullOrEmpty(admin.getUsername())) {
			throw new RuntimeException("账号不能为空.");
		}
		if (StringUtil.isNullOrEmpty(admin.getPassword())) {
			throw new RuntimeException("密码不能为空.");
		}
		//验证通过之后
		Admin sysAdmin = adminDAO.selectOneByUsername(admin.getUsername());
		//根据账号差不多数据
		if (sysAdmin==null) {
			throw new RuntimeException("账号不存在.");
		}
		
		//判断状态
		if (sysAdmin.getStatus()==1) {
			throw new RuntimeException("账号没有被使用,请更换账号.");
		}
		
		//加密用户密码
		String password = MD5Util.encode(admin.getPassword());
		if (!sysAdmin.getPassword().equals(password)) {
			throw new RuntimeException("密码错误.");
		}
		//登录成功
		//设置IP
		String ip = sysAdmin.getIp();
		//设置上一次登录ip
		sysAdmin.setLastIp(ip);
		//设置这一次登录ip
		sysAdmin.setIp(admin.getIp());
		//设置登录时间
		Date lastTime = sysAdmin.getLoginTime();
		//设置上一次登录时间
		sysAdmin.setLastTime(lastTime);
		//设置本次登录时间
		sysAdmin.setLoginTime(new Date());
		//修改
		try {
			int i = adminDAO.update(sysAdmin);
			if (i!=1) {
				throw new RuntimeException("登录失败,请稍后重试.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("登录失败,请稍后重试.");
		}
		return sysAdmin;
	}


}
