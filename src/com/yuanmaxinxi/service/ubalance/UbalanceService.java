package com.yuanmaxinxi.service.ubalance;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yuanmaxinxi.dao.ubalance.UbalanceDAO;
import com.yuanmaxinxi.entity.ubalance.Ubalance;

@Service
public class UbalanceService{
	@Autowired
	private UbalanceDAO ubalanceDAO;

	@Transactional
	public void insert(Ubalance ubalance){

		try {
			int i = ubalanceDAO.insert(ubalance);
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
	public void update(Ubalance ubalance){

		try {
			int i = ubalanceDAO.update(ubalance);
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
			int i = ubalanceDAO.delete(id);
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
	public Ubalance selectOneById(Long id){
		try {
			Ubalance ubalance= ubalanceDAO.selectOneById(id);

			return ubalance;
		} catch (Exception e) {
			e.printStackTrace();
			if (e instanceof SQLException) {
				throw new RuntimeException("查询失败,网络异常,请稍后或刷新重试.");
			}
			throw new RuntimeException("查询失败.");
		}
	}
	public List<Ubalance> selectAll(){
		try {
			List<Ubalance> list = ubalanceDAO.selectAll();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			if (e instanceof SQLException) {
				throw new RuntimeException("查询失败,网络异常,请稍后或刷新重试.");
			}
			throw new RuntimeException("查询失败.");
		}
	}

	public Ubalance selectOneByOpenId(String openid) {
		Ubalance obj = ubalanceDAO.selectOneByOpenId(openid);
		if(obj==null) {
			throw new RuntimeException("查询失败.");
		}
		return obj;
	}
	

}