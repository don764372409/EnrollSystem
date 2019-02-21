package com.yuanmaxinxi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yuanmaxinxi.dao.testresult.TestResultDAO;
import com.yuanmaxinxi.entity.test.TestResult;

@Service
public class TestResultService {
	@Autowired
	private TestResultDAO testResultDAO;
	public TestResult selectOneByResult(String result) {
		return testResultDAO.selectOneByResult(result);
	}
}
