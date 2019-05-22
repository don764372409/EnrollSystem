package com.yuanmaxinxi.service.ulogpay;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yuanmaxinxi.dao.ubalance.UbalanceDAO;
import com.yuanmaxinxi.dao.ulogpay.UlogpayDAO;
import com.yuanmaxinxi.dao.user.UserDAO;
import com.yuanmaxinxi.entity.ubalance.Ubalance;
import com.yuanmaxinxi.entity.ulogpay.Ulogpay;
import com.yuanmaxinxi.entity.user.User;
import com.yuanmaxinxi.util.payWeixin.PayWeixin;

@Service
public class UlogpayService{
	@Autowired
	private UlogpayDAO ulogpayDAO;
	@Autowired
	private UbalanceDAO ubalanceDAO;
	@Autowired
	private UserDAO userDAO;

	@Transactional
	public void insert(Ulogpay ulogpay){

		try {
			int i = ulogpayDAO.insert(ulogpay);
			if(i!=1){
				throw new RuntimeException("添加失败.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (e instanceof SQLException) {
				throw new RuntimeException("添加失败,网络异常,请稍后或刷新重试.");
			}
			throw new RuntimeException("添加失败.");
		}
	}
	@Transactional
	public void update(Ulogpay ulogpay){

		try {
			int i = ulogpayDAO.update(ulogpay);
			if(i!=1){
				throw new RuntimeException("修改失败.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (e instanceof SQLException) {
				throw new RuntimeException("修改失败,网络异常,请稍后或刷新重试.");
			}
			throw new RuntimeException("修改失败.");
		}
	}
	@Transactional
	public void delete(Long id){
		try {
			int i = ulogpayDAO.delete(id);
			if(i!=1){
				throw new RuntimeException("删除失败.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (e instanceof SQLException) {
				throw new RuntimeException("删除失败,网络异常,请稍后或刷新重试.");
			}
			throw new RuntimeException("删除失败.");
		}
	}
	public Ulogpay selectOneById(Long id){
		try {
			Ulogpay ulogpay= ulogpayDAO.selectOneById(id);

			return ulogpay;
		} catch (Exception e) {
			e.printStackTrace();
			if (e instanceof SQLException) {
				throw new RuntimeException("查询失败,网络异常,请稍后或刷新重试.");
			}
			throw new RuntimeException("查询失败.");
		}
	}
	public List<Ulogpay> selectAll(){
		try {
			List<Ulogpay> list = ulogpayDAO.selectAll();

			return list;
		} catch (Exception e) {
			e.printStackTrace();
			if (e instanceof SQLException) {
				throw new RuntimeException("查询失败,网络异常,请稍后或刷新重试.");
			}
			throw new RuntimeException("查询失败.");
		}
	}
	public Map<String, String> payWeixin(Ulogpay ulogpay) {
		//假设是充值订单
		Map<String, String> map = new HashMap<String, String>();
		try {
			ulogpay.setNumber(new Date().getTime());//订单号
			ulogpay.setPaytime(new Date());
			map = PayWeixin.payWeixin(ulogpay);
			if("success".equals(map.get("responseState"))) {
				//形成微信流水订单
				User user = userDAO.selectOneByOpenid(ulogpay.getOpenid());
				ulogpay.setuId(user.getId());
				int row = ulogpayDAO.insert(ulogpay);
				if(row!=1) {
					throw new RuntimeException("添加订单失败");
				}
				//设置外部微信商户订单
				Ulogpay ulogpay2 = new Ulogpay();
				ulogpay2.setId(ulogpay.getId());
				ulogpay2.setOutNumber(map.get("package"));
				int row1 = ulogpayDAO.updateOutNumber(ulogpay2);
				if(row1!=1) {
					throw new RuntimeException("修改订单失败");
				}
			}
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("微信支付错误，请稍后重试");
		}
	}

	public void finish(Ulogpay ulogpay) {
		//更改订单状态
		String outNumber = ulogpay.getOutNumber();
		Ulogpay obj = ulogpayDAO.selectOneByOutNumber(outNumber);
		int row = ulogpayDAO.updateStatus(obj.getId());
		if(row!=1) {
			throw new RuntimeException("更新账户订单错误");
		}
		//更改积分余额
		Ubalance selectUbla = ubalanceDAO.selectOneByuId(obj.getuId());
		if(selectUbla==null) {
			throw new RuntimeException("未创建改用户账户");
		}
		BigDecimal money = selectUbla.getMoney();
		int number = selectUbla.getNumber();
		selectUbla.setNumber(number+1);
		BigDecimal add = money.add(obj.getValue());//增加余额
		selectUbla.setMoney(add);
		int update = ubalanceDAO.update(selectUbla);
		if(update!=1) {
			throw new RuntimeException("更新账户余额错误");
		}
		User user = userDAO.selectOneById(obj.getuId());
		if(user.getVip()==0) {
			int row1 =userDAO.updateVip(selectUbla.getuId());
			if(row1!=1) {
				throw new RuntimeException("更新账户余额错误");
			}
		}
	}
	
	public void pay(Ulogpay ulogpay) {
		ulogpay.setPaytime(new Date());
		int row = ulogpayDAO.insert(ulogpay);
		if(row!=1) {
			throw new RuntimeException("更新账户余额错误");
		}
		Ubalance selectUbla = ubalanceDAO.selectOneByuId(ulogpay.getuId());
		if(selectUbla==null) {
			throw new RuntimeException("未创建改用户账户");
		}
		BigDecimal money = selectUbla.getMoney();
		BigDecimal add = money.subtract(ulogpay.getValue());//减少余额
		selectUbla.setMoney(add);
		int update = ubalanceDAO.update(selectUbla);
		if(update!=1) {
			throw new RuntimeException("更新账户余额错误");
		}
		
	}
	public List<Ulogpay> selectAllByOpenId(String openid) {
		List<Ulogpay>  list = ulogpayDAO.selectAllByOpenId(openid);
		return list;
	}


}