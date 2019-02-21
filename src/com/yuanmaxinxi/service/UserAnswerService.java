package com.yuanmaxinxi.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yuanmaxinxi.dao.answer.AnswerDAO;
import com.yuanmaxinxi.dao.useranswer.UserAnswerDAO;
import com.yuanmaxinxi.entity.test.MBTIAnswer;
import com.yuanmaxinxi.entity.test.UserAnswer;
import com.yuanmaxinxi.entity.test.UserAnswerItem;

@Service
@Transactional
public class UserAnswerService {
	@Autowired
	private UserAnswerDAO userAnswerDAO;
	@Autowired
	private AnswerDAO answerDAO;
	public void insert(UserAnswer ua) {
		try {
			int i = userAnswerDAO.insert(ua);
			if (i!=1) {
				throw new RuntimeException("");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("添加测试记录失败.");
		}
	}
	
	public UserAnswer selectOneAir(UserAnswer ua){
		return userAnswerDAO.selectOneAir(ua);
	}
	
	/**
	 * 记录答题答案
	 * @param ua
	 * @param uai
	 */
	public void userAnswer(UserAnswerItem uai) {
		//先查询答案中的type
		MBTIAnswer answer = answerDAO.selectOneById(uai.getAnswerId());
		if (answer==null) {
			throw new RuntimeException("添加答案记录失败.");
		}
		//再记录答案
		uai.setType(answer.getType());
		try {
			//先查是否存在这个题的答案  如果已存在就修改  不存在才是添加
			UserAnswerItem sysUai = userAnswerDAO.selectOneUserAnswerItemByObj(uai);
			if (sysUai!=null) {
				uai.setId(sysUai.getId());
				int i = userAnswerDAO.updateUserAnswerItem(uai);
				if (i!=1) {
					throw new RuntimeException("添加答案记录失败.");
				}
			}else {
				int i = userAnswerDAO.insertUserAnswerItem(uai);
				if (i!=1) {
					throw new RuntimeException("添加答案记录失败.");
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("添加答案记录失败.");
		}
	}
	/**
	 * 获取一个测试记录
	 * 如果有未完成的就拿未完成的 如果没有 就创建一个返回 
	 * @param ua
	 * @return
	 */
	public UserAnswer getUserAnswer(UserAnswer ua) {
		//先找是否有为完成的
		UserAnswer sysUa = selectOneAir(ua);
		if (sysUa==null) {
			sysUa = ua;
			sysUa.setTime(new Date());
			insert(sysUa);
		}
		return sysUa;
	}

	public int getMaXItem(Long uaId) {
		return userAnswerDAO.getMaXItem(uaId);
	}
	/**
	 * 解析测试答案
	 * @param uaId
	 */
	public String parseResult(Long uaId) {
		try {
			List<UserAnswerItem> items = userAnswerDAO.selectAllUserAnswerItemByUaId(uaId);
			int E = 0;
			int I = 0;
			int S = 0;
			int N = 0;
			int T = 0;
			int F = 0;
			int J = 0;
			int P = 0;
			for (UserAnswerItem uai : items) {
				switch (uai.getType()) {
				case "E":
					E++;
					break;
				case "I":
					I++;
					break;
				case "S":
					S++;
					break;
				case "N":
					N++;
					break;
				case "T":
					T++;
					break;
				case "F":
					F++;
					break;
				case "J":
					J++;
					break;
				case "P":
					P++;
					break;
				default:
					break;
				}
			}
			String result = ""; 
			if (I>=E) {
				result+="I";
			}else {
				result+="E";
			}
			
			if (N>=S) {
				result+="N";
			}else {
				result+="S";
			}
		
			if (F>=T) {
				result+="F";
			}else {
				result+="T";
			}
			
			if (P>=J) {
				result+="P";
			}else {
				result+="J";
			}
			//修改当前测试的结果
			UserAnswer ua = new UserAnswer();
			ua.setId(uaId);
			ua.setResult(result);
			int i = userAnswerDAO.updateUserAnswerResult(ua);
			if (i!=1) {
				throw new RuntimeException("解析失败,请稍后重试.");
			}
			return result;
		} catch (Exception e2) {
			e2.printStackTrace();
			throw new RuntimeException("解析失败,请稍后重试.");
		}
	}
}
