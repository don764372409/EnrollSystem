<<<<<<< HEAD

=======
>>>>>>> branch 'master' of https://github.com/don764372409/EnrollSystem.git
package com.yuanmaxinxi.service;

<<<<<<< HEAD
import java.util.List;

import com.yuanmaxinxi.dao.occupation.OccupationDAO;
import com.yuanmaxinxi.dto.BaseQueryPageDTO;
import com.yuanmaxinxi.entity.admin.Admin;
=======
import java.util.List;

import com.yuanmaxinxi.dao.occupation.OccupationDAO;
import com.yuanmaxinxi.dto.BaseQueryPageDTO;
import com.yuanmaxinxi.entity.admin.Admin;
>>>>>>> branch 'master' of https://github.com/don764372409/EnrollSystem.git
import com.yuanmaxinxi.entity.occupation.Occupation;
import com.yuanmaxinxi.util.StringUtil;

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
<<<<<<< HEAD

=======
>>>>>>> branch 'master' of https://github.com/don764372409/EnrollSystem.git
