package com.yuanmaxinxi.smallsoft.member;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yuanmaxinxi.dto.UniAndMajorDTO;
import com.yuanmaxinxi.dto.UniNumberDTO;
import com.yuanmaxinxi.dto.enroll.EnrollQueryPageDTO;
import com.yuanmaxinxi.entity.enroll.Enroll;
import com.yuanmaxinxi.entity.enroll.ScoreEnroll;
import com.yuanmaxinxi.entity.enroll.UniversityEnroll;
import com.yuanmaxinxi.entity.major.Major;
import com.yuanmaxinxi.entity.province.Province;
import com.yuanmaxinxi.entity.university.University;
import com.yuanmaxinxi.service.EnrollService;
import com.yuanmaxinxi.service.UniversityService;

/**
 * 会员功能
 * 
 * @author 微笑ぃ一刀
 *
 */
@Controller
@RequestMapping("/soft/member")
@CrossOrigin
public class MemberController {
	@Autowired
	private UniversityService universityService;
	@Autowired
	private EnrollService enrollService;

	@RequestMapping("/xx")
	public @ResponseBody Object xx(HttpSession session, HttpServletResponse response) {
		enrollService.xxxx();
		return "login";
	}

	/**
	 * 获取招生省份 提供给用户做查询
	 * 
	 * @return
	 */
	@RequestMapping("/selectEnrollProvince")
	@ResponseBody
	public List<Province> selectEnrollProvince() {
		return enrollService.selectEnrollProvince();
	}

	/**
	 * 录取学校和专业
	 * 
	 * @return
	 */
	@RequestMapping("/serch2")
	@ResponseBody
	public List<UniNumberDTO> serch2(Long pId, String bath, String type, int ranking, int number, int range) {
		Map<String, Object> map = new HashMap<>();
		map.put("pId", pId);
		map.put("bath", bath);
		map.put("type", type);
		map.put("ranking", ranking);
		map.put("number", number);
		map.put("range", range);
		List<UniNumberDTO> list = enrollService.serch2(map);
//		for (UniNumberDTO uniNumberDTO : list) {
//			System.err.println(uniNumberDTO.getName());
//			for (UniAndMajorDTO uam : uniNumberDTO.getUams()) {
//				System.err.println("\t"+uam.getName());
//			}
//		}
		return list;
	}

	/**
	 * 历年录取匹配查询
	 * 
	 * @return
	 */
	@RequestMapping("/serch")
	@ResponseBody
	public Map<String, Map<String, List<Enroll>>> serch(Long pId, String bath, String type, int ranking, int number,
			int range) {
		Map<String, Object> map = new HashMap<>();
		map.put("pId", pId);
		map.put("bath", bath);
		map.put("type", type);
		map.put("ranking", ranking);
		map.put("number", number);
		map.put("range", range);
		return enrollService.serch(map);
	}

	/**
	 * 历年录取匹配查询
	 * 
	 * @return
	 */
	@RequestMapping("/enrollBigUni")
	@ResponseBody
	public List<Enroll> enrollBigUni(Long pId, String bath, String type, int ranking, int number, int range) {
		Map<String, Object> map = new HashMap<>();
		map.put("pId", pId);
		map.put("bath", bath);
		map.put("type", type);
		map.put("ranking", ranking);
		map.put("number", number);
		map.put("range", range);
		return enrollService.enrollBigUni(map);
	}
	
	@RequestMapping("/enrollqueryUniversity")
	@ResponseBody
	public List<University> queryUniversity(String name){
		return enrollService.queryUniversity(name);
	}
	@RequestMapping("/enrollqueryMajorByuId")
	@ResponseBody
	public List<Major> queryMajorByuId(Long uId){
		return enrollService.queryMajorByuId(uId);
	}

	
	/**
	 * 查询录取概率
	 * 
	 * @return
	 */
	@RequestMapping("/enrollPercent")
	@ResponseBody
	public List<UniversityEnroll> enrollPercent(List<UniversityEnroll> ues, int socre) {
		EnrollQueryPageDTO ed = new EnrollQueryPageDTO();
		int pScore = 600;
		for (UniversityEnroll us : ues) {
			ed.setuId(us.getuId());
			ed.setmId(us.getmId());
			ed.setbId(us.getbId());
			for (ScoreEnroll se : us.getSes()) {
				ed.setmId(se.getmId());
				List<Enroll> es = enrollService.queryPage(ed);
				int d=0,dl = 0, da = 0, dk = 0, len = es.size();
				for (Enroll e : es) {
					dl += e.getMinNumber();
					da += e.getAvgNumber();
					dk += pScore;
				}
				d = socre-pScore;
				dl = (dl - dk) / len;
				da = (da - dk) / len;
				if(d==dl) {
					se.setPercent(50);
				}else if(d>=da){
					se.setPercent(100);
				}else {
					se.setPercent((da-dl)*100/(d-dl) >> 1+ 50);
				}
				
			}
		}
		return ues;
	}
}
