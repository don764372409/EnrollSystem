package com.yuanmaxinxi.smallsoft.member;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yuanmaxinxi.dto.UniNumberDTO;
import com.yuanmaxinxi.dto.enroll.EnrollQueryPageDTO;
import com.yuanmaxinxi.entity.enroll.Enroll;
import com.yuanmaxinxi.entity.major.Major;
import com.yuanmaxinxi.entity.province.Province;
import com.yuanmaxinxi.entity.provincescore.Provincescore;
import com.yuanmaxinxi.entity.university.University;
import com.yuanmaxinxi.entity.volunteer.Volunteer;
import com.yuanmaxinxi.entity.volunteer.VolunteerMajor;
import com.yuanmaxinxi.entity.volunteer.VolunteerUni;
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
	public List<University> queryUniversity(String name) {
		return enrollService.queryUniversity(name);
	}

	@RequestMapping("/enrollqueryMajorByuId")
	@ResponseBody
	public List<Major> queryMajorByuId(Long uId) {
		return enrollService.queryMajorByuId(uId);
	}
	/**
	 * 根据名次和专业查询学校专业
	 * @param rank
	 * @param mId
	 * @return
	 */

	@RequestMapping("/queryUniANDMajorByRankANDMajor")
	@ResponseBody
	public Map<University, List<Major>> queryUniANDMajorByRankANDMajor(EnrollQueryPageDTO page){
		return enrollService.queryUniANDMajorByRankANDMajor(page);
	}
	/**
	 * 查询录取概率
	 * 
	 * @return
	 */
	@RequestMapping("/enrollPercent")
	@ResponseBody
	public Volunteer enrollPercent(@RequestBody Volunteer v) {
//		Map<String, String[]> map = req.getParameterMap();
//		System.out.println(map);
//		String vStr = req.getParameter("v");
//		System.out.println(vStr);
//		Volunteer v = JSON.parseObject(vStr, Volunteer.class);
		EnrollQueryPageDTO ed = new EnrollQueryPageDTO();
		Provincescore ps = new Provincescore();

		// 设置省份和批次
		ed.setmId(v.getProId());
		ps.setpId(v.getProId());
		Long bId = enrollService.selectOneByName(v.getBatch()).getId();
		ed.setbId(bId);
		ps.setbId(bId);

		for (VolunteerUni vn : v.getUnis()) {
			if (vn.getId()==0) {
				continue;
			}
			University university = universityService.selectOneById(vn.getId());
			vn.setAddress(university.getPro().getName());
			vn.setType(university.getType());
			ed.setuId(vn.getId());
			for (VolunteerMajor vm : vn.getMajors()) {
				if (vm.getId()==0) {
					continue;
				}
				ed.setmId(vm.getId());
				List<Enroll> es = enrollService.queryPage(ed);
				if (es.size()==0) {
					vm.setPercent(999);
					continue;
				}
				int d = 0, dl = 0, da = 0, dk = 0, len = es.size();
				//根据往年录取数据计算概率
				for (Enroll e : es) {
					dl += e.getMinNumber();
					da += e.getAvgNumber();
					ps.setTime(e.getTime());
					dk += enrollService.queryProvinceScore(ps);
				}
				
				d = v.getNumber() - 500;
				if (d<0) {
					vm.setPercent(0);
					continue;
				}
				dl = (dl - dk) / len;
				da = (da - dk) / len;
				if (d == dl) {
					vm.setPercent(50);
				}else if (d >= da) {
					vm.setPercent(100);
				} else {
					//解决平均分和最低分相等时的BUG
					int result = 1;
					if (da!=dl) {
						result = da-dl;
					}
					vm.setPercent((result * 100 / (d - dl))/2 + 50);// 100/-490/2+50
				}
			}
		}
		return v;
	}
}
