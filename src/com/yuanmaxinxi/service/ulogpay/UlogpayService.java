package com.yuanmaxinxi.service.ulogpay;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yuanmaxinxi.dao.ulogpay.UlogpayDAO;
import com.yuanmaxinxi.entity.ulogpay.Ulogpay;
import com.yuanmaxinxi.util.payWeixin.PayWeixin;

@Service
public class UlogpayService{
	@Autowired
	private UlogpayDAO ulogpayDAO;

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
	public void payWeixin(Ulogpay ulogpay) {
		//假设是任务订单
		try {
			Map<String, String> payWeixin = PayWeixin.payWeixin(ulogpay);
			System.out.println(payWeixin.toString());
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("微信支付错误，请稍后重试");
		}
	}


}