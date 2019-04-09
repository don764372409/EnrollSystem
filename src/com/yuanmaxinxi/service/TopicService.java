package com.yuanmaxinxi.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yuanmaxinxi.dao.answer.AnswerDAO;
import com.yuanmaxinxi.dao.topic.TopicDAO;
import com.yuanmaxinxi.entity.test.Answer;
import com.yuanmaxinxi.entity.test.Topic;

@Service
@Transactional
public class TopicService {
	@Autowired
	private TopicDAO topicDAO;
	@Autowired
	private AnswerDAO answerDAO;
	/**
	 * 获取对应类型的题
	 * @param type 这个type不是topic模型中的属性  而是一个简单的标识  1代表查询mbti的题 2-代表查霍兰德的题 而具体查询是通过id大小来
	 * @return
	 */
	public List<Topic> selectAllTopic(int type) {
		Map<String,Integer> map = new HashMap<>();
		map.put("type", type);
		List<Topic> list = topicDAO.selectAll(map);
		for (Topic topic : list) {
			//获取答案
			List<Answer> answers = answerDAO.selectAllBytopicId(topic.getId());
			topic.setAnswers(answers);
		}
		return list;
	}

}
