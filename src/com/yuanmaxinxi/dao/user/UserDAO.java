package com.yuanmaxinxi.dao.user;

import com.yuanmaxinxi.dao.BaseDAO;
import com.yuanmaxinxi.entity.user.User;

public interface UserDAO extends BaseDAO<User>{
	User selectOneByOpenid(String openid);
	/**
	 * 根据自己唯一的邀请码找到用户对象
	 * @param code
	 * @return
	 */
	public User selectOneByCode(String code);
	/**
	 * 绑定openid对应对象的邀请人
	 * @param openid
	 * @param code
	 * @return
	 */
	public int bingNumber(User user) ;
	int selectShoucangNumbers(Long id);
}
