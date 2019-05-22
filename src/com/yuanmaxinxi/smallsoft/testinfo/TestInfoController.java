package com.yuanmaxinxi.smallsoft.testinfo;

import java.io.FileInputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections4.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yuanmaxinxi.dto.ResultDTO;
import com.yuanmaxinxi.entity.test.TestInfo;
import com.yuanmaxinxi.entity.test.TestResult;
import com.yuanmaxinxi.entity.test.Topic;
import com.yuanmaxinxi.entity.test.UserAnswer;
import com.yuanmaxinxi.entity.test.UserAnswerItem;
import com.yuanmaxinxi.service.TestInfoService;
import com.yuanmaxinxi.service.TestResultService;
import com.yuanmaxinxi.service.TopicService;
import com.yuanmaxinxi.service.UserAnswerService;
import com.yuanmaxinxi.service.UserService;

@Controller
@RequestMapping("/testInfo")
public class TestInfoController {
	@Autowired
	private TestInfoService testInfoService;
	@Autowired
	private TopicService topicService;
	@Autowired
	private UserService userService;
	@Autowired
	private UserAnswerService userAnswerService;
	@Autowired
	private TestResultService testResultService;
	@RequestMapping("/selectOneByType")
	@ResponseBody
	public TestInfo selectOneByType(int type) {
		TestInfo obj = testInfoService.selectOneByType(type);
		return obj;
	}
	/**
	 * 查询最新的一次是否完成，只查最新的
	 * @param type
	 * @return
	 */
	@RequestMapping("/checkTest")
	@ResponseBody
	public Map<String,Integer> checkTest(Long uId) {
		UserAnswer ua = new UserAnswer();
		ua.setuId(uId);
		ua.setType(1);
		//先检查MBTI
		int test1 = userAnswerService.checkTest(ua);
		ua.setType(2);
		//在检查霍兰德
		int test2 = userAnswerService.checkTest(ua);
		Map<String,Integer> map = new HashedMap<>();
		map.put("test1", test1);
		map.put("test2", test2);
		return map;
	}

	@RequestMapping("/selectAllTopic")
	@ResponseBody
	public List<Topic> selectAllTopic(int type) {
		List<Topic> list = topicService.selectAllTopic(type);
		return list;
	}

	/**
	 * 解析测试答案
	 * 
	 * @param ua
	 * @return
	 */
	@RequestMapping("/selectResult")
	@ResponseBody
	public TestResult selectResult(String result) {
		// 解析得到结果
		TestResult tr = testResultService.selectOneByResult(result);
		return tr;
	}
	/**
	 * 解析测试答案
	 * 
	 * @param ua
	 * @return
	 */
	@RequestMapping(value="/downloadTestFile")
	@ResponseBody
	public void downloadTestFile(Long uId,HttpServletRequest request, HttpServletResponse response) {
		//ResultDTO dto;
		try {
			// 解析得到结果
			UserAnswer usa = new UserAnswer();
			usa.setuId(uId);
			usa.setType(1);
			UserAnswer ua = userAnswerService.selectNewUserAnswer(usa);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String uName = userService.selectOneId(ua.getuId()).getName();
			String rpath = this.getClass().getClassLoader().getResource("../../MBTI/").getPath();
			String[] cmds = { "/bin/sh", "-c", "/usr/bin/sed -i '0,/<h3>/s/\\(<h3>\\).*\\(<\\/h3>\\)/\\1" + uName + " "
					+ sdf.format(new Date()) + "\\2/' " + rpath + ua.getResult() + ".html" };
			if (Runtime.getRuntime().exec(cmds).waitFor() != 0) {
				throw new RuntimeException();
			}
			// 解析得到结果
			usa.setType(2);
			//如果没有出结果 可能会报空指针
			String result = userAnswerService.selectNewUserAnswer(usa).getResult();
			String[] s;
			for(String str: result.split(",")) {
				s=str.split(":");
				cmds[2]="/usr/bin/sed -i -e '0,/<td>"+s[0]+"/s/\\(<td>"+s[0]+"[^[:digit:]]*\\)[[:digit:]]\\{1,2\\}/\\1" + s[1] + "/' -e "
				+ "'/<td>"+s[0]+"/{n;n;s/[[:digit:]]\\{1,2\\}%/"+s[1]+"%/;}' "+rpath + ua.getResult() + ".html" ;
				System.err.println(cmds[2]);
				if (Runtime.getRuntime().exec(cmds).waitFor() != 0) {
					throw new RuntimeException();
				}
			}
			
			System.err.println("/usr/bin/wkhtmltopdf " + rpath + ua.getResult() + ".html " + rpath + "tmp.pdf");
			if (Runtime.getRuntime()
					.exec("/usr/bin/wkhtmltopdf " + rpath + ua.getResult() + ".html " + rpath + "tmp.pdf")
					.waitFor() != 0) {
				throw new RuntimeException();
			}
			response.setContentType("application/pdf");
			response.setHeader("content-disposition", "attachment;filename=" + ua.getResult() + ".pdf");
			FileInputStream in = new FileInputStream(rpath+"tmp.pdf");
			OutputStream out = response.getOutputStream();
			byte buffer[] = new byte[1024];
			int len = 0;
			while ((len = in.read(buffer)) > 0) {
				out.write(buffer, 0, len);
			}
				out.flush();
			in.close();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 解析测试答案
	 * 
	 * @param ua
	 * @return
	 */
	@RequestMapping(value="/parseResult")
	@ResponseBody
	public ResultDTO parseResult(Long uaId,HttpServletRequest request, HttpServletResponse response) {
		ResultDTO dto;
		try {
			// 解析得到结果
			userAnswerService.parseResult(uaId);
			// 根据结果去获取解析详情
			dto = ResultDTO.newInstance(true, "");
		} catch (Exception e) {
			e.printStackTrace();
			dto = ResultDTO.newInstance(false, e.getMessage());
		}
		return dto;
	}

	/**
	 * 解析测试答案
	 * 
	 * @param ua
	 * @return
	 */
	@RequestMapping("/parseHLDResult")
	@ResponseBody
	public ResultDTO parseHLDResult(Long uaId) {
		ResultDTO dto;
		try {
			// 解析得到结果
			String result = userAnswerService.parseHLDResult(uaId);
			// 根据结果去获取解析详情
			dto = ResultDTO.newInstance(true, result);
		} catch (Exception e) {
			e.printStackTrace();
			dto = ResultDTO.newInstance(false, e.getMessage());
		}
		return dto;
	}

	/**
	 * 获取答题数目 以索引形式
	 * 
	 * @param ua
	 * @return
	 */
	@RequestMapping("/getCountItem")
	@ResponseBody
	public int getCountItem(Long uaId) {
		return userAnswerService.getCountItem(uaId);
	}

	/**
	 * 获取已经存在的
	 * 
	 * @param ua
	 * @return
	 */
	@RequestMapping("/selectOneAir")
	@ResponseBody
	public UserAnswer selectOneAir(UserAnswer ua) {
		return userAnswerService.selectOneAir(ua);
	}

	@RequestMapping("/getUserAnswer")
	@ResponseBody
	public UserAnswer getUserAnswer(UserAnswer ua) {
		return userAnswerService.getUserAnswer(ua);
	}

	/**
	 * 记录答案
	 * 
	 * @param ua
	 * @param uai
	 * @return
	 */
	@RequestMapping("/userAnswer")
	@ResponseBody
	public ResultDTO userAnswer(UserAnswerItem uai) {
		ResultDTO dto;
		try {
			userAnswerService.userAnswer(uai);
			dto = ResultDTO.newInstance(true, "记录成功!");
		} catch (Exception e) {
			e.printStackTrace();
			dto = ResultDTO.newInstance(false, e.getMessage());
		}
		return dto;
	}
}
