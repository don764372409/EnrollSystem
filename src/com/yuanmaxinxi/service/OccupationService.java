<<<<<<< HEAD
package com.yuanmaxinxi.service;

import java.util.List;

import com.yuanmaxinxi.dao.occupation.OccupationDAO;
import com.yuanmaxinxi.dto.BaseQueryPageDTO;
import com.yuanmaxinxi.entity.admin.Admin;
import com.yuanmaxinxi.entity.occupation.Occupation;
import com.yuanmaxinxi.util.StringUtil;

public class OccupationService {
	private OccupationDAO ocpDAO = new OccupationDAO();
	public void insert(Occupation obj) {
		if (StringUtil.isNullOrEmpty(obj.getName())) {
			throw new RuntimeException("专业名不能为空.");
		}
		if (StringUtil.isNullOrEmpty(obj.getRemark())) {
			throw new RuntimeException("专业简介不能为空.");
		}
		if (StringUtil.isNullOrEmpty(obj.getWorkContent())) {
			throw new RuntimeException("工作内容不能为空.");
		}
		int i = ocpDAO.insert(obj);
	}

	public void update(Occupation obj) {
	}

	public void delete(Long id) {
	}

	public Occupation selectOneById(Long id) {
		return null;
	}

	public List<Occupation> selectAll() {
		return ocpDAO.selectAll();
	}

	public List<Occupation> queryPage(BaseQueryPageDTO dto) {
		return null;
	}
}
=======
package com.yuanmaxinxi.service;

import java.util.List;

import com.yuanmaxinxi.dao.occupation.OccupationDAO;
import com.yuanmaxinxi.dto.BaseQueryPageDTO;
import com.yuanmaxinxi.entity.admin.Admin;
import com.yuanmaxinxi.entity.occupation.Occupation;

public class OccupationService {
	private OccupationDAO ocpDAO = new OccupationDAO();
	public void insert(Admin obj) {
	}

	public void update(Admin obj) {
	}

	public void delete(Long id) {
	}

	public Admin selectOneById(Long id) {
		return null;
	}

	public List<Occupation> selectAll() {
		return ocpDAO.selectAll();
	}

	public List<Admin> queryPage(BaseQueryPageDTO dto) {
		return null;
	}
}
>>>>>>> branch 'master' of https://github.com/don764372409/EnrollSystem.git
