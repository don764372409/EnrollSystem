package com.yuanmaxinxi.dao.topic;

import java.util.List;
import java.util.Map;

import com.yuanmaxinxi.dao.BaseDAO;
import com.yuanmaxinxi.entity.test.Topic;

public interface TopicDAO extends BaseDAO<Topic>{

	List<Topic> selectAll(Map<String, Integer> map);
}
