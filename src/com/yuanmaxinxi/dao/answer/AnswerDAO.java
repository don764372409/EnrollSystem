package com.yuanmaxinxi.dao.answer;

import java.util.List;

import com.yuanmaxinxi.dao.BaseDAO;
import com.yuanmaxinxi.entity.test.MBTIAnswer;

public interface AnswerDAO extends BaseDAO<MBTIAnswer>{

	List<MBTIAnswer> selectAllByMbtiId(Long mbtiId);
}
