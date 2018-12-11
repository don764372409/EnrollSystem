package com.yuanmaxinxi.service;

import java.util.List;

import com.yuanmaxinxi.dao.major.MajorDAO;
import com.yuanmaxinxi.dto.BaseQueryPageDTO;
import com.yuanmaxinxi.entity.admin.Admin;
import com.yuanmaxinxi.entity.major.Major;
import com.yuanmaxinxi.util.StringUtil;

public class MajorService {
	private MajorDAO majorDAO = new MajorDAO();
	public void insert(Major obj) {
		if (StringUtil.isNullOrEmpty(obj.getName())) {
			throw new RuntimeException("专业名不能为空.");
		}
		if (StringUtil.isNullOrEmpty(obj.getRemark())) {
			throw new RuntimeException("专业简介不能为空.");
		}
		if (StringUtil.isNullOrEmpty(obj.getExplain())) {
			throw new RuntimeException("专业解读不能为空.");
		}
		if (StringUtil.isNullOrEmpty(obj.getRanking())) {
			throw new RuntimeException("专业排名不能为空.");
		}
	}

	public void update(Major obj) {
	}

	public void delete(Long id) {
	}

	public Major selectOneById(Long id) {
		return null;
	}

	public List<Major> selectAll() {
		return majorDAO.selectAll();
	}

	public List<Major> queryPage(BaseQueryPageDTO dto) {
		return null;
	}
}
