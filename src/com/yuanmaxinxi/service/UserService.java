package com.yuanmaxinxi.service;

import java.util.List;

import com.yuanmaxinxi.dao.user.UserDao;
import com.yuanmaxinxi.dto.DeptQueryPageDTO;
import com.yuanmaxinxi.entity.user.User;

public class UserService {
	UserDao userDAO=new UserDao();
	public List<User> selectOneById(DeptQueryPageDTO deptQuery) {
		try {
			return userDAO.selectOneById(deptQuery);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	public List<User> selectAll() {
		List<User> list = userDAO.selectAll();
		return list;
	}
	public User selectOneByOpenid(String openid) {
		userDAO.selectOneByOpenid(openid);
		return null;
	}
	
}
