package com.yuanmaxinxi.dao.testresult;

import com.yuanmaxinxi.dao.BaseDAO;
import com.yuanmaxinxi.entity.test.TestResult;

public interface TestResultDAO extends BaseDAO<TestResult>{
	TestResult selectOneByResult(String result);
}
