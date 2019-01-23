package com.yuanmaxinxi.service;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.yuanmaxinxi.dao.major.MajorDAO;
import com.yuanmaxinxi.dto.MyBatisQueryPageDTO;
import com.yuanmaxinxi.entity.major.Major2;
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
	public void insert(Major2 obj) {
		try {
			majorDAO.insert(obj);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void update(Major2 obj) {
		try {
			majorDAO.update(obj);
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

	public Major2 selectOneById(Long id) {
		Major2 mj = majorDAO.selectOneById(id);
		Major2 major2 = selectOneByOn(mj.getpNo());
		if (major2!=null) {
			//查父亲的父亲
			Major2 major1 = selectOneByOn(major2.getpNo());
			if (major1!=null) {
				mj.setName1(major1.getName());
			}
			mj.setName2(major2.getName());
		}
		return mj;
	}

	public List<Major2> selectAll() {
		return majorDAO.selectAll();
	}

	public void queryPage(MyBatisQueryPageDTO<Major2> dto) {
		int count = majorDAO.selectCount(dto);
		dto.setCount(count);
		List<Major2> list = majorDAO.queryPage(dto);
		//查父亲
		for (Major2 mj : list) {
			Major2 major2 = selectOneByOn(mj.getpNo());
			if (major2!=null) {
				//查父亲的父亲
				Major2 major1 = selectOneByOn(major2.getpNo());
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
	private Major2 selectOneByOn(String on) {
		return majorDAO.selectOneByOn(on);
	}
	/**
	 * 获取具有层次关系的专业
	 * @return
	 */
	public List<Major2> selectAllByLayer(int type) {
		List<Major2> list = majorDAO.selectFirstMajor(type);
		for (Major2 mj : list) {
			List<Major2> children = majorDAO.selectChildrenByPNo(mj.getNo());
			mj.setChildren(children);
			for (Major2 mj2 : children) {
				List<Major2> children2 = majorDAO.selectChildrenByPNo(mj2.getNo());
				mj2.setChildren(children2);
			}
		}
		return list;
	}
}