package com.yuanmaxinxi.service;

import java.util.List;

import com.yuanmaxinxi.dao.user.UserDAO;
import com.yuanmaxinxi.dto.DeptQueryPageDTO;
import com.yuanmaxinxi.entity.user.User;
import com.yuanmaxinxi.util.StringUtil;

public class UserService {
	private UserDAO userDAO=new UserDAO();
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
		return userDAO.selectOneByOpenid(openid);
	}
	//授权注册
	public void regist(User user) {
		User sysUser = userDAO.selectOneByOpenid(user.getOpenid());
		//说明这个openid是已经受过权了的
		if (sysUser!=null) {
			throw new RuntimeException("授权成功!点击进入下一步");
		}
		try {
			String code=null;
			User sysUser2 = null;
			do {
				//获取6位随机数
				code = StringUtil.getRandomStrByLength(6);
				//看随机数是否已经存在
				sysUser2 = userDAO.selectOneByCode(code);
				//如果不存在
			}while(sysUser2!=null);
			//处理自己唯一的邀请码
			user.setCode(code);
			//添加
			int i = userDAO.insert(user);
			if (i!=1) {
				throw new RuntimeException("授权失败,请稍后再次尝试");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("授权失败,请稍后再次尝试");
		}
	}
	/**
	 * 绑定openid对应用户的邀请人
	 * @param openid
	 * @param code
	 */
	public void bindNumber(String openid, String code) {
		try {
			//判断是否已经绑定了
			User u = userDAO.selectOneByOpenid(openid);
			if (StringUtil.isNotNullAndEmpty(u.getNumber())) {
				throw new RuntimeException("您已经绑定了邀请人,不能重复绑定哟~.");
			}
			//通过邀请码找邀请人
			User user = userDAO.selectOneByCode(code);
			//邀请码不存在
			if (user==null) {
				throw new RuntimeException("邀请码不存在,请核对后再次尝试.");
			}
			if (user.getOpenid().equals(openid)) {
				throw new RuntimeException("不能自己邀请自己.");
			}
			int i = userDAO.bingNumber(openid,code);
			if (i!=1) {
				throw new RuntimeException("绑定失败,请稍后再次尝试.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
	}
}
