package com.yuanmaxinxi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yuanmaxinxi.dao.testinfo.TestInfoDAO;
import com.yuanmaxinxi.entity.test.TestInfo;

@Service
public class TestInfoService {
	@Autowired
	private TestInfoDAO testInfoDAO;
	public TestInfo selectOneByType(int type) {
		return testInfoDAO.selectOneByType(type);
	}
	
	public List<TestInfo> selectAll(){
		return testInfoDAO.selectAll();
	}
	
	public TestInfo selectOneById(int id) {
		return testInfoDAO.selectOneById(id);
	}
}
