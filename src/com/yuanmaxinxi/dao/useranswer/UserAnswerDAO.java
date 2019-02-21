package com.yuanmaxinxi.dao.useranswer;

import java.util.List;

import com.yuanmaxinxi.dao.BaseDAO;
import com.yuanmaxinxi.entity.test.UserAnswer;
import com.yuanmaxinxi.entity.test.UserAnswerItem;

public interface UserAnswerDAO extends BaseDAO<UserAnswer>{
	/**
	 * 获取未完成的测试
	 * @param ua
	 * @return
	 */
	UserAnswer selectOneAir(UserAnswer ua);
	int insertUserAnswerItem(UserAnswerItem uai);
	UserAnswerItem selectOneUserAnswerItemByObj(UserAnswerItem uai);
	int updateUserAnswerItem(UserAnswerItem uai);
	int getMaXItem(Long uaId);
	List<UserAnswerItem> selectAllUserAnswerItemByUaId(Long uaId);
	int updateUserAnswerResult(UserAnswer ua);
}
