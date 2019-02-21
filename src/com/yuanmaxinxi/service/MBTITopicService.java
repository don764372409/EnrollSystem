package com.yuanmaxinxi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yuanmaxinxi.dao.answer.AnswerDAO;
import com.yuanmaxinxi.dao.topic.MBTITopicDAO;
import com.yuanmaxinxi.entity.test.MBTIAnswer;
import com.yuanmaxinxi.entity.test.MBTITopic;

@Service
@Transactional
public class MBTITopicService {
	@Autowired
	private MBTITopicDAO mbtiTopicDAO;
	@Autowired
	private AnswerDAO answerDAO;
	public List<MBTITopic> selectAllMBTITopic() {
		List<MBTITopic> list = mbtiTopicDAO.selectAll();
		for (MBTITopic mt : list) {
			//获取答案
			List<MBTIAnswer> answers = answerDAO.selectAllByMbtiId(mt.getId());
			mt.setAnswers(answers);
		}
		return list;
	}

}
