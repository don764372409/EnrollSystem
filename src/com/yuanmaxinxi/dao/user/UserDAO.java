package com.yuanmaxinxi.dao.user;

import com.yuanmaxinxi.dao.BaseDAO;
import com.yuanmaxinxi.entity.ulogpay.Ulogpay;
import com.yuanmaxinxi.entity.user.User;

public interface UserDAO extends BaseDAO<User>{
	
	User selectOneByOpenid(String openid);
	User selectOneById(String id);
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
	/**
	 * @Author: 灰飞的猪
	 * @Param @param getuId
	 * @Param @return
	 * @return int    返回类型
	 */
	int updateVip(Long id);
}
