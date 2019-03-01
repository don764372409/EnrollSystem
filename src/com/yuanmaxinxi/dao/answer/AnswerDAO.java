package com.yuanmaxinxi.dao.answer;

import java.util.List;

import com.yuanmaxinxi.dao.BaseDAO;
import com.yuanmaxinxi.entity.test.Answer;

public interface AnswerDAO extends BaseDAO<Answer>{

	List<Answer> selectAllBytopicId(Long topicId);
}
