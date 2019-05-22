package com.yuanmaxinxi.service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yuanmaxinxi.dao.answer.AnswerDAO;
import com.yuanmaxinxi.dao.useranswer.UserAnswerDAO;
import com.yuanmaxinxi.entity.test.Answer;
import com.yuanmaxinxi.entity.test.UserAnswer;
import com.yuanmaxinxi.entity.test.UserAnswerItem;
import com.yuanmaxinxi.util.StringUtil;

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
		Answer answer = answerDAO.selectOneById(uai.getAnswerId());
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
	/**
	 * 获取已测试的题目数量  以索引形式
	 * @param uaId
	 * @return
	 */
	public int getCountItem(Long uaId) {
		Integer max = userAnswerDAO.getCountItem(uaId);
		if (max==null) {
			return 0;
		}
		if (max>0) {
			return max-1;
		}
		return max;
	}
	/**
	 * 解析测试答案
	 * @param uaId
	 */
	public UserAnswer parseResult(Long uaId) {
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
			return userAnswerDAO.selectOneById(uaId);
		} catch (Exception e2) {
			e2.printStackTrace();
			throw new RuntimeException("解析失败,请稍后重试.");
		}
	}
	/**
	 * 解析测试答案
	 * @param uaId
	 */
	public String parseHLDResult(Long uaId) {
		try {
			List<UserAnswerItem> items = userAnswerDAO.selectAllUserAnswerItemByUaId(uaId);
			int S = 0;
			int E = 0;
			int C = 0;
			int R = 0;
			int I = 0;
			int A = 0;
			for (UserAnswerItem uai : items) {
				switch (uai.getType()) {
				case "S":
					S++;
					break;
				case "E":
					E++;
					break;
				case "C":
					C++;
					break;
				case "R":
					R++;
					break;
				case "I":
					I++;
					break;
				case "A":
					A++;
					break;
				default:
					break;
				}
			}
			int[] numbers ={S,E,C,R,I,A};
			Arrays.sort(numbers);
			String result = "";
			int s0 = 0;
			int e0 = 0;
			int c0 = 0;
			int r0 = 0;
			int i0 = 0;
			int a0 = 0;
			for (int j = 0; j < numbers.length; j++) {
				int number = numbers[j];
				if (number==S&&s0==0) {
					result = "S:"+S+","+result;
					s0++;
				}else if (number==E&&e0==0) {
					result = "E:"+E+","+result;
					e0++;
				}else if (number==C&&c0==0) {
					result = "C:"+C+","+result;
					c0++;
				}else if (number==R&&r0==0) {
					result = "R:"+R+","+result;
					r0++;
				}else if (number==I&&i0==0) {
					result = "I:"+I+","+result;
					i0++;
				}else if (number==A&&a0==0) {
					result = "A:"+A+","+result;
					a0++;
				}
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
	/**
	 * 检查最新的测试是否有结果
	 * @param type
	 * @return
	 */
	public int checkTest(UserAnswer ua) {
		UserAnswer sysUa = userAnswerDAO.selectNewUserAnswer(ua);
		if (sysUa!=null&&StringUtil.isNotNullAndEmpty(sysUa.getResult())) {
			return 1;
		}
		return 0;
	}

	public UserAnswer selectNewUserAnswer(UserAnswer usa) {
		return userAnswerDAO.selectNewUserAnswer(usa);
	}
}
