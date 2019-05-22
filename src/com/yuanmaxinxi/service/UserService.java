package com.yuanmaxinxi.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yuanmaxinxi.dao.ubalance.UbalanceDAO;
import com.yuanmaxinxi.dao.user.UserDAO;
import com.yuanmaxinxi.entity.ubalance.Ubalance;
import com.yuanmaxinxi.entity.user.User;
import com.yuanmaxinxi.util.StringUtil;
@Service
@Transactional
public class UserService {
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private UbalanceDAO ubalanceDAO;
	public List<User> selectAll() {
		List<User> list = userDAO.selectAll();
		return list;
	}
	public User selectOneId(Long id) {
		return userDAO.selectOneById(id);
	}
	public User selectOneByOpenid(String openid) {
		User user = userDAO.selectOneByOpenid(openid);
		if (user!=null) {
			Ubalance ubalance = ubalanceDAO.selectOneByuId(user.getId());
			user.setFen(String.valueOf(ubalance.getMoney().intValue()));
		}
		return user;
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
			Ubalance ubalance = new Ubalance();
			ubalance.setuId(user.getId());
			int insert = ubalanceDAO.insert(ubalance);
			if(insert!=1) {
				throw new RuntimeException("创建个人账户,请稍后再次尝试");
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (e instanceof SQLException) {
				throw new RuntimeException("网络异常,请稍后重试.");
			}
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
			User user2 = new User();
			user2.setOpenid(openid);
			user2.setNumber(code);
			int i = userDAO.bingNumber(user2);
			if (i!=1) {
				throw new RuntimeException("绑定失败,请稍后再次尝试.");
			}
		} catch (Exception e) {
			if (e instanceof SQLException) {
				throw new RuntimeException("网络异常,请稍后重试.");
			}
			throw new RuntimeException(e.getMessage());
		}
	}
	public int selectShoucangNumbers(Long id) {
		return userDAO.selectShoucangNumbers(id);
	}
	@Transactional
	public void update(User user) {
		if (user.getId()==null||user.getId()<1) {
			throw new RuntimeException("登录失效了,请重新登录后尝试.");
		}
		if (StringUtil.isNullOrEmpty(user.getName())) {
			throw new RuntimeException("用户名不能为空.");
		}
		if (StringUtil.isNullOrEmpty(user.getUrl())) {
			throw new RuntimeException("头像不能为空.");
		}
		if (StringUtil.isNullOrEmpty(user.getNumber())) {
			throw new RuntimeException("手机号码不能为空.");
		}
		if (!StringUtil.isMobile(user.getNumber())) {
			throw new RuntimeException("手机号码格式不正确.");
		}
		try {
			int i = userDAO.update(user);
			if (i!=1) {
				throw new RuntimeException("修改信息失败,请刷新或稍后重试.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("修改信息失败,请刷新或稍后重试.");
		}
	}
}
