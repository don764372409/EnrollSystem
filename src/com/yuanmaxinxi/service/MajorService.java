package com.yuanmaxinxi.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yuanmaxinxi.dao.major.MajorDAO;
import com.yuanmaxinxi.dao.occupation.OccupationDAO;
import com.yuanmaxinxi.dao.university.UniversityDao;
import com.yuanmaxinxi.dto.MyBatisQueryPageDTO;
import com.yuanmaxinxi.entity.major.Major;
import com.yuanmaxinxi.entity.occupation.Occupation;
import com.yuanmaxinxi.entity.university.University;
import com.yuanmaxinxi.util.StringUtil;

@Service
@Transactional
public class MajorService {
	@Autowired
	private MajorDAO majorDAO;
	@Autowired
	private OccupationDAO occupationDAO;
	@Autowired
	private UniversityDao universityDao;

	public void insert(Major mj) {
		try {
			majorDAO.insert(mj);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("添加失败");
		}
	}

	public void add(Major obj) {
		if (StringUtil.isNullOrEmpty(obj.getpNo())) {
			throw new RuntimeException("父级id不能为空");
		}
		if (StringUtil.isNullOrEmpty(obj.getName())) {
			throw new RuntimeException("名称不能为空");
		}
		if (StringUtil.isNullOrEmpty(obj.getType() + "")) {
			throw new RuntimeException("学历分类id不能为空");
		}
		if (StringUtil.isNullOrEmpty(obj.getJianjie())) {
			throw new RuntimeException("简介内容内容不能为空");
		}
		if (StringUtil.isNullOrEmpty(obj.getMubiao())) {
			throw new RuntimeException("目标内容不能为空");
		}
//		if(StringUtil.isNullOrEmpty(obj.getJianjie())) {
//			throw new RuntimeException("简介内容内容不能为空");
//		}
		try {
			int rs = majorDAO.insert(obj);
			if (rs != 1) {
				throw new RuntimeException("添加失败");
			}
		} catch (Exception e) {
			throw new RuntimeException("添加失败");
		}
	}

	public void update(Major mj) {
		try {
			majorDAO.update(mj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void delete(Long id) {
		try {
			majorDAO.delete(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 获取具有层次关系的专业
	 * @param pId
	 * @param cnt 层级
	 * @return
	 */
	public List<Major> selectLayer(String pNo,int cnt) {
		if(cnt==0) {
			return null;
		}else {
			List<Major> children = majorDAO.selectLayer(pNo);
			for(Major major :children) {
				List<Major> clild=selectLayer(major.getNo(),cnt-1);
				major.setChildren(clild);
			}
			return children;
		}
	}
	
	/**
	 * 根据职业查专业
	 * @param oId
	 * @return
	 */
	public List<Major> selectByoId(Long oId){
		return majorDAO.selectByOcc(oId);
	}

	public Major selectOneById(Long id) {
		Major mj = majorDAO.selectOneById(id);
		Major major2 = selectOneByOn(mj.getpNo());
		if (major2 != null) {
			// 查父亲的父亲
			Major major1 = selectOneByOn(major2.getpNo());
			if (major1 != null) {
				mj.setName1(major1.getName());
			}
			mj.setName2(major2.getName());
		}
		return mj;
	}

	public Major selectOneBenByName(String name) {
		return majorDAO.selectOneBenByName(name);
	}

	public List<Major> selectAll() {
		return majorDAO.selectAll();
	}

	public void queryPage(MyBatisQueryPageDTO<Major> dto) {
		int count = majorDAO.selectCount(dto);
		dto.setCount(count);
		List<Major> list = majorDAO.queryPage(dto);
		// 查父亲
		for (Major mj : list) {
			Major major2 = selectOneByOn(mj.getpNo());
			if (major2 != null) {
				// 查父亲的父亲
				Major major1 = selectOneByOn(major2.getpNo());
				if (major1 != null) {
					mj.setName1(major1.getName());
				}
				mj.setName2(major2.getName());
			}
		}
		dto.setRows(list);
	}

	/**
	 * 根据专业代码查专业
	 * 
	 * @param on
	 * @return
	 */
	public Major selectOneByOn(String on) {
		return majorDAO.selectOneByOn(on);
	}

	/**
	 * 获取具有层次关系的专业
	 * 
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
				/*
				for (Major mj3 : children2) {
					mj3.setOcc(occupationDAO.selectByMajor(mj3.getId()));
				}
				*/
			}
		}
		return list;
	}

	public void addMajorAndUniversity(String no, String name) {
		try {
			University uni = universityDao.selectOneByName(name);
			if (uni != null) {
				Map<String, Object> map = new HashMap<>();
				map.put("no", no);
				map.put("uId", uni.getId());
				String majorNo = majorDAO.selectxxx(map);

				// 如果存在 就不保存
				if (StringUtil.isNotNullAndEmpty(majorNo)) {
					System.err.println(no + "---" + name + "-----已存在,跳过");
					return;
				}
				System.err.println("开始保存-------------------" + no + "---" + name);
				majorDAO.insertMajorAndUniversity(map);
			} else {
				// 找不到学校 把名字保存下来
				throw new RuntimeException("");
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
			try {
				System.err.println(no + "," + name + "saveError");

//				majorDAO.insertXXX(no+","+name+"saveError");
//				session.commit();
			} catch (Exception e2) {
				System.err.println(e2.getMessage());
			}
		}
	}

	/**
	 * 获取当前ID对应的相近专业
	 * 
	 * @param id
	 * @return
	 */
	public List<Major> selectLikeMajorsById(Long id) {
		return majorDAO.selectLikeMajorsById(id);
	}

	/**
	 * 获取当前专业的开设院校
	 * 
	 * @param id
	 * @return
	 */
	public List<University> selectUnis(Long id) {
		return majorDAO.selectUnis(id);
	}
}