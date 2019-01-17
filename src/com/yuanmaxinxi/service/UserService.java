package com.yuanmaxinxi.service;

import java.util.List;

import com.yuanmaxinxi.dao.user.UserDao;
import com.yuanmaxinxi.dto.DeptQueryPageDTO;
import com.yuanmaxinxi.entity.user.User;

public class UserService {
	UserDao userdao=new UserDao();
	public List<User> selectOneById(DeptQueryPageDTO deptQuery) {
	
		try {
			return userdao.selectOneById(deptQuery);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
	public List<User> selectAll() {
		List<User> list = userdao.selectAll();
		return list;
	}
}
