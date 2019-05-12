package com.yuanmaxinxi.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yuanmaxinxi.dao.major.MajorDAO;
import com.yuanmaxinxi.dao.occupation.OccupationDAO;
import com.yuanmaxinxi.entity.major.Major;
//github.com/don764372409/EnrollSystem.git
import com.yuanmaxinxi.entity.occupation.Occupation;
import com.yuanmaxinxi.util.StringUtil;
@Service
public class OccupationService {
	
	@Autowired
	private MajorDAO majorDAO;
	@Autowired
	private OccupationDAO occupationDAO;
	
	/**
	 * 根据专业查职业
	 * @param mId
	 * @return
	 */
	public List<Occupation> selectBymId(Long mId){
		return occupationDAO.selectByMajor(mId);
	}
	
	
	private List<Occupation> selectChild(Long pId,int cnt){
		if(cnt==0) {
			return null;
		}else {
			List<Occupation> children = occupationDAO.selectBypId(pId);
			for(Occupation occ :children) {
				occ.setChildren(selectChild(occ.getpId(), cnt-1));
				/*
				List<Occupation> clild=selectChild(occ.getId(),cnt+1);
				if(clild==null) {
					occ.setMajor(majorDAO.selectByOcc(occ.getId()));
				}else {
					occ.setChildren(clild);
				}
				*/
			}
			return children;
		}
	}
	/**
	 * 获取具有层次关系的职业
	 * @return
	 */
	public List<Occupation> selectAllByLayer() {
		List<Occupation> list = occupationDAO.selectFirst();
		for (Occupation occ : list) {
			occ.setChildren(selectChild(occ.getId(),2));
		}
		return list;
	}
//	private void init() {
//		SqlSession session = DBUtil.openSession();
//		ocpDAO = session.getMapper(OccupationDAO.class);
//	}
//	public OccupationService() {
//		init();
//	}
	@Transactional
	public void insert(Occupation obj) {
		if(StringUtil.isNullOrEmpty(Long.toString(obj.getpId()))) {
			throw new RuntimeException("id不能为空");
		}
		if(StringUtil.isNullOrEmpty(obj.getName())) {
			throw new RuntimeException("名称不能为空");
		}
		if(StringUtil.isNullOrEmpty(obj.getRemark())) {
			throw new RuntimeException("内容不能为空");
		}
//		if(StringUtil.isNullOrEmpty(obj.getWorkContent())) {
//			throw new RuntimeException("工作内容不能为空");
//		}
		try {
			System.out.println("666");
			int rs = occupationDAO.insert(obj);
			if(rs!=1) {
				throw new RuntimeException("添加失败");
			}
		} catch (Exception e) {
			throw new RuntimeException("添加失败");
		}
		
	}
	
	@Transactional
	public void update(Occupation obj) {

		if(StringUtil.isNullOrEmpty(Long.toString(obj.getpId()))) {
			throw new RuntimeException("id不能为空");
		}
		if(StringUtil.isNullOrEmpty(obj.getName())) {
			throw new RuntimeException("名称不能为空");
		}
		if(StringUtil.isNullOrEmpty(obj.getRemark())) {
			throw new RuntimeException("内容不能为空");
		}
//		if(StringUtil.isNullOrEmpty(obj.getWorkContent())) {
//			throw new RuntimeException("工作内容不能为空");
//		}
		try {
			int rs = occupationDAO.update(obj);
			if(rs!=1) {
				throw new RuntimeException("更新失败");
			}
		} catch (Exception e) {
			throw new RuntimeException("更新失败");
		}
	}
	@Transactional
	public void delete(Long id) {
		try {
			int rs = occupationDAO.delete(id);
			if(rs!=1) {
				throw new RuntimeException("删除失败");
			}
		} catch (Exception e) {
			throw new RuntimeException("删除失败");
		}
		
	}

	public Occupation selectOneById(Long id) {
		return occupationDAO.selectOneById(id);
	}
//
	public List<Occupation> selectAll() {
		return occupationDAO.selectAll();
	}
	
	public List<Occupation> sFselectAll(){
		List<Occupation> list = occupationDAO.selectByPid();
		for (Occupation occupation : list) {
			List<Occupation> childrenList = occupationDAO.selectByFatherPid(occupation.getId());
			occupation.setChildren(childrenList);
			for (Occupation list2 : childrenList) {
				List<Occupation> list3 = occupationDAO.selectByFatherPid(list2.getId());
				list2.setChildren(list3);
		}
	}
		return list;
	}
	
	public Occupation selectFatherOneByPid(Long pId) {
		System.out.println(pId);
		Occupation selectFatherOneByPid = occupationDAO.selectFatherOneByPid(pId);
		System.out.println(selectFatherOneByPid.getpId());
		Occupation selectFatherOneByPid2 = occupationDAO.selectFatherOneByPid(selectFatherOneByPid.getpId());
		selectFatherOneByPid.setOc(selectFatherOneByPid2);
		return selectFatherOneByPid;
	}
//
//	public List<Occupation> queryPage(BaseQueryPageDTO dto) {
//		return null;
//	}
}