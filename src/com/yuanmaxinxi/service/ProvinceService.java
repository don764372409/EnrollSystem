package com.yuanmaxinxi.service;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yuanmaxinxi.dao.admin.AdminDAO;
import com.yuanmaxinxi.dao.province.ProvinceDao;
import com.yuanmaxinxi.dto.BaseQueryPageDTO;
import com.yuanmaxinxi.entity.admin.Admin;
import com.yuanmaxinxi.entity.province.Province;
import com.yuanmaxinxi.util.CrawlProvinceDataUtil;
import com.yuanmaxinxi.util.DBUtil;
import com.yuanmaxinxi.util.StringUtil;
@Service
public class ProvinceService {
	@Autowired
	private ProvinceDao provincedao;
	public List<Province> selectAll() {
	return provincedao.selectAll();
}
//	private void init() {
//		SqlSession session = DBUtil.openSession();
//		provincedao = session.getMapper(ProvinceDao.class);
//	}
//	public ProvinceService() {
//		init();
//	}
//	public void insert(Province obj) {
//	}
//
//	public void update(Province obj) {
//	}
//
//	public void delete(Long id) {
//	}
//
//	public Admin selectOneById(Long id) {
//		return null;
//	}
//
//	public List<Province> selectAll() {
//		return provincedao.selectAll();
//	}
//
//	public List<Admin> queryPage(BaseQueryPageDTO dto) {
//		return null;
//	}
//
//	public void reload() {
////		List<String> list = CrawlProvinceDataUtil.craw();
////		try {
////			//不能自动提交 而是先写进内存
////			DBUtil.getConn().setAutoCommit(false);
////			for (String name : list) {
////				Province pro = provincedao.selectOneById(name);
////				//在数据库中不存在这个省
////				if (pro==null&&StringUtil.isNotNullAndEmpty(name)) {
////					pro = new Province();
////					pro.setName(name);
////					int i = provincedao.insert(pro);
////					if (i!=1) {
////						throw new RuntimeException("保存失败.");
////					}
////				}
////			}
////			//提交数据
////			DBUtil.getConn().commit();
////			//将自动提交设置为true  不影响其他操作
////			DBUtil.getConn().setAutoCommit(true);
////		} catch (Exception e) {
////			//报了错 回滚
////			try {
////				DBUtil.getConn().rollback();
////			} catch (SQLException e1) {
////				e1.printStackTrace();
////				throw new RuntimeException("事务回滚失败,请联系技术人员.");
////			}
////			e.printStackTrace();
////			throw new RuntimeException("重新爬取省份失败,请稍后重试.");
////		}
//	}
}
