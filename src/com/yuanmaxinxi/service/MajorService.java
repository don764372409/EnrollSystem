package com.yuanmaxinxi.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.ibatis.session.SqlSession;

import com.yuanmaxinxi.dao.major.MajorDAO;
import com.yuanmaxinxi.dto.MyBatisQueryPageDTO;
import com.yuanmaxinxi.entity.major.Major;
import com.yuanmaxinxi.util.DBUtil;

public class MajorService {
	private MajorDAO majorDAO;
	private SqlSession session;
	private void init() {
		session = DBUtil.openSession();
		majorDAO = session.getMapper(MajorDAO.class);
	}
	public MajorService() {
		init();
	}
	public void insert(Major mj) {
		try {
			majorDAO.insert(mj);
			session.commit();
		} catch (SQLException e) {
			//报错之后  回滚事务 将内存中的数据恢复提交之前的状态
			session.rollback();
			e.printStackTrace();
		}
	}

	public void update(Major mj) {
		try {
			majorDAO.update(mj);
			session.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void delete(Long id) {
		try {
			majorDAO.delete(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Major selectOneById(Long id) {
		Major mj = majorDAO.selectOneById(id);
		Major major2 = selectOneByOn(mj.getpNo());
		if (major2!=null) {
			//查父亲的父亲
			Major major1 = selectOneByOn(major2.getpNo());
			if (major1!=null) {
				mj.setName1(major1.getName());
			}
			mj.setName2(major2.getName());
		}
		return mj;
	}

	public List<Major> selectAll() {
		return majorDAO.selectAll();
	}

	public void queryPage(MyBatisQueryPageDTO<Major> dto) {
		int count = majorDAO.selectCount(dto);
		dto.setCount(count);
		List<Major> list = majorDAO.queryPage(dto);
		//查父亲
		for (Major mj : list) {
			Major major2 = selectOneByOn(mj.getpNo());
			if (major2!=null) {
				//查父亲的父亲
				Major major1 = selectOneByOn(major2.getpNo());
				if (major1!=null) {
					mj.setName1(major1.getName());
				}
				mj.setName2(major2.getName());
			}
		}
		dto.setRows(list);
	}
	/**
	 * 根据专业代码查专业
	 * @param on
	 * @return
	 */
	public Major selectOneByOn(String on) {
		return majorDAO.selectOneByOn(on);
	}
	/**
	 * 获取具有层次关系的专业
	 * @return
	 */
	public List<Major> selectAllByLayer(int type) {
		List<Major> list = majorDAO.selectFirstMajor(type);
		for (Major mj : list) {
			List<Major> children = majorDAO.selectChildrenByPNo(mj.getNo());
			mj.setChildren(children);
			for (Major mj2 : children) {
				List<Major> children2 = majorDAO.selectChildrenByPNo(mj2.getNo());
				mj2.setChildren(children2);
			}
		}
		return list;
	}
}